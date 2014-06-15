package tests;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import source.MathUtil;
import source.RSA;

public class TestRSA {

	private RSA rsa;
	private MathUtil util;
	@Before
	public void setUp() throws Exception {
		rsa = new RSA(1);
		util= new MathUtil();
	}

	@Test
	public void testRSA(){
		
		String teste = "macartur";
		
		String cypherText = rsa.encrypt(teste);
		
		System.out.println("CYPHER = "+cypherText);
		String decypherText = rsa.decrypt(teste);
		System.out.println("DECYPHER = "+decypherText);
	}
	
	
	
	
}
