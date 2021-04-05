package com.humane.society.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humane.society.dao.EmployeeDao;
import com.humane.society.entity.Employee;

@Service
public class EmployeeService {
	private EmployeeDao empD = new EmployeeDao();
	
	@Autowired
	public EmployeeService(EmployeeDao empD) {
		this.empD=empD;
	}
	
	public void addEmpService(Employee emp) {
		empD.addEmp(emp);
		}
	
	public Employee getEmpService (int eId) {
		return empD.getEmp(eId);
	}
	
	public void updateEmpService(Employee emp) {
		empD.updateEmp(emp);
	}
	
	public List<Employee> getAllEmpService() {
		return empD.getAllEmp();
	}
	
	public void removeEmpService(int eId) {
		empD.removeEmp(eId);
	}
	
	public boolean validateEmpService(int eId, String firstName, String lastName, double salary, String position, int locationId, String password) {
		Employee empFound = empD.getEmp(eId);
		if (empFound!=null) {
			if(empFound.getFirstName().equals(firstName) && 
					empFound.getLastName().equals(lastName) && 
					empFound.getSalary()==(salary) && 
					empFound.getPosition().equals(position) &&
					empFound.getLocationId()==(locationId) &&
					empFound.getPassword()==(password))
			{
				
				return true;
				
			}
			
		}
		return false;
	}
	
}
