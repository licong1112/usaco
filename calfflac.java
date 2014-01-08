/*
ID: licong12
LANG: JAVA
TASK: calfflac
*/

/**
 * Practiced on 11/24/2013
 * 
 * Shame on me... spent 5 hours on this problem.
 * 
 * Key points:
 * 1. Store data in char[], so we don't need to take care of each line individually
 * 2. Use the simplest way to find the palindrome: for each char, search toward the
 *    two directions
 * 3. After finish identifying the longest palindrome, search toward the two sides
 *    to include all digits, which are needed for printing
 */

package com.congli.usaco;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class calfflac {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	int start = 0;
	int end = 0;
	int length = 0;
	
	public static void main(String[] args) {
		calfflac test = new calfflac();
		test.start();
	}
	
	public void solve() throws IOException
	{
		char[] data = new char[20010];
		int index = 0;
		while (in.ready()) {
			data[index++] = (char)in.read();
		}
		length = index;
		
		int max_length = 0;
		int max_start = 0;
		int max_end = 0;
		// Find the longest palindrome
		for (int i = 0; i < length; ++i) {
			start = i;
			end = i;
			int current_palin_length = findPalin(data);
			if (max_length < current_palin_length) {
				max_length = current_palin_length;
				max_start = start;
				max_end = end;
			}
			
			start = i;
			end = i+1;
			current_palin_length = findPalin(data);
			if (max_length < current_palin_length) {
				max_length = current_palin_length;
				max_start = start;
				max_end = end;
			}
		}
		
		out.println(max_length);
		
		// Search toward the two sides to include all digits.
		do {
			--max_start;
		} while (max_start >= 0 && isDigit(data[max_start]));
		++max_start;
		
		do {
			++max_end;
		} while (max_end < length && isDigit(data[max_end])); 
		--max_end;
		
		for (int k = max_start; k <= max_end; ++k) {
			out.print(data[k]);
		}
		out.println();
	}
	
	// Return the length of the palindrome. Note that this length does not
	// include any character that is not a letter.
	public int findPalin(char[] data) {
		int palin_length = 0;
		if (isLetter(data[start]) && isLetter(data[end])) {
			if (convert(data[start]) != convert(data[end])) {
				return 0;
			} else {
				palin_length = start == end ? 1 : 2;
			}
		}
		
		int next_start = 0;
		int next_end = 0;
		
		while (start >= 0 && end < length) {
			next_start = start-1;
			next_end = end+1;
			while (next_start >= 0 && !isLetter(data[next_start])) {
				--next_start;
			}
			while (next_end < length && !isLetter(data[next_end])) {
				++next_end;
			}
			if (next_start >= 0 && next_end < length && convert(data[next_start]) == convert(data[next_end])) {
				start = next_start;
				end = next_end;
				palin_length += 2;
			} else {
				break;
			}
		}
		return palin_length;
	}
	
	public char convert(char c) {
		char result = c;
		if (result <= 'Z' && result >= 'A') {
			return (char)(result - 'A' + 'a');
		}
		return result;
	}
	
	public boolean isLetter(char c) {
		if (c <= 'z' && c >= 'a') return true;
		if (c <= 'Z' && c >= 'A') return true;
		return false;
	}
	
	public boolean isDigit(char c) {
		return c <= '9' && c >= '0';
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("calfflac.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("calfflac.out")));*/
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
