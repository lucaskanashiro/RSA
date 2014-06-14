package source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;


public class ReadRandom {
	private File file;
	private FileReader fr;
	private BufferedReader io;
	
	
	public ReadRandom(){
		
	}
	
	public String Random(){
		String result = "" ;
		
		try {
			file = new File("/dev/random");
			fr = new FileReader(file);
		    io = new BufferedReader(fr);
		    	    
		    if(io.read() != 0){
		    	for(int i=0;i<7;i++)
		    		result +=io.read();
		    }
		    
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
}
