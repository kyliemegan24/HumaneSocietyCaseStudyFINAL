package com.humane.society.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humane.society.dao.LocationDao;
import com.humane.society.entity.Cat;
import com.humane.society.entity.Employee;
import com.humane.society.entity.Location;


@Service
public class LocationService {
	
private LocationDao locL = new LocationDao();

	@Autowired
	public LocationService(LocationDao locL) {
		this.locL=locL;
	}
	
	public void addLocService(Location loc) {
		locL.addLoc(loc);
		}
	
	public Location getLocService (int locId) {
		return locL.getLoc(locId);
	}
	
	public List<Location> getAllLocsService() {
		return locL.getAllLocs();
	}
	
	public void updateLocService(Location loc) {
		locL.updateLoc(loc);
	}
	
	public void removeLocService(int locId) {
		locL.removeLoc(locId);
	}
	
	public void addEmpToLocService(int eId, int locId) {
		locL.addEmptoLoc(eId, locId);
	}
	
	public void removeEmpFromLocService(int eId, int locId) {
		locL.removeEmpfromLoc(eId, locId);
	}
	
	public List<Employee> viewAllEmpService(int locId) {
		return locL.viewAllEmp(locId);
	}

	
	public boolean validateLocService(int locId, String name, String address) {
		Location locFound = locL.getLoc(locId);
		if (locFound!=null) {
			if(locFound.getName().equals(name) && 
				locFound.getAddress().equals(address))
			{
				
				return true;
				
			}
			
		}
		return false;
	}

}
