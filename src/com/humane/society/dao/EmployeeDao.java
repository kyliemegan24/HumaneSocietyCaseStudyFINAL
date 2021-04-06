package com.humane.society.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.humane.society.dbConnection.DBConnection;
import com.humane.society.entity.Employee;


@Repository
public class EmployeeDao extends DBConnection implements EmployeeDaoI {

	@Override
	public boolean addEmp(Employee emp) {
		try {
			this.connect();
			em.getTransaction().begin();
			em.persist(emp);
			em.getTransaction().commit();
			this.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Employee getEmp(int eId) {
		this.connect();
		Employee empFound = em.find(Employee.class, eId);
		this.disconnect();
		return empFound;
	}

	@Override
	public boolean updateEmp(Employee emp) {
		try {
			this.connect();
			em.getTransaction().begin();
			Employee empFound = em.find(Employee.class, emp.getEId());
			empFound.setFirstName(emp.getFirstName());
			empFound.setLastName(emp.getLastName());
			empFound.setSalary(emp.getSalary());
			empFound.setPosition(emp.getPosition());
			empFound.setLocationId(emp.getLocationId());
			
			em.getTransaction().commit();
			this.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeEmp(int eId) {
		try {
			this.connect();
			em.getTransaction().begin();
			Employee empFound = em.find(Employee.class, eId);
			em.remove(empFound);
			em.getTransaction().commit();
			this.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Employee> getAllEmp() {
		this.connect();
		List<Employee> emps = em.createQuery("select e from Employee e").getResultList();
		this.disconnect();
		return emps;
	}

}
