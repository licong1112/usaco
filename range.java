/*
ID: licong12
LANG: JAVA
TASK: range
*/
/**
 * Practiced on 4/22/2014
 * 
 * An O(n^2) algorithm:
 * 
 * 1. For (i,j)-th element, calculate the largest square size with (i,j) as its
 *    lower-right point. Note how to do this calculation. Need to remember!
 * 2. For the square of each size s, count how many squares have size s.
 * 3. Note that if there is a square with size s, whose lower-right point is 
 *    (i,j), then there must exist squares with size 2 ~ (s-1), whose
 *    lower-right points are also (i,j). Therefore we need to accumulate
 *    the number of squares.
 */
package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class range {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		range test = new range();
		test.start();
	}
	
	public void solve() throws IOException {
		int n = readInt();
		int[][] field = new int[n][n];
		for (int i = 0; i < n; ++i) {
			String str = in.readLine();
			for (int j = 0; j < n; ++j) {
				field[i][j] = str.charAt(j)-'0';
			}
		}
		
		for (int i = 1; i < n; ++i) {
			for (int j = 1; j < n; ++j) {
				if (field[i][j] == 1) { // Size of the largest square
					field[i][j] = Math.min(Math.min(field[i-1][j], field[i][j-1]), 
											field[i-1][j-1])+1;
				}
			}
		}
		
		// Count how many squares have size s
		int[] result = new int[n+1];
		for (int i = 1; i < n; ++i) {
			for (int j = 1; j < n; ++j) {
				result[field[i][j]]++;
			}
		}
		
		// Accumulate the number of squares to smaller sizes.
		for (int i = n-1; i > 1; --i) {
			result[i] += result[i+1];
		}
		
		for (int i = 2; i <= n; ++i) {
			if (result[i] == 0) break;
			out.println(i + " " + result[i]);
		}
 	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("range.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("range.out")));*/
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