package main;

import java.math.BigInteger;

import source.Generator;
import source.MathUtil;
import source.RSA;
import source.ReadRandom;

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
		MathUtil p = new MathUtil();
		
		BigInteger e= new BigInteger("53");
		BigInteger d;
		BigInteger mod = new BigInteger("144");
		
		
		System.out.println(p.inverseMod(e, mod));
		System.out.println(e.modInverse(mod));
		
		
		
		
		
		/*
		RSA rsa = new RSA();

		rsa.generate();
	
		System.out.println("-----------------------------");
				
		String text1 = "1234567890";
		
		System.out.println("PlainText: "+text1);
		//BigInteger text = new BigInteger(text1.getBytes());
		
		BigInteger cyphetText= rsa.encrypt(text1);	
		
		System.out.println("CypherText: "+cyphetText);
		
		BigInteger plainText = rsa.decrypt(cyphetText);
		
		String text2 = plainText.toString();
		
		System.out.println("PlainText: "+ text2);

		System.out.println("-----------------------------");*/
	}
	
}
