package com.humane.society.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity (name="Location")
@Table (name="Location")
public class Location {
	
	@Id
	@Column (name="locId")
	private int locId;
	@Column (name="name")
	private String name;
	@Column (name="address")
	private String address;
	@OneToMany(targetEntity=Employee.class, fetch=FetchType.EAGER)
	private List<Employee> employeeList;
	
	
	
	public Location() {
		super();
		
	}



	public Location(int locId, String name, String address, List<Employee> employeeList) {
		super();
		this.locId = locId;
		this.name = name;
		this.address = address;
		this.employeeList = employeeList;
	}



	public int getLocId() {
		return locId;
	}



	public void setLocId(int locId) {
		this.locId = locId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}
	
	public List<Employee> getEmployeeList() {
		return employeeList;
	}



	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	
	
	
	@Override
	public String toString() {
		// need to fill out the first argument, need to figure out how and don't want to rn
		return String.format("ID: %-20s, Name: %-20s, Address: %-20s", locId, name, address);
	}
	
	@Override
	public boolean equals(Object o) {
		Location comparedTo = (Location) o;
		if (this.locId==comparedTo.getLocId()
			&& this.name.equals(comparedTo.getName())
			&& this.address.equals(comparedTo.getAddress()) )
			 {
			return true;
		}
		
		return false;
	}
	
	
	
}
