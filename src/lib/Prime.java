package lib;

import java.math.BigInteger;
import java.util.ArrayList;

public class Prime {
<<<<<<< HEAD

	BigInteger zero = new BigInteger("0");
	BigInteger um = new BigInteger("1");
	BigInteger dois = new BigInteger("2");
	BigInteger tres = new BigInteger("3");
    BigInteger cinco = new BigInteger("5");
	BigInteger dez = new BigInteger("10");

	BigInteger value;
	
	public Prime(int bits) {
		//this.value = generatePrime(bits);	
	}
	
	private BigInteger generatePrime(int bits){
		this.value = new BigInteger("0");
		BigInteger result= new BigInteger(""+bits);
		
		while(true){
			this.value= result.multiply(new BigInteger("6"));
			this.value = this.value.subtract(new BigInteger("1"));
			System.out.println("V1 "+this.value);
			if(Millen_Rabin(this.value))
				return this.value;
			this.value = this.value.add(new BigInteger("2"));
			System.out.println("V2 "+this.value);
			if(Millen_Rabin(this.value))
				return this.value;
		}
	}
		
	
	public boolean Millen_Rabin (BigInteger n){
	     if(n.equals(dois) || n.equals(tres) || n.equals(cinco) ){
	    	 return true;	    	 
	     }
			
	     if(n.remainder(dois).equals(zero) || n.remainder(tres).equals(zero) || n.remainder(cinco).equals(zero) ){
	    	 return false;
	     }
	     
	     if(n.subtract(new BigInteger("25")).signum() == -1 ){
	    	 return true;	    	 
	     }
	      
	     ArrayList<BigInteger> a = new ArrayList<BigInteger>();  
	     a.add(dois);
	     a.add(tres);
	     a.add(cinco);
	     a.add(new BigInteger("7"));
	     a.add(new BigInteger("11"));
	     a.add(new BigInteger("13"));
	     a.add(new BigInteger("17"));
	     a.add(new BigInteger("19"));
	     
	     BigInteger b = n.subtract(um);
	     BigInteger d,x;
	     d = b;
	     
	     for(;b.remainder(dois).equals(zero);b = b.divide(dois));
	     
	     for(int i=0; i<a.size();i++){
	    	 x = modPow( a.get(i)  , b, n);
  
	    	 if(x.equals(um) || x.equals(n.subtract(um))){
	    		 continue;	    		 
	    	 }
	     
	    	 for(boolean t = true; (t==true)  && ( d.subtract(n.subtract(um)).signum() == -1)  ;d = d.multiply(dois))
	    	 {
		    	 System.out.println("3");
	    		 x = modPow(x, x, n);
	    		 if(x.equals(n.subtract(um))){
	    			 t = false;
	    		 }
	    		 if(t) return false;
	    	 }
	     }
	     return true;
	}
	
	public BigInteger modProd(BigInteger a,BigInteger b , BigInteger n){

		if(b.equals(zero)){
			return zero;			
		}
		
		if(b.equals(um)){
			return a.remainder(n);
		}

		BigInteger b1,n1,b2;
	    n1 = zero;
		b1 = b.remainder(dez);
		b2 = b.subtract(b1).divide(new BigInteger("10"));
		b1 = modProd(a, b2, n);
		b1.multiply(dez);
		b1.add(b2);
		b1.multiply(a);
		
		return b1.remainder(n);
	}
	
	
	public BigInteger modPow(BigInteger a,BigInteger b , BigInteger n){
		if(b.equals(zero)){
			return um;			
		}
		
		if(b.equals(um)){
			return a.remainder(n);
		}
		
		if(b.remainder(dois).equals(zero)){
			BigInteger c = modPow(a, b.divide(dois), n);
			return modProd(c, c, n);		
		}
		
		return modProd(a, modPow(a, b.subtract(um), n), n);		
	}
		
	public BigInteger getValue() {
		return this.value;
=======
	
	public BigInteger generatePrime(String stringSeed){
		
		PseudoRandomGenerator generator = new PseudoRandomGenerator(stringSeed);
		
		BigInteger number = generator.generate();
		
		if(number.isProbablePrime(1))
			return number;
		else
			return generatePrime(stringSeed + "1");
>>>>>>> af7d21fce427b9789b2ea5f805cc3e515a212736
	}
		
}
