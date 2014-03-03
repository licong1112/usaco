/*
ID: licong12
LANG: JAVA
TASK: sort3
*/
/**
 * Practiced on 1/23/2014
 */
package com.congli.usaco;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class sort3 {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		sort3 test = new sort3();
		test.start();
	}
	
	public void solve() throws IOException {
		int n = readInt();
		int[] array = new int[n];
		int[] sorted = new int[n];
		for (int i = 0; i < n; ++i) {
			array[i] = readInt();
			sorted[i] = array[i];
		}
		Arrays.sort(sorted);
		int[][] matrix = new int[4][4];
		for (int i = 0; i < n; ++i) {
			if (array[i] != sorted[i]) {
				matrix[array[i]][sorted[i]]++;
			}
		}
		int count = 0;
		count += countLengthTwoLoop(matrix, 1, 2);
		count += countLengthTwoLoop(matrix, 1, 3);
		count += countLengthTwoLoop(matrix, 2, 3);
		
		// Only length-3 loop left. It takes two switches.
		for (int i = 1; i < 4; ++i) {
			for (int j = 1; j < 4; ++j) {
				if (matrix[i][j] != 0) {
					count += (2*matrix[i][j]); // Note multiply by 2
					out.println(count);
					return;
				}
			}
		}
		out.println(count);
	}
	
	public int countLengthTwoLoop(int[][] matrix, int end1, int end2) {
		int count_loop = Math.min(matrix[end1][end2], matrix[end2][end1]);
		matrix[end1][end2] -= count_loop;
		matrix[end2][end1] -= count_loop;
		return count_loop;
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("sort3.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));*/
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


