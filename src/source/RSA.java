package src.source;

import java.math.BigInteger;

public class RSA {
	private BigInteger privateKey;
	private BigInteger publicKey;
	private BigInteger mod;
	
	private MathUtil util;
	private Generator generator;
	
	public RSA(int numberOfBytes){
		this.util = new MathUtil();
		this.generator = new Generator();
		this.generateKeys(numberOfBytes);
	}
	
	private void generateKeys(int numberOfDigits) {
		BigInteger p = this.generator.generatePrimeNumber(numberOfDigits);
		BigInteger q = this.generator.generatePrimeNumber(numberOfDigits);
		
		this.mod = p.multiply(q);
		
		BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		
		this.publicKey = this.generator.generatePrimeNumber(numberOfDigits);
		
		while (this.util.gcd(phi,this.publicKey).compareTo(BigInteger.ONE) > 0 && this.publicKey.compareTo(phi) < 0 ) 
            this.publicKey.add(BigInteger.ONE); 
		
		this.privateKey = this.util.inverseMod(phi, this.publicKey);
	}
	
	public String encrypt(String message){
		BigInteger s =  this.util.modPow(new BigInteger(message.getBytes()), this.publicKey, this.mod);
		return s.toString();
	}
	
	public String decrypt(String message){
		return new String(this.util.modPow(new BigInteger(message), this.privateKey, this.mod).toByteArray());
	}
	
}
