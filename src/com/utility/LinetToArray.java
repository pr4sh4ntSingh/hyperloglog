package com.utility;

import java.util.Arrays;

public class LinetToArray {

	public static int[] convertLineToArray(String line) {
		String lineList[] = line.split("\t");
		int inputVector[] = new int[lineList.length];
		for (int i = 0; i < inputVector.length; i++) {
			inputVector[i] = Integer.parseInt(lineList[i]);

		}
		return inputVector;

	}

	public static void main(String args[]) {
		String s = "2	3	3	3	2	4	4";
		int inputVector[] = convertLineToArray(s);
		System.out.println(Arrays.toString(inputVector));
	}
}
