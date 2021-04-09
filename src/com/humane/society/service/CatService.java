package com.humane.society.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humane.society.dao.CatDao;
import com.humane.society.entity.Cat;


@Service
public class CatService {
	
	private CatDao catC = new CatDao();
	
	@Autowired
	public CatService(CatDao catC) {
		this.catC=catC;
	}
	
	public void addCatService(Cat cat) {
		catC.addCat(cat);
	}
	
	public Cat getCatService(int cId) {
		return catC.getCat(cId);
	}
	
	public void updateCatService(Cat cat) {
		catC.updateCat(cat);
	}
	
	public List<Cat> getAllCatsService() {
		return catC.getAllCats();
	}
	
	public void removeCatService(int cId) {
		catC.removeCat(cId);
	}
	
	public boolean validateCatService(int cId, String name, int age, String breed, boolean upToDateShots, int gender, int locationId) {
		Cat catFound = catC.getCat(cId);
		if(catFound!=null) {
			if(catFound.getName().equals(name) && 
					catFound.getAge()==(age) && 
					catFound.getBreed().equals(breed) && 
					catFound.isUpToDateShots()==(upToDateShots) && 
					catFound.getGender()==(gender) && 
					catFound.getLocationId()==(locationId)) {
				
				return true;
			}
			
		}
		
		return false;
		}
	}


