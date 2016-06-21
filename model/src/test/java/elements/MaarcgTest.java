package elements;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MaarcgTest {
	
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
	public void getTYPE() { 
		Maarcg Maarcg = new Maarcg();
		Maarcg.setTYPE(8);
	    Assert.assertTrue(Maarcg.getTYPE() == 8);
	}
	
	@Test
	public void setType() { 
		Maarcg Maarcg = new Maarcg();
		Maarcg.setTYPE(8);
	    Assert.assertTrue(Maarcg.getTYPE() == 8);
	}
}



