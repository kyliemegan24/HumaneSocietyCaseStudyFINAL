package com.humane.society.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.humane.society.dbConnection.DBConnection;
import com.humane.society.entity.Cat;
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
		Location locFound = em.find(Location.class, locId);
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
	
//	@Override
//	public void removeEmpFromLoc(int eId, int locId) {
//		this.connect();
//		em.getTransaction().begin();
//		Employee empFound = em.find(Employee.class, eId);
//		Location locFound =em.find(Location.class, locId);
//		List<Employee> emps = locFound.getEmployeeList();
//		emps.remove(empFound);
//		em.getTransaction().commit();
//		this.disconnect();
//		
//	}
//	
//	@Override
//	public void addEmpToLoc(int eId, int locId) {
//		this.connect();
//		em.getTransaction().begin();
//		Employee empFound = em.find(Employee.class, eId);
//		Location locFound = em.find(Location.class, locId);
//		List<Employee> emps = locFound.getEmployeeList();
//		emps.add(empFound);
//		em.getTransaction().commit();
//		this.disconnect();
//		
//	}
//
//	@Override
//	public List<Employee> viewAllEmp(int locId) {
//		// TODO Auto-generated method stub
//		return this.getLoc(locId).getEmployeeList();
//	}
//
//	
//	
//	@Override
//	public void addCatToLoc(int cId, int locId) {
//		this.connect();
//		em.getTransaction().begin();
//		Cat catFound = em.find(Cat.class, cId);
//		Location locFound = em.find(Location.class, locId);
//		List<Cat> cats = locFound.getCatList();
//		cats.add(catFound);
//		em.getTransaction().commit();
//		this.disconnect();
//		
//	}
//
//	@Override
//	public List<Cat> viewAllCats(int locId) {
//		// TODO Auto-generated method stub
//		return this.getLoc(locId).getCatList();
//	}
//	
//	
//	@Override
//	public void removeCatFromLoc(int cId, int locId) {
//		this.connect();
//		em.getTransaction().begin();
//		Cat catFound = em.find(Cat.class, cId);
//		Location locFound =em.find(Location.class, locId);
//		List<Cat> cats = locFound.getCatList();
//		cats.remove(catFound);
//		em.getTransaction().commit();
//		this.disconnect();
//		
//	}
//	
//	
//	
//	public List<Cat> viewAllCat(int locId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public void addCatToLoc(int cId, int locId) {
//		// TODO Auto-generated method stub
//		
//	}

}
