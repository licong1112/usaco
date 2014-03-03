/*
ID: licong12
LANG: JAVA
TASK: lamps
*/
/**
 * Practiced on 2/13/2014
 * 
 * Key observation:
 * 1. Each button has only two status: press 0 time and press 1 time.
 * 2. The order of pressing doesn't matter.
 */
package com.congli.usaco;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class lamps {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	boolean[] result = null;
	
	public static void main(String[] args) {
		lamps test = new lamps();
		test.start();
	}
	
	public void solve() throws IOException {
		int n = readInt(), c = readInt();
		result = new boolean[n+1];
		ArrayList<Integer> on = new ArrayList<Integer>();
		ArrayList<Integer> off = new ArrayList<Integer>();
		int num = readInt();
		while (num != -1) {
			on.add(num);
			num = readInt();
		}
		num = readInt();
		while (num != -1) {
			off.add(num);
			num = readInt();
		}
		
		int[] num_press = new int[4];
		PriorityQueue<boolean[]> final_result = new PriorityQueue<boolean[]>(10, new BooleanComparator());
		dfs(num_press, 0, c, 0, result, on, off, final_result);
		
		if (final_result.size() == 0) {
			out.println("IMPOSSIBLE");
		} else {
			boolean[] result_entry = null;
			boolean[] result_entry_pre = null;
			while (!final_result.isEmpty()) {
				result_entry = final_result.poll();
				if (checkEqual(result_entry, result_entry_pre)) {
					continue;
				}
				result_entry_pre = result_entry;
				for (int j = 1; j < result_entry.length; ++j) {
					out.print(result_entry[j] ? 1 : 0);
				}
				out.println();
			}
		}
 	}
	
	public boolean checkEqual(boolean[] array1, boolean[] array2) {
		if (array1 == null && array2 == null) return true;
		if (array1 == null || array2 == null) return false;
		for (int i = 0; i < array1.length; ++i) {
			if (array1[i] != array2[i]) {
				return false;
			}
		}
		return true;
	}
	
	public void dfs(int[] num_press, int sum_press, int c, int level, boolean[] result, ArrayList<Integer> on, ArrayList<Integer> off, PriorityQueue<boolean[]> final_result) {
		if (sum_press > c) {
			return;
		}
		if (level == 4) {
			for (int i = 1; i < result.length; ++i) {
				result[i] = true;
			}
			genResult(num_press, result);
			if (isValid(result, on, off)) {
				final_result.add(result.clone());
			}
			return;
		}

		for (int i = 0; i <= 1; ++i) {
			num_press[level] = i;
			dfs(num_press, sum_press+i, c, level+1, result, on, off, final_result);
		}
	}
	
	public boolean isValid(boolean[] result, ArrayList<Integer> on, ArrayList<Integer> off) {
		for (Integer i : on) {
			if (!result[i]) {
				return false;
			}
		}
		for (Integer i : off) {
			if (result[i]) {
				return false;
			}
		}
		return true;
	}
	
	public void genResult(int[] num_press, boolean[] result) {
		if (num_press[0] == 1) {
			for (int i = 1; i < result.length; ++i) {
				result[i] = !result[i];
			}
		}
		if (num_press[1] == 1) {
			for (int i = 1; i < result.length; i += 2) {
				result[i] = !result[i];
			}
		}
		if (num_press[2] == 1) {
			for (int i = 2; i < result.length; i += 2) {
				result[i] = !result[i];
			}
		}
		if (num_press[3] == 1) {
			for (int i = 1; i < result.length; i += 3) {
				result[i] = !result[i];
			}
		}
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("lamps.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));*/
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
	
	class BooleanComparator implements Comparator<boolean[]> {
		public int compare(boolean[] arg0, boolean[] arg1) {
			for (int i = 0; i < arg0.length; ++i) {
				if (arg0[i] != arg1[i]) {
					return arg0[i] ? 1 : -1;
				}
			}
			return 0;
		}
	}
}