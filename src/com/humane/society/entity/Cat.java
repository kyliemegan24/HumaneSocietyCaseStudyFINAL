package com.humane.society.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity (name="Cat")
@Table (name="Cat")
public class Cat {
	
	@Id
	@Column (name="cId")
	private int cId;
	
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
	
//	@Column (name="catPicture")
//	private String catPicture;
	
	
	public Cat() {
		super();
	}


	public Cat(int cId, String name, int age, String breed, boolean upToDateShots, int gender, int locationId) {
		super();
		this.cId = cId;
		this.name = name;
		this.age = age;
		this.breed = breed;
		this.upToDateShots = upToDateShots;
		this.gender = gender;
		this.locationId = locationId;
	}
	
	public int getCId() {
		return cId;
	}
	
	public void setCId(int cId) {
		this.cId = cId;
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
		// need to fill out %3s  in quotation marks (for each thingy)
		return String.format("ID: %-20s, Name: %-20s, Age: %-20s, Breed: %-20s, Up to Date Shots: %-20s, Gender: %-20s, Location Id: %-20s", cId, name, age, breed, upToDateShots, gender, locationId);
	}
	
	@Override
	public boolean equals(Object o) {
		Cat comparedTo = (Cat) o;
		if (this.cId==comparedTo.getCId()
			&& this.name.equals(comparedTo.getName())
			&& this.age==(comparedTo.getAge())
			&& this.breed.contentEquals(comparedTo.getBreed())
			&& this.upToDateShots==(comparedTo.isUpToDateShots()) 
			&& this.gender==(comparedTo.getGender())
			&& this.locationId==(comparedTo.getLocationId()) )
			 {
			return true;
		}
		
		return false;
	}

}
