package com.humane.society.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="Employee")
@Table (name="Employee")
public class Employee {

	@Id
	@Column (name="eId")
	private int eId;
	@Column (name="firstName")
	private String firstName;
	@Column (name="lastName")
	private String lastName;
	@Column (name="salary")
	private double salary;
	@Column (name="position")
	private String position;
	@Column (name="locationId")
	private int locationId;
	@Column (name="password")
	private String password;
	
	public Employee() {
		super();
	}

	public Employee(int eId, String firstName, String lastName, double salary, String position, int locationId, String password) {
		super();
		this.eId = eId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		this.position = position;
		this.locationId = locationId;
		this.password = password;
	}

	//getters and setters
	
	public int getEId() {
		return eId;
	}
	
	public void setEId(int eId) {
		this.eId = eId;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	public int getLocationId() {
		return locationId;
	}
	
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	@Override
	public String toString() {
		
		return String.format("ID: %-20s, First Name: %-20s, Last Name: %-20s, Salary: %-20s, Position: %-20s, Location Id: %-20s", eId, firstName, lastName, salary, position, locationId, password);
	}
	
	@Override
	public boolean equals(Object o) {
		Employee comparedTo = (Employee) o;
		if (this.eId==comparedTo.getEId()
			&& this.firstName.equals(comparedTo.getFirstName())
			&& this.lastName.equals(comparedTo.getLastName())
			&& this.salary==(comparedTo.getSalary())
			&& this.position.equals(comparedTo.getPosition())
			&& this.locationId==(comparedTo.getLocationId()) 
			&& this.password.equals(comparedTo.getPassword()) 
			 )
			 {
			return true;
		}
		
		return false;
	}
	
}
