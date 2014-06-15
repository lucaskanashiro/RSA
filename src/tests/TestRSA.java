package src.tests;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import src.source.MathUtil;
import src.source.RSA;

public class TestRSA {

	private RSA rsa;
	private MathUtil util;
	@Before
	public void setUp() throws Exception {
		rsa = new RSA(112);
		util= new MathUtil();
	}

	@Test
	public void testRSA(){
		
		/*String message = "kanashiro";
		
		BigInteger cypher = this.rsa.encrypt(message);
		
		System.out.println("CYPHER= "+cypher);
		
		BigInteger decypher = this.rsa.decrypt(cypher);
		
		System.out.println("DECYPHER= "+new String(decypher.toByteArray()));*/
		
		
		
		
		String texto = "criptografia";
		String cyphertext = "";
		byte[] b = texto.getBytes();
		
		for (byte bytes : b) {
			System.out.println("byte ="+bytes +" "+((char) bytes));
			String buffer = "";
			buffer+=bytes;
			BigInteger big = new BigInteger(buffer);
			 //String c=  new String(this.util.modPow( big  , new BigInteger("53"), new BigInteger("185")).toByteArray());
			String c = new String(big.modPow(new BigInteger("53"), new BigInteger("185")).toByteArray());
			cyphertext += c;
		}
		
		System.out.println("CHYPHERTEXT = "+cyphertext);
		String plainText = "";
		b = cyphertext.getBytes();
		for (byte bytes : b) {
			System.out.println("byte ="+bytes +" "+((char) bytes));
			String buffer = "";
			buffer+=bytes;
			BigInteger big = new BigInteger(buffer);
			//String c=  new String(this.util.modPow( big  , new BigInteger("125"), new BigInteger("185")).toByteArray());
			String c = new String(big.modPow(new BigInteger("125"), new BigInteger("185")).toByteArray());
			System.out.println(c);
			plainText += c;
			System.out.println(plainText);
		}
		
		System.out.println("PLAINTEXT ="+plainText);
		
	}
	
	
	
	
}
