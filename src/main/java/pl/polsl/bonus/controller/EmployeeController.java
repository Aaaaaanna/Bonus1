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

import pl.polsl.bonus.dto.EmployeeDTO;
import pl.polsl.bonus.mapper.MapperImpl;
import pl.polsl.bonus.model.Employee;
import pl.polsl.bonus.repository.EmployeeJpaRepository;

@CrossOrigin
@RestController
@RequestMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

	@Autowired
	private EmployeeJpaRepository employeeRepository;
	
	@Autowired
	private MapperImpl mapper;  
	
	@RequestMapping(method = RequestMethod.GET)
	public List<EmployeeDTO> employeeList(){
		return this.mapper.toEmployeeListDTO(employeeRepository.findAll(), true);
	}
	
	@RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public EmployeeDTO create(@RequestBody Employee employee){
		System.out.println(employee.getFirstName());
		return this.mapper.toEmployeeDTO(employeeRepository.saveAndFlush(employee), true);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public EmployeeDTO get(@PathVariable Integer id){
		return this.mapper.toEmployeeDTO(employeeRepository.findOne(id), true);
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public EmployeeDTO update(@PathVariable Integer id, @RequestBody Employee employee){
		Employee existingEmployee = employeeRepository.findOne(id);
		BeanUtils.copyProperties(employee, existingEmployee);
		return this.mapper.toEmployeeDTO(employeeRepository.saveAndFlush(existingEmployee), true);
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public EmployeeDTO delete(@PathVariable Integer id){
		Employee existingEmployee = employeeRepository.findOne(id);
		employeeRepository.delete(existingEmployee);
		return this.mapper.toEmployeeDTO(existingEmployee, true);
		
	}

	



}
