/*
ID: licong12
LANG: JAVA
TASK: palsquare
*/

/**
 * Practiced on 11/14/2013
 */

package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class palsquare {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		palsquare test = new palsquare();
		test.start();
	}
	
	public void solve() throws IOException
	{
		int base = readInt();
		String num = "", num_square = "";
		for (int i = 1; i <= 300; ++i) {
			num = convert(i, base);
			num_square = convert(i*i, base);
			if (isPalindrome(num_square)) {
				out.println(num + " " + num_square);
			}
		}	
	}
	
	public boolean isPalindrome(String str) {
		int start = 0, end = str.length()-1;
		while (start <= end) {
			if (str.charAt(start++) != str.charAt(end--)) {
				return false;
			}
		}
		return true;
	}
	
	public String convert(int num, int base) {
		String result = "";
		int mod = 0;
		while (num != 0) {
			mod = num % base;
			result = (mod > 9 ? (char)(mod - 10 + 'A') : (char)(mod + '0')) + result;
			num /= base;
		}
		return result;
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("palsquare.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));*/
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
