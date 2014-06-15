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
		
		System.out.println("p: " + p.toString());
		System.out.println("q: " + q.toString());
		
		this.mod = p.multiply(q);
		
		System.out.println("mod: " + this.mod.toString());
		
		BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		
		System.out.println("phi: "+ phi.toString());
		
		this.publicKey = this.generator.generatePrimeNumber(numberOfDigits);
		
		while (this.util.gcd(phi,this.publicKey).compareTo(BigInteger.ONE) > 0 && this.publicKey.compareTo(phi) < 0 ) 
            this.publicKey.add(BigInteger.ONE); 
		
		System.out.println("publicKey: " + this.publicKey.toString());
		
		this.privateKey = this.util.inverseMod(this.publicKey, phi);
		
		System.out.println("privateKey: " + this.privateKey.toString());
	}
	
	public BigInteger encrypt(String message){
		BigInteger message_bytes = new BigInteger(message.getBytes());
		return encrypt(message_bytes);
	}
	
	private BigInteger encrypt(BigInteger message_bytes) {
		return this.util.modPow(message_bytes, this.privateKey, this.mod);
	}

	public BigInteger decrypt(BigInteger message){
		return this.util.modPow(message, this.publicKey, this.mod);
	}
	
}
