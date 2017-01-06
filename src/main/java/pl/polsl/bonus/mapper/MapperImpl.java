package pl.polsl.bonus.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import pl.polsl.bonus.dto.EmployeeDTO;
import pl.polsl.bonus.dto.TeamDTO;
import pl.polsl.bonus.model.Employee;
import pl.polsl.bonus.model.Team;

@Component
public class MapperImpl implements Mapper {

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	// context - zapobiega zapętlaniu , jeżeli wywołanie zewnętrzne to true, jeżeli nie - false 
	public EmployeeDTO toEmployeeDTO(Employee employee, Boolean context) {
		if (employee == null)
			return null;
		else {
			EmployeeDTO employeeDTO = this.modelMapper.map(employee, EmployeeDTO.class);
			if (context)
				employeeDTO.setTeamId(toTeamDTO(employee.getTeam(),!context));
			else employeeDTO.setTeamId(null);
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
				teamDTO.setTeamManager(toEmployeeDTO(team.getTeamManager(),!context));
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
			employees.forEach(employee -> employeeListDTO.add(this.toEmployeeDTO(employee,context)));
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

}
