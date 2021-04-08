package com.humane.society.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CatDaoTest.class, DogDaoTest.class, EmployeeDaoTest.class, LocationDaoTest.class })
public class AllTests {

}
