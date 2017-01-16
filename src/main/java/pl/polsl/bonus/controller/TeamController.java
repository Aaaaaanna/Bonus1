package pl.polsl.bonus.controller;

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

import pl.polsl.bonus.dto.TeamDTO;
import pl.polsl.bonus.mapper.MapperImpl;
import pl.polsl.bonus.model.Team;
import pl.polsl.bonus.repository.TeamJpaRepository;

@CrossOrigin
@RestController
@RequestMapping(value = "/teams", produces = MediaType.APPLICATION_JSON_VALUE)
public class TeamController {

	@Autowired
	private TeamJpaRepository teamRepository;

	@Autowired
	private MapperImpl mapper;

	@RequestMapping(method = RequestMethod.GET)
	public List<TeamDTO> teamList() {
		return this.mapper.toTeamListDTO(teamRepository.findAll(), true);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody TeamDTO team) {
		try {
			Team newTeam = this.mapper.fromTeamDTO(team, true);
			return new ResponseEntity<>(teamRepository.saveAndFlush(newTeam), HttpStatus.OK);
		} catch (Exception e) {
			String errorMessage;
			errorMessage = "Failed to add team";
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public TeamDTO get(@PathVariable Integer id) {
		return this.mapper.toTeamDTO(teamRepository.findOne(id), true);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Team team) {
		try {
			Team existingteam = teamRepository.findOne(id);
			BeanUtils.copyProperties(team, existingteam);
			teamRepository.saveAndFlush(existingteam);
			return new ResponseEntity<>(existingteam, HttpStatus.OK);
		} catch (Exception e) {

			String errorMessage;
			errorMessage = "Failed to edit team";
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try {
			Team existingteam = teamRepository.findOne(id);
			teamRepository.delete(existingteam);
			return new ResponseEntity<>(existingteam, HttpStatus.OK);
		} catch (Exception ex) {
			String errorMessage;
			errorMessage = "Failed to delete team";
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
