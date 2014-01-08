/*
ID: licong12
LANG: JAVA
TASK: milk
*/
/**
 * Practiced on 11/19/2013
 * 
 * If use weight[i] to store the weight of milk that can be provided at price i,
 * then there is no sorting needed. Notice the price ranges from 0 to 1000.
 */

package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class milk {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		milk test = new milk();
		test.start();
	}
	
	public void solve() throws IOException
	{
		int total = readInt();
		int num_farmers = readInt();
		int[] weights = new int[1001]; // the prices are from 0 to 1000
		for (int i = 0; i < num_farmers; ++i) {
			weights[readInt()] += readInt();
		}
		
		int current_weight = 0;
		int index = 0;
		int price = 0;
		int weight_needed = 0;
		while (current_weight < total) {
			weight_needed = Math.min(weights[index], total-current_weight);
			price += weight_needed * index;
			current_weight += weight_needed;
			++index;
		}
		out.println(price);
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("milk.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));*/
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

