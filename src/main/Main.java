package src.main;

import java.math.BigInteger;

import src.source.Generator;
import src.source.RSA;
import src.source.ReadRandom;

public class Main {

	public static void main(String[] args) {
		RSA rsa = new RSA(128);
	
		System.out.println("-----------------------------");
				
		String text1 = "1234567890";
		
		System.out.println("PlainText: "+text1);
		//BigInteger text = new BigInteger(text1.getBytes());
		
		BigInteger cyphetText= rsa.encrypt(text1);	
		
		System.out.println("CypherText: "+cyphetText);
		
		BigInteger plainText = rsa.decrypt(cyphetText);
		
		String text2 = plainText.toString();
		
		System.out.println("PlainText: "+ text2);

		System.out.println("-----------------------------");
	}
	
	
	
	
	
}
