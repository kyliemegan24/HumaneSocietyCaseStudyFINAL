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

import com.humane.society.dao.LocationDao;
import com.humane.society.entity.Location;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Parameterized.class)
public class LocationDaoTest {
	static LocationDao lDao;
	
	int locId;
	Location locExpected;
	
	public LocationDaoTest(int locId, Location locExpected) {
		this.locId = locId;
		this.locExpected = locExpected;
	}
	
	@Parameterized.Parameters
	public static Collection params() {
		return Arrays.asList(new Object[][] {
			{1, new Location(1, "Highland Park Humane Society", "1111 Marc Ave, Saint Paul MN, 55102", null)},
			{2, new Location(2, "Downtown Humane Society", "5555 Shepard Rd, St Paul, MN 55105", null)},
			
		});
	}

	@BeforeClass
	public static void setUp() throws Exception {
		lDao = new LocationDao();
	}

	@Test
	public void testBAddLoc() {
		System.out.println("testAddLoc");
		boolean actual = lDao.addLoc(this.locExpected);
		assertTrue(actual);
	}
	
	@Test
	public void testCGetLoc() {
		System.out.println("testGetLoc");
		Location actual = lDao.getLoc(this.locId);
		assertEquals(this.locExpected, actual);
	}
	
	@Test
	public void testDUpdateLoc() {
		System.out.println("testUpdateLoc");
		assertTrue(lDao.updateLoc(this.locExpected));
	}
	
	@Test
	public void testARemoveLoc() {
		System.out.println("testRemoveLoc");
		boolean actual = lDao.removeLoc(this.locId);
		assertEquals(true, actual);
	}

}