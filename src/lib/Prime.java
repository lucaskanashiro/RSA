package src.lib;

import java.math.BigInteger;

public class Prime {
	BigInteger value;
	
	public Prime(int bits) {
		this.value = generatePrime(bits);	
	}
	
	private BigInteger generatePrime(int bits){
		this.value = new BigInteger("0");
		BigInteger result= new BigInteger(""+bits);
		
			
		while(true){
			this.value= result.multiply(new BigInteger("6"));
			
			this.value = this.value.subtract(new BigInteger("1"));
			System.out.println("V1 "+this.value);
			
			if(FermatTest())
				return this.value;
						
			this.value = this.value.add(new BigInteger("2"));
			System.out.println("V2 "+this.value);
			if(FermatTest())
				return this.value;
		}
	}
		
	
	public boolean FermatTest(){
		
		if(this.value.equals(new BigInteger("0")))
			return false;
		
		BigInteger base = new BigInteger("2");
		BigInteger m = this.value;
		
		if(this.value.equals(base))
			return true;
		
		m = m.subtract(new BigInteger("1"));
		while(!m.equals(new BigInteger("1")))
		{
			base = base.multiply(new BigInteger("2"));
			m = m.subtract(new BigInteger("1"));			
		}
		
		if( base.remainder(this.value).equals(new BigInteger("1")))
		{
			return true;
		}
		else{		
			return false;
		}
	}
	
	public BigInteger getValue() {
		return this.value;
	}
	
}
