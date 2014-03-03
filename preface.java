/*
ID: licong12
LANG: JAVA
TASK: preface
*/
/**
 * Practiced on 2/7/2014
 */

package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class preface {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		preface test = new preface();
		test.start();
	}
	
	public void solve() throws IOException {
		int n = readInt();
		final char[] roman_chars = {'I', 'V', 'X', 'L', 'C', 'D', 'M'}; 
		int[] count = new int[7];
		int num = 0;
		for (int i = 1; i <= n; ++i) {
			int index = -1;
			num = i;
			while(num > 0) {
				int digit = num % 10;
				num /= 10;
				++index;
				if(digit != 0) {
					processDigit(digit, index, count);
				}
			}
		}
		for (int i = 0; i < 7; ++i) {
			if (count[i] != 0) {
				out.println(roman_chars[i] + " " + count[i]);
			}
		}
	}
	
	private void processDigit(int digit, int index, int[] count)
	{
		if(digit == 4 || digit == 9) {
			count[index*2]++;
			if (digit == 4) {
				count[index*2+1]++;
			} else {
				count[index*2+2]++;
			}
		} else {
			if (digit <= 3) {
				count[index*2] += digit;
			} else {
				count[index*2+1]++;
				count[index*2] += (digit-5);
			}
		}
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("preface.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));*/
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


