package tests;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Vector;
import lib.Prime;
import org.junit.Before;
import org.junit.Test;

public class PrimeTest {

	private Prime p;
	
	@Before
	public void Before(){
		p = new Prime();
	}
	
	
	@Test
	public void verifyGCD(){
		Vector<BigInteger> values = new Vector<BigInteger>();
		values = p.gcd(new BigInteger("15"), new BigInteger("20"));
		
		assertEquals( new BigInteger("-1").toString(), values.get(1).toString());
		assertEquals( new BigInteger("1").toString(), values.get(0).toString());
		assertEquals( new BigInteger("5").toString(), values.get(2).toString());
	}
	
	@Test
	public void verifyBigGCD(){
		Vector<BigInteger> values = new Vector<BigInteger>();
		values = p.gcd(new BigInteger("1000"), new BigInteger("300"));
		
		assertEquals( new BigInteger("-3").toString(), values.get(1).toString());
		assertEquals( new BigInteger("1").toString(), values.get(0).toString());
		assertEquals( new BigInteger("100").toString(), values.get(2).toString());		
	}
	
	
	@Test
	public void modPowTest(){
		
		BigInteger base = new BigInteger("981144251");
		BigInteger expoent = new BigInteger("21773");
		BigInteger modulus = new BigInteger("2323232");
		
		BigInteger result =	p.modPow(base, expoent, modulus);
		
		assertEquals(result.toString(), "633835");
		
		modulus = new BigInteger("83928392");
		result =	p.modPow(base, expoent, modulus);
		assertEquals(result.toString(), "31691795");		
	}
	
	@Test
	public void modProdZeroTest(){
		BigInteger value1 = new BigInteger("123414");
		BigInteger value2 = new BigInteger("0");
		BigInteger modulus = new BigInteger("2323232");
		
		BigInteger result = p.modProd(value1, value2, modulus);
		
		assertEquals("0", result.toString());
		value1 = new BigInteger("0");
		value2 = new BigInteger("23232");
		
		result = p.modPow(value1, value2, modulus);
		assertEquals("0", result.toString());
	}
	
	@Test
	public void modProdUmTest(){
		BigInteger value1 = new BigInteger("1232323414");
		BigInteger value2 = new BigInteger("1");
		BigInteger modulus = new BigInteger("23231352");
		
		BigInteger result = p.modProd(value1, value2, modulus);
		
		assertEquals("1061758", result.toString());
		
		value1 = new BigInteger("1");
		value2 = new BigInteger("1232323414");
		result = p.modProd(value1, value2, modulus);
		assertEquals("1061758", result.toString());	
	}
		
	@Test
	public void modProdTest(){
		BigInteger value1 = new BigInteger("12323234141232323414");
		BigInteger value2 = new BigInteger("981144251981144251");
		BigInteger modulus = new BigInteger("23231352");
		
		BigInteger result = p.modProd(value1, value2, modulus);
		
		assertEquals("18847682", result.toString());		
	}
	
	@Test
	public void MillerRabinTest(){
		
		p.Miller_Rabin(new BigInteger("345"));
		
		
		
		
		
	}
	
	
	
	
	
}
