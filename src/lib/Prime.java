package lib;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Vector;

public class Prime {

	private final BigInteger zero = new BigInteger("0");
	private final BigInteger um = new BigInteger("1");
	private final BigInteger dois = new BigInteger("2");
	private final BigInteger tres = new BigInteger("3");
	private final BigInteger cinco = new BigInteger("5");
	private final BigInteger dez = new BigInteger("10");

	private BigInteger value;
	
	public Prime(){
		this.value = zero;
	}
	
	public Prime(String bits) {
		this.value = generatePrime(bits);	
	}
	
	
	private BigInteger generatePrime (String bits){
		BigInteger result = zero;
		BigInteger value = zero;
		
		//gerar um numero aleatorio passando numero de bits
		result= new PseudoRandomGenerator(bits).generate();
					
		while(true){
			// 6k +1
			value = result.multiply(new BigInteger("6"));
			value = value.subtract(new BigInteger("1"));
			if(Miller_Rabin(value))
				return value;
			//6k - 1 
			value = value.add(new BigInteger("2"));
			if(Miller_Rabin(value))
				return value;
		}
		
	}
		
	
	
	public boolean Miller_Rabin (BigInteger n){
	    BigInteger base = new BigInteger("2");
	    BigInteger nsub1 = n.subtract(um);
		BigInteger s = um;
		BigInteger d = dois ;
		
		boolean test1 = false;
		boolean test2 = false;
		
		
		while(d.remainder(dois).equals(zero))
		{
			base = base.shiftLeft(1);
			s = s.add(um);
			d = nsub1.divide(base);
		}
		
		System.out.println("[[BASE = "+base+
						   "\nS =" + s+
						   "\nD ="+d+"]]");
		
		
		while(true){
			BigInteger a = new PseudoRandomGenerator("23").generate();
						
			if(modPow(a, d, n).equals(um)){
				test1 = true;
			}
			
			if(modPow(a,d,n).equals(nsub1)){
				test2 = true;
			}
			
			if(test1 && test2){
				return false;
			}
			
			System.out.println("\nA= "+a+"\nD="+d+"\nN= "+n);
			
			BigInteger contador;
			for(contador = um;!contador.subtract(base).equals(zero);contador = contador.shiftLeft(1))
			{
				System.out.println("Contador="+contador+"\nBASE="+base);
				if(modPow(a,contador.multiply(d),n).equals(nsub1))
				{
					test2 = true;						
					break;
				}
			}
			if(test1 && test2) return false;
			if((test1 && !test2) || (!test1 && test2))return true;
		}
		
	}
		
	
	
	
	
	public BigInteger modProd(BigInteger a,BigInteger b , BigInteger n){

		if(b.equals(zero) || a.equals(zero)){
			return zero;			
		}
		if(a.equals(um) ){
			return b.remainder(n);
		} 
		if(b.equals(um)){
			return a.remainder(n);
		}
		
		BigInteger b1,b2;
		
		b1 = a.remainder(n);
		b2 = b.remainder(n);
		
		b1 = b1.multiply(b2);
		
		return b1.remainder(n);
	}
	

	public BigInteger modPow(BigInteger a , BigInteger b , BigInteger n)
	{
		BigInteger result = um;
		BigInteger temp = a;
		BigInteger temp_exp= b;
		
		while(!temp_exp.equals(zero))
		{
			if(!temp_exp.remainder(dois).equals(zero) )
			{	
				result= (result.remainder(n).multiply(temp.remainder(n)).remainder(n)); 
			}	
			temp= temp.remainder(n).multiply(temp.remainder(n)).remainder(n);
			temp_exp = temp_exp.divide(dois);
		}
		
		return result;
	}
	
	

	public Vector<BigInteger> gcd(BigInteger value1, BigInteger value2) {
		BigInteger zero = new BigInteger("0");
		Vector<BigInteger> result = new Vector<BigInteger>();
		
		if(value1.subtract(value2).signum() < 0 ){
			BigInteger aux = value1;
			value1 = value2;
			value2 = aux;
		}
		
		if(value2.equals(zero)){
			result.add(new BigInteger("1"));  
			result.add(zero);	   			  
			result.add(value1);    			  
			return result;
		}
		
		Vector<BigInteger> d = gcd(value2,value1.mod(value2));
		result.add(d.get(1));
		BigInteger y = d.get(0);
		BigInteger divisao = value1.divide(value2);
		divisao = divisao.multiply(d.get(1));
		y = y.subtract(divisao);
		result.add(y);
		result.add(d.get(2));
		
		return result;
	}

	
		
	public BigInteger getValue() {
		return this.value;
	}		
}
