package lib;

import java.math.BigInteger;
import java.util.Vector;

public class RSA {

	private BigInteger n,privateKey,publicKey;
	private Prime p;
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
		return new String((p.modPow(new BigInteger(message), this.privateKey, this.n)).toByteArray());
	}
	
	public BigInteger decrypt(BigInteger message){
		Prime p = new Prime();
		return p.modPow(message, this.privateKey, this.n);
	}
	
	
	public void generate(){
		
		Prime p = new Prime("182349712403987210471");
		Prime q = new Prime("947309178243017402140");
		
		System.out.println("P = "+p.getValue());
		System.out.println("Q = "+q.getValue());
		
		BigInteger totiente = totiente(p.getValue(), q.getValue());
		
		this.n = (p.getValue().multiply(q.getValue()));
		System.out.println("n = "+this.n);
		
		this.publicKey = e(totiente);
		System.out.println("totiente = "+totiente);
		System.out.println("public key = "+this.publicKey);
		this.privateKey =(modInverse(this.publicKey,totiente));
		System.out.println("private key = "+this.privateKey);
	}
	
	
	public BigInteger modInverse(BigInteger publicKey,BigInteger totiente){
		Vector<BigInteger> result = p.gcd(publicKey, totiente);
		return result.get(1);
	}

	public BigInteger e(BigInteger totiente) {
		BigInteger e = new BigInteger("3");
		BigInteger dois = new BigInteger("2");
		
		Vector<BigInteger> values= p.gcd(e,totiente);
		System.out.println(values.get(0)+" "+values.get(1)+" "+values.get(2));
		
		
		while(!(values.get(2).equals(new BigInteger("1"))))
		{
			System.out.println("OK");
			e = e.add(dois);		
			values = p.gcd(e,totiente);	
		}
		
		return e;
	}

	public BigInteger totiente(BigInteger p, BigInteger q) {
		BigInteger v1 = p.subtract(new BigInteger("1"));
		BigInteger v2 = q.subtract(new BigInteger("1"));

		return v1.multiply(v2);
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
