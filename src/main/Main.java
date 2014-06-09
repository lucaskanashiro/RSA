package main;

import java.math.BigInteger;
import lib.Prime;
import lib.PseudoRandomGenerator;

public class Main {

	public static void main(String[] args) {

		Prime p = new Prime(2);
		System.out.println(p.Millen_Rabin(new BigInteger("104729")));
	
	
	}
}
