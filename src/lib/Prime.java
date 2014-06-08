package src.lib;

import java.math.BigInteger;

public class Prime {
	
	public BigInteger generatePrime(String stringSeed){
		
		PseudoRandomGenerator generator = new PseudoRandomGenerator(stringSeed);
		
		BigInteger number = generator.generate();
		
		if(number.isProbablePrime(1))
			return number;
		else
			return generatePrime(stringSeed + "1");
	}
		
}
