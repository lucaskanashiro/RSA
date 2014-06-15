package src.source;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSA {
	private BigInteger privateKey;
	private BigInteger publicKey;
	private BigInteger mod;
	
	private MathUtil util;
	private Generator generator;
	private RandomPrime prime = new RandomPrime();
	//private SecureRandom random = new SecureRandom();
	private PRNG random;
	
	public RSA(int numberOfBytes){
		this.util = new MathUtil();
		this.generator = new Generator();
		this.random = new PRNG(numberOfBytes);
		this.generateKeys(numberOfBytes);
	}
	
	private void generateKeys(int numberOfDigits) {
		//BigInteger p = BigInteger.probablePrime(numberOfDigits, random);
		//BigInteger q = BigInteger.probablePrime(numberOfDigits, random);
		//BigInteger p = this.generator.generatePrimeNumber(numberOfDigits);
		//BigInteger q = this.generator.generatePrimeNumber(numberOfDigits);
		BigInteger p,q,val;
		System.out.println("OK");
		//while (true){
        p = prime.getRandomPrime(random, numberOfDigits, 5);
        q = prime.getRandomPrime(random, numberOfDigits, 5);

            //val = this.util.gcd(p, q);
            
            //if (val.equals(BigInteger.ONE))
                //break;
        //}
		
		//while(p.subtract(q).signum() == 0)
			//q = this.generator.generatePrimeNumber(numberOfDigits);
		
		
		BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		
		this.mod = p.multiply(q);	
		
		this.publicKey = prime.getRandomPrime(random, numberOfDigits, 5);
		
		while (this.util.gcd(phi,this.publicKey).compareTo(BigInteger.ONE) > 0 && this.publicKey.compareTo(phi) < 0 ) 
            this.publicKey.add(BigInteger.ONE);
		
		//this.publicKey = new BigInteger("65537");
		//this.privateKey = this.publicKey.modInverse(phi);
		
		this.privateKey = this.util.inverseMod(this.publicKey, phi);
		
		System.out.println("p: " + p);
		System.out.println("q: " + q);
		System.out.println("mod: " + this.mod);
		System.out.println("phi: "+ phi);
		System.out.println("publicKey: " + this.publicKey);
		System.out.println("privateKey: " + this.privateKey);
		
		
		/*BigInteger p, q, val, phin;
		
		
		while (true){
            p = prime.getRandomPrime(random, numberOfDigits, 5);
            q = prime.getRandomPrime(random, numberOfDigits, 5);
            
            if (q.compareTo(p) == 1)
                val = this.util.gcd(p, q);
            else
                val = this.util.gcd(p, p);
            
            if (val.equals(BigInteger.ONE))
                break;
        }
        
        System.out.println("P: " + p);
        System.out.println("-----------------------------------");
        System.out.println("Q: " + q);
        System.out.println("-----------------------------------");
        this.mod = p.multiply(q);
        phin = p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1")));
        
        System.out.println("N: " + this.mod);
        System.out.println("-----------------------------------");
        System.out.println("PHI(N): " + phin);
        System.out.println("-----------------------------------");
        
        while (true){
            this.publicKey = prime.getRandomPrime(random, numberOfDigits, 5);
            
        if (publicKey.compareTo(phin) == 1)
            val = this.util.gcd(publicKey, phin);
        else
            val = this.util.gcd(publicKey, phin);
        
        if (val.equals(BigInteger.ONE))
            break;
        }
        privateKey = publicKey.modInverse(phin);
        
        System.out.println("E: "+ publicKey);
        System.out.println("-----------------------------------");
        System.out.println("D: "+ privateKey);
        System.out.println("-----------------------------------");*/
	}
	
	
		
	public BigInteger encrypt(String message){
		BigInteger message_bytes = new BigInteger(message.getBytes());
		return encrypt(message_bytes);
	}
	
	public BigInteger encrypt(BigInteger message_bytes) {
		return this.util.modPow(message_bytes, this.privateKey, this.mod);
		//return message_bytes.modPow(privateKey, mod);
	}

	public BigInteger decrypt(BigInteger message){
		return this.util.modPow(message, this.publicKey, this.mod);
		//return message.modPow(publicKey, mod);
	}
	
	
	
	
	/*private final static BigInteger one      = new BigInteger("1");
	   private final static SecureRandom random = new SecureRandom();

	private MathUtil util =  new MathUtil();
	private Generator generator = new Generator();
	   
	   private BigInteger privateKey;
	   private BigInteger publicKey;
	   private BigInteger modulus;

	   // generate an N-bit (roughly) public and private key
	   public RSA(int N) {
	      BigInteger p = BigInteger.probablePrime(N, random);
	      BigInteger q = BigInteger.probablePrime(N, random);
	      BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));

	      modulus    = p.multiply(q);                                  
	      publicKey  = new BigInteger("65537");     // common value in practice = 2^16 + 1
	      privateKey = publicKey.modInverse(phi);
	      //privateKey = this.util.inverseMod(publicKey, phi);
	      
	      System.out.println("p: " + p);
	      System.out.println("q: " + q);
	      System.out.println("mod: " + this.modulus);
	      System.out.println("phi: "+ phi);
	      System.out.println("publicKey: " + this.publicKey);
	      System.out.println("privateKey: " + this.privateKey);
	   }


	   public BigInteger encrypt(BigInteger message) {
	      return message.modPow(publicKey, modulus);
		  //return this.util.modPow(message, publicKey, modulus);
	   }

	   public BigInteger decrypt(BigInteger encrypted) {
	      return encrypted.modPow(privateKey, modulus);
		  //return this.util.modPow(encrypted, privateKey, modulus);
	   }

	   public String toString() {
	      String s = "";
	      s += "public  = " + publicKey  + "\n";
	      s += "private = " + privateKey + "\n";
	      s += "modulus = " + modulus;
	      return s;
	   }
	 
	   public static void main(String[] args) {
		   
		  Scanner scan = new Scanner(System.in);
	      int N = scan.nextInt();
	      RSA key = new RSA(N);
	      System.out.println(key);
	 
	      // create random message, encrypt and decrypt
	      BigInteger message = new BigInteger("kanashiro".getBytes());

	      //// create message by converting string to integer
	      // String s = "test";
	      // byte[] bytes = s.getBytes();
	      // BigInteger message = new BigInteger(s);

	      BigInteger encrypt = key.encrypt(message);
	      BigInteger decrypt = key.decrypt(encrypt);
	      System.out.println("message   = " + message);
	      System.out.println("encrpyted = " + encrypt);
	      System.out.println("decrypted = " + decrypt);
	   }*/
	
}
