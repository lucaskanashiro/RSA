package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

import source.RSA;

public class Main {
	
	public static void main(String[] args) {
		if(args[0].equals("-genKeys") && args.length == 4 ){
			genKeys(args);			
		} else if(args[0].equals("-encrypt") && args.length == 4){
			alterMessage(args,0);			
		}else if(args[0].equals("-decrypt") && args.length == 4){
			alterMessage(args, 1);
		}else{
			useMessage();
		}
	}	
	
	public static void useMessage(){
		System.out.println("-----------------------------");
		System.out.println("\nYou should use :");
		
		System.out.println("\nTo Generate Keys use :\n"
						 + "	-genKeys [<numberOfBits>] <publicKeyFile>.txt <privateKeyFile>.txt");
		
		System.out.println("\nTo Encrypt Text use :\n"
						 + "	-encrypt <privateKeyFile>.txt <textFile>.txt <cypherText>.txt");
		
		System.out.println("\nTo Decrypt Text use :\n"
						 + "	-decrypt <publicKeyFile>.txt <textFile>.txt <decypherText>.txt");
		
		System.out.println("-----------------------------");
	}
	
	public static void genKeys(String[] args){
		RSA rsa = new RSA();
		System.out.println("Generating keys...");

		rsa.generateKeys(Integer.parseInt(args[1]));
		
		String publicKey = new String(rsa.getPublicKey().toString());
		String privateKey = new String(rsa.getPrivateKey().toString());
		String modulus = new String(rsa.getModulusKey().toString());
		
		String textPrivateKey = privateKey + "::"+ modulus;
		String privateKeyFile = args[2];
		writeFile(privateKeyFile,textPrivateKey);
		
		String textPublicKey = publicKey + "::" +modulus;
		String publicKeyFile = args[3];
		writeFile(publicKeyFile, textPublicKey);
		
		System.out.println("The Keys was generated.");

	}
	
	public static void alterMessage(String []args,int mode){
		RSA rsa = new RSA();
		
		String KeyFile = args[1];
		String textFile = args[2];
		
		String textKey = readFile(KeyFile);
	
		String[] values = textKey.split("::");
		rsa.setModulus(new BigInteger(values[1]));
		String text = readFile(textFile);
		
		if(mode == 0){
			
			rsa.setPrivateKey(new BigInteger(values[0]));
			String cyherText = new String(rsa.encrypt(new BigInteger(text.getBytes())).toString());
			writeFile(args[3], cyherText);
		
		}else{
			rsa.setPublicKey(new BigInteger(values[0]));
			String decyherText = new String(rsa.decrypt(new BigInteger(text)).toByteArray());
			writeFile(args[3], decyherText);
		
		}
	}
		
	public static String readFile(String fileName){
		String text = "";
		try{
	      File file = new File(fileName);
          BufferedReader input = new BufferedReader(new FileReader(file));
          text = input.readLine();
          input.close();
		}catch(Exception e){
			System.out.println("Error reading file : "+ fileName);
		}
        return text;		
	}
	
	public static void writeFile(String fileName,String text){
		try{
			File file = new File(fileName);
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			output.write(text);
			output.close();
		}catch(Exception e){
			System.out.println("Error writing the file");
		}		
	}
	
	
}
