/*
ID: licong12
LANG: JAVA
TASK: runround
*/
/**
 * Practiced on 2/11/2014
 * 
 * What's the point of this problem?....
 */
package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class runround {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	int[] digits = new int[10];
	boolean[] visited = new boolean[10];
	
	public static void main(String[] args) {
		runround test = new runround();
		test.start();
	}
	
	public void solve() throws IOException {
		int n = readInt();
		for (int i = n+1; i <= Integer.MAX_VALUE; ++i) {
			if (isValid(i)) {
				out.println(i);
				return;
			}
		}
		return;
 	}
	
	public boolean isValid(int num) {
		int temp_num = num, sum = 0, num_digits = 0;
		while (temp_num > 0) {
			num_digits++;
			temp_num /= 10;
		}
		int index = num_digits-1;
		for (int i = 0; i < 10; ++i) {
			visited[i] = false;
		}
		while (num > 0) {
			digits[index] = num % 10;
			if (visited[digits[index]]) {
				return false;
			}
			visited[digits[index]] = true;
			sum += digits[index--];
			num /= 10;
		}
		if (sum % num_digits != 0) {
			return false;
		}
		for (int i = 0; i < 10; ++i) {
			visited[i] = false;
		}
		
		int curr_index = 0, next_index = 0;
		for (int i = 0; i < num_digits-1; ++i) {
			visited[curr_index] = true;
			next_index = (curr_index + digits[curr_index]) % num_digits;
			if (visited[next_index]) {
				return false;
			}
			visited[next_index] = true;
			curr_index = next_index;
		}
		if ((curr_index + digits[curr_index]) % num_digits == 0) {
			return true;
		}
		return false;
	}
	
	public int nextIndex (int curr_index, int digit, int array_length) {
		return (curr_index + digit) % array_length;
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("runround.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));*/
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


