package dbs.project.entity;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CountryTest {
	
	Country country;
	@Before
	public void setUp() throws Exception {
		country = new Country();
	}

	@After
	public void tearDown() throws Exception {
		country = null;
	}

	@Test
	public void testCountry() {
		assertNotNull(country);
	}

	@Test
	public void testCountryString() {
		country = new Country("Country");
		assertEquals("Country", country.getName());
	}

	@Test
	public void testName() {
		country.setName("Country");
		assertEquals("Country", country.getName());
	}

}
