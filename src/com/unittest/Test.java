package com.unittest;

public class Test {

	public static void main(String args[]) {
		System.out.println(padLeft("10202", 6));
	}
	
	public static String padLeft(String s, int n) {
	    return String.format("%" + n + "s", s).replace(' ', '0');
	}
}
