/*
ID: licong12
LANG: JAVA
TASK: stamps
*/
/**
 * Practiced on 3/19/2014.
 */
package com.congli.usaco;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class stamps {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		stamps test = new stamps();
		test.start();
	}
	
	public void solve() throws IOException {
		int K = readInt(), N = readInt();
		int[] stamp = new int[N];
		for (int i = 0; i < N; ++i) {
			stamp[i] = readInt();
		}
		
		Arrays.sort(stamp);
		
		int[] dp = new int[10000*200+1];
		dp[0] = 0;
		int num = 1;
		int min = 0;
		while (true) {
			min = Integer.MAX_VALUE;
			for (int i = 0; i < N && num - stamp[i] >= 0; ++i) {
				min = Math.min(min, dp[num-stamp[i]]);
			}
			if (min == K) {
				out.println(num-1);
				return;
			}
			dp[num] = min+1;
			++num;
		}
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("stamps.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));*/
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
