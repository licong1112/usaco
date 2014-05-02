/*
ID: licong12
LANG: JAVA
TASK: kimbits
*/
/**
 * Practiced on 3/23/2014.
 * 
 * The following solution is based on the analysis provided by USACO.
 */
package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class kimbits {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		kimbits test = new kimbits();
		test.start();
	}
	
	public void solve() throws IOException {
		int num_bits = readInt();
		int num_ones = readInt();
		long num = readLong();
		
		int[][] set_size = new int[num_bits+1][num_ones+1];
		for (int i = 0; i <= num_ones; ++i) {
			set_size[0][i] = 1;
		}
		
		for (int i = 1; i <= num_bits; ++i) {
			set_size[i][0] = 1;
			for (int j = 1; j <= num_ones; ++j) {
				set_size[i][j] = set_size[i-1][j] // start with '0'
								+ set_size[i-1][j-1];  // start with '1'
			}
		}
		
		for (int i = num_bits; i > 0; --i) {
			if (num <= set_size[i-1][num_ones]) {
				out.print('0');
			} else {
				out.print('1');
				num -= set_size[i-1][num_ones];
				--num_ones;
			}
		}
		out.println();
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("kimbits.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));*/
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
