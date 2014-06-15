/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.source;

import java.math.BigInteger;

/**
 *
 * @author hialo
 */
public class RandomPrime {

    public static final BigInteger ZERO = BigInteger.valueOf(0);  
    public static final BigInteger ONE = BigInteger.valueOf(1);
    public static final BigInteger TWO = BigInteger.valueOf(2);
    
    public static final int[] array = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41}; 

    public RandomPrime() {
    }

    public RandomPrime(PRNG random, int N_LENGTH) {
    }
    
    
    public BigInteger getRandomPrime(PRNG random, int N_LENGTH, int certainty){
        BigInteger randomPrime;
      
        while(true){
            randomPrime = random.getRandomNumber(N_LENGTH);
            
            if (isProbablyPrime(randomPrime, certainty))
                break;
        }
        
        return randomPrime;
    }
    
    /*  Method who executes the second loop for MillerRabin test.
     * 
     */
    
    public static boolean testingSecondLoop(BigInteger n, BigInteger a, int p, BigInteger d) {
        for (int i = 0; i < p; i++) {
            BigInteger exp = TWO.pow(i);
            exp = exp.multiply(d);
            BigInteger result = a.modPow(exp, n);
            
            if (result.equals(n.subtract(ONE)) || result.equals(ONE)) {
                return true;
            }
        }
        return false;
    }
    
    /*  Method which tests the possible primality of a given number, using the MillerRabin test.
     * 
     */

    public static boolean isProbablyPrime(BigInteger number, int certainty) { 
        BigInteger odd_factor = number.subtract(ONE);                          
        
        int p = 0;
        
        while (odd_factor.mod(TWO).equals(ZERO)) {
            p++;
            odd_factor = odd_factor.divide(TWO);
        }
        
        // First loop.
        for (int i = 0; i < certainty; i++) {      
            BigInteger a = BigInteger.valueOf(array[i]);  
            
            boolean answer = testingSecondLoop(number, a, p, odd_factor);
            
            if (!answer) {
                return false;
            }
        }
        return true;
    }
}
