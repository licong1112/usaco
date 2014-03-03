/*
ID: licong12
LANG: JAVA
TASK: frac1
*/
/**
 * Practiced on 1/22/2014
 * 
 * An amazing algorithm: http://blog.csdn.net/yuwuc/article/details/4540630
 */
package com.congli.usaco;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class frac1 {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		frac1 test = new frac1();
		test.start();
	}
	
	public void solve() throws IOException {
		int n = readInt();
		boolean[][] visited = new boolean[n+1][n+1];
		int gcd_num = 0;
		int count = 0;
		for (int j = 1; j <= n; ++j) {
			for (int i = j; i >= 0; --i) {
				gcd_num = gcd(i, j);
				if (gcd_num == 1 && !visited[i][j]) {
					visited[i][j] = true;
					++count;
				}
			}
		}
		
		RationalNum[] array = new RationalNum[count];
		int index = 0;
		for (int j = 1; j <= n; ++j) {
			for (int i = j; i >= 0; --i) {
				if (visited[i][j]) {
					array[index++] = new RationalNum(i, j);
				}
			}
		}
		Arrays.sort(array);
		for (int i = 0; i < array.length; ++i) {
			out.println(array[i]);
		}
	}
	
	public int gcd(int a, int b) {
		if (b == 0) return a;
		return gcd(b, a%b);
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("frac1.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));*/
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
	
	class RationalNum implements Comparable<RationalNum>{
		int numerator;
		int denominator;
		public RationalNum(int n, int d) {
			numerator = n;
			denominator = d;
		}
		@Override
		public int compareTo(RationalNum num) {
			// TODO Auto-generated method stub
			return this.numerator*num.denominator - this.denominator*num.numerator;
		}
		
		public String toString() {
			return numerator + "/" + denominator;
		}
	}
}


