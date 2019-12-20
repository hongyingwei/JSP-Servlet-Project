package com.itcast.store.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
	/**
	 * 浣跨敤md5鐨勭畻娉曡繘琛屽姞瀵�
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("娌℃湁md5杩欎釜绠楁硶锛�");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);// 16杩涘埗鏁板瓧
		// 濡傛灉鐢熸垚鏁板瓧鏈弧32浣嶏紝闇�瑕佸墠闈㈣ˉ0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}
}
