package src.source;

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

	public RSA(int numberOfBytes){
		this.util = new MathUtil();
		this.generator = new Generator();
		this.generateKeys(numberOfBytes);
	}
	
	private void generateKeys(int numberOfDigits) {
		System.out.println("Gerando chaves...");
		
        BigInteger p = this.generator.getRandomPrime(numberOfDigits, 5);
        BigInteger q = this.generator.getRandomPrime(numberOfDigits, 5);
		
		BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		
		this.mod = p.multiply(q);	
		
		this.publicKey = this.generator.getRandomPrime(numberOfDigits, 5);
		
		while (this.util.gcd(phi,this.publicKey).compareTo(BigInteger.ONE) > 0 && this.publicKey.compareTo(phi) < 0 ) 
            this.publicKey.add(BigInteger.ONE);
		
		this.privateKey = this.util.inverseMod(this.publicKey, phi);
		
		System.out.println("p: " + p);
		System.out.println("q: " + q);
		System.out.println("mod: " + this.mod);
		System.out.println("phi: "+ phi);
		System.out.println("publicKey: " + this.publicKey);
		System.out.println("privateKey: " + this.privateKey);
		
		try {
	          File file = new File("publicKey.txt");
	          BufferedWriter output = new BufferedWriter(new FileWriter(file));
	          output.write("Public Key: \n\n" + this.publicKey.toString());
	          output.close();
	    } catch ( IOException e ) {
	           e.printStackTrace();
	    }
		
		try {
	          File file = new File("privateKey.txt");
	          BufferedWriter output = new BufferedWriter(new FileWriter(file));
	          output.write("Private Key: \n\n" + this.privateKey.toString());
	          output.close();
	    } catch ( IOException e ) {
	           e.printStackTrace();
	    }
	}	
		
	public BigInteger encrypt(String message){
		BigInteger message_bytes = new BigInteger(message.getBytes());
		return encrypt(message_bytes);
	}
	
	public BigInteger encrypt(BigInteger message_bytes) {
		return this.util.modPow(message_bytes, this.privateKey, this.mod);
	}

	public BigInteger decrypt(BigInteger message){
		return this.util.modPow(message, this.publicKey, this.mod);
	}

}
