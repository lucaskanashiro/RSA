package src.source;

import java.math.BigInteger;
import java.util.ArrayList;

public class Generator {
	
	private MathUtil util;
	private ReadRandom reader;
	
	public Generator(){
		this.util = new MathUtil();
		this.reader = new ReadRandom();
	}
	
	public boolean testMillerRabin(BigInteger number) {
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
		String random = this.reader.Random(numberOfDigits);
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
            p = new BigInteger(this.reader.Random(seed_length));
            
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
        
		BigInteger m = this.getN(seed_length, p, q);*/
		BigInteger m = p.multiply(q);
		
		seed = (seed.multiply(seed)).mod(m);

		return seed;
	}
}
