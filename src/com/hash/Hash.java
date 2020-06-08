package com.hash;

import com.hash.MurmurHash3.LongPair;

public class Hash {

	public static int getHashValue(String word, int seed) {
		int hashvalue = Math.abs((MurmurHash3.murmurhash3_x86_32(word, 0, word.length(), seed)));
		return hashvalue;
	}
	
	

	//public static int getHashValue64(String word, int seed) {
		//LongPair p=new LongPair();
		//int hashvalue = Math.abs((MurmurHash3.murmurhash3_x64_128(word, 0, word.length(), seed,p)));
		//return hashvalue;
	//}


	
	public static void main(String args[]) {
		System.out.print(getHashValue("127.0.0.1",34));
	}

}