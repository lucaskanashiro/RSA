package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

import lib.Prime;
import lib.PseudoRandomGenerator;
import lib.RSA;

public class Main {

	public static void main(String[] args) {

		
		
	Prime p = new Prime("2000012345678904567890982839283928392832983928392832");

	RSA rsa = new RSA();

	rsa.generate();
	
	String text1 = "Yellow and Black Border Collies";
	
	System.out.println("PlainText: "+text1);
	
	BigInteger plainText = new BigInteger(text1.getBytes());
	
	BigInteger cyphetText= rsa.encrypt(plainText);	
	System.out.println("CypherText: "+cyphetText);
	
	plainText = rsa.decrypt(cyphetText);
	
	String text2 = new String(plainText.toByteArray());
	
	System.out.println("PlainText: "+ text2);
	
	
//	Prime prime = new Prime();
//	System.out.println(prime.modPow(new BigInteger("123456789"), new BigInteger("123456789"), new BigInteger("31")));
	
	
	
	
	}
}
