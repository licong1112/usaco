/*
ID: licong12
LANG: JAVA
TASK: money
*/
/**
 * Practiced on 2/19/2014
 * 
 * Note solve2() function is more space efficient. Note that multiplier should
 * start from 1 in this case, and j has to go from n down to 0.
 */
package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class money {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		money test = new money();
		test.start();
	}
	
	public void solve() throws IOException {
		int v = readInt(), n = readInt();
		int[] coin = new int[v];
		for (int i = 0; i < v; ++i) {
			coin[i] = readInt();
		}
		long[][] dp = new long[v][n+1];
		for (int i = 0; i*coin[0] <= n; ++i) {
			dp[0][coin[0]*i] = 1;
		}
		
		for (int i = 1; i < v; ++i) {
			for (int j = 0; j <= n; ++j) {
				int multiplier = 0;
				while (j-multiplier*coin[i] >= 0) {
					dp[i][j] += dp[i-1][j-multiplier*coin[i]];
					++multiplier;
				}
			}
		}
		
		out.println(dp[v-1][n]);
	}
	
	public void solve2(int v, int n, int[] coin) {
		long[] dp = new long[n+1];
		for (int i = 0; i*coin[0] <= n; ++i) {
			dp[coin[0]*i] = 1;
		}
		
		for (int i = 1; i < v; ++i) {
			for (int j = n; j >= 0; --j) {
				int multiplier = 1;
				while (j-multiplier*coin[i] >= 0) {
					dp[j] += dp[j-multiplier*coin[i]];
					++multiplier;
				}
			}
		}
		out.println(dp[n]);
	}

	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("money.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));*/
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