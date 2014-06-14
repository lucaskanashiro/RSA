package main;

import java.math.BigInteger;

import source.ReadRandom;

public class Main {

	public static void main(String[] args) {
		ReadRandom rr = new ReadRandom();
		
		System.out.println(rr.Random());
		
		
		/*
		RSA rsa = new RSA();

		rsa.generate();
	
		System.out.println("----------------------");
				
		String text1 = "Yellow and Black Border Collies";
		
		System.out.println("PlainText: "+text1);
		BigInteger plainText = new BigInteger(text1.getBytes());
		
		BigInteger cyphetText= rsa.encrypt(plainText);	
		
		System.out.println("CypherText: "+cyphetText);
		
		plainText = rsa.decrypt(cyphetText);
		
		String text2 = new String(plainText.toByteArray());
		
		System.out.println("PlainText: "+ text2);

		System.out.println("-----------------------------");*/
	}
	
	
	
	
	
}
