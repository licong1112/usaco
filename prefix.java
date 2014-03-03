/*
ID: licong12
LANG: JAVA
TASK: prefix
*/
/**
 * Practiced on 2/16/2014
 */
package com.congli.usaco;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class prefix {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		prefix test = new prefix();
		test.start();
	}
	
	public void solve() throws IOException {
		StringBuilder sb = new StringBuilder();
		String str = null;
		while (!(str = in.readLine()).equals(".")) {
			sb.append(str);
			sb.append(" ");
		}

		String[] prefix = sb.toString().split(" ");
		HashSet<String> set = new HashSet<String>();
		int max_prefix_size = 0;
		for (String s : prefix) {
			set.add(s);
			max_prefix_size = Math.max(max_prefix_size, s.length());
		}
		sb = new StringBuilder();
		while ((str = in.readLine()) != null) {
			sb.append(str);
		}
		
		boolean[] dp = new boolean[sb.length()];
		
		for (String s : prefix) {
			if (sb.substring(0, s.length()).equals(s)) {
				dp[s.length()-1] = true;
			}
		}
		for (int i = 0; i < sb.length(); ++i) {
			if (!dp[i]) {
				for (int j = 1; j <= max_prefix_size && i-j >= 0; ++j) {
					if (dp[i-j] && set.contains(sb.substring(i-j+1, i+1))) {
						dp[i] = true;
						break;
					}
				}
			}
		}
		
		for (int i = sb.length()-1; i >= 0; --i) {
			if (dp[i]) {
				out.println(i+1);
				return;
			}
		}
		out.println(0);
 	}

	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("prefix.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));*/
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