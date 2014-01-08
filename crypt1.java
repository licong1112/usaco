/*
ID: licong12
LANG: JAVA
TASK: crypt1
*/

/**
 * Practiced on 11/25/2013
 */

package com.congli.usaco;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class crypt1 {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		crypt1 test = new crypt1();
		test.start();
	}
	
	public void solve() throws IOException
	{
		int n = readInt();
		int[] digits = new int[n];
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < n; ++i) {
			digits[i] = readInt();
			set.add(digits[i]);
		}
		int[] result = new int[5];
		out.println(dfs(digits, set, 0, result));
	}
	
	public int dfs(int[] digits, HashSet<Integer> set, int index, int[] result) {
		if (index == 5) {
			if (result[0] * result[3] > 9 || result[0] * result[4] > 9) {
				return 0;
			}
			
			int[] sol1 = new int[3];
			int[] sol2 = new int[3];
			if (!multiply(result, result[4], set, sol1) || !multiply(result, result[3], set, sol2)) {
				return 0;
			}
			int carry = 0;
			if (!set.contains((sol1[1] + sol2[2]) % 10)) {
				return 0;
			}
			carry = (sol1[1] + sol2[2]) / 10;
			
			if (!set.contains((sol1[0] + sol2[1] + carry) % 10)) {
				return 0;
			}
			carry = (sol1[0] + sol2[1] + carry) / 10;
			
			if (sol2[0] + carry > 9 || !set.contains(sol2[0] + carry)) {
				return 0;
			}
			return 1;
		}
		
		int num_combination = 0;
		for (int i = 0; i < digits.length; ++i) {
			result[index] = digits[i];
			num_combination += dfs(digits, set, index+1, result);
		}
		return num_combination;
	}
	
	public boolean multiply(int[] result, int multiplier, HashSet<Integer> set, int[] sol) {
		int carry = 0;
		int digit = 0;
		for (int i = 2; i >= 0; --i) {
			digit = result[i]*multiplier + carry;
			if (i == 0 && digit > 9) {
				return false;
			}
			sol[i] = digit % 10;
			if (!set.contains(sol[i])) {
				return false;
			}
			carry = digit / 10;
		}
		return true;
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("crypt1.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));*/
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
