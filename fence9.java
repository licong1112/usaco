/*
ID: licong12
LANG: JAVA
TASK: fence9
*/
/**
 * Practiced on 4/30/2014
 */
package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class fence9 {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	double D_x = 0.0;
	double D_y = 0.0;
	
	public static void main(String[] args) {
		fence9 test = new fence9();
		test.start();
	}
	
	// line 1: [(0, 0) (n, m)]
	// line 2: [(n, m) (p, 0)]
	public void solve() throws IOException {
		int n = readInt(), m = readInt(), p = readInt();
		
		D_x = (n+p)/3.0;
		D_y = m/3.0;
		
		int start_x = 0, end_x = p;
		int y = 1;
		
		int total = 0;
		
		while (start_x <= end_x) {
			while (!sameSide(0, 0, n, m, start_x, y)) {
				++start_x;
			}

			while (!sameSide(n, m, p, 0, end_x, y)) {
				--end_x;
			}
			
			while (sameSide(n, m, p, 0, end_x, y)) {
				++end_x;
			}
			
			if (start_x <= end_x) {
				total += (end_x-start_x);
			}
			
			++y;
		}
		
		out.println(total);
	}
	
	// If C and D are the same side of line segment AB
	public boolean sameSide(double A_x, double A_y, double B_x, double B_y, double C_x, double C_y) {
		double B_A_x = B_x - A_x;
		double B_A_y = B_y - A_y;
		double C_A_x = C_x - A_x;
		double C_A_y = C_y - A_y;
		
		double z1 = B_A_x * C_A_y - B_A_y * C_A_x;
		
		double D_A_x = D_x - A_x;
		double D_A_y = D_y - A_y;
		
		double z2 = B_A_x * D_A_y - B_A_y * D_A_x;
		
		return z1*z2 > 0;
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("fence9.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("fence9.out")));*/
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