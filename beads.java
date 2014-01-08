/*
ID: licong12
LANG: JAVA
TASK: beads
*/

/**
 * Practiced on 2/28/2013
 */

package com.congli.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class beads {
	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		beads test = new beads();
		test.start();
	}

	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("beads.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));*/
			in = new BufferedReader(new FileReader("..\\USACO\\src\\com\\congli\\usaco\\training\\input.txt"));
			out = new PrintWriter(System.out);
			solve();
			in.close();
			out.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	public void solve() throws IOException
	{
		int beads_length = readInt();
		String beads = readString();
		beads += beads;

		int i = 0;
		while(i < beads_length && beads.charAt(i) == 'w')
			++i;
		if(i == beads_length)
		{
			out.println(beads_length);
			return;
		}
		
		int length = 0;
		int first_start = i;
		char first = beads.charAt(i);
		while(i < beads.length()-1 && (beads.charAt(i+1)==first || beads.charAt(i+1) == 'w'))
			++i;
		if(i == beads.length()-1)
		{
			out.println(length == 0 ? beads_length : length);
			return;
		}
		
		char second = beads.charAt(++i);
		int second_start = i;
		while(i < beads.length()-1)
		{ 
			while(i < beads.length()-1 && (beads.charAt(i+1)==second || beads.charAt(i+1) == 'w'))
				++i;
			if(i == beads.length()-1)
			{
				out.println(length);
				return;
			}
			++i;
			
			length = i-first_start > beads_length ? length : Math.max(length, i-first_start);
			if(i > beads_length)
			{
				out.println(length);
				break;
			}
			first_start = i-1;
			while(beads.charAt(first_start) == second || beads.charAt(first_start) == 'w')
				--first_start;
			++first_start;
			second_start = i;
			first = second;
			second = beads.charAt(i);
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


