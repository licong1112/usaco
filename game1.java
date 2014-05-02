/*
ID: licong12
LANG: JAVA
TASK: game1
*/
/**
 * Practiced on 4/23/2014
 * 
 * Given data from array[start] to array[end], dp_first[start][end] stores
 * Player1's optimal solution if Plyaer1 starts first, while 
 * dp_second[start][end] stores Player1's optimal solution if Player2 starts
 * first.
 */
package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class game1 {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		game1 test = new game1();
		test.start();
	}
	
	public void solve() throws IOException {
		int n = readInt();
		int[] array = new int[n];
		int sum = 0;
		for (int i = 0; i < n; ++i) {
			array[i] = readInt();
			sum += array[i];
		}
		
		int[][] dp_first = new int[n][n];
		int[][] dp_second = new int[n][n];

		for (int i = 0; i < n-1; ++i) {
			dp_first[i][i+1] = array[i] > array[i+1] ? array[i] : array[i+1];
			dp_second[i][i+1] = array[i] > array[i+1] ? array[i+1] : array[i];
		}
		
		for (int length = 3; length <= n; ++length) {
			for (int start = 0; start < n-length+1; ++start) {
				int end = start+length-1;
				dp_first[start][end] = Math.max(array[start]+dp_second[start+1][end], 
												array[end]+dp_second[start][end-1]);
				
				dp_second[start][end] = Math.min(dp_first[start+1][end], 
												dp_first[start][end-1]);
			}
		}

		out.println(dp_first[0][n-1] + " " + (sum - dp_first[0][n-1]));
 	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("game1.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("game1.out")));*/
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