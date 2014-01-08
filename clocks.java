/*
ID: licong12
LANG: JAVA
TASK: clocks
*/

/**
 * Practiced on 12/13/2013
 * 
 * Key observation:
 * 1. Each one of the nine ways of moves can be executed at most 3 times.
 * 2. The order of the move doesn't matter. So start with the way of move
 *    with the smallest ID, then as long as a solution is found, it is the
 *    correct answer to the problem.
 */

package com.congli.usaco;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class clocks {
	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		clocks test = new clocks();
		test.start();
	}
	
	public void solve() throws IOException
	{
		int[] data = new int[9];
		for (int i = 0; i < 9; ++i) {
			data[i] = readInt() / 3;
		}
		
		int[][] matrix = {{4, 1, 2, 4, 5},
						  {3, 1, 2, 3},
						  {4, 2, 3, 5, 6},
						  {3, 1, 4, 7},
						  {5, 2, 4, 5, 6, 8},
						  {3, 3, 6, 9},
						  {4, 4, 5, 7, 8},
						  {3, 7, 8, 9},
						  {4, 5, 6, 8, 9}};
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		dfs(data, matrix, 0, result);
		for (int i = 0; i < result.size()-1; ++i) {
			out.print(result.get(i) + " ");
		}
		out.println(result.get(result.size()-1));
	}
	
	public boolean dfs (int[] data, int[][] matrix, int row, ArrayList<Integer> result) {
		if (isSuccess(data)) return true;
		if (row == 9) return false;
		
		for (int i = 0; i < 3; ++i) {
			transfer(data, matrix, row, true);
			result.add(row+1);
			if (dfs(data, matrix, row+1, result)) {
				return true;
			}
		}
		for (int i = 0; i < 3; ++i) {
			result.remove(result.size()-1);
			transfer(data, matrix, row, false);
		}
		return dfs(data, matrix, row+1, result);
	}
	
	public void transfer(int[] data, int[][] matrix, int row, boolean is_add) {
		int num_change = matrix[row][0];
		if (is_add) {
			for (int i = 0; i < num_change; ++i) {
				data[matrix[row][i+1]-1]++;
			}
		} else {
			for (int i = 0; i < num_change; ++i) {
				data[matrix[row][i+1]-1]--;
			}
		}
	}
	
	public boolean isSuccess(int[] data) {
		for (int i = 0; i < 9; ++i) {
			if (data[i] % 4 != 0) {
				return false;
			}
		}
		return true;
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("clocks.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("clocks.out")));*/
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
