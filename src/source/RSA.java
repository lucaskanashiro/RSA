package source;

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
		
		System.out.println("P = "+p);
		System.out.println("Q ="+q);
		
		this.mod = p.multiply(q);
		
		BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		System.out.println("PHI = "+phi);
		
		this.publicKey = this.generator.generatePrimeNumber(numberOfDigits);
		
		while (this.util.gcd(phi,this.publicKey).compareTo(BigInteger.ONE) > 0 && this.publicKey.compareTo(phi) < 0 ) 
            this.publicKey.add(BigInteger.ONE); 
		
		this.privateKey = this.util.inverseMod(this.publicKey,phi );
		
		System.out.println("Public KEY="+this.publicKey+"\nPrivate Key= "+this.privateKey+"\nMOD= "+this.mod);
		
	}
	
	
	public String encrypt(String message){
		System.out.println("BEFORE ENCRYPTION="+new BigInteger(message.getBytes()));
		BigInteger s =  this.util.modPow(new BigInteger(message.getBytes()), this.publicKey, this.mod);
		System.out.println("AFTER ENCRYPTION="+s);
		return new String(s.toByteArray());
	}
	
	public String decrypt(String message){
		System.out.println("BEFORE DECRYPTION="+new BigInteger(message.getBytes()));
		BigInteger s= this.util.modPow(new BigInteger(message.getBytes()), this.privateKey, this.mod);
		System.out.println("AFTER DECRYPTION="+s);
		return new String(s.toByteArray());
	}
	
}
