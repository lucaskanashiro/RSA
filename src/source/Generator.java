package src.source;

import java.math.BigInteger;
import java.util.ArrayList;

public class Generator {
	
	private MathUtil util;
	private ReadRandom reader;
	private final int[] array = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41};
	
	public Generator(){
		this.util = new MathUtil();
		this.reader = new ReadRandom();
	}
	
	/*public boolean testMillerRabin(BigInteger number) {
		if(number.equals(Constant.two) || number.equals(Constant.three) || number.equals(Constant.five) )
	    	 return true;	    	 
			
	     if(number.remainder(Constant.two).equals(Constant.zero) || number.remainder(Constant.three).equals(Constant.zero) || 
	    		 number.remainder(Constant.five).equals(Constant.zero) )
	    	 return false;
	     
	     if(number.subtract(new BigInteger("25")).signum() == -1 )
	    	 return true;	    	 
	      
	     ArrayList<BigInteger> a = new ArrayList<BigInteger>();  
	     a.add(Constant.two); a.add(Constant.three); a.add(Constant.five); a.add(Constant.seven); a.add(Constant.eleven);
	     a.add(Constant.thirteen); a.add(Constant.seventeen); a.add(Constant.nineteen);
	     
	     BigInteger b = number.subtract(Constant.one);
	     BigInteger d,x;
	     d = b;
	     
	     for(;b.remainder(Constant.two).equals(Constant.zero);b = b.divide(Constant.two));
	     
	     for(int i=0; i<a.size();i++){
	    	 x = this.util.modPow(a.get(i), b, number);
 
	    	 if(x.equals(Constant.one) || x.equals(number.subtract(Constant.one)))
	    		 continue;	    		 
	     
	    	 for(boolean t=true; (t==true) && (d.subtract(number.subtract(Constant.one)).signum() == -1);
	    			 d = d.multiply(Constant.two)){
	    		 x = this.util.modPow(x, x, number);
	    		 if(x.equals(number.subtract(Constant.one)))
	    			 t = false;
	    		 
	    		 if(t) return false;
	    	 }
	     }
	     return true;
	}
	
	public BigInteger generatePrimeNumber(int numberOfDigits) {
		BigInteger value = Constant.zero;
		String random = this.reader.getSeed(numberOfDigits);
		BigInteger result= this.pseudoRandomGenerator(random, numberOfDigits);
		
		while(true){
			value= result.multiply(Constant.six);
			value = value.subtract(Constant.one);
			
			if(this.testMillerRabin(value))
				return value;
			
			value = value.add(Constant.two);
			
			if(this.testMillerRabin(value))
				return value;
		}
	
	}	
	
	public BigInteger getPrime(int seed_length) {
        BigInteger p;

        while (true) {
            p = new BigInteger(this.reader.getSeed(seed_length));
            
            if (p.mod(Constant.four).equals(Constant.three))
                break;
        }

        return p;
    }
	
	public BigInteger getN(int seed_length, BigInteger p, BigInteger q) {
        while (p.equals(q)) 
            q = getPrime(seed_length);
        
        return p.multiply(q);
    }
	
	private BigInteger pseudoRandomGenerator(String stringSeed, int seed_length){
		BigInteger seed = new BigInteger(stringSeed);

		BigInteger p = new BigInteger("12345678912345678912345678912345678912345678912345678901234567890123456789123456789012345678901234567890123943");
		BigInteger q = new BigInteger("12345678912345678912345678912345678912345678912345678901234567890123456789123456789012345678901234567890123731");

		/*BigInteger p = getPrime(seed_length);
        BigInteger q = getPrime(seed_length);
		
        while (seed.mod(p).equals(Constant.zero) || seed.mod(q).equals(Constant.zero)) {
            p = getPrime(seed_length);
            q = getPrime(seed_length);
        }
        
		BigInteger m = this.getN(seed_length, p, q);
		BigInteger m = p.multiply(q);
		
		seed = (seed.multiply(seed)).mod(m);

		return seed;
	}*/
	
	public BigInteger getPrime(int seed_length) {
        BigInteger p;

        while (true) {
            p = new BigInteger(this.reader.getSeed(seed_length));
            if (p.mod(Constant.four).equals(Constant.three)) {
                break;
            }
        }

        return p;
    }
	
	public BigInteger getN(int seed_length, BigInteger p, BigInteger q) {
        while (p.equals(q)) 
            q = this.getPrime(seed_length);
        
        return p.multiply(q);
    }
	
	public BigInteger getRandomNumber(int seed_length) {
        BigInteger randomNumber;
        BigInteger seed = new BigInteger(this.reader.getSeed(seed_length));
        BigInteger p = this.getPrime(seed_length);
        BigInteger q = this.getPrime(seed_length);

        while (seed.mod(p).equals(Constant.zero) || seed.mod(p).equals(Constant.zero)) {
            p = this.getPrime(seed_length);
            q = this.getPrime(seed_length);
        }
        
        BigInteger N = this.getN(seed_length, p, q); 

        randomNumber = (seed.multiply(seed)).mod(N);
        
        return randomNumber;
    }
	
	public BigInteger getRandomPrime(int N_LENGTH, int certainty){
        BigInteger randomPrime;
      
        while(true){
            randomPrime = this.getRandomNumber(N_LENGTH);
            
            if (this.isProbablyPrime(randomPrime, certainty))
                break;
        }
        
        return randomPrime;
    }
	
	public boolean testingSecondLoop(BigInteger n, BigInteger a, int p, BigInteger d) {
        for (int i = 0; i < p; i++) {
            BigInteger exp = Constant.two.pow(i);
            exp = exp.multiply(d);
            BigInteger result = a.modPow(exp, n);
            
            if (result.equals(n.subtract(Constant.one)) || result.equals(Constant.one)) {
                return true;
            }
        }
        return false;
    }
	
	public boolean isProbablyPrime(BigInteger number, int certainty) { 
        BigInteger odd_factor = number.subtract(Constant.one);                          
        
        int p = 0;
        
        while (odd_factor.mod(Constant.two).equals(Constant.zero)) {
            p++;
            odd_factor = odd_factor.divide(Constant.two);
        }
        
        // First loop.
        for (int i = 0; i < certainty; i++) {      
            BigInteger a = BigInteger.valueOf(array[i]);  
            
            boolean answer = this.testingSecondLoop(number, a, p, odd_factor);
            /*boolean answer;
            
            for (int j = 0; j < p; j++) {
                BigInteger exp = Constant.two.pow(j);
                exp = exp.multiply(odd_factor);
                BigInteger result = a.modPow(exp, number);
                
                if (result.equals(number.subtract(Constant.one)) || result.equals(Constant.one)) {
                    answer = true;
                }
            }
            answer = false;*/
            
            if (!answer) {
                return false;
            }
        }
        return true;
    }
}
