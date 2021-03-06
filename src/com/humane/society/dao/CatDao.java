package com.humane.society.dao;

import java.util.List;



import org.springframework.stereotype.Repository;

import com.humane.society.dbConnection.DBConnection;
import com.humane.society.entity.Cat;


@Repository
public class CatDao extends DBConnection implements CatDaoI {
	
	
	@Override
	public boolean addCat(Cat cat) {
		try {
			this.connect();
			em.getTransaction().begin();
			em.persist(cat);
			em.getTransaction().commit();
			this.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	@Override
	public Cat getCat(int cId) {
		this.connect();
		Cat catFound = em.find(Cat.class, cId);
		this.disconnect();
		return catFound;
	}
	
	
	@Override
	public boolean updateCat(Cat cat) {
		try {
			this.connect();
			em.getTransaction().begin();
			Cat catFound = em.find(Cat.class, cat.getCId());
			catFound.setName(cat.getName());
			catFound.setAge(cat.getAge());
			catFound.setBreed(cat.getBreed());
			catFound.setUpToDateShots(cat.isUpToDateShots());
			catFound.setGender(cat.getGender());
			catFound.setLocationId(cat.getLocationId());
			
			em.getTransaction().commit();
			this.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	@Override
	public boolean removeCat(int cId) {
		try {
			this.connect();
			em.getTransaction().begin();
			Cat catFound = em.find(Cat.class, cId);
			em.remove(catFound);
			em.getTransaction().commit();
			this.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<Cat> getAllCats() {
		this.connect();
		List<Cat> cats = em.createQuery("select c from Cat c").getResultList();
		this.disconnect();
		return cats;
	}
	
	
	

}
