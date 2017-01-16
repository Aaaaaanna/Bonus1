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

import pl.polsl.bonus.dto.NodeDTO;
import pl.polsl.bonus.mapper.MapperImpl;
import pl.polsl.bonus.model.Node;
import pl.polsl.bonus.repository.NodeJpaRepository;

@CrossOrigin
@RestController
@RequestMapping(value = "/nodes", produces = MediaType.APPLICATION_JSON_VALUE)
public class NodeController {

	@Autowired
	private NodeJpaRepository nodeRepository;

	
	@Autowired
	private MapperImpl mapper;

	@RequestMapping(method = RequestMethod.GET)
	public List<NodeDTO> nodeList() {
		return this.mapper.toNodeListDTO(nodeRepository.findAll(), true);
	}

	@RequestMapping( method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody NodeDTO node){
		try{
			Node newNode = this.mapper.fromNodeDTO(node,true);
			nodeRepository.saveAndFlush(newNode);
			return new ResponseEntity<>( newNode, HttpStatus.OK);
		}
		catch(Exception e) {
			String errorMessage;
			errorMessage = "Failed to create node";
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public NodeDTO get(@PathVariable Integer id) {
		return this.mapper.toNodeDTO(nodeRepository.findOne(id), true);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Node node) {
		try{
		Node existingnode = nodeRepository.findOne(id);
		BeanUtils.copyProperties(node, existingnode);
		nodeRepository.saveAndFlush(existingnode);
		return new ResponseEntity<>(existingnode, HttpStatus.OK);
		}
		catch(Exception e){
			String errorMessage;
			errorMessage = "Failed to update node";
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);				
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try{
		Node existingNode = nodeRepository.findOne(id);
		nodeRepository.delete(existingNode);
		return new ResponseEntity<>(existingNode, HttpStatus.OK);}
		catch(Exception e ){
			String errorMessage;
			errorMessage = "Failed to update node";
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);					
		}

	}

	
	
}
