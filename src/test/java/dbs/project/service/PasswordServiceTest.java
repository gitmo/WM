package dbs.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PasswordServiceTest {

	private PasswordService service;

	@Before
	public void setUp() throws Exception {
		service = PasswordService.getInstance();
	}

	@After
	public void tearDown(){
		service = null;
	}
	
	@Test
	public void testGetInstance() {
		assertNotNull(service);
	}

	@Test
	public void testEncrypt() {

		final String plaintextPW = "plaintext";
		String hash = null;

		// Encrypt a password and check if the same plain text creates same hash
		try {
			hash = service.encrypt(plaintextPW);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			fail();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			assertEquals(hash, service.encrypt(plaintextPW));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			fail();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			fail();
		}
	}

}
