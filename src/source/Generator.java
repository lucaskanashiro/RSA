package source;

import java.math.BigInteger;

public class Generator {

	private ReadRandom reader;
	private final int[] array = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41};
	
	public Generator(){
		this.reader = new ReadRandom();
	}
	
	public BigInteger getPrime(int seed_length) {
        BigInteger p;

        while (true) {
            p = new BigInteger(this.reader.getSeed(seed_length));
            if (p.mod(Constant.four).equals(Constant.three)) {
                break;
            }
        }

        return p;
    }
	
	public BigInteger getN(int seed_length, BigInteger p, BigInteger q) {
        while (p.equals(q)) 
            q = this.getPrime(seed_length);
        
        return p.multiply(q);
    }
	
	public BigInteger getRandomNumber(int seed_length) {
        BigInteger randomNumber;
        BigInteger seed = new BigInteger(this.reader.getSeed(seed_length));
        BigInteger p = this.getPrime(seed_length);
        BigInteger q = this.getPrime(seed_length);

        while (seed.mod(p).equals(Constant.zero) || seed.mod(p).equals(Constant.zero)) {
            p = this.getPrime(seed_length);
            q = this.getPrime(seed_length);
        }
        
        BigInteger N = this.getN(seed_length, p, q); 

        randomNumber = (seed.multiply(seed)).mod(N);
        
        return randomNumber;
    }
	
	public BigInteger getRandomPrime(int N_LENGTH, int certainty){
        BigInteger randomPrime;
      
        while(true){
            randomPrime = this.getRandomNumber(N_LENGTH);
            
            if (this.testMillerRabin(randomPrime, certainty))
                break;
        }
        
        return randomPrime;
    }
	
	public boolean testingSecondLoop(BigInteger n, BigInteger a, int p, BigInteger d) {
        for (int i = 0; i < p; i++) {
            BigInteger exp = Constant.two.pow(i);
            exp = exp.multiply(d);
            BigInteger result = a.modPow(exp, n);
            
            if (result.equals(n.subtract(Constant.one)) || result.equals(Constant.one)) {
                return true;
            }
        }
        return false;
    }
	
	public boolean testMillerRabin(BigInteger number, int certainty) { 
        BigInteger odd_factor = number.subtract(Constant.one);                          
        
        int p = 0;
        
        while (odd_factor.mod(Constant.two).equals(Constant.zero)) {
            p++;
            odd_factor = odd_factor.divide(Constant.two);
        }
        
        for (int i = 0; i < certainty; i++) {      
            BigInteger a = BigInteger.valueOf(array[i]);  
            
            boolean answer = this.testingSecondLoop(number, a, p, odd_factor);
            
            if (!answer) {
                return false;
            }
        }
        return true;
    }
}
