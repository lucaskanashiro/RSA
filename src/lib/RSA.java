package src.lib;

import java.math.BigInteger;
import java.util.Vector;


public class RSA {

	public RSA() {
	}

	public Prime generateKey(int bits) {
		return new Prime(bits);
	}

	public BigInteger generateCypherKey() {
		return new BigInteger("232938293829");
	}

	public BigInteger generateDecypherKey() {
		return new BigInteger("232938293829");
	}

	public BigInteger cypher() {
		return new BigInteger("232938293829");
	}

	public BigInteger decypher() {
		return new BigInteger("232938293829");
	}

	
	
	public BigInteger e(BigInteger totiente) {
		BigInteger aux = totiente;
		while (!aux.equals(new BigInteger("1"))) {
			if (gcd(aux, totiente).equals(new BigInteger("1"))) {
				return aux;
			}
			aux.subtract(new BigInteger("1"));
		}
		return aux;
	}

	public BigInteger totiente(BigInteger p, BigInteger q) {
		BigInteger v1 = p.subtract(new BigInteger("1"));
		BigInteger v2 = q.subtract(new BigInteger("1"));

		return v1.multiply(v2);
	}

	public Vector<BigInteger> gcd(BigInteger value1, BigInteger value2) {
		BigInteger zero = new BigInteger("0");
		Vector<BigInteger> result = new Vector<BigInteger>();
		
		if(value1.subtract(value2).signum() < 0 ){
			BigInteger aux = value1;
			value1 = value2;
			value2 = value1;
		}
		
		if(value2.equals(zero)){
			result.add(new BigInteger("1"));  
			result.add(zero);	   			  
			result.add(value1);    			  
			return result;
		}
		
		Vector<BigInteger> d = gcd(value2,value1.remainder(value2));
		result.add(d.get(1));
		BigInteger y = d.get(0);
		BigInteger divisao = value1.divide(value2);
		divisao = divisao.multiply(d.get(1));
		y = y.subtract(divisao);
		result.add(y);
		result.add(d.get(2));
		
		return result;
	}

}
