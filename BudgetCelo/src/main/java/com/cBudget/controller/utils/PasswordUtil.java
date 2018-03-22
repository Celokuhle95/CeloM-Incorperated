package com.cBudget.controller.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PasswordUtil {
	
	private static final String SALT = "XCD-POQT-CELO";
	
	private PasswordUtil() {
	}

	public static String generateHash(String input) {
		input = salted(input);
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		return hash.toString();
	}
	
	public static boolean isAuthenticated() {
		HttpSession session = getCurrentSession();
		if(session.getAttribute("currentUser") == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public static HttpSession getCurrentSession() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request =(HttpServletRequest) externalContext.getRequest();
		return (HttpSession)request.getSession(false);
	}

	private static String salted(String toSalt) {
		return SALT + toSalt;
	}

}
