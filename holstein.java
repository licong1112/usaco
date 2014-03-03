/*
ID: licong12
LANG: JAVA
TASK: holstein
*/
/**
 * Practiced on 1/28/2014
 * 
 * Just thought BFS may be faster...
 */

package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class holstein {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	int min_length = Integer.MAX_VALUE;
	int v = 0, g = 0;
	
	public static void main(String[] args) {
		holstein test = new holstein();
		test.start();
	}
	
	public void solve() throws IOException {
		v = readInt();
		int[] target = new int[v];
		for (int i = 0; i < v; ++i) {
			target[i] = readInt();
		}
		g = readInt();
		int[][] vitamin = new int[g][v];
		for (int i = 0; i < g; ++i) {
			for (int j = 0; j < v; ++j) {
				vitamin[i][j] = readInt();
			}
		}
		int[] array = dfs(target, vitamin, 0, 0, new int[25], 0);
		for (int i = 0; i < array[0]; ++i) {
			out.print(array[i] + " ");
		}
		out.println(array[array[0]]);
	}
	
	public int[] dfs(int[] target, int[][] vitamin, int group, int num_valid, int[] result, int length) {
		if (length > min_length) {
			return null;
		}
		if (num_valid == v) {
			min_length = length;
			return result.clone();
		}
		if (group == g) {
			return null;
		}
		
		// Not use current group
		int[] array1 = dfs(target, vitamin, group+1, num_valid, result, length);
		
		
		// Use current group
		for (int i = 0; i < v; ++i) {
			if (target[i] > 0 && target[i]-vitamin[group][i] <= 0) {
				++num_valid;
			}
			target[i] -= vitamin[group][i];
		}
		result[length+1] = group+1;
		result[0]++;
		int[] array2 = dfs(target, vitamin, group+1, num_valid, result, length+1);
		result[length+1] = 0;
		result[0]--;
		
		for (int i = 0; i < v; ++i) {
			if (target[i] <= 0 && target[i]+vitamin[group][i] > 0) {
				--num_valid;
			}
			target[i] += vitamin[group][i];
		}
		if (array1 == null && array2 == null) return null;
		if (array1 == null) return array2;
		if (array2 == null) return array1;
		if (array2[0] <= array1[0]) return array2;
		return array1;
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("holstein.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));*/
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


