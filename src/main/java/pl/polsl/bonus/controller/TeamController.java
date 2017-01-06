package pl.polsl.bonus.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.polsl.bonus.dto.TeamDTO;
import pl.polsl.bonus.mapper.MapperImpl;
import pl.polsl.bonus.model.Team;
import pl.polsl.bonus.repository.TeamJpaRepository;

@CrossOrigin
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class TeamController {


	@Autowired
	private TeamJpaRepository teamRepository;
	
	@Autowired
	private MapperImpl mapper;
	
	@RequestMapping(value = "teams", method = RequestMethod.GET)
	public List<TeamDTO> teamList(){
		return this.mapper.toTeamListDTO(teamRepository.findAll(), true);
	}
	
	@RequestMapping(value = "teams/{id}", method = RequestMethod.POST)
	public TeamDTO create(@RequestBody Team team){
		return this.mapper.toTeamDTO(teamRepository.saveAndFlush(team),true);
	}
	
	@RequestMapping(value = "teams/{id}", method = RequestMethod.GET)
	public TeamDTO get(@PathVariable Integer id){
		return this.mapper.toTeamDTO(teamRepository.findOne(id), true);
	}
	@RequestMapping(value = "teams/{id}", method = RequestMethod.PUT)
	public TeamDTO update(@PathVariable Integer id, @RequestBody Team team){
		Team existingteam = teamRepository.findOne(id);
		BeanUtils.copyProperties(team, existingteam);
		return this.mapper.toTeamDTO(teamRepository.saveAndFlush(existingteam), true);
	}
	@RequestMapping(value = "teams/{id}", method = RequestMethod.DELETE)
	public TeamDTO delete(@PathVariable Integer id){
		Team existingteam = teamRepository.findOne(id);
		teamRepository.delete(existingteam);
		return this.mapper.toTeamDTO(existingteam, true);
		
	}



	
}
