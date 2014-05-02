/*
ID: licong12
LANG: JAVA
TASK: inflate
*/
/**
 * Practiced on 3/5/2014
 * 
 * Knapsack problem.
 */
package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class inflate {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		inflate test = new inflate();
		test.start();
	}
	
	public void solve() throws IOException {
		int total_min = readInt(), n = readInt();
		int[] point = new int[n];
		int[] minute = new int[n];
		int minimal_min = Integer.MAX_VALUE;
		for (int i = 0; i < n; ++i) {
			point[i] = readInt();
			minute[i] = readInt();
			minimal_min = Math.min(minimal_min, minute[i]);
		}
		
		int[] dp = new int[total_min+1];
		for (int i = minimal_min; i <= total_min; ++i) {
			for (int j = 0; j < n; ++j) {
				if (i - minute[j] >= 0) {
					dp[i] = Math.max(dp[i], dp[i-minute[j]] + point[j]);
				}
			}
		}
		
		out.println(dp[total_min]);
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("inflate.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));*/
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
