package src.main;

import java.math.BigInteger;

import src.source.Generator;
import src.source.MathUtil;
import src.source.RSA;
import src.source.ReadRandom;

public class Main {

	public static void main(String[] args) {
		RSA rsa = new RSA(512);
	
		System.out.println("-----------------------------");
	
		BigInteger message = new BigInteger("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012" +
				"3456789012345678901234567890123456789012345678912345678901234567890123456789012345678901234567890123456789012345678901234567890" +
				"1234567890123456789012345678901234567890123456789012345678901234567890123456789");
	    BigInteger encrypt = rsa.encrypt(message);
	    BigInteger decrypt = rsa.decrypt(encrypt);
	    
	    System.out.println("message   = " + message);
	    System.out.println("encrpyted = " + encrypt);
	    System.out.println("decrypted = " + decrypt);

		System.out.println("-----------------------------");
	}
	
}
