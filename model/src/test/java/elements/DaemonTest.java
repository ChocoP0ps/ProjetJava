package elements;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class DaemonTest {
	

	
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
	public void getPosX(){
    Daemon Dd = new Daemon();
    Dd.setPosX(2);
    Assert.assertTrue(Dd.getPosX() == 2);
	}
	
	@Test
	public void setPosX(){
	    Daemon Dd = new Daemon();
	    Dd.setPosX(2);
	    Assert.assertTrue(Dd.getPosX() == 2);
		}
	
	@Test
	public void getPosy(){
    Daemon Dd = new Daemon();
    Dd.setPosX(2);
    Assert.assertTrue(Dd.getPosX() == 2);
	}
	
	@Test
	public void setPosY(){
	    Daemon Dd = new Daemon();
	    Dd.setPosX(2);
	    Assert.assertTrue(Dd.getPosX() == 2);
		}
	
	@Test
	public void getPENETRABLE() { 
		Daemon Dd = new Daemon();
	    Dd.setPENETRABLE(true);
	    Assert.assertTrue(Dd.getPENETRABLE() == true);
	}

	@Test
	public void setPENETRABLE(boolean penetrable) { 
		Daemon Dd = new Daemon();
	    Dd.setPENETRABLE(true);
	    Assert.assertTrue(Dd.getPENETRABLE() == true);
	}
}




	