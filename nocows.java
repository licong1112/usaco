/*
ID: licong12
LANG: JAVA
TASK: nocows
*/
/**
 * Practced on 2/16/2014
 * 
 * dp[i][j] represents how many different structures there are with i nodes
 * and height j.
 * 
 * Key idea:
 * A tree with height h can be constructed by two subtrees, while at least
 * one subtree must have height h-1.
 * 
 * The time complexity can be improved (the inner-most for-loop can be eliminated)
 * if additional memory is used to store the number of trees in total with i nodes
 * and height less than j.
 */
package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class nocows {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		nocows test = new nocows();
		test.start();
	}
	
	public void solve() throws IOException {
		int num_nodes = readInt(), height = readInt();
		int[][] dp = new int[num_nodes+1][height+1];
		dp[1][1] = 1;
		
		int MOD = 9901;
		int result_one_side = 0;
		int other_side = 0;
		
		for (int h = 2; h <= height; ++h) {
			int n_bound = h < 32 ? Math.min((1<<h)-1, num_nodes) : num_nodes;
			for (int n = 2*h-1; n <= n_bound; n += 2) {
				for (int one_side = 1; one_side < n; one_side += 2) {
					result_one_side = 0;
					other_side = n-1-one_side;
					
					// Only one subtree has height h-1
					for (int h_temp = 1; h_temp < h-1; ++h_temp) {
						result_one_side += dp[one_side][h_temp];
					}
					dp[n][h] += (2 * (result_one_side % MOD) * dp[other_side][h-1]) % MOD;
					
					// Both subtrees have height h-1
					dp[n][h] += (dp[one_side][h-1] * dp[other_side][h-1]) % MOD;
				}
				dp[n][h] %= MOD;
			}
		}
		out.println(dp[num_nodes][height]);
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("nocows.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));*/
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