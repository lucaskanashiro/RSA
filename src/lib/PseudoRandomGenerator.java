package lib;

import java.math.BigInteger;


public class PseudoRandomGenerator {

	private BigInteger seed;
	private BigInteger m;
	
	public PseudoRandomGenerator(){
		this.seed = new BigInteger(new ReadRandom().Random());
		
		BigInteger p = new BigInteger("23895371");
		BigInteger q = new BigInteger("23895379");
		
		this.m = p.multiply(q);
	}
	
	public BigInteger generate(){
		this.seed = this.seed.multiply(this.seed);
		this.seed = this.seed.mod(this.m);
		
		return this.seed;
	}
}

