package com.mysher.platform;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	/**
	 * MD5 text encode
	 * 
	 * @param text
	 * @throws NoSuchAlgorithmException
	 */
	public static String MD5(String text) {
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes());
			byte b[] = md.digest();
	
			int i;
			StringBuffer buf = new StringBuffer("");
			
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				
				buf.append(Integer.toHexString(i));
			}
	
			return buf.toString();
		}
		catch (Exception e) {
			return text;
		}
	}

	public static String MD5MD5(String text){
		return MD5(MD5(text));
	}
	
	public static void main(String agrs[]) throws NoSuchAlgorithmException {
		System.out.println(MD5("2"));
	}
}