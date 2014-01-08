/*
ID: licong12
LANG: JAVA
TASK: ride
*/

/**
 * Practiced on 1/13/2013
 */
package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class ride {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		ride test = new ride();
		test.start();
	}

	public void start()
	{
		try {
			in = new BufferedReader(new FileReader("ride.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
			/*in = new BufferedReader(new FileReader("..\\USACO\\src\\com\\congli\\usaco\\training\\input.txt"));
			out = new PrintWriter(System.out);*/
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
	
	public void solve() throws IOException
	{
		String comet = readString();
		String group = readString();
		
		long cometNum = 1;
		for(int i = 0; i < comet.length(); ++i)
			cometNum *= (comet.charAt(i)-'A'+1);
		
		long groupNum = 1;
		for(int i = 0; i < group.length(); ++i)
			groupNum *= (group.charAt(i)-'A'+1);
		
		out.println((cometNum%47) == (groupNum%47) ? "GO" : "STAY");
	}
	
	
}
