package dbs.project.entity;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CountryTest {
	
	Country c;
	@Before
	public void setUp() throws Exception {
		c = new Country();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCountry() {
		assertEquals(c.getClass(), Country.class);
	}

	@Test
	public void testCountryString() {
		c = new Country("Country");
		assertEquals("Country", c.getName());
	}

	@Test
	public void testName() {
		c.setName("Country");
		assertEquals(c.getName(), "Country");
	}

}
