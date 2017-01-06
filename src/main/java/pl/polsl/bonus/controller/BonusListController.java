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

import pl.polsl.bonus.model.BonusList;
import pl.polsl.bonus.repository.BonusListJpaRepository;

@CrossOrigin
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class BonusListController {


	@Autowired
	private BonusListJpaRepository bonusListRepository;
	
	@RequestMapping(value = "bonusLists", method = RequestMethod.GET)
	public List<BonusList> bonusListList(){
		return bonusListRepository.findAll();
	}
	
	@RequestMapping(value = "bonusLists/{id}", method = RequestMethod.POST)
	public BonusList create(@RequestBody BonusList bonusList){
		return bonusListRepository.saveAndFlush(bonusList);
	}
	
	@RequestMapping(value = "bonusLists/{id}", method = RequestMethod.GET)
	public BonusList get(@PathVariable Integer id){
		return bonusListRepository.findOne(id);
	}
	@RequestMapping(value = "bonusLists/{id}", method = RequestMethod.PUT)
	public BonusList update(@PathVariable Integer id, @RequestBody BonusList bonusList){
		BonusList existingbonusList = bonusListRepository.findOne(id);
		BeanUtils.copyProperties(bonusList, existingbonusList);
		return bonusListRepository.saveAndFlush(existingbonusList);
	}
	@RequestMapping(value = "bonusLists/{id}", method = RequestMethod.DELETE)
	public BonusList delete(@PathVariable Integer id){
		BonusList existingbonusList = bonusListRepository.findOne(id);
		bonusListRepository.delete(existingbonusList);
		return existingbonusList;
		
	}



}
