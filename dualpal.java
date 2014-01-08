/*
ID: licong12
LANG: JAVA
TASK: dualpal
*/
/**
 * Practiced on 11/15/2013
 */


package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class dualpal {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		dualpal test = new dualpal();
		test.start();
	}
	
	public void solve() throws IOException
	{
		int n = readInt();
		int s = readInt()+1;
		
		int count = 0;
		int num_pal = 0;
		while (count < n) {
			num_pal = 0;
			for (int base = 2; base <= 10; ++base) {
				if (isPalindrome(convert(s, base))) {
					++num_pal;
				}
				if (num_pal == 2) {
					out.println(s);
					++count;
					break;
				}
			}
			if (s == Integer.MAX_VALUE) return;
			++s;
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
			/*in = new BufferedReader(new FileReader("dualpal.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));*/
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
