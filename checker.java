/*
ID: licong12
LANG: JAVA
TASK: checker
*/
/**
 * Practiced on 1/7/2013
 */
package com.congli.usaco;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class checker {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	private static int count = 0;
	
	public static void main(String[] args) {
		checker test = new checker();
		test.start();
	}
	
	public void solve() throws IOException {
		count = 0;
		int n = readInt();
		int[] col_ind = new int[n];
		for (int i = 0; i < n; ++i) {
			col_ind[i] = -1;
		}
		boolean[] col_visited = new boolean[n];
		boolean[] diag_visited_1 = new boolean[2*n-1];
		boolean[] diag_visited_2 = new boolean[2*n-1];
		dfs(col_visited, diag_visited_1, diag_visited_2, col_ind, 0, n);
		out.println(count);
	}
	
	public void dfs(boolean[] col_visited, boolean[] diag_visited_1, boolean[] diag_visited_2, int[] col_ind, int row, int size) {
		if (row == size) {
			if (count < 3) {
				for (int i = 0; i < size-1; ++i) {
					out.print(col_ind[i]+1 + " ");
				}
				out.println(col_ind[size-1]+1);
			}
			++count;
			return;
		}
		for (int col = 0; col < size; ++col) {
			if (!col_visited[col] && !diag_visited_1[row+col] && !diag_visited_2[row-col+size-1]) {
				col_visited[col] = true;
				diag_visited_1[row+col] = true;
				diag_visited_2[row-col+size-1] = true;
				
				col_ind[row] = col;
				dfs(col_visited, diag_visited_1, diag_visited_2, col_ind, row+1, size);
				col_ind[row] = -1;
				
				col_visited[col] = false;
				diag_visited_1[row+col] = false;
				diag_visited_2[row-col+size-1] = false;
			}
		}
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("checker.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("checker.out")));*/
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


