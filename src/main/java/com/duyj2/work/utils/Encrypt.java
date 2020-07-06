package com.duyj2.work.utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class Encrypt {

	private final static String password="1234567";

	private static String byteToString(byte[] resultBytes) {
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < resultBytes.length; i++) {
			int val = resultBytes[i] & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	public static String MD5_Encrypt(String info) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
		}

		byte[] srcBytes = info.getBytes();
		md5.update(srcBytes);
		byte[] resultBytes = md5.digest();
		return byteToString(resultBytes);
	}

	// 对字符串加密
	public static byte[] AES_Encryt(String content) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | 
				UnsupportedEncodingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException e) {
		}
		return null;
	}

	// 对字符串解密
	public static byte[] AES_Decrypt(byte[] content) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | 
				InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
		}
		return null;
	}
	
	public static void main(String[] args) {
		
		String message = "2016-8-18, so far so good.";
		System.out.println("加密前：" + message);

		String md5 = MD5_Encrypt(message);
		System.out.println("MD5加密后：" + md5);
        System.out.println("MD5加密后length：" + md5.length());

        System.out.println(MD5_Encrypt("123"));
        System.out.println(MD5_Encrypt("123asda"));
        System.out.println(MD5_Encrypt("123 awd///////asda/sd/asd/a/dsa/d"));
        System.out.println(MD5_Encrypt("123 adddddddddd"));
        System.out.println(MD5_Encrypt("123" + new File("").getAbsolutePath()));


//		// AES加密
//		byte[] encryptResult = AES_Encryt(message);
//		System.out.println("AES加密后：" + byteToString(encryptResult));
//
//		// AES解密
//		byte[] decryptResult = AES_Decrypt(encryptResult);
//		System.out.println("AES解密后：" + byteToString(decryptResult));
	}

}
