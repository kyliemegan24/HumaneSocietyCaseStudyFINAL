package com.humane.society.main;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.humane.society.entity.Cat;
import com.humane.society.entity.Dog;
import com.humane.society.entity.Employee;
import com.humane.society.entity.Location;

public class HumaneSocietyAppMain {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("HumaneSocietyCaseStudy");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		// adding cats to database
		
		Cat c1 = new Cat(11, "Hila", 7, "orange tabby", true, 2, 1);
		Cat c2 = new Cat(12, "Norah", 11, "orange tabby", false, 2, 1);
		Cat c3 = new Cat(13, "Wii-U", 7, "gray tabby", true, 1, 2);
		Cat c4 = new Cat(14, "Mrs. Big", 10, "blue point siamese", true, 2, 2);
		Cat c5 = new Cat(15, "Jingo", 1, "tan tabby", true, 1, 2);
		Cat c6 = new Cat(16, "Cola", 20, "brown tabby", true, 2, 1);
	
		
		em.persist(c1);
		em.persist(c2);
		em.persist(c3);
		em.persist(c4);
		em.persist(c5);
		em.persist(c6);
		
		//adding new dogs to database
		
		Dog d1 = new Dog(22, "Lizzie", 20, "bichon frise", true, 2, 1);
		Dog d2 = new Dog(33, "Junior", 18, "bichon-poodle", true, 1, 2);
		Dog d3 = new Dog(44, "Lemon", 7, "bull terrier", true, 1, 1);
		Dog d4 = new Dog(55, "Rogue", 5, "collie", true, 2, 1);
		Dog d5 = new Dog(66, "Edgar", 10, "pug", true, 1, 2);
		
	
		
		em.persist(d1);
		em.persist(d2);
		em.persist(d3);
		em.persist(d4);
		em.persist(d5);
		
		
		
		//adding employees to database
		
		Employee e1 = new Employee(1, "Abby", "Aka", 100000.00, "Manager", 1, "kylie1");
		Employee e2 = new Employee(2, "Shelby", "Sleeker", 90000.00, "HR Consultant", 1, "kylie2");
		Employee e3 = new Employee(3, "Kate", "Kuman", 70000.00, "Adoption Assistant", 2, "kylie3");
		Employee e4 = new Employee(4, "Tay", "Tashton", 70000.00, "Adoption Assitant", 2, "kylie4");
		Employee e5 = new Employee(5, "Caleb", "Colcutt", 100000.00, "Resident Vet", 1, "kylie5");
		
		
		em.persist(e1);
		em.persist(e2);
		em.persist(e3);
		em.persist(e4);
		em.persist(e5);
		
		
		
		//adding locations to database
		
		
		Location loc1 = new Location(1, "Highland Park Humane Society", "1111 Marc Ave, Saint Paul MN, 55102", new ArrayList<Employee>());
		List<Employee> emps1 = loc1.getEmployeeList();
		emps1.add(e1);
		emps1.add(e2);
		emps1.add(e5);
		
	
		Location loc2 = new Location(2, "Downtown Humane Society", "5555 Shepard Rd, St Paul, MN 55105", new ArrayList<Employee>());
		List<Employee> emps2 = loc2.getEmployeeList();
		emps2.add(e3);
		emps2.add(e4);
	
		
		em.persist(loc1);
		em.persist(loc2);
		
		
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		System.out.println("Information added to database");


	
	}
	
	
	

}
