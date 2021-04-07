package com.humane.society.dao;

import java.util.List;

import com.humane.society.entity.Cat;
import com.humane.society.entity.Employee;
import com.humane.society.entity.Location;

public interface LocationDaoI {
	public boolean addLoc(Location loc);
	public Location getLoc(int id);
	public boolean updateLoc(Location loc);
	public boolean removeLoc(int id);
	public List<Location> getAllLocs();
	public void addEmptoLoc(int eId, int locId);
	public void removeEmpfromLoc(int eId, int locId);
	public List<Employee> viewAllEmp(int locId);

}
