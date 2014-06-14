package src.tests;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

import src.source.MathUtil;

public class TestMathUtil {

	private MathUtil util;
	
	@Before
	public void setUp(){
		this.util = new MathUtil();
	}
	
	@Test
	public void testSimpleGCD() {
		assertEquals(BigInteger.ONE, this.util.gcd(new BigInteger("11"), new BigInteger("19")));
		assertEquals(new BigInteger("3"), this.util.gcd(new BigInteger("123456"), new BigInteger("654321")));
		assertEquals(new BigInteger("3"), this.util.gcd(new BigInteger("192837"), new BigInteger("657483")));
	}

	@Test
	public void testExtendedGCD(){
		BigInteger[] result = this.util.gcdExtended(new BigInteger("123456"), new BigInteger("654321"));
		
		assertEquals(new BigInteger("3"), result[0]);
		assertEquals(new BigInteger("8819"), result[1]);
		assertEquals(new BigInteger("-46741"), result[2]);
		
		BigInteger[] result2 = this.util.gcdExtended(new BigInteger("987654"), new BigInteger("123456"));
		
		assertEquals(new BigInteger("6"), result2[0]);
		assertEquals(new BigInteger("1"), result2[1]);
		assertEquals(new BigInteger("-8"), result2[2]);
	}
	
	@Test
	public void testInverseMod(){
		assertEquals(new BigInteger("-48"), this.util.inverseMod(new BigInteger("42"), new BigInteger("2017")));
		assertEquals(new BigInteger("-5"), this.util.inverseMod(new BigInteger("19557"), new BigInteger("13")));
	}
	
	@Test
	public void testPowMod(){
		assertEquals(new BigInteger("123").modPow(new BigInteger("987654"), new BigInteger("963258741")),
				this.util.modPow(new BigInteger("123"), new BigInteger("987654"), new BigInteger("963258741")));
		assertEquals(new BigInteger("15975369842").modPow(new BigInteger("7418529632145697"), new BigInteger("3571598246")),
				this.util.modPow(new BigInteger("15975369842"), new BigInteger("7418529632145697"), new BigInteger("3571598246")));
	}

}
