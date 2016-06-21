package elements;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PurseTest {
	
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
	public void isTAKEN() { 
		Purse Purse = new Purse();
	    Purse.setTAKEN(true);
	    Assert.assertTrue(Purse.isTAKEN() == true);
	}
	
	@Test
	public void setTAKEN() { 
		Purse Purse = new Purse();
	    Purse.setTAKEN(true);
	    Assert.assertTrue(Purse.isTAKEN() == true);
	}
}



