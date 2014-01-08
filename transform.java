/*
ID: licong12
LANG: JAVA
TASK: transform
*/

/**
 * Practiced on 11/12/2013
 * 
 * Just use brute force.
 */

package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class transform {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		transform test = new transform();
		test.start();
	}
	
	public void solve() throws IOException
	{
		int size = readInt();
		char[][] matrix_original = new char[size][size];
		char[][] matrix = new char[size][size];
		
		for (int i = 0; i < matrix_original.length; ++i) {
			String str = readString();
			for (int j = 0; j < matrix_original.length; ++j) {
				matrix[i] = str.toCharArray();
			}
		}
		for (int i = 0; i < matrix_original.length; ++i) {
			String str = readString();
			for (int j = 0; j < matrix_original.length; ++j) {
				matrix_original[i] = str.toCharArray();
			}
		}
		
		int first_judge = judgeFirstThree(matrix_original, matrix); 
		if (first_judge != -1) {
			out.println(first_judge);
			return;
		}
		rotate(matrix);
		reflect(matrix);
		if (equal(matrix_original, matrix)) {
			out.println(4);
			return;
		}
		if (judgeFirstThree(matrix_original, matrix) != -1) {
			out.println(5);
			return;
		}
		rotate(matrix);
		reflect(matrix);
		if (equal(matrix_original, matrix)) {
			out.println(6);
			return;
		}
		out.println(7);
	}
	
	public void reflect(char[][] matrix) {
		char temp = ' ';
		for (int col = 0; col < matrix.length/2; ++col) {
			for (int row = 0; row < matrix.length; ++row) {
				temp = matrix[row][col];
				matrix[row][col] = matrix[row][matrix.length-col-1];
				matrix[row][matrix.length-col-1] = temp;
			}
		}
	}
	
	public int judgeFirstThree(char[][] matrix_original, char[][] matrix) {
		rotate(matrix);
		if (equal(matrix_original, matrix)) return 1;
		rotate(matrix);
		if (equal(matrix_original, matrix)) return 2;
		rotate(matrix);
		if (equal(matrix_original, matrix)) return 3;
		return -1;
	}
	
	public void rotate(char[][] matrix) {
		int n = matrix.length;
		for(int layer = 0; layer < n/2; ++layer)
        {
            for(int j = layer; j < n-layer-1; ++j)
            {
                char temp = matrix[layer][j];
                matrix[layer][j] = matrix[n-j-1][layer];
                matrix[n-j-1][layer] = matrix[n-layer-1][n-j-1];
                matrix[n-layer-1][n-j-1] = matrix[j][n-layer-1];
                matrix[j][n-layer-1] = temp;
            }
        }
	}
	
	public boolean equal(char[][] a, char[][] b) {
		if (a.length != b.length || a[0].length != b[0].length) return false;
		for (int i = 0; i < a.length; ++i) {
			for (int j = 0; j < a[0].length; ++j) {
				if (a[i][j] != b[i][j]) return false;
			}
		}
		return true;
	}
	
	public void start()
	{
		try {
			in = new BufferedReader(new FileReader("transform.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
			/*in = new BufferedReader(new FileReader("/Users/congli/Documents/javaworkspace/usaco/src/com/congli/usaco/input.txt"));
			out = new PrintWriter(System.out);*/
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
