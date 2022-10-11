package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import exception.ResourceNotFoundException;
import model.Employee;
import services.EmpService;

@RestController
public class EmpController {

	 @Autowired
	 private EmpService Service;

	    @GetMapping("/employees")
	    public List<Employee> getAllEmps() {
	        return Service.findAllEmployees();
	    }

	    @GetMapping("/employees/{id}")
	    public ResponseEntity<Optional<Employee>> getEmpById(@PathVariable(value = "id") Integer Id)
	     {
	    	Optional<Employee> Emp = Service.getEmp(Id);
	        return ResponseEntity.ok().body(Emp);
	    }
	    
	    @PostMapping("/employees")
	    public Employee create(@RequestBody Employee emp) {
	        return Service.save(emp);
	    }

	    @PutMapping("/employees/{id}")
	    public ResponseEntity<Employee> update(@PathVariable(value = "id") Integer Id, @RequestBody Employee emp) throws ResourceNotFoundException 
	    {
	    	Employee employee = Service.getEmp(Id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + Id));

	    	employee.setName(emp.getName());
	    	employee.setGender(emp.getGender());
	    	employee.setEmailId(emp.getEmailId());
	    	
	        final Employee updateEmp = Service.save(employee);
	        return ResponseEntity.ok(updateEmp);
	    }

	    @DeleteMapping("/admins/{id}")
	    public Map<String, Boolean> delete(@PathVariable(value = "id") Integer Id)
	         throws ResourceNotFoundException {
	    	Employee emp = Service.getEmp(Id)
	       .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + Id));

	    	Service.delete(emp);
	        Map<String, Boolean> response = new HashMap();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
}
