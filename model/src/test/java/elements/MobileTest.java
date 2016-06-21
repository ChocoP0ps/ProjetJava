package elements;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MobileTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
	
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void isAlive() { 
		Mobile Mobile = new Mobile();
	    Mobile.setAlive(true);
	    Assert.assertTrue(Mobile.isAlive() == true);
	}
	
	@Test
	public void setAlive() { 
		Mobile Mobile = new Mobile();
	    Mobile.setAlive(true);
	    Assert.assertTrue(Mobile.isAlive() == true);
	}
}