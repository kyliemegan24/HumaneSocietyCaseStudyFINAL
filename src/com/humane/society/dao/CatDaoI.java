package com.humane.society.dao;

import java.util.List;

import com.humane.society.entity.Cat;

public interface CatDaoI {
	public boolean addCat(Cat cat);
	public Cat getCat(int cId);
	public boolean updateCat(Cat cat);
	public boolean removeCat(int cId);
	public List<Cat> getAllCats();

}
