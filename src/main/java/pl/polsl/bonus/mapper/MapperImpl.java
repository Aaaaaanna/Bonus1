package pl.polsl.bonus.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import pl.polsl.bonus.dto.BonusListDTO;
import pl.polsl.bonus.dto.EmployeeDTO;
import pl.polsl.bonus.dto.NodeDTO;
import pl.polsl.bonus.dto.TeamDTO;
import pl.polsl.bonus.model.BonusList;
import pl.polsl.bonus.model.Employee;
import pl.polsl.bonus.model.Node;
import pl.polsl.bonus.model.Team;

@Component
public class MapperImpl implements Mapper {

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	// context - zapobiega zapętlaniu , jeżeli wywołanie zewnętrzne to true,
	// jeżeli nie - false
	public EmployeeDTO toEmployeeDTO(Employee employee, Boolean context) {
		if (employee == null)
			return null;
		else {
			EmployeeDTO employeeDTO = this.modelMapper.map(employee, EmployeeDTO.class);
			if (context)
				employeeDTO.setTeam(toTeamDTO(employee.getTeam(), !context));
			else
				employeeDTO.setTeam(null);
			return employeeDTO;
		}
	}

	@Override
	public TeamDTO toTeamDTO(Team team, Boolean context) {
		if (team == null)
			return null;
		else {
			TeamDTO teamDTO = this.modelMapper.map(team, TeamDTO.class);
			if (context)
				teamDTO.setTeamManager(toEmployeeDTO(team.getTeamManager(), !context));
			else
				teamDTO.setTeamManager(null);
			return teamDTO;
		}
	}

	@Override
	public List<EmployeeDTO> toEmployeeListDTO(List<Employee> employees, Boolean context) {
		if (employees == null)
			return null;
		else {
			List<EmployeeDTO> employeeListDTO = new ArrayList<>();
			employees.forEach(employee -> employeeListDTO.add(this.toEmployeeDTO(employee, context)));
			return employeeListDTO;
		}
	}

	@Override
	public List<TeamDTO> toTeamListDTO(List<Team> teams, Boolean context) {
		if (teams == null)
			return null;
		else {
			List<TeamDTO> teamListDTO = new ArrayList<>();
			teams.forEach(team -> teamListDTO.add(this.toTeamDTO(team, context)));
			return teamListDTO;
		}
	}

	@Override
	public Employee fromEmployeeDTO(EmployeeDTO employeeDTO, Boolean context) {
		System.out.println("context from employeee dto : " + context);
		if (employeeDTO == null)
			return null;
		else {
			Employee employee = this.modelMapper.map(employeeDTO, Employee.class);
			if (context)
				employee.setTeam(fromTeamDTO(employeeDTO.getTeam(), !context));
			else
				employee.setTeam(null);
			return employee;

		}
	}

	@Override
	public Team fromTeamDTO(TeamDTO teamDTO, Boolean context) {
		if (teamDTO == null)
			return null;
		else {

			Team team = this.modelMapper.map(teamDTO, Team.class);
			if (context)
				team.setTeamManager(fromEmployeeDTO(teamDTO.getTeamManager(), !context));
			else
				team.setTeamManager(null);
			return team;
		}

	}

	@Override
	public NodeDTO toNodeDTO(Node node, Boolean context) {
		if (node == null)
			return null;
		else {
			NodeDTO nodeDTO = this.modelMapper.map(node, NodeDTO.class);
			if (context) {
				nodeDTO.setParentNode(toNodeDTO(node.getParentNode(), !context));
				nodeDTO.setEmployee(toEmployeeDTO(node.getEmployee(), !context));
			} else {
				nodeDTO.setParentNode(null);
				nodeDTO.setEmployee(null);
			}
			return nodeDTO;
		}

	}

	@Override
	public BonusListDTO toBonusListDTO(BonusList bonusList, Boolean context) {
		if (bonusList == null)
			return null;
		else {
			System.out.println(	bonusList.getBeginningDate());
			BonusListDTO bonusListDTO = this.modelMapper.map(bonusList, BonusListDTO.class);
			if (context){
				bonusListDTO.setNode(toNodeDTO(bonusList.getNode(), !context));
				bonusListDTO.getNode().setEmployee(toEmployeeDTO(bonusList.getNode().getEmployee(), !context));
			}
			else 
				{
					bonusListDTO.setNode(null);
					bonusListDTO.getNode().setEmployee(null);
				}
			return bonusListDTO;
		}
	}

	@Override
	public List<NodeDTO> toNodeListDTO(List<Node> nodes, Boolean context) {
		if (nodes == null)
			return null;
		else {
			List<NodeDTO> nodeListDTO = new ArrayList<>();
			nodes.forEach(node -> nodeListDTO.add(this.toNodeDTO(node, context)));
			return nodeListDTO;
		}
	}

	@Override
	public List<BonusListDTO> toBonusesListDTO(List<BonusList> bonuses, Boolean context) {
		if (bonuses == null)
			return null;
		else {
			List<BonusListDTO> bonusesListDTO = new ArrayList<>();
			bonuses.forEach(bonusList -> bonusesListDTO.add(this.toBonusListDTO(bonusList, context)));
			return bonusesListDTO;
		}
	}

	@Override
	public Node fromNodeDTO(NodeDTO nodeDTO, Boolean context) {
		if (nodeDTO == null)
			return null;
		else {

			Node node = this.modelMapper.map(nodeDTO, Node.class);
			if (context) {
				node.setEmployee(fromEmployeeDTO(nodeDTO.getEmployee(), !context));
				node.setParentNode(fromNodeDTO(nodeDTO.getParentNode(), !context));
			} else {
				node.setEmployee(null);
				node.setParentNode(null);
			}
			return node;
		}
	}

	@Override
	public List<Node> fromNodeListDTO(List<NodeDTO> nodeListDTO, Boolean context){
		if (nodeListDTO == null)
			return null;
		else {
			List<Node> nodeList = new ArrayList<Node>();
			for (NodeDTO n: nodeListDTO ){
				nodeList.add(fromNodeDTO(n, context));
			}
			return nodeList;
		}
		
	}
	
	@Override
	public BonusList fromBonusListDTO(BonusListDTO bonusListDTO, Boolean context) {
		if (bonusListDTO == null)
			return null;
		else {
			BonusList bonusList = this.modelMapper.map(bonusListDTO, BonusList.class);
			if (context) {
				bonusList.setNode(fromNodeDTO(bonusListDTO.getNode(), !context));
			} else {
				bonusList.setNode(null);
			}
			return bonusList;
		}
	}

}
