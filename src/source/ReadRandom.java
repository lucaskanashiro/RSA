package src.source;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class ReadRandom {
	
	public String getSeed(int seed_length) {
		try {
	        
	        File file = new File("/dev/urandom");
	        InputStream is = new FileInputStream(file);
	
	        byte[] bytes = new byte[seed_length];
	
	        is.read(bytes);
	        is.close();
	
	        String s = new String();
	
	        for (int i = 0; i < bytes.length; i++) {
	            s += ((int) bytes[i] & 0xFF);
	        }
	
	        return s;
	
	    } catch (Exception e) {
	        System.out.println("Error getting the seed!");
	        return "ERROR";
	    }
	}
}
