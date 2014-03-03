/*
ID: licong12
LANG: JAVA
TASK: hamming
*/
/**
 * Practiced on 2/4/2014
 * 
 * Look at the solution: http://www.nocow.cn/index.php/USACO/hamming
 * Why linear search is correct?? Don't understand...
 */

package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class hamming {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		hamming test = new hamming();
		test.start();
	}
	
	public void solve() throws IOException {
		int n = readInt(), b = readInt(), d = readInt();
		int max_num = (1 << b);
		boolean[][] distance = new boolean[max_num][max_num];
		for (int i = 0; i < max_num; ++i) {
			for (int j = 0; j < max_num; ++j) {
				distance[i][j] = calDistance(i, j, b, d);
			}
		}
		int[] result = new int[n];
		dfs(result, n, 0, 0, max_num, distance);
		for (int i = 1; i <= n; ++i) {
			if ((i%10) > 0 && i != n) {
				out.print(result[i-1] + " ");
			} else {
				out.println(result[i-1]);
			}
		}
	}
	
	public boolean dfs(int[] result, int n, int index, int start, int max_num, boolean[][] distance) {
		if (index == n) {
			return true;
		}
		for (int num = start; num < max_num; ++num) {
			if (isValid(result, index, num, distance)) {
				result[index] = num;
				if (dfs(result, n, index+1, num+1, max_num, distance)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isValid(int[] result, int index, int num, boolean[][] distance) {
		for (int i = 0; i < index; ++i) {
			if(!distance[result[i]][num]) {
				return false;
			}
		}
		return true;
	}
	
	public boolean calDistance(int x, int y, int num_bits, int least_dis) {
		int c = x ^ y;
		int distance = 0;
		for (int i = 0; i < num_bits; ++i) {
			distance += (((c >> i) & 1) == 1) ? 1 : 0;
		}
		return distance >= least_dis;
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("hamming.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));*/
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


