package src.source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadRandom {
	
	private File file;
	private FileReader fr;
	private BufferedReader io;
	
	public String Random(int numberOfDigits){
		String result = "" ;
		
		try {
			file = new File("/dev/urandom");
			fr = new FileReader(file);
		    io = new BufferedReader(fr);
		    	    
		    if(io.read() != 0){
		    	for(int i=0;i<numberOfDigits;i++)
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
