/*
ID: licong12
LANG: JAVA
TASK: friday
*/

/**
 * Practiced on 2/11/2013
 */

package com.congli.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class friday {
	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		friday test = new friday();
		test.start();
	}

	public void start()
	{
		try {
			in = new BufferedReader(new FileReader("friday.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
			/*in = new BufferedReader(new FileReader("..\\USACO\\src\\com\\congli\\usaco\\training\\input.txt"));
			out = new PrintWriter(System.out);*/
			solve();
			in.close();
			out.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	public void solve() throws IOException
	{
		int N = readInt();
		int total_days = 13;
		int[] result = new int[7];
		
		for(int year = 1900; year < 1900+N; ++year)
		{
			for(int month = 1; month <= 12; ++month)
			{
				result[total_days % 7]++;
				total_days += GetDays(year, month);
			}
		}
		
		out.print(result[6] + " ");
		for(int i = 0; i < 5; ++i)
			out.print(result[i] + " ");
		out.println(result[5]);
	}

	private int GetDays(int year, int month)
	{
		switch(month)
		{
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			case 2:
				if(IsLeapYear(year))
					return 29;
				else
					return 28;
			default:
				return 31;
		}
	}
	
	private boolean IsLeapYear(int year)
	{
		if(year%400 == 0 || (year%100 != 0 && year%4 == 0))
			return true;
		return false;
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

