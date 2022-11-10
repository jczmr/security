package com.jc.security.aes;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.io.FileUtils;

public class AES256 {

	private static final String SECRET_KEY = "the_strongest_secret_key";
	private static final String SALT = "T0azvkYywVCO6qqGSm+mm5UQYzWLlCII+Z9TuOYmpnI=";
	private static final byte[] IV = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	public static byte[] encrypt(String stringToEncrypt) {

		try {

			IvParameterSpec ivspec = new IvParameterSpec(IV);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(StandardCharsets.UTF_8), 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
			return cipher.doFinal(stringToEncrypt.getBytes(StandardCharsets.UTF_8));

		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
			return null;
		}
	}

	public static byte[] decrypt(byte[] arrayBytesToDecrypt) {

		try {
			IvParameterSpec ivspec = new IvParameterSpec(IV);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(StandardCharsets.UTF_8), 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
			return cipher.doFinal(arrayBytesToDecrypt);

		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
			return null;
		}
	}
	
	public static byte[] readFileToByteArray(File file) {

		try {
			return FileUtils.readFileToByteArray(file);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void writeByteArrayToFile(File file, byte[] bytes, boolean append) {

		try {
			FileUtils.writeByteArrayToFile(file, bytes, append);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}