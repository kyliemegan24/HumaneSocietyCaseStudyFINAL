package com.humane.society.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.humane.society.dbConnection.DBConnection;
import com.humane.society.entity.Cat;
import com.humane.society.entity.Dog;

@Repository
public class DogDao extends DBConnection implements DogDaoI {

	@Override
	public boolean addDog(Dog dog) {
		try {
			this.connect();
			em.getTransaction().begin();
			em.persist(dog);
			em.getTransaction().commit();
			this.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	

	@Override
	public Dog getDog(int dId) {
		this.connect();
		Dog dogFound = em.find(Dog.class, dId);
		this.disconnect();
		return dogFound;
	}
	
	
	@Override
	public boolean updateDog(Dog dog) {
		try {
			this.connect();
			em.getTransaction().begin();
			Dog dogFound = em.find(Dog.class, dog.getDId());
			dogFound.setName(dog.getName());
			dogFound.setAge(dog.getAge());
			dogFound.setBreed(dog.getBreed());
			dogFound.setUpToDateShots(dog.isUpToDateShots());
			dogFound.setGender(dog.getGender());
			dogFound.setLocationId(dog.getLocationId());
			
			em.getTransaction().commit();
			this.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	@Override
	public boolean removeDog(int dId) {
		try {
			this.connect();
			em.getTransaction().begin();
			Dog dogFound = em.find(Dog.class, dId);
			em.remove(dogFound);
			em.getTransaction().commit();
			this.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<Dog> getAllDogs() {
		this.connect();
		List<Dog> dogs = em.createQuery("select d from Dog d").getResultList();
		this.disconnect();
		return dogs;
	}
	
	
	
}
