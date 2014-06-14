package tests;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

import source.RSA;

public class TestRSA {

	private RSA rsa;
	
	@Before
	public void setUp() throws Exception {
		rsa = new RSA(129); 
	}

	@Test
	public void testRSA(){
		String message = "MEU NOME";
		String t = rsa.encrypt(message);
		System.out.println("BIGINTEGER = "+new BigInteger(message.getBytes()));
		System.out.println(t.toString());
		String plainText = rsa.decrypt(t);
		System.out.println(plainText.toString());
		assertEquals(t,plainText);
		
	}
	
	
	
	
}
