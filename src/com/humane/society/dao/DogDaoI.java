package com.humane.society.dao;

import java.util.List;

import com.humane.society.entity.Dog;

public interface DogDaoI {
	public boolean addDog(Dog dog);
	public Dog getDog(int dId);
	public boolean updateDog(Dog dog);
	public boolean removeDog(int dId);
	public List<Dog> getAllDogs();

}
