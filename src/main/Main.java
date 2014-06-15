package src.main;

import java.math.BigInteger;

import src.source.Generator;
import src.source.MathUtil;
import src.source.RSA;
import src.source.ReadRandom;

public class Main {

	public static void main(String[] args) {
		
		//Generator g = new Generator();
		//System.out.println(g.generatePrimeNumber(100));
		/*
		P = 5
		Q =37
		PHI = 144
		Public KEY=53
		Private Key= -19
		MOD= 185
		BEFORE ENCRYPTION=7881690093058291058
		AFTER ENCRYPTION=43
		CYPHER = +
		BEFORE DECRYPTION=43
		AFTER DECRYPTION=142
		DECYPHER = 
		*/
		/*MathUtil p = new MathUtil();
		
		BigInteger e= new BigInteger("53");
		BigInteger d;
		BigInteger mod = new BigInteger("144");
		
		
		System.out.println(p.inverseMod(e, mod));
		System.out.println(e.modInverse(mod));*/
		
		
		
		
		
		
		RSA rsa = new RSA(512);
	
		System.out.println("-----------------------------");
				
		/*String text1 = "123456";
		
		System.out.println("Text: "+text1);
		BigInteger text = new BigInteger(text1.getBytes());
		
		BigInteger cyphetText= rsa.encrypt(text);	
		
		System.out.println("CypherText: "+cyphetText);
		
		BigInteger plainText = rsa.decrypt(cyphetText);
		
		//String text2 = plainText.toString();
		
		System.out.println("PlainText: "+ plainText);*/
		
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
