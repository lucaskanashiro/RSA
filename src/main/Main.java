package src.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import src.source.RSA;

public class Main {

	public static void main(String[] args) {
		RSA rsa = new RSA(64);
	
		System.out.println("-----------------------------");
	
		BigInteger message = new BigInteger("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012" +
				"34567890123123456789012345678902345678901234567890");
	    BigInteger encrypt = rsa.encrypt(message);
	    
	    try {
	          File file = new File("encryptedText.txt");
	          BufferedWriter output = new BufferedWriter(new FileWriter(file));
	          output.write("Encrypted text: \n\n" + encrypt.toString());
	          output.close();
	    } catch ( IOException e ) {
	           e.printStackTrace();
	    }
	    
	    BigInteger decrypt = rsa.decrypt(encrypt);
	    
	    try {
	          File file = new File("decryptedText.txt");
	          BufferedWriter output = new BufferedWriter(new FileWriter(file));
	          output.write("Decrypted text: \n\n" + decrypt.toString());
	          output.close();
	    } catch ( IOException e ) {
	           e.printStackTrace();
	    }
	    
	    System.out.println("message   = " + message);
	    System.out.println("encrpyted = " + encrypt);
	    System.out.println("decrypted = " + decrypt);

		System.out.println("-----------------------------");
	}
	
}
