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

import pl.polsl.bonus.model.Node;
import pl.polsl.bonus.repository.NodeJpaRepository;

@CrossOrigin
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class NodeController {


	@Autowired
	private NodeJpaRepository nodeRepository;
	
	@RequestMapping(value = "nodes", method = RequestMethod.GET)
	public List<Node> nodeList(){
		return nodeRepository.findAll();
	}
	
	@RequestMapping(value = "nodes/{id}", method = RequestMethod.POST)
	public Node create(@RequestBody Node node){
		return nodeRepository.saveAndFlush(node);
	}
	
	@RequestMapping(value = "nodes/{id}", method = RequestMethod.GET)
	public Node get(@PathVariable Integer id){
		return nodeRepository.findOne(id);
	}
	@RequestMapping(value = "nodes/{id}", method = RequestMethod.PUT)
	public Node update(@PathVariable Integer id, @RequestBody Node node){
		Node existingnode = nodeRepository.findOne(id);
		BeanUtils.copyProperties(node, existingnode);
		return nodeRepository.saveAndFlush(existingnode);
	}
	@RequestMapping(value = "nodes/{id}", method = RequestMethod.DELETE)
	public Node delete(@PathVariable Integer id){
		Node existingNode = nodeRepository.findOne(id);
		nodeRepository.delete(existingNode);
		return existingNode;
		
	}


	
}
