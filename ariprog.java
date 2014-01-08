/*
ID: licong12
LANG: JAVA
TASK: ariprog
*/
/**
 * Practiced on 1/1/2014
 * 
 * 1. First tried using HashSet, TLE. Seems HashSet has large constant.
 * 2. Another great observation that I didn't notice:
 *    1) Let list[] contains all bisquare numbers
 *    2) For a valid number a and b, there must be some i and j, such that 
 *       a = list[i] and b = list[j]-list[i],
 *       
 *       Here is the reason:
 *       
 *       Since we want a+nb in list[], then it must be in list when n = 0
 *       and n = 1. This means there exists some i and j such that:
 *       
 *       a + 0*b = a = list[i]  AND
 *       a + 1*b = a+b = list[j]
 *       
 *       Therefore a = list[i] and b = list[j] - list[i].
 * 
 * Check the link below for an detailed discussion.
 * http://www.nocow.cn/index.php/USACO/ariprog
 * 
 * The code below use brute force search for a and b. The code is simple, but
 * not very efficient. It'll be much more efficient if we consider the above
 * observation, but the code will be lengthy.
 */

package com.congli.usaco;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class ariprog {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		ariprog test = new ariprog();
		test.start();
	}
	
	public void solve() throws IOException
	{
		int N = readInt(), M = readInt();
		boolean[] has_bisquare = new boolean[2*M*M+10]; 
		for (int p = 0; p <= M; ++p) {
			for (int q = 0; q <= M; ++q) {
				has_bisquare[p*p+q*q] = true;
			}
		}
		
		int count = 0;
		int b_limit = 2*M*M/(N-1);
		int a_limit = 0;
		int n = 0;
		for (int b = 1; b <= b_limit; ++b) {
			a_limit = 2*M*M - b*(N-1);
			for (int a = 0; a <= a_limit; ++a) {
				for (n = 0; n < N; ++n) {
					if (!has_bisquare[a+n*b]) {
						break;
					}
				}
				if (n == N) {
					out.println(a + " " + b);
					++count;
				}
			}
		}
		if (count == 0) {
			out.println("NONE");
		}
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("ariprog.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));*/
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


