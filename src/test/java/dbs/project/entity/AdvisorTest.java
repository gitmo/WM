package dbs.project.entity;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AdvisorTest {

	Advisor advisor;
	@Before
	public void setUp() throws Exception {
	advisor = new Advisor();
	}

	@After
	public void tearDown() throws Exception {
	advisor = null;
	}

	@Test
	public void testAdvisor() {
		assertNotNull(advisor);
	}

	@Test
	public void testAdvisorStringStringDateIntInt() {
		advisor = new Advisor("firstname", "lastname", new Date(100), 12, 23, "Trainer");
		assertNotNull(advisor);
		assertEquals("firstname",advisor.getFirstname());
		assertEquals("lastname",advisor.getLastname());
		assertEquals(new Date(100),advisor.getBirthday());
		assertEquals((Integer) 12,advisor.getHeight());
		assertEquals((Integer) 23, advisor.getWeight());
	}

	@Test
	public void testTask() {
		advisor.setTask("task");
		assertEquals("task", advisor.getTask());
	}

}
