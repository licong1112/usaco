/*
ID: licong12
LANG: JAVA
TASK: barn1
*/
/**
 * Practiced on 11/21/2013
 */

package com.congli.usaco;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class barn1 {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		barn1 test = new barn1();
		test.start();
	}
	
	public void solve() throws IOException
	{
		int num_boards = readInt();
		int num_stalls = readInt();
		int num_cows = readInt();		
		int[] cow_pos = new int[num_cows];
		for (int i = 0; i < num_cows; ++i) {
			cow_pos[i] = readInt();
		}
		if (num_boards >= num_cows) {
			out.println(num_cows);
			return;
		}
		Arrays.sort(cow_pos);
		
		int[] gap = new int[num_cows-1];
		for (int i = 0; i < num_cows-1; ++i) {
			gap[i] = cow_pos[i+1] - cow_pos[i] - 1;
		}

		int difference = cow_pos[cow_pos.length-1] - cow_pos[0] + 1;
		Arrays.sort(gap);
		int gap_index = gap.length-1;
		for (int i = 2; i <= num_boards; ++i) {
			difference -= gap[gap_index--];
		}
		out.println(difference);
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("barn1.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));*/
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


