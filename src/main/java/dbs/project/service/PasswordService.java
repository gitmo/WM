package dbs.project.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public final class PasswordService {
	private static PasswordService instance;
	static {
		instance = new PasswordService();
	}

	private PasswordService() {}

	/**
	 * singleton access
	 * 
	 * @return
	 */
	public static synchronized PasswordService getInstance() {
		return instance;
	}

	/**
	 * Encrypts the given plain text password with the SHA hashing algorithm and
	 * encodes the resulting UTF8 String with BASE64 in order for it to be
	 * safely stored in the data base.
	 * 
	 * @param plaintext
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public synchronized String encrypt(String plaintext)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = null;
		md = MessageDigest.getInstance("SHA");
		md.update(plaintext.getBytes("UTF-8"));
		byte raw[] = md.digest();
		String hash = (new BASE64Encoder()).encode(raw);
		return hash; // step 6
	}
}