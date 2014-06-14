package source;

import java.math.BigInteger;
import java.util.ArrayList;

public class Generator {
	
	private MathUtil util;
	
	public Generator(){
		this.util = new MathUtil();
	}
	
	
	public BigInteger generatePseudoRandomNumber(BigInteger seed) {
		BigInteger p = new BigInteger("23895371");
		BigInteger q = new BigInteger("23895379");
		
		BigInteger m = p.multiply(q);
		
		seed = seed.multiply(seed);
		seed = seed.mod(m);
		
		return seed;
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
	
	
	
	public BigInteger generatePrimeNumber(BigInteger seed) {
		BigInteger value = Constant.zero;
		BigInteger result= this.generatePseudoRandomNumber(seed);
		
			
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
}
