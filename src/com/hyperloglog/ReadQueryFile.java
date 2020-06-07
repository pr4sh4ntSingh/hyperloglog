package com.hyperloglog;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.utility.LinetToArray;

public class ReadQueryFile {

	public static void main(String args[]) throws IOException {
		
	String inputFile = args[0];
	BufferedReader br = new BufferedReader(new FileReader(inputFile));
	String st;


	while ((st = br.readLine()) != null) {
		// System.out.println(st);
		int array[] = LinetToArray.convertLineToArray(st);		
	    
	}
	}
}
