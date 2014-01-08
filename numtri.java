/*
ID: licong12
LANG: JAVA
TASK: numtri
*/
/**
 * Practiced on 1/4/2014
 */

package com.congli.usaco;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class numtri {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		numtri test = new numtri();
		test.start();
	}
	
	public void solve() throws IOException
	{
		int num_row = readInt();
		int[][] triangle = new int[num_row][num_row];
		for (int i = 0; i < num_row; ++i) {
			for (int j = 0; j <= i; ++j) {
				triangle[i][j] = readInt();
			}
		}
		
		int[][] dp = new int[num_row][num_row];
		for (int i = 0; i < num_row; ++i) {
			dp[num_row-1][i] = triangle[num_row-1][i];
		}
		
		for (int row = num_row-2; row >= 0; --row) {
			for (int col = 0; col <= row; ++col) {
				dp[row][col] = triangle[row][col] + Math.max(dp[row+1][col], dp[row+1][col+1]);
			}
		}
		out.println(dp[0][0]);
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("numtri.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));*/
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


