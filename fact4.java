/*
ID: licong12
LANG: JAVA
TASK: fact4
*/
/**
 * Practiced on 3/21/2014
 * 
 * Only 2*5 can produce 0. So remove all {2, 5} pairs first. Then multiply
 * all left numbers, but only need to keep the last digit on each multiplication.
 */
package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class fact4 {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		fact4 test = new fact4();
		test.start();
	}
	
	public void solve() throws IOException {
		int n = readInt();
		
		int[] array = new int[n];
		for (int i = 0; i < n; ++i) {
			array[i] = i+1;
		}
		
		int count_five = 0;
		for (int i = 4; i < n; i+=5) {
			while (array[i]%5 == 0) {
				array[i] /= 5;
				++count_five;
			}
		}
		
		for (int i = 1; i < n; i+=2) {
			while (count_five > 0 && array[i]%2 == 0) {
				array[i] /= 2;
				--count_five;
			}
		}
		
		int result = 1;
		for (int i = 0; i < n; ++i) {
			result *= array[i];
			while (result % 10 == 0) {
				result /= 10;
			}
			while (result >= 10) {
				result %= 10;
			}
		}
		out.println(result);
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("fact4.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));*/
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
