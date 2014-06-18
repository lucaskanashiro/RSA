package src.tests;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

import src.source.Generator;

public class TestGenerator {

	private Generator generator;
	
	@Before
	public void setUp() throws Exception {
		this.generator = new Generator();
	}
	
	@Test
	public void testMillerRabin() {
		assertFalse(this.generator.testMillerRabin(new BigInteger("7418529637894561230"), 5));
		assertFalse(this.generator.testMillerRabin(new BigInteger("741852963789456123023456789234567888998765456789098765434567898"), 5));
		assertFalse(this.generator.testMillerRabin(new BigInteger("123456789012345678901234567890123456789012345678901234567890"), 5));
		assertFalse(this.generator.testMillerRabin(new BigInteger("7418529639638527417418529633216549871234567898529637539518527539514"), 5));
		assertFalse(this.generator.testMillerRabin(new BigInteger("98765432198774853214235396877415201236298"), 5));
	}

}
