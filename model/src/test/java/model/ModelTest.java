
package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class ModelTest {
	 
	private Model model;
	private int level;
	
	 
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
		this.model = new Model(1);
		this.level = 1;
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getTYPE() { 
		
		Assert.assertEquals("bhhhhhhhhhhhhhhhhhhbvnnnnnnnnsnnnnnnnnnvvnnbhbhhhhhhhhbhbnnvvnnnnvnnnnnnnnvnnnnvbhhbnvnnnnnnnnvnbhhbdnnbnvnnnnnnnnvnbncvbbnnnvnnnnnnnnvnnnbbbbhhhbnnnnnnnnbhhhbbvnmnnvnnnnnnnnvnnmnvbhhhhbhhhhhhhhbhhhhbvnnnmnnnnnnnnnnmnnnvbhhhhhhhhhhhhhhhhhhb", this.model.getMap());
	}
	
	@Test
	public void testGetMapString() {
		Assert.assertEquals("bhhhhhhhhhhhhhhhhhhbvnnnnnnnnsnnnnnnnnnvvnnbhbhhhhhhhhbhbnnvvnnnnvnnnnnnnnvnnnnvbhhbnvnnnnnnnnvnbhhbdnnbnvnnnnnnnnvnbncvbbnnnvnnnnnnnnvnnnbbbbhhhbnnnnnnnnbhhhbbvnmnnvnnnnnnnnvnnmnvbhhhhbhhhhhhhhbhhhhbvnnnmnnnnnnnnnnmnnnvbhhhhhhhhhhhhhhhhhhb", this.model.getMap());
	}
	
	@Test
	public void setNextLevel(){
		Assert.assertEquals(2,this.model.setNextLevel());
		}
	
}

