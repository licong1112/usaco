/*
ID: licong12
LANG: JAVA
TASK: subset
*/
/**
 * Practiced on 2/9/2014
 */
package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class subset {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		subset test = new subset();
		test.start();
	}
	
	public void solve() throws IOException {
		int n = readInt();
		int sum = (1+n)*n/2;
		if (sum % 2 == 1) {
			out.println(0);
			return;
		}
		sum /= 2;
		long[] dp = new long[sum+10];
		dp[0] = 1;
		
		for (int i = 1; i <= n; ++i) {
			for (int j = sum; j >= i; --j) {
				dp[j] += dp[j-i];
			}
		}

		out.println(dp[sum]/2);
 	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("subset.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));*/
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


