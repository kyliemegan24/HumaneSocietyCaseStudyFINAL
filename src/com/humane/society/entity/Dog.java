package com.humane.society.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity (name="Dog")
@Table (name="Dog")
public class Dog {
	
	@Id
	@Column (name="dId")
	private int dId;
	@Column (name="name")
	private String name;
	@Column (name="age")
	private int age;
	@Column (name="breed")
	private String breed;
	@Column (name="upToDateShots")
	private boolean upToDateShots;
	@Column (name="gender")
	private int gender;
	@Column (name="locationId")
	private int locationId;
	
	
	public Dog() {
		super();
	}


	public Dog(int dId, String name, int age, String breed, boolean upToDateShots, int gender, int locationId) {
		super();
		this.dId = dId;
		this.name = name;
		this.age = age;
		this.breed = breed;
		this.upToDateShots = upToDateShots;
		this.gender = gender;
		this.locationId = locationId;
	}

	// getters and setters 
	
	public int getDId() {
		return dId;
	}


	public void setDId(int dId) {
		this.dId = dId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getBreed() {
		return breed;
	}


	public void setBreed(String breed) {
		this.breed = breed;
	}


	public boolean isUpToDateShots() {
		return upToDateShots;
	}


	public void setUpToDateShots(boolean upToDateShots) {
		this.upToDateShots = upToDateShots;
	}


	public int getGender() {
		return gender;
	}


	public void setGender(int gender) {
		this.gender = gender;
	}


	public int getLocationId() {
		return locationId;
	}


	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	
	@Override
	public String toString() {
		
		return String.format("ID: %-20s, Name: %-20s, Age: %-20s, Breed: %-20s, Up to Date Shots: %-20s, Gender: %-20s, Location Id: %-20s", dId, name, age, breed, upToDateShots, gender, locationId);
	}
	
	
	@Override
	public boolean equals(Object o) {
		Dog comparedTo = (Dog) o;
		if (this.dId==comparedTo.getDId()
			&& this.name.equals(comparedTo.getName())
			&& this.age==(comparedTo.getAge())
			&& this.breed.equals(comparedTo.getBreed())
			&& this.upToDateShots==(comparedTo.isUpToDateShots()) 
			&& this.gender==(comparedTo.getGender())
			&& this.locationId==(comparedTo.getLocationId()) )
			 {
			return true;
		}
		
		return false;
	}
}
