/*
ID: licong12
LANG: JAVA
TASK: namenum
*/

/**
 * Practiced on 11/13/2013
 * 
 * Watch out this kind of question that are related to dictionary..
 */

package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class namenum {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		namenum test = new namenum();
		test.start();
	}
	
	public void solve() throws IOException
	{
		BufferedReader dict_reader = new BufferedReader(new FileReader("/Users/congli/Documents/javaworkspace/usaco/src/com/congli/usaco/dict.txt"));
		
		//BufferedReader dict_reader = new BufferedReader(new FileReader("dict.txt"));
		char[] chars = readString().toCharArray();
		int[] digits = new int[chars.length];
		for (int i = 0; i < chars.length; ++i) {
			digits[i] = chars[i] - '0';
		}
		
		String name = " ";
		int count = 0;
		while (name != null) {
			name = dict_reader.readLine();
			if (name != null && name.length() == digits.length && processWord(name, digits)) {
				++count;
			}
		}
		if (count == 0) {
			out.println("NONE");
		}
			
		dict_reader.close();
	}
	
	public boolean processWord(String word, int[] digits) {
		for (int i = 0; i < word.length(); ++i) {
			if (getDigit(word.charAt(i)) != digits[i]) return false;
		}
		out.println(word);
		return true;
	}
	
	public int getDigit(char c) {
		int digit = c - 'A';
		return digit < 16 ? digit/3+2 : (digit-1)/3+2;
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("namenum.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));*/
			in = new BufferedReader(new FileReader("/Users/congli/Documents/javaworkspace/usaco/src/com/congli/usaco/input.txt"));
			out = new PrintWriter(System.out);
			solve();
			in.close();
			out.close();
		} catch (Throwable t) {
			// TODO Auto-generated catch block
			t.printStackTrace();
		}
	}
	
	public String readString() throws IOException {
		while (!tok.hasMoreTokens()) {
			tok = new StringTokenizer(in.readLine());
		}
		return tok.nextToken();
	}

	public int readInt() throws IOException {
		return Integer.parseInt(readString());
	}

	public long readLong() throws IOException {
		return Long.parseLong(readString());
	}

	public double readDouble() throws IOException {
		return Double.parseDouble(readString());
	}	
}
