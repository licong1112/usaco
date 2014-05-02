/*
ID: licong12
LANG: JAVA
TASK: ratios
*/
/**
 * Practiced on 3/26/2014
 * 
 * Another solution can be Cramer's rule for solving linear equation.
 */
package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class ratios {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		ratios test = new ratios();
		test.start();
	}
	
	public void solve() throws IOException {
		int[] goal = new int[3];
		for (int i = 0; i < 3; ++i) {
			goal[i] = readInt();
		}
		int[][] mix = new int[3][3];
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				mix[i][j] = readInt();
			}
		}
		
		int[] result = new int[3];
		int min_sum = Integer.MAX_VALUE;
		for (int i = 0; i < 100; ++i) {
			for (int j = 0; j < 100; ++j) {
				for (int k = 0; k < 100; ++k) {
					if (isValid(mix, goal, i, j, k) && (i+j+k < min_sum)) {
						min_sum = i+j+k;
						result[0] = i;
						result[1] = j;
						result[2] = k;
					}
				}
			}
		}
		if (min_sum == Integer.MAX_VALUE) {
			out.println("NONE");
			return;
		}
		out.print(result[0] + " " + result[1] + " " + result[2] + " ");
		for (int i = 0; i < 3; ++i) {
			if (goal[i] != 0) {
				out.println((result[0]*mix[0][i] + result[1]*mix[1][i] + result[2]*mix[2][i]) / goal[i]);
				return;
			}
		}
		out.println(0);
	}
	
	public boolean isValid(int[][] mix, int[] goal, int i, int j, int k) {
		int numerator = i*mix[0][0] + j*mix[1][0] + k*mix[2][0];
		int quotient = 0;
		if (goal[0] == 0 || numerator == 0) {
			if (goal[0] != numerator) return false;
		} else {
			if (numerator % goal[0] != 0) return false;
			quotient = numerator / goal[0];
		}
		
		for (int col = 1; col <= 2; ++col) {
			numerator = i*mix[0][col] + j*mix[1][col] + k*mix[2][col];
			if (goal[col] == 0 || numerator == 0) {
				if (goal[col] != numerator ) return false;
			} else {
				if (numerator % goal[col] != 0) return false;
				if (numerator / goal[col] != quotient) return false;
			}
		}
		
		return true;
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("ratios.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("ratios.out")));*/
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
