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

import pl.polsl.bonus.model.Team;
import pl.polsl.bonus.repository.TeamJpaRepository;

@CrossOrigin
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class TeamController {


	@Autowired
	private TeamJpaRepository teamRepository;
	
	@RequestMapping(value = "teams", method = RequestMethod.GET)
	public List<Team> teamList(){
		return teamRepository.findAll();
	}
	
	@RequestMapping(value = "teams/{id}", method = RequestMethod.POST)
	public Team create(@RequestBody Team team){
		return teamRepository.saveAndFlush(team);
	}
	
	@RequestMapping(value = "teams/{id}", method = RequestMethod.GET)
	public Team get(@PathVariable Integer id){
		return teamRepository.findOne(id);
	}
	@RequestMapping(value = "teams/{id}", method = RequestMethod.PUT)
	public Team update(@PathVariable Integer id, @RequestBody Team team){
		Team existingteam = teamRepository.findOne(id);
		BeanUtils.copyProperties(team, existingteam);
		return teamRepository.saveAndFlush(existingteam);
	}
	@RequestMapping(value = "teams/{id}", method = RequestMethod.DELETE)
	public Team delete(@PathVariable Integer id){
		Team existingteam = teamRepository.findOne(id);
		teamRepository.delete(existingteam);
		return existingteam;
		
	}



	
}
