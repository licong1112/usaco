/*
ID: licong12
LANG: JAVA
TASK: msquare
*/
/**
 * Practiced on 3/28/2014
 * 
 * BFS.
 */
package com.congli.usaco;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class msquare {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	final static int[][] template = {{7, 6, 5, 4, 3, 2, 1, 0},
									{3, 0, 1, 2, 5, 6, 7, 4},
									{0, 6, 1, 3, 4, 2, 5, 7}};
	StringBuilder sb = new StringBuilder();
	String target;
	
	public static void main(String[] args) {
		msquare test = new msquare();
		test.start();
	}
	
	public void solve() throws IOException {
		sb.delete(0, 8);
		for (int i = 0; i < 8; ++i) {
			sb.append(Integer.toString(readInt()));
		}
		target = sb.toString();
		HashSet<String> set = new HashSet<String>();
		set.add("12345678");
		
		String[] square = {"12345678"};
		String[] move = {""};
		int size = 1;
		int num_iter = 0;
		while (true) {
			String[] square_next = new String[size*3];
			String[] move_next = new String[size*3];
			int size_next = 0;
			
			for (int i = 0; i < size; ++i) {
				if (square[i].equals(target)) {
					out.println(num_iter);
					printResult(move[i]);
					return;
				}
				for (int j = 0; j < 3; ++j) {
					String next_sq = getNextSquare(square[i], j);
					if (!set.contains(next_sq)) {
						set.add(next_sq);
						square_next[size_next] = next_sq;
						move_next[size_next] = (move[i]+(char)('A'+j));
						++size_next;
					}
				}
			}
			
			size = size_next;
			square = square_next;
			move = move_next;
			++num_iter;
		}
	}
	
	public void printResult(String str) {
		int i = 0;
		while (i < str.length()) {
			out.print(str.charAt(i));
			if ((i+1)%60 == 0) {
				out.println();
			}
			++i;
		}
		if (i == 0 || i%60 != 0) {
			out.println();
		}
	}

	public String getNextSquare(String square, int row) {
		sb.delete(0, 8);
		for (int i = 0; i < 8; ++i) {
			sb.append(Character.toString(square.charAt(template[row][i])));
		}
		return sb.toString();
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("msquare.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));*/
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
