package source;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class RSA {
	private BigInteger privateKey;
	private BigInteger publicKey;
	private BigInteger mod;
	
	private MathUtil util;
	private Generator generator;

	
	public RSA(){
		this.util = new MathUtil();
		this.generator = new Generator();		
	}
	
	public RSA(int numberOfBytes){
		this.util = new MathUtil();
		this.generator = new Generator();
		this.generateKeys(numberOfBytes);
	}
	
	public void generateKeys(int numberOfDigits) {
		
        BigInteger p = this.generator.getRandomPrime(numberOfDigits, 5);
        BigInteger q = this.generator.getRandomPrime(numberOfDigits, 5);
		
		BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		
		this.mod = p.multiply(q);	
		
		this.publicKey = this.generator.getRandomPrime(numberOfDigits, 5);
		
		while (this.util.gcd(phi,this.publicKey).compareTo(BigInteger.ONE) > 0 && this.publicKey.compareTo(phi) < 0 ) 
            this.publicKey.add(BigInteger.ONE);
		
		this.privateKey = this.util.inverseMod(this.publicKey, phi);

	}	
	
	public void setPublicKey(BigInteger publicKey){
		this.publicKey = publicKey;
	}
	public BigInteger getPublicKey(){
		return this.publicKey;
	}
	
	public void setPrivateKey(BigInteger privateKey){
		this.privateKey = privateKey;
	}
	public BigInteger getPrivateKey(){
		return this.privateKey;
	}
	public void setModulus(BigInteger modulus){
		this.mod = modulus;
	}
	public BigInteger getModulusKey(){
		return this.mod;
	}
	
	
	public BigInteger encrypt(BigInteger message) {
		return this.util.modPow(message, this.privateKey, this.mod);
	}

	public BigInteger decrypt(BigInteger message){
		return this.util.modPow(message, this.publicKey, this.mod);
	}

}
