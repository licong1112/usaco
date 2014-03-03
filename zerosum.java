/*
ID: licong12
LANG: JAVA
TASK: zerosum
*/
/**
 * Practiced on 2/18/2014
 * 
 * A simple DFS.
 */
package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class zerosum {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		zerosum test = new zerosum();
		test.start();
	}
	
	public void solve() throws IOException {
		int n = readInt();
		dfs(0, 1, 2, n, new char[n+1], true);
	}
	
	public void dfs(int sum, int partial_sum, int level, int n, char[] result, boolean pre_plus) {
		int part_sum = pre_plus ? sum+partial_sum : sum-partial_sum;
		
		if (level == n+1) {
			if (part_sum == 0) {
				for (int i = 1; i < n; ++i) {
					out.print(i);
					out.print(result[i+1]);
				}
				out.println(n);
			}
			return;
		}
		
		// blank
		result[level] = ' ';
		dfs(sum, partial_sum*10+level, level+1, n, result, pre_plus);
		
		// plus
		result[level] = '+';
		dfs(part_sum, level, level+1, n, result, true);
		
		// minus
		result[level] = '-';
		dfs(part_sum, level, level+1, n, result, false);
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("zerosum.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));*/
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