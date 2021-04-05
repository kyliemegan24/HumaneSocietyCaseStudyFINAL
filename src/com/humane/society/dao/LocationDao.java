package com.humane.society.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.humane.society.dbConnection.DBConnection;
import com.humane.society.entity.Employee;
import com.humane.society.entity.Location;


@Repository
public class LocationDao extends DBConnection implements LocationDaoI {
	
	@Override
	public boolean addLoc(Location loc) {
		try {
			this.connect();
			em.getTransaction().begin();
			em.persist(loc);
			em.getTransaction().commit();
			this.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Location getLoc(int locId) {
		this.connect();
//		Employee empFound = em.find(Employee.class, id);
//		Query q = em.createQuery("select e from Employee e where e.id = :eID");
		Query q = em.createNamedQuery("getLocById");
		q.setParameter("locId", locId);
		List<Location> locs = q.getResultList();
		Location locFound = null;
		for (Location l: locs) {
			locFound = l;
			break;
		}
		this.disconnect();
		return locFound;
	}
	

	@Override
	public boolean updateLoc(Location loc) {
		try {
			this.connect();
			em.getTransaction().begin();
			Location locFound = em.find(Location.class, loc.getLocId());
			locFound.setName(loc.getName());
			locFound.setAddress(loc.getAddress());
			
			em.getTransaction().commit();
			this.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	@Override
	public boolean removeLoc(int locId) {
		try {
			this.connect();
			em.getTransaction().begin();
			Location locFound = em.find(Location.class, locId);
			em.remove(locFound);
			em.getTransaction().commit();
			this.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Location> getAllLocs() {
		this.connect();
		List<Location> locs = em.createQuery("select l from Location l").getResultList();
		this.disconnect();
		return locs;
	}
	
	@Override
	public void removeEmpFromStore(int eId, int locId) {
		this.connect();
		em.getTransaction().begin();
		Employee empFound = em.find(Employee.class, eId);
		Location locFound =em.find(Location.class, locId);
		List<Employee> emps = locFound.getEmployeeList();
		emps.remove(empFound);
		em.getTransaction().commit();
		this.disconnect();
		
	}
	
	
	@Override
	public void addEmpToStore(int eId, int locId) {
		this.connect();
		em.getTransaction().begin();
		Employee empFound = em.find(Employee.class, eId);
		Location locFound = em.find(Location.class, locId);
		List<Employee> emps = locFound.getEmployeeList();
		emps.add(empFound);
		em.getTransaction().commit();
		this.disconnect();
		
	}

	@Override
	public List<Employee> viewAllEmp(int locId) {
		// TODO Auto-generated method stub
		return this.getLoc(locId).getEmployeeList();
	}
}
