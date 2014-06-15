package src.source;

import java.math.BigInteger;

import  src.source.Constant;

public class MathUtil {

	public BigInteger gcd(BigInteger number1, BigInteger number2) {
		if(number2.subtract(BigInteger.ONE).signum() < 0)
			return number1;
		else
			return gcd(number2, number1.mod(number2));
	}

	public BigInteger[] gcdExtended(BigInteger number1, BigInteger number2) {
		BigInteger[] result = new BigInteger[3];
		BigInteger quocient;
		
		if(number1.subtract(number2).signum() < 0){
			BigInteger aux = number1;
			number1 = number2;
			number2 = aux;
		}
		
		if(number2.equals(BigInteger.ZERO)){
			result[0] = number1; result[1] = BigInteger.ONE; result[2] = BigInteger.ZERO;
			return result;
		}
		else{
			quocient = number1.divide(number2);
			result = gcdExtended(number2, number1.mod(number2));
			BigInteger tmp = result[1].subtract(result[2].multiply(quocient));
			result[1] = result[2];
			result[2] = tmp;
		}
		
		return result;
	}
	

	public BigInteger inverseMod(BigInteger a, BigInteger b) {	
		/*BigInteger[] result = this.gcdExtended(a, b);
				
		if(a.subtract(b).signum() < 0)
		{
			return result[2];
		}
		else
		{
			return result[1];
		}*/
		
		BigInteger b0 = b, t, q;
		BigInteger x0 = Constant.zero, x1 = Constant.one;
		
		if (b.equals(Constant.one)) return Constant.one;
		
		while (a.subtract(Constant.one).signum() > 0) {
			q = a.divide(b);
			t = b; b = a.mod(b); a = t;
			t = x0; x0 = x1.subtract(q.multiply(x0)); x1 = t;
		}
		
		if (x1.signum() < 0) x1 = x1.add(b0);
		
		return x1;
	}
		
	public BigInteger modPow(BigInteger a , BigInteger b , BigInteger n)
	{
		if(b.compareTo(Constant.zero) == -1)
			return this.modPow(this.inverseMod(a, n), b.negate(), n);
		if(b.equals(Constant.one))
			return this.inverseMod(a, n);
		
		BigInteger result = Constant.one;
		BigInteger temp = a;
		BigInteger temp_exp= b;
		
		while(!temp_exp.equals(Constant.zero))
		{
			if(!temp_exp.remainder(Constant.two).equals(Constant.zero) )
			{	
				result= (result.remainder(n).multiply(temp.remainder(n)).remainder(n)); 
			}	
			temp= temp.remainder(n).multiply(temp.remainder(n)).remainder(n);
			temp_exp = temp_exp.divide(Constant.two);
		}
		return result;
	}

	
	public BigInteger modProd(BigInteger a,BigInteger b , BigInteger n){

		if(b.equals(Constant.zero) || a.equals(Constant.zero)){
			return Constant.zero;			
		}
		if(a.equals(Constant.one) ){
			return b.remainder(n);
		} 
		if(b.equals(Constant.one)){
			return a.remainder(n);
		}
		
		BigInteger b1,b2;
		
		b1 = a.remainder(n);
		b2 = b.remainder(n);
		
		b1 = b1.multiply(b2);
		
		return b1.remainder(n);
	}
	

}
