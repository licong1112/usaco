/*
ID: licong12
LANG: JAVA
TASK: contact
*/
/**
 * Practiced on 3/17/2014
 * 
 * 1. Use sliding window to calculate the int value of the binary code in the
 *    window.
 * 2. Use heap to sort.
 */
package com.congli.usaco;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class contact {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		contact test = new contact();
		test.start();
	}
	
	public void solve() throws IOException {
		int A = readInt(), B = readInt(), N = readInt();
		StringBuilder sb = new StringBuilder();
		String str = null;
		while ((str = in.readLine()) != null) {
			sb.append(str);
		}
		
		int[] hash = new int[(1<<12)];
		PriorityQueue<StringCount> heap = new PriorityQueue<StringCount>();
		
		for (int i = A; i <= B; ++i) {
			if (i > sb.length()) break;
			processSubstrWithLength(sb, i, hash, heap);
		}
		
		// Output
		while (heap.size() > 0 && N > 0) {
			int total = 0;
			int count = heap.peek().count;
			out.println(count);
			StringBuilder out_sb = new StringBuilder();
			while (heap.size() > 0 && heap.peek().count == count) {
				++total;
				out_sb.append(heap.poll().str + " ");
				if (total % 6 == 0) {
					out.println(out_sb.substring(0, out_sb.length()-1));
					out_sb.delete(0, out_sb.length());
				}
			}
			if (out_sb.length() > 0) {
				out.println(out_sb.substring(0, out_sb.length()-1));
				out_sb.delete(0, out_sb.length());
			}
			--N;
		}
	}
	
	public void processSubstrWithLength(StringBuilder sb, int length, int[] hash, PriorityQueue<StringCount> heap) {
		Arrays.fill(hash, 0);
		int front = 0;
		int end = length-1;
		int num = 0;
		for (int i = front; i <= end; ++i) {
			num *= 2;
			num += (sb.charAt(i)-'0');
		}
		++hash[num];
		++end;
		
		// Sliding window, calculate the int value of the binary code in the window.
		int divide = 1<<length;
		while (end < sb.length()) {
			num *= 2;
			num += (sb.charAt(end)-'0');
			if (sb.charAt(front) == '1') {
				num %= divide;
			}
			++front;
			++end;
			++hash[num];
		}
		
		int bound = 1<<length;
		for (int i = 0; i < bound; ++i) {
			if (hash[i] != 0) {
				String s = new String();
				int temp = i;
				for (int l = 0; l < length; ++l) {
					s = (char)((temp%2) + '0') + s;
					temp /= 2;
				}
				heap.add(new StringCount(s, hash[i]));
			}
		}
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("contact.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));*/
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
	
	class StringCount implements Comparable<StringCount> {
		String str;
		int count;
		public StringCount(String s, int c) {
			this.str = s;
			this.count = c;
		}
		
		public int compareTo(StringCount other_str_count) {
			if (this.count < other_str_count.count) return 1;
			if (this.count > other_str_count.count) return -1;
			if (this.str.length() < other_str_count.str.length()) return -1;
			if (this.str.length() > other_str_count.str.length()) return 1;
			return this.str.compareTo(other_str_count.str);
		}
	}
}


