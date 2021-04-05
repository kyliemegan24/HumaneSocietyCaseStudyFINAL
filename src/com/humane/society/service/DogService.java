package com.humane.society.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humane.society.dao.DogDao;
import com.humane.society.entity.Cat;
import com.humane.society.entity.Dog;

@Service
public class DogService {
private DogDao dogD = new DogDao();

	@Autowired
	public DogService(DogDao dogD) {
		this.dogD=dogD;
	}
		
	public void addDogService(Dog dog) {
		dogD.addDog(dog);
	}
	
	public Dog getDogService(int dId) {
		return dogD.getDog(dId);
	}
	
	public void updateDogService(Dog dog) {
		dogD.updateDog(dog);
	}
	
	public List<Dog> getAllDogsService() {
		return dogD.getAllDogs();
	}
	
	public void removeDogService(int dId) {
		dogD.removeDog(dId);
	}
	
	public boolean validateDogService(int dId, String name, int age, String breed, boolean upToDateShots, int gender, int locationId) {
		Dog dogFound = dogD.getDog(dId);
		if(dogFound!=null) {
			if(dogFound.getName().equals(name) && 
					dogFound.getAge()==(age) && 
					dogFound.getBreed().equals(breed) && 
					dogFound.isUpToDateShots()==(upToDateShots) && 
					dogFound.getGender()==(gender) && 
					dogFound.getLocationId()==(locationId)) {
				
				return true;
			}
			
		}
		
		return false;
		
		}
}
