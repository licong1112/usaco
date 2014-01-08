/*
ID: licong12
LANG: JAVA
TASK: milk3
*/

/**
 * Practiced on 1/2/2014
 * 
 * DFS is the idea. Pour any possible jar to any other possible jar, and see
 * what happens.
 */

package com.congli.usaco;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class milk3 {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		milk3 test = new milk3();
		test.start();
	}
	
	public void solve() throws IOException {
		int[] capacity = new int[3];
		capacity[0] = readInt();
		capacity[1] = readInt();
		capacity[2] = readInt();
		
		int[] jar = new int[3]; // Current amount of milk in each jar
		jar[2] = capacity[2];
		
		// set[jar[0]][jar[1]][jar[2]] stores whether the situation "the amount of milk in each jar
		// equals jar[0], jar[1], jar[2]" has been visited 
		boolean[][][] set = new boolean[21][21][21]; 
		// result[i] stores if jar C can contains i unit milk when jar A is empty
		boolean[] result = new boolean[21];
		
		dfs(capacity, jar, set, result);
		
		int[] final_result = new int[20];
		int count = 0;
		for (int i = 0; i < 21; ++i) {
			if (result[i]) {
				final_result[count++] = i;
			}
		}
		for (int i = 0; i < count-1; ++i) {
			out.print(final_result[i] + " ");
		}
		out.println(final_result[count-1]);
	}
	
	public void dfs(int[] capacity, int[] jar, boolean[][][] set, boolean[] result) {
		for (int i = 0; i < 3; ++i) {
			if (jar[i] != 0) { // jar i has to contain some milk to pour
				for (int j = 0; j < 3; ++j) {
					if (j != i && !isFull(capacity, jar, j)) { // jar j has to have some space to store milk
						int amount = Math.min(jar[i], capacity[j]-jar[j]);
						pour(jar, i, j, amount); // pour milk
						if (!set[jar[0]][jar[1]][jar[2]]) {
							set[jar[0]][jar[1]][jar[2]] = true;
							if (jar[0] == 0) {
								result[jar[2]] = true; // jar C can contains jar[2] amount of milk
							}
							dfs(capacity, jar, set, result);
						}
						pour(jar, j, i, amount); // pour milk back
					}
				}
			}
		}
	}
	
	public void pour(int[] jar, int start, int end, int amount) {
		jar[end] += amount;
		jar[start] -= amount;
	}
	
	public boolean isFull(int[] capacity, int[] jar, int index) {
		return jar[index] == capacity[index];
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("milk3.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));*/
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


