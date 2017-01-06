package pl.polsl.bonus.mapper;

import java.util.List;

import pl.polsl.bonus.dto.EmployeeDTO;
import pl.polsl.bonus.dto.TeamDTO;
import pl.polsl.bonus.model.Employee;
import pl.polsl.bonus.model.Team;

public interface Mapper {
	
	EmployeeDTO toEmployeeDTO (Employee employee);
	TeamDTO toTeamDTO (Team team);
	List <EmployeeDTO> toEmployeeListDTO (List <Employee> employees);
	List <TeamDTO> toTeamListDTO (List <Team> teams);
	
	
}
