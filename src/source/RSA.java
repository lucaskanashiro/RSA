package source;

import java.math.BigInteger;

public class RSA {
	private BigInteger privateKey;
	private BigInteger publicKey;
	private BigInteger mod;
	
	private MathUtil util;
	private Generator generator;
	
	public RSA(int numberOfBits){
		this.util = new MathUtil();
		this.generator = new Generator();
		this.generateKeys(numberOfBits);
	}
	
	private void generateKeys(int numberOfBits) {
		BigInteger p = this.generator.generatePrimeNumber(new BigInteger("1234567890123456789"));
		BigInteger q = this.generator.generatePrimeNumber(new BigInteger("9876543234567876543456"));
		
		this.mod = p.multiply(q);
		
		BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		
		this.publicKey = this.generator.generatePrimeNumber(new BigInteger("67899432345678765432123456789"));
		
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
