package pl.polsl.bonus.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.polsl.bonus.dto.BonusListDTO;
import pl.polsl.bonus.dto.NodeDTO;
import pl.polsl.bonus.mapper.MapperImpl;
import pl.polsl.bonus.model.BonusList;
import pl.polsl.bonus.model.Employee;
import pl.polsl.bonus.model.Node;
import pl.polsl.bonus.model.Team;
import pl.polsl.bonus.repository.BonusListJpaRepository;
import pl.polsl.bonus.repository.EmployeeJpaRepository;
import pl.polsl.bonus.repository.NodeJpaRepository;
import pl.polsl.bonus.repository.TeamJpaRepository;

@CrossOrigin
@RestController
@RequestMapping(value = "/bonuses", produces = MediaType.APPLICATION_JSON_VALUE)
public class BonusListController {

	@Autowired
	private MapperImpl mapper;

	@Autowired
	private BonusListJpaRepository bonusListRepository;

	@Autowired
	private EmployeeJpaRepository employeeRepository;

	@Autowired
	private TeamJpaRepository teamRepository;

	@Autowired
	private NodeJpaRepository nodeRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<BonusListDTO> bonusListList() {
		return this.mapper.toBonusesListDTO(bonusListRepository.findAll(), true);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody BonusListDTO bonusList) {
		try {
			// znajdywanie teamu prezesa
			Team topTeam = teamRepository.findTopLevelTeam();
			System.out.println(topTeam.getTeamName() + " " + topTeam.getTeamId());
			// znajdywanie prezesa
			List<Employee> employeeList = employeeRepository.findEmployeesByTeam(topTeam);
			for (Employee e : employeeList) {
				System.out.println(e.getFirstName());
			}
			// Node prezesa
			Node topNode = new Node();
			topNode.setParentNode(null);
			topNode.setEmployee(employeeList.get(0));
			System.out.println(topNode.getEmployee().getEmployeeId());
			BonusList newBonusList = new BonusList();
			newBonusList.setBeginningDate(bonusList.getBeginningDate());
			newBonusList.setEndDate(bonusList.getEndDate());
			newBonusList.setNode(topNode);
			newBonusList.setState("Initialized");
			newBonusList.setBudget(bonusList.getBudget());
			newBonusList.setDescription(bonusList.getDescription());
			topNode.setLevel(0);
			topNode.setOwnBonus(0.0);
			topNode.setTeamBonus(bonusList.getBudget());
			topNode.setState("");
			// dodanie Node'a prezeza
			nodeRepository.saveAndFlush(topNode);

			// dodawanie nodow podrzednych
			addChildren(topNode);

			bonusListRepository.saveAndFlush(newBonusList);

			return new ResponseEntity<>(newBonusList, HttpStatus.OK);
			// return new ResponseEntity<>(bonusList, HttpStatus.OK);
		} catch (Exception e) {
			String errorMessage;
			errorMessage = "Failed to add bonus";
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public BonusListDTO get(@PathVariable Integer id) {
		return this.mapper.toBonusListDTO(bonusListRepository.findOne(id), true);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public BonusList update(@PathVariable Integer id, @RequestBody BonusListDTO bonusListDTO) {
		BonusList existingbonusList = bonusListRepository.findOne(id);
		BonusList bonusList = this.mapper.fromBonusListDTO(bonusListDTO, true);
		BeanUtils.copyProperties(bonusList, existingbonusList);
		return bonusListRepository.saveAndFlush(existingbonusList);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public BonusList delete(@PathVariable Integer id) {
		BonusList existingbonusList = bonusListRepository.findOne(id);
		bonusListRepository.delete(existingbonusList);
		return existingbonusList;

	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> updateNodes(@RequestBody List<NodeDTO> nodes) {
		try {
			List<Node> nodeList = new ArrayList<Node>();
			nodeList = this.mapper.fromNodeListDTO(nodes, true);
			for (Node n : nodeList) {
				Node existingNode = nodeRepository.findOne(n.getNodeId());
				BeanUtils.copyProperties(n, existingNode);
				nodeRepository.saveAndFlush(existingNode);
			}
			return new ResponseEntity<>("", HttpStatus.OK);

		} catch (Exception e) {
			String errorMessage;
			errorMessage = "Failed to add bonus";
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@RequestMapping(value = "/{nodeId}/{employeeId}", method = RequestMethod.GET)
	public List<NodeDTO> getNodesByEmployee(@PathVariable Integer nodeId, @PathVariable Integer employeeId) {

		Employee currentEmployee = employeeRepository.findOne(employeeId);
		Node topNode = nodeRepository.findOne(nodeId);
		if (topNode.getEmployee() == currentEmployee) {
			return this.mapper.toNodeListDTO(nodeRepository.findNodesByParentNode(topNode), true);
		} else {
			Node currentNode = nodeRepository.findNodeByEmployeeIdAndParentNode(topNode, currentEmployee);
			return this.mapper.toNodeListDTO(getChildren(currentNode), true);

		}
	}

	@RequestMapping(value = "currNode/{nodeId}/{employeeId}", method = RequestMethod.GET)
	public NodeDTO getNodeByEmployeeId(@PathVariable Integer nodeId, @PathVariable Integer employeeId) {

		Employee currentEmployee = employeeRepository.findOne(employeeId);
		Node topNode = nodeRepository.findOne(nodeId);
		if (topNode.getEmployee() == currentEmployee) {
			return this.mapper.toNodeDTO(topNode, true);
		} else
			return this.mapper.toNodeDTO(nodeRepository.findNodeByEmployeeIdAndParentNode(topNode, currentEmployee),
					true);

	}

	public void addChildren(Node parentNode) {
		Team team = teamRepository.findTeamByManager(parentNode.getEmployee());
		List<Employee> employees = employeeRepository.findEmployeesByTeam(team);
		for (Employee e : employees) {
			Node newNode = new Node();
			newNode.setParentNode(parentNode);
			newNode.setLevel(parentNode.getLevel() + 1);
			newNode.setEmployee(e);
			newNode.setOwnBonus(0.0);
			newNode.setTeamBonus(0.0);
			newNode.setState("Initialized");
			nodeRepository.saveAndFlush(newNode);
			addChildren(newNode);

		}

	}

	public List<Node> getChildren(Node parentNode) {
		List<Node> nodes = new ArrayList<Node>();
		try {
			Team team = teamRepository.findTeamByManager(parentNode.getEmployee());
			List<Employee> employees = employeeRepository.findEmployeesByTeam(team);
			for (Employee e : employees) {
				System.out.println(e.getFirstName());
				Node node = nodeRepository.findNodeByEmployeeIdAndParentNode(parentNode, e);
				nodes.add(node);
			}
			return nodes;
		} catch (Exception e) {
			return null;
		}
	}

	@RequestMapping(value = "/byBonus/{nodeId}", method = RequestMethod.GET)
	public List<NodeDTO> getAllNodesByBonusList(@PathVariable Integer nodeId) {
		Node parentNode = this.nodeRepository.findOne(nodeId);
		System.out.println(parentNode.getNodeId());
		List<NodeDTO> allNodes = this.mapper.toNodeListDTO(nodeRepository.findNodesByParentNode(parentNode), true);
		List<NodeDTO> allChildrenNodes  = new ArrayList<NodeDTO>();
		for (NodeDTO node : allNodes) {
			node.setParentNode(this.mapper.toNodeDTO(this.nodeRepository.findOne(node.getParentNode().getNodeId()), true));
			allChildrenNodes.addAll(this.getAllNodesByBonusList(this.mapper.fromNodeDTO(node, true).getNodeId()));
		}
		allNodes.addAll(allChildrenNodes);
		return allNodes;
	}
}
