/*
ID: licong12
LANG: JAVA
TASK: heritage
*/
/**
 * Practiced on 4/28/2014.
 */
package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class heritage {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	int target = 0;
	public static void main(String[] args) {
		heritage test = new heritage();
		test.start();
	}
	
	public void solve() throws IOException {
		String str = readString();
		char[] in_order = new char[str.length()];
		for (int i = 0; i < in_order.length; ++i) {
			in_order[i] = str.charAt(i);
		}
		str = readString();
		char[] pre_order = new char[str.length()];
		for (int i = 0; i < pre_order.length; ++i) {
			pre_order[i] = str.charAt(i);
		}
		printPostOrder(in_order, pre_order);
	}
	
	public void printPostOrder(char[] in_order, char[] pre_order) {
		out.println(processRange(in_order, 0, in_order.length-1, pre_order));
	}
	
	public String processRange(char[] in_order, int start, int end, char[] pre_order) {
		if (start > end) {
			--target;
			return "";
		}
		if (start == end) return Character.toString(in_order[start]);
		String str = "";
		for (int i = start; i <= end; ++i) {
			if (in_order[i] == pre_order[target]) {
				++target;
				str = processRange(in_order, start, i-1, pre_order);
				++target;
				String str2 = processRange(in_order, i+1, end, pre_order);
				str = str + str2;
				str = str + Character.toString(in_order[i]);
				break;
			}
		}
		return str;
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("heritage.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("heritage.out")));*/
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