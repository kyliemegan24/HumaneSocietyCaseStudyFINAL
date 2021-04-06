package com.humane.society.dao;

import java.util.List;

import com.humane.society.entity.Cat;

import com.humane.society.entity.Location;

public interface LocationDaoI {
	public boolean addLoc(Location loc);
	public Location getLoc(int id);
	public boolean updateLoc(Location loc);
	public boolean removeLoc(int id);
	public List<Location> getAllLocs();
//	public void addCatToLoc(int cId, int locId);
//	public void removeCatFromLoc(int cId, int locId);
//	public List<Cat> viewAllCats(int locId);
}
