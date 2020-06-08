package com.hyperloglog;

import java.util.ArrayList;
import java.util.Arrays;

import com.hash.Hash;

public class HyperLogLog {
	
	
	public static int[] hyperloglog(String[] vector, int p) {


		int noOfBuckets = (int) Math.pow(2, p);
		int hashArray[] = new int[noOfBuckets];

		for (String str : vector) {
			int hashValue = Hash.getHashValue(str, 40);

			// create hash of string
			

			String stringHash = Integer.toBinaryString(hashValue);

			String stringHash2 = padLeft(stringHash, 32);

			System.out.println(stringHash2);

			char[] strHash = stringHash2.toCharArray();

			// take out p bits and use it as index
			char[] indexArray = Arrays.copyOfRange(strHash, 0, p + 1);

			int index = convertBinarytoInt(indexArray);
			char[] slicedArray = Arrays.copyOfRange(strHash, p + 1, strHash.length);

			int prev_value = hashArray[index];

			int trailing_zeros = getTrailingZeros(slicedArray);
			int max = prev_value > trailing_zeros ? prev_value : trailing_zeros;

			hashArray[index] = max;

			String s2 = printString(strHash);
			System.out.println(s2 + " : " + trailing_zeros + " : " + index);

		}
		System.out.println(Arrays.toString(hashArray));

		return hashArray;
	}

	
	public static void hyperlogloForOneStream(int[] HLL,String item, int p) {
	

		int noOfBuckets = HLL.length;

		
			int hashValue = Hash.getHashValue(item, 40);

			// create hash of string

			String stringHash = Integer.toBinaryString(hashValue);

			String stringHash2 = padLeft(stringHash, 32);

			System.out.println(stringHash2);

			char[] strHash = stringHash2.toCharArray();

			// take out p bits and use it as index
			char[] indexArray = Arrays.copyOfRange(strHash, 0, p + 1);

			int index = convertBinarytoInt(indexArray);
			char[] slicedArray = Arrays.copyOfRange(strHash, p + 1, strHash.length);

			int prev_value = HLL[index];

			int trailing_zeros = getTrailingZeros(slicedArray);
			int max = prev_value > trailing_zeros ? prev_value : trailing_zeros;

			HLL[index] = max;

			//String s2 = printString(strHash);
			//System.out.println(s2 + " : " + trailing_zeros + " : " + index);

		
			//System.out.println(Arrays.toString(HLL));

			//return HLL;
	}

	
	
	public static double calculateCardinality(int[] HLL) {
		double sum = 0;
		for (int num : HLL) {
			if (num != 0)
				sum += (num);
		}

		int m = HLL.length;
		double avg = sum / m;
		//System.out.println(hMean);
		double constant = 0.79402;
		double cardinality = constant * m * Math.pow(2, avg);
		return cardinality;

	}

	public static double calculateCardinality2(int[] HLL) {
		double sum = 0;
		for (int num : HLL) {
			if (num != 0) {
				double hm = Math.pow(2, -num); // 2^-r
				sum = sum + hm;
			}

		}

		int m = HLL.length;
		double hMean = m / sum;
		System.out.println(hMean+"**");
		double constant = 0.79402;
		double cardinality = constant * m * hMean;
		return cardinality;

	}
	
	
	public static double calculateCardinality3(int[] HLL) {
		double sum = 0;
		for (int num : HLL) {
			if (num != 0.0) {
				double hm = 1f/num; // 2^-r
				sum = sum + hm;
			}
			//System.out.println(sum);
		}

		int m = HLL.length;
		double hMean = m / sum;
		//System.out.println(hMean+"is harmonic mean");
		double constant = 0.79402;
		System.out.println(Math.pow(2,hMean));
		double cardinality = constant * m * Math.pow(2,hMean);
		return cardinality;

	}

	public static int getTrailingZeros(char[] arr) {
		int zeros = 0;
		for (int i = arr.length - 1; i >= 0; i--) {
			if (arr[i] == '0') {
				zeros++;
			} else {
				break;
			}

		}
		return zeros;

	}

	public static int convertBinarytoInt(char[] indexArray) {
		String binaryString = new String(indexArray);
		int decimal = Integer.parseInt(binaryString, 2);
		return decimal;
	}

	public static String printString(char[] array) {
		StringBuilder str = new StringBuilder();
		for (char s : array) {
			str.append(s);
		}
		return str.toString();
	}

	public static String padLeft(String s, int n) {
		return String.format("%" + n + "s", s).replace(' ', '0');
	}
}
