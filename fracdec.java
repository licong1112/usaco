/*
ID: licong12
LANG: JAVA
TASK: fracdec
*/
/**
 * Practiced on 3/2/2014
 * 
 * Take 3/8 as an example:
 * 
 * 1. To find the first digit, we can calculate 30/8 = 3. Then we know the 
 * result is 0.3xxxx...
 * 2. To find the second digit, we first minus 3/8 by 0.3, which is 3/8 - 3/10
 * = (3*10 - 3*8) / 8*10 = 6/80. Note that since we only want to calculate the
 * next digit, we can multiply 6/80 by 10, which gives 6/8. Then we treat 6/8
 * as a new fraction, and repeat step 1. The first digit of 6/8 is 7, which means
 * 6/8 = 0.7xxxx, i.e. 3/8 = 0.37xxxx
 * 3. The above steps are repeated until numerator is 0, or a repeating sequence
 * is found.
 * 4. To find repeating sequence, we store each previous fraction. If the new
 * fraction equals one of the previous fractions, it means there is a repeating
 * sequence.
 */
package com.congli.usaco;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class fracdec {
	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		fracdec test = new fracdec();
		test.start();
	}
	
	public void solve() throws IOException {
		int numerator = readInt(), denominator = readInt();
		int int_part = numerator / denominator;
		numerator %= denominator;
		
		if (numerator == 0) {
			out.println(int_part + ".0");
			return;
		}
		
		String result = Integer.toString(int_part) + "." + getFrac(numerator, denominator);
		
		// output 76 characters each line
		for (int i = 0; i < result.length(); ++i) {
			out.print(result.charAt(i));
			if ((i+1) % 76 == 0) {
				out.println();
			}
		}
		if (result.length() % 76 != 0) {
			out.println();
		}
	}
	
	public String getFrac(int numerator, int denominator) {
		ArrayList<Integer> frac_part = new ArrayList<Integer>();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		StringBuilder sb = new StringBuilder();
		int index = 0;
		
		while (numerator != 0) {
			String hash = Integer.toString(numerator) + " " + Integer.toString(denominator);
			if (map.containsKey(hash)) { // Has repeating sequence
				int start_paren = map.get(hash);
				for (int i = 0; i < start_paren; ++i) {
					sb.append(frac_part.get(i));
				}
				sb.append("(");
				for (int i = start_paren; i < frac_part.size(); ++i) {
					sb.append(frac_part.get(i));
				}
				sb.append(")");
				return sb.toString();
			}
			
			// Has not discovered repeating yet
			map.put(hash, index++);
			int new_digit = numerator*10/denominator;
			frac_part.add(new_digit);
			numerator = numerator*10 - new_digit*denominator;
			int gcd = getgcd(numerator, denominator);
			numerator /= gcd;
			denominator /= gcd;
		}
		// There is no repeating sequence
		for (int i = 0; i < frac_part.size(); ++i) {
			sb.append(frac_part.get(i));
		}
		return sb.toString();
	}
	
	public int getgcd(int a, int b) {
		return b == 0 ? a : getgcd(b, a%b);
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("fracdec.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));*/
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
