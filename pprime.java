/*
ID: licong12
LANG: JAVA
TASK: pprime
*/
/**
 * Practiced on 1/5/2014
 * 
 * 1. Generate all palindromes then check which ones are prime
 * 2. Generate all primes in [1, 10000], then use these primes to check
 *    if the palindromes are primes. You'll get TLE if you check each palindrome
 *    in a brute-force manner.
 */

package com.congli.usaco;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class pprime {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		pprime test = new pprime();
		test.start();
	}
	
	public void solve() throws IOException
	{
		int start = readInt(), end = readInt();
		int num_digits_start = numDigits(start);
		int num_digits_end = numDigits(end);
		
		int[] palin = new int[100000];
		int palin_size = genPalin(start, end, num_digits_start/2, num_digits_end/2, palin);
		
		int[] prime = new int[10000];
		int prime_size = genPrime(prime);
		
		int[] result_prime = new int[palin_size];
		int result_prime_length = checkPrime(palin, palin_size, result_prime, prime, prime_size);
		Arrays.sort(result_prime, 0, result_prime_length);
		for (int i = 0; i < result_prime_length; ++i) {
			out.println(result_prime[i]);
		}
	}
	
	public int genPalin(int start, int end, int num_digits_start, int num_digits_end, int[] palin) {
		int start_gen = 1, end_gen = 9;
		while (num_digits_start > 1) {
			start_gen *= 10;
			--num_digits_start;
		}
		while (num_digits_end > 1) {
			end_gen = end_gen*10+9;
			--num_digits_end;
		}
		int palin_size = 0;
		if (start_gen == 1) {
			for (int i = start; i < 10; i += 2) {
				palin[palin_size++] = i;
			}
		}
		
		long new_num = 0;
		long temp = 0;
		long multiplier = 1;
		long add = 0;
		for (int i = start_gen; i <= end_gen; ++i) {
			temp = i;
			add = 0;
			multiplier = 1;
			while (temp > 0) {
				add += temp % 10;
				multiplier *= 10;
				temp /= 10;
				if (temp > 0) {
					add *= 10;
				}
			}
			new_num = i*multiplier + add;
			if (new_num > end) break;
			if (new_num >= start && (new_num & 1) != 0) {
				palin[palin_size++] = (int)new_num;
			}
			
			for (int j = 0; j < 10; ++j) {
				new_num = (i*10 + j) * multiplier + add;
				if (new_num > end) break;
				if (new_num >= start && (new_num & 1) != 0) {
					palin[palin_size++] = (int)new_num;
				}
			}
		}
		return palin_size;
	}
	
	public int genPrime(int[] prime) {
		prime[0] = 2;
		int index = 1;
		int runner = 0;
		for (int i = 3; i <= 10000; ++i) {
			runner = 0;
			while (runner < index) {
				if (i % prime[runner] == 0) {
					break;
				}
				++runner;
			}
			if (runner == index) {
				prime[index++] = i;
			}
		}
		return index;
	}
	
	public int numDigits(int num) {
		int result = 0;
		while (num > 0) {
			num /= 10;
			++result;
		}
		return result;
	}
	
	public int checkPrime(int[] palin, int palin_size, int[] result, int[] prime, int prime_size) {
		int index = 0;
		for (int i = 0; i < palin_size; ++i) {
			if (isPrime(palin[i], prime, prime_size)) {
				result[index++] = palin[i];
			}
		}
		return index;
	}
	
	public boolean isPrime(int num, int[] prime, int prime_size) {
		for (int i = 0; i < prime_size && prime[i] < num; ++i) {
			if (num % prime[i] == 0) return false;
		}
		return true;
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("pprime.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));*/
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


