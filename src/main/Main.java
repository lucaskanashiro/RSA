package main;

import java.math.BigInteger;
import lib.Prime;
import lib.PseudoRandomGenerator;

public class Main {

	public static void main(String[] args) {

		Prime p = new Prime(2);
		System.out.println(p.Millen_Rabin(new BigInteger("104729")));
	
<<<<<<< HEAD
	
=======
//	Vector<BigInteger> result= a.gcd(new BigInteger("120"), new BigInteger("23"));

		Prime prime = new Prime();
		//PseudoRandomGenerator generator = new PseudoRandomGenerator("1234567890");
		
		//for(int i=0; i<10; i++){
			//BigInteger number = generator.generate();
			//if(prime.FermatTest(number))
				//System.out.println(number);
		//}
		
		System.out.println(prime.generatePrime("987654321"));
		System.out.println(prime.generatePrime("123456789"));
>>>>>>> af7d21fce427b9789b2ea5f805cc3e515a212736
	}
}
