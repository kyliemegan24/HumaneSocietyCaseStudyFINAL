package com.humane.society.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;


import com.humane.society.dao.DogDao;

import com.humane.society.entity.Dog;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Parameterized.class)
public class DogDaoTest {
	static DogDao dDao;
	
	int dId;
	Dog dogExpected;
	
	public DogDaoTest(int dId, Dog dogExpected) {
		this.dId = dId;
		this.dogExpected = dogExpected;
	}
	
	@Parameterized.Parameters
	public static Collection params() {
		return Arrays.asList(new Object[][] {
			{22, new Dog(22, "Lizzie", 10, "orange tabby", true, 2, 1)},
			{33, new Dog(33, "Junior", 10, "orange tabby", true, 2, 1)},
			{44, new Dog(44, "Lemon", 10, "orange tabby", true, 2, 1)},
			{55, new Dog(55, "Rogue", 10, "orange tabby", true, 2, 1)},
			{66, new Dog(66, "Edgar", 10, "orange tabby", true, 2, 1)},
		});
	}

	@BeforeClass
	public static void setUp() throws Exception {
		dDao = new DogDao();
	}

	@Test
	public void testBAddDog() {
		System.out.println("testAddDog");
		boolean actual = dDao.addDog(this.dogExpected);
		assertTrue(actual);
	}
	
	@Test
	public void testCGetDog() {
		System.out.println("testGetDog");
		Dog actual = dDao.getDog(this.dId);
		assertEquals(this.dogExpected, actual);
	}
	
	@Test
	public void testDUpdateDog() {
		System.out.println("testUpdateDog");
		assertTrue(dDao.updateDog(this.dogExpected));
	}
	
	@Test
	public void testARemoveDog() {
		System.out.println("testRemoveDog");
		boolean actual = dDao.removeDog(this.dId);
		assertEquals(true, actual);
	}

}
