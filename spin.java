/*
ID: licong12
LANG: JAVA
TASK: spin
*/
/**
 * Practiced on 3/25/2014
 * 
 * Brute force. Use boolean[][] wedge to simulate the position of the wedges
 * on the wheels.
 */
package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class spin {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		spin test = new spin();
		test.start();
	}
	
	public void solve() throws IOException {
		boolean[][] wedge = new boolean[5][360];
		int[] speed = new int[5];
		for (int i = 0; i < 5; ++i) {
			speed[i] = readInt();
			int num_wedge = readInt();
			for (int j = 0; j < num_wedge; ++j) {
				fillWedge(wedge, i, readInt(), readInt());
			}
		}
		
		boolean[][] next_wedge = new boolean[5][360];
		for (int i = 0; i < 360; ++i) {
			if (isValid(wedge)) {
				out.println(i);
				return;
			}
			for (int j = 0; j < 5; ++j) {
				walk(wedge, next_wedge, j, speed[j]);
			}
			boolean[][] temp = wedge;
			wedge = next_wedge;
			next_wedge = temp;
		}
		out.println("none");
	}
	
	public void walk(boolean[][] wedge, boolean[][] next_wedge, int index, int speed) {
		for (int i = 0; i < 360; ++i) {
			next_wedge[index][i] = wedge[index][(i-speed+360)%360];
		}
	}
	
	public void fillWedge(boolean[][] wedge, int index, int start, int extend) {
		for (int i = start; i <= start+extend; ++i) {
			wedge[index][i%360] = true;
		}
	}
	
	public boolean isValid(boolean[][] wedge) {
		int j = 0;
		for (int i = 0; i <360; ++i) {
			j = 0;
			while (j < 5 && wedge[j][i]) {
				++j;
			}
			if (j == 5) return true;
		}
		return false;
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("spin.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("spin.out")));*/
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
