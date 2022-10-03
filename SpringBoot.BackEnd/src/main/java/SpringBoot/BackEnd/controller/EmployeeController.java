package SpringBoot.BackEnd.controller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SpringBoot.BackEnd.exception.ResourceNotFoundException;
import SpringBoot.BackEnd.model.Employee;
import SpringBoot.BackEnd.repository.EmployeeRepository;

@CrossOrigin(origins ="http://localhost:4200") 
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){  
		
		return employeeRepository.findAll();
		
	}
	
	//create employee
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee)
	{
		return employeeRepository.save(employee);
		
	}
	//get employee By Id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee>  getEmployeeById(@PathVariable Long id)
	{
		Employee employee=employeeRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException(""
				+ "Employee Not Exicting with id:"+id));
		return ResponseEntity.ok(employee);
		
	}
	
	//update employee
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee( @PathVariable Long id,
			@RequestBody Employee employeeDetails)
	{
		Employee employee=employeeRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException(""
				+ "Employee Not Exicting with id:"+id));
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		Employee updatedEmployee=employeeRepository.save(employee);
		
		
		return ResponseEntity.ok(updatedEmployee);
		
	}
	//Delete Employ
	@DeleteMapping("/employees/{id}")
	public ResponseEntity < Map<String,Boolean>> deleteEmployee(@PathVariable Long id)
	{
		Employee employee=employeeRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException(""
				+ "Employee Not Exicting with id:"+id));
		employeeRepository.delete(employee);
		Map<String,Boolean> response = new HashMap<>();
		response.put("Deleted",Boolean.TRUE);
		
		return ResponseEntity.ok(response);
		
	}
	
}
