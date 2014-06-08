package main;

import java.math.BigInteger;
import java.util.Vector;

import lib.Prime;
import lib.RSA;
public class Main {

	private static BigInteger dois = new BigInteger("2");
	private static BigInteger um = new BigInteger("1");
	private static BigInteger zero = new BigInteger("0");
	
	public static void main(String[] args) {

		
//	Prime p = new Prime(1281212121);
//	System.out.println(p.getValue());
	
//	Prime p2 = new Prime(1282121212);
//	System.out.println(p2.getValue());
			
	RSA a = new RSA();
	System.out.println("\n");
	
	Vector<BigInteger> result= a.gcd(new BigInteger("1008"), new BigInteger("71"));

//	System.out.println(result.get(0)+" "+result.get(1)+" "+result.get(2));
	
	
	for(int i=0;i<50;i++){
		
		System.out.println( (6*i)+1);
		System.out.println((6*i)-1);
		
	}
	
		
	}

}
