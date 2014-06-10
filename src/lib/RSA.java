package lib;

import java.math.BigInteger;
import java.util.Vector;

public class RSA {

	private BigInteger n,privateKey,publicKey;

	public RSA() {
	}


	public String encrypt(String message){
		Prime p = new Prime();
		BigInteger s =  p.modPow(new BigInteger(message.getBytes()), this.publicKey, this.n);
		return s.toString();	
	}
	
	public BigInteger encrypt(BigInteger message){
		Prime p= new Prime();
		return p.modPow(message, this.publicKey, this.n) ; 
	}
	
	public String decryption(String message){
		Prime p= new Prime();
		return new String(p.modPow(new BigInteger(message), this.privateKey, this.n).toByteArray());
	}
	
	public BigInteger decrypt(BigInteger message){
		Prime p = new Prime();
		return p.modPow(message, this.privateKey, this.n);
	}
	
	
	public void generate(){
		
		Prime p1 = new Prime("182349712403987210471");
		Prime p2 = new Prime("947309178243017402140");
		
		BigInteger totiente = totiente(p1.getValue(), p2.getValue());
		
		this.n = (p1.getValue().multiply(p2.value));
		
		this.publicKey = e(totiente);
		this.privateKey =(modInverse(this.publicKey,totiente));
	}
	
	
	public BigInteger modInverse(BigInteger publicKey,BigInteger totiente){
		Vector<BigInteger> result = gcd(publicKey, totiente);
		return result.get(1);
	}
		
	public BigInteger e(BigInteger totiente) {
		BigInteger e = new BigInteger("3");
				
		while(totiente.gcd(e).intValue() > 1)
		{
			e = e.add(new BigInteger("2"));			
		}
		return e;
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
			value2 = aux;
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

	public BigInteger getPrivateKey() {
		return this.privateKey;
	}

	public BigInteger getN() {
		return this.n;
	}
	
	public BigInteger getPublicKey(){
		return this.publicKey;
	}
	
	

}
