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

import com.humane.society.dao.EmployeeDao;
import com.humane.society.entity.Employee;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Parameterized.class)
public class EmployeeDaoTest {
	static EmployeeDao eDao;
	
	int empId;
	Employee empExpected;
	
	public EmployeeDaoTest(int empId, Employee empExpected) {
		this.empId = empId;
		this.empExpected = empExpected;
	}
	
	@Parameterized.Parameters
	public static Collection params() {
		return Arrays.asList(new Object[][] {
			{1, new Employee(1, "Abby", "Aka", 100000.00, "Manager", 1, "kylie1")},
			{2, new Employee(2, "Shelby", "Sleeker", 90000.00, "HR Consultant", 2, "kylie2")},
			{3, new Employee(3, "Kate", "Kuman", 70000.00, "Adoption Assistant", 2, "kylie3")},
			{4, new Employee(4, "Tay", "Tashton", 70000.00, "Adoption Assistant", 2, "kylie4")},
			{5, new Employee(5, "Caleb", "Colcutt", 100000.00, "Resident Vet", 1, "kylie5")},
			
			
		});
	}

	@BeforeClass
	public static void setUp() throws Exception {
		eDao = new EmployeeDao();
	}
	
	@Test
	public void testBAddEmp() {
		System.out.println("testAddEmp");
		boolean actual = eDao.addEmp(this.empExpected);
		assertTrue(actual);
	}
	
	@Test
	public void testCGetEmp() {
		System.out.println("testGetEmp");
		Employee actual = eDao.getEmp(this.empId);
		assertEquals(this.empExpected, actual);
	}
	
	@Test
	public void testDUpdateEmp() {
		System.out.println("testUpdateEmp");
		assertTrue(eDao.updateEmp(this.empExpected));
	}
	
	@Test
	public void testARemoveEmp() {
		System.out.println("testRemoveEmp");
		boolean actual = eDao.removeEmp(this.empId);
		assertEquals(true, actual);
	}

}