package pl.polsl.bonus.mapper;

import java.util.List;

import pl.polsl.bonus.dto.EmployeeDTO;
import pl.polsl.bonus.dto.TeamDTO;
import pl.polsl.bonus.model.Employee;
import pl.polsl.bonus.model.Team;

public interface Mapper {
	
	EmployeeDTO toEmployeeDTO (Employee employee, Boolean context);
	TeamDTO toTeamDTO (Team team, Boolean context);
	List <EmployeeDTO> toEmployeeListDTO (List <Employee> employees, Boolean context);
	List <TeamDTO> toTeamListDTO (List <Team> teams, Boolean context);
	Employee fromEmployeeDTO(EmployeeDTO employeeDTO, Boolean context);
	Team fromTeamDTO(TeamDTO teamDTO, Boolean context);
	
}
