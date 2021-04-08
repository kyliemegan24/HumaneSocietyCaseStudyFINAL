package com.humane.society.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import com.humane.society.dao.CatDao;
import com.humane.society.entity.Cat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Parameterized.class)
public class CatDaoTest {
	static CatDao cDao;
	
	int cId;
	Cat catExpected;
	
	public CatDaoTest(int cId, Cat catExpected) {
		this.cId = cId;
		this.catExpected = catExpected;
	}
	
	@Parameterized.Parameters
	public static Collection params() {
		return Arrays.asList(new Object[][] {
			{11, new Cat(11, "Hila", 10, "orange tabby", true, 2, 1)},
			{12, new Cat(12, "Norah", 10, "orange tabby", false, 2, 1)},
			{13, new Cat(13, "Wii-U", 10, "orange tabby", true, 2, 1)},
			{14, new Cat(14, "Mrs. Big", 10, "orange tabby", true, 2, 1)},
			{15, new Cat(15, "Jingo", 10, "orange tabby", false, 2, 1)},
			{16, new Cat(16, "Cola", 10, "orange tabby", true, 2, 1)},
			{17, new Cat(17, "Jingle", 10, "orange tabby", true, 2, 1)},
		});
	}

	@BeforeClass
	public static void setUp() throws Exception {
		cDao = new CatDao();
	}

	@Test
	public void testBAddCat() {
		System.out.println("testAddCat");
		boolean actual = cDao.addCat(this.catExpected);
		assertTrue(actual);
	}
	
	@Test
	public void testCGetCat() {
		System.out.println("testGetCat");
		Cat actual = cDao.getCat(this.cId);
		assertEquals(this.catExpected, actual);
	}
	
	@Test
	public void testDUpdateCat() {
		System.out.println("testUpdateCat");
		assertTrue(cDao.updateCat(this.catExpected));
	}
	
	@Test
	public void testARemoveCat() {
		System.out.println("testRemoveCat");
		boolean actual = cDao.removeCat(this.cId);
		assertEquals(true, actual);
	}

}