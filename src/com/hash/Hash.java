package com.hash;

public class Hash {

	public static int getHashValue(String word, int seed) {
		int hashvalue = Math.abs((MurmurHash3.murmurhash3_x86_32(word, 0, word.length(), seed)));
		return hashvalue;
	}


	
	public static void main(String args[]) {
		System.out.print(getHashValue("127.0.0.1",34));
	}

}