package com.jc.security;

import java.io.File;
import java.nio.charset.StandardCharsets;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jc.security.aes.AES256;

@SpringBootApplication
public class SecurityApplication {

	private static final File f = new File("src/main/resources/output.txt");

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
		
		// File text to be encrypted
		File fileConn = new File("src/main/resources/conn.txt");
		String stringfileConn = new String(AES256.readFileToByteArray(fileConn), StandardCharsets.UTF_8);
		// Encrypt
		byte[] encryptedText = AES256.encrypt(stringfileConn);
		AES256.writeByteArrayToFile(f, encryptedText, false);
		// Decrypt
		byte[] decryptedText = AES256.decrypt(AES256.readFileToByteArray(f));
		System.out.println(new String(decryptedText, StandardCharsets.UTF_8));
		
	}
}