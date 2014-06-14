package tests;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

import source.Generator;

public class TestGenerator {

	private Generator generator;
	
	@Before
	public void setUp() throws Exception {
		this.generator = new Generator();
	}

	@Test
	public void testPseudoRandomGenerator(){
		assertEquals(new BigInteger("188377102516679"), this.generator.generatePseudoRandomNumber(new BigInteger("1234567890")));
		assertEquals(new BigInteger("91963355173079"), this.generator.generatePseudoRandomNumber(new BigInteger("1234567890987654321852963741")));
	}
	
	@Test
	public void testMillerRabin() {
		assertFalse(this.generator.testMillerRabin(new BigInteger("7418529637894561230")));
		assertFalse(this.generator.testMillerRabin(new BigInteger("741852963789456123023456789234567888998765456789098765434567898")));
		assertFalse(this.generator.testMillerRabin(new BigInteger("123456789012345678901234567890123456789012345678901234567890")));
		assertFalse(this.generator.testMillerRabin(new BigInteger("7418529639638527417418529633216549871234567898529637539518527539514")));
		assertFalse(this.generator.testMillerRabin(new BigInteger("98765432198774853214235396877415201236298")));
	}
	

	@Test
	public void testGeneratePrimeNumber(){
		assertEquals(new BigInteger("1130262615100073"), this.generator.generatePrimeNumber(new BigInteger("1234567890")));
		assertEquals(new BigInteger("551780131038473"), this.generator.generatePrimeNumber(new BigInteger("1234567890987654321852963741")));
	}
	
	
}
