package dbs.project.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import dbs.project.entity.permission.Actor;

public class ActorService {

	public static String setPasswordEncrypted(String plaintext) {
		String base64Hash = null;
		try {
			base64Hash = PasswordService.getInstance().encrypt(plaintext);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return base64Hash;
	}
}
