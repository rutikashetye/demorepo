package services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Employee;
import repository.EmpRepo;

@Service
@Transactional
public class EmpService {
	
	@Autowired
	EmpRepo emprepo;
	
	public Optional<Employee> getEmp(int id)
	{
		return emprepo.findById(id);
	}

	public Employee save(Employee emp){
		return emprepo.save(emp);
	}
	
	public void delete(Employee emp) {
		emprepo.delete(emp);
	}

	
	public List<Employee> findAllEmployees(){
		return emprepo.findAll();
	}
	
}
