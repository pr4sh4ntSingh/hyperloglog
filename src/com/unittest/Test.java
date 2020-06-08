package com.unittest;

import java.util.Arrays;

import com.hyperloglog.HyperLogLog;

public class Test {

	public static void main(String args[]) {
		// System.out.println(padLeft("10202", 6));
		int hmm[] = new int[] { 1, 2, 3, 4, 5, 6 };
		int hmm2[] = new int[] { 10, 1, 30, 2, 44, 3 };

		int unionar[] = union(hmm, hmm2);
		System.out.println(Arrays.toString(unionar));

		System.out.println(HyperLogLog.calculateCardinality3(hmm));

	}

	public static String padLeft(String s, int n) {
		return String.format("%" + n + "s", s).replace(' ', '0');
	}

	public static int[] union(int[] sketch1, int[] sketch2) {
		int union[] = new int[sketch1.length];
		for (int i = 0; i < sketch1.length; i++) {

			int max = sketch1[i] > sketch2[i] ? sketch1[i] : sketch2[i];
			union[i] = max;
		}

		return union;
	}
}
