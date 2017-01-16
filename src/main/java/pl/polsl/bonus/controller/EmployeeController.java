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

import pl.polsl.bonus.dto.EmployeeDTO;
import pl.polsl.bonus.mapper.MapperImpl;
import pl.polsl.bonus.model.Employee;
import pl.polsl.bonus.repository.EmployeeJpaRepository;

@CrossOrigin
@RestController
@RequestMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE )
public class EmployeeController {

	@Autowired
	private EmployeeJpaRepository employeeRepository;
	
	@Autowired
	private MapperImpl mapper;  
	
	@RequestMapping(method = RequestMethod.GET)
	public List<EmployeeDTO> employeeList(){
		return this.mapper.toEmployeeListDTO(employeeRepository.findAll(), true);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody EmployeeDTO employee){
		try{
			Employee newEmployee = this.mapper.fromEmployeeDTO(employee, true);
			employeeRepository.saveAndFlush(newEmployee);
			return new ResponseEntity<>(newEmployee, HttpStatus.OK);
		}
		catch(Exception e){
			String errorMessage;
			errorMessage = "Failed to add employee";
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public EmployeeDTO get(@PathVariable Integer id){
		return this.mapper.toEmployeeDTO(employeeRepository.findOne(id), true);
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Employee employee){
		try{
			Employee existingEmployee = employeeRepository.findOne(id);
			BeanUtils.copyProperties(employee, existingEmployee);
			employeeRepository.saveAndFlush(existingEmployee);
			return new ResponseEntity<>(existingEmployee, HttpStatus.OK);
			
		}
		catch (Exception e){
			String errorMessage;
			errorMessage = "Failed to update employee ";
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Integer id ){
		try{
			Employee existingEmployee = employeeRepository.findOne(id);
			employeeRepository.delete(existingEmployee);
			return new ResponseEntity<>(existingEmployee, HttpStatus.OK);
			}
		catch(Exception e){
			String errorMessage;
			errorMessage = "Failed to delete employee";
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}

	



}
