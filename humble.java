/*
ID: licong12
LANG: JAVA
TASK: humble
*/
/**
 * Practiced on 3/10/2014
 * 
 * Below is the analysis given by USACO:
 * 
 * We compute the first n humble numbers in the "hum" array. For simplicity of 
 * implementation, we treat 1 as a humble number, and adjust accordingly.
 * Once we have the first k humble numbers and want to compute the k+1st, we do 
 * the following:
 * 
 * 	for each prime p
 * 		find the minimum humble number h
 * 		  such that h * p is bigger than the last humble number.
 * 
 * 	take the smallest h * p found: that's the next humble number.
 * 
 * To speed up the search, we keep an index "pindex" of what h is for each 
 * prime, and start there rather than at the beginning of the list.
 * 
 * ==============
 * 
 * Another idea is BFS + Heap, but got TLE on the last test set.
 */
package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class humble {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		humble test = new humble();
		test.start();
	}
	
	public void solve() throws IOException {
		int num_prime = readInt();
		int count = readInt();
		int[] prime = new int[num_prime];
		for (int i = 0; i < num_prime; ++i) {
			prime[i] = readInt();
		}
		int[] humble = new int[count+1];
		int[] pindex = new int[num_prime];
		humble[0] = 1;

		for (int i = 1; i <= count; ++i) {
			int min = INF;
			for (int j = 0; j < num_prime; ++j) {
				for (int k = pindex[j]; k < i; ++k) {
					if (prime[j]*humble[k] > humble[i-1]) {
						min = Math.min(min, prime[j]*humble[k]);
						pindex[j] = k;
						break;
					}
				}
			}
			humble[i] = min;
		}
		out.println(humble[count]);
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("humble.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));*/
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
