package src.main;

import java.math.BigInteger;
import java.util.Vector;

import src.lib.*;

public class Main {

/*	private static BigInteger dois = new BigInteger("2");
	private static BigInteger um = new BigInteger("1");
	private static BigInteger zero = new BigInteger("0");
*/	
	public static void main(String[] args) {

		
//	Prime p = new Prime(1281212121);
//	System.out.println(p.getValue());
	
//	Prime p2 = new Prime(1282121212);
//	System.out.println(p2.getValue());
			
//	RSA a = new RSA();
//	System.out.println("\n");
	
//	Vector<BigInteger> result= a.gcd(new BigInteger("120"), new BigInteger("23"));

	
	PseudoRandomGenerator generator = new PseudoRandomGenerator("1234567890");
	
	for(int i=0; i<10; i++)
		generator.generate();
		
	}

}
