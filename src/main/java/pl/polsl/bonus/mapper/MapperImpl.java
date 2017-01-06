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
public class MapperImpl implements Mapper{

	private ModelMapper modelMapper = new ModelMapper(); 
	
	@Override
	public EmployeeDTO toEmployeeDTO(Employee employee) {
		if (employee == null)
			return null;
		else 
			return this.modelMapper.map(employee, EmployeeDTO.class);
	}

	@Override
	public TeamDTO toTeamDTO(Team team) {
		if (team == null)
			return null;
		else return this.modelMapper.map(team, TeamDTO.class);
	}

	@Override
	public List<EmployeeDTO> toEmployeeListDTO(List<Employee> employees) {
		if (employees == null)
			return null;
		else 
		{
			List<EmployeeDTO> employeeListDTO = new ArrayList<>();
			employees.forEach(employee -> employeeListDTO.add(this.toEmployeeDTO(employee)));
			return employeeListDTO;
		}
	}

	@Override
	public List<TeamDTO> toTeamListDTO(List<Team> teams) {
		if (teams == null)
			return null;
		else {
			List <TeamDTO> teamListDTO = new ArrayList<>();
			teams.forEach(team -> teamListDTO.add(this.toTeamDTO(team)));
			return teamListDTO;
		}
	}
	
	
	
	
}
