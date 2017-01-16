package pl.polsl.bonus.mapper;

import java.util.List;

import pl.polsl.bonus.dto.BonusListDTO;
import pl.polsl.bonus.dto.EmployeeDTO;
import pl.polsl.bonus.dto.NodeDTO;
import pl.polsl.bonus.dto.TeamDTO;
import pl.polsl.bonus.model.BonusList;
import pl.polsl.bonus.model.Employee;
import pl.polsl.bonus.model.Node;
import pl.polsl.bonus.model.Team;

public interface Mapper {
	
	EmployeeDTO toEmployeeDTO (Employee employee, Boolean context);
	TeamDTO toTeamDTO (Team team, Boolean context);
	NodeDTO toNodeDTO (Node node, Boolean context);
	BonusListDTO toBonusListDTO (BonusList bonusList, Boolean context);
	List <EmployeeDTO> toEmployeeListDTO (List <Employee> employees, Boolean context);
	List <TeamDTO> toTeamListDTO (List <Team> teams, Boolean context);
	List <NodeDTO> toNodeListDTO (List <Node> nodes, Boolean context);
	List <BonusListDTO> toBonusesListDTO (List<BonusList> bonuses, Boolean context );	
	Employee fromEmployeeDTO(EmployeeDTO employeeDTO, Boolean context);
	Team fromTeamDTO(TeamDTO teamDTO, Boolean context);
	Node fromNodeDTO(NodeDTO nodeDTO, Boolean context);
	BonusList fromBonusListDTO (BonusListDTO bonusListDTO, Boolean context);
	List<Node> fromNodeListDTO(List<NodeDTO> nodeListDTO, Boolean context);
}
