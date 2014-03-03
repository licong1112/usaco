/*
ID: licong12
LANG: JAVA
TASK: ttwo
*/
/**
 * Practiced on 2/24/2014
 */
package com.congli.usaco;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class ttwo {
	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		ttwo test = new ttwo();
		test.start();
	}
	
	public void solve() throws IOException {
		char[][] grid = new char[10][10];
		for (int i = 0; i < 10; ++i) {
			grid[i] = readString().toCharArray();
		}
		
		int[] farmer = new int[3];
		int[] cow = new int[3];
		for (int i = 0; i < 10; ++i) {
			for (int j = 0; j < 10; ++j) {
				if (grid[i][j] == 'F') {
					farmer[0] = i;
					farmer[1] = j;
					farmer[2] = 0;
				} else if (grid[i][j] == 'C') {
					cow[0] = i;
					cow[1] = j;
					cow[2] = 0;
				}
			}
		}
		
		HashSet<Integer> set = new HashSet<Integer>();
		int count = 0;
		int hash_int = 0;
		while (farmer[0] != cow[0] || farmer[1] != cow[1]) {
			hash_int = 0;
			for (int i = 0; i < 3; ++i) {
				hash_int = hash_int * 10 + farmer[i];
			}
			for (int i = 0; i < 3; ++i) {
				hash_int = hash_int * 10 + cow[i];
			}
			if (set.contains(hash_int)) {
				out.println(0);
				return;
			}
			set.add(hash_int);
			nextPosition(farmer, grid);
			nextPosition(cow, grid);
			++count;
		}
		out.println(count);
	}
	
	public void nextPosition(int[] array, char[][] grid) {
		if (array[2] == 0 && array[0] > 0 && grid[array[0]-1][array[1]] != '*') { // north
			--array[0];
		} else if (array[2] == 1 && array[1] < 9 && grid[array[0]][array[1]+1] != '*') { // east
			++array[1];
		} else if (array[2] == 2 && array[0] < 9 && grid[array[0]+1][array[1]] != '*') { // south
			++array[0];
		} else if (array[2] == 3 && array[1] > 0 && grid[array[0]][array[1]-1] != '*') { // west
			--array[1];
		} else { // on the boarder or has obstacle
			array[2] = (array[2] + 1) % 4; 
		}
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("ttwo.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));*/
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