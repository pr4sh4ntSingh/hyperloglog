package com.hyperloglog;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import com.hyperloglog.HyperLogLog;
import com.hash.Hash;

public class cdestimate {

	public static void main(String args[]) throws NumberFormatException, IOException {

		String fileName = args[0];

		HLLSketch.createSketches(fileName);

		String queryFile = args[1];

		BufferedReader br = new BufferedReader(new FileReader(queryFile));
		String st;

		while ((st = br.readLine()) != null) {
			double answer=ReadQueryFile.evaluateQuery(st);
			
			System.out.println(answer+"*********");
			
		}

	}

}
