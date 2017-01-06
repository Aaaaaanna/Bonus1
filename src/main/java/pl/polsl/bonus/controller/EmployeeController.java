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

import pl.polsl.bonus.model.Employee;
import pl.polsl.bonus.repository.EmployeeJpaRepository;

@CrossOrigin
@RestController
@RequestMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

	@Autowired
	private EmployeeJpaRepository employeeRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Employee> employeeList(){
		return employeeRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public Employee create(@RequestBody Employee employee){
		System.out.println(employee.getFirstName());
		return employeeRepository.saveAndFlush(employee);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Employee get(@PathVariable Integer id){
		return employeeRepository.findOne(id);
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Employee update(@PathVariable Integer id, @RequestBody Employee employee){
		Employee existingEmployee = employeeRepository.findOne(id);
		BeanUtils.copyProperties(employee, existingEmployee);
		return employeeRepository.saveAndFlush(existingEmployee);
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Employee delete(@PathVariable Integer id){
		Employee existingEmployee = employeeRepository.findOne(id);
		employeeRepository.delete(existingEmployee);
		return existingEmployee;
		
	}

	



}
