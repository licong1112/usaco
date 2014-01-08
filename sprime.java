/*
ID: licong12
LANG: JAVA
TASK: sprime
*/
/**
 * Practiced on 1/6/2013
 */
package com.congli.usaco;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class sprime {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		sprime test = new sprime();
		test.start();
	}
	
	public void solve() throws IOException {
		int n = readInt();
		int[] prime_base = {2, 3, 5, 7};
		if (n == 1) {
			for (int num : prime_base) {
				out.println(num);
			}
		} else  {
			for (int i = 0; i < prime_base.length; ++i) {
				genPrime(prime_base[i], n);
			}
		}
	}
	
	public void genPrime(int prime_base, int n) {
		if (n == 1) {
			out.println(prime_base);
			return;
		}
		int new_prime = 0;
		for (int i = 1; i < 10; i += 2) {
			if (i == 5) continue;
			new_prime = prime_base * 10 + i;
			if (isPrime(new_prime)) {
				genPrime(new_prime, n-1);
			}
		}
	}
	
	public boolean isPrime(int num) {
		int sqrt_num = sqrt(num);
		for (int i = 3; i <= sqrt_num; i += 2) {
			if (num % i == 0) return false;
		}
		return true;
	}
	
	public int sqrt(int num) {
		int start = 1;
		int end = num;

		while(start <= end)
		{
			int mid = start + (end-start)/2;
			if(mid*mid == num) return mid;
			if(mid*mid < num)
				start = mid+1;
			else
				end = mid-1;
		}
		return start-1;
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("sprime.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));*/
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


