/*
ID: licong12
LANG: JAVA
TASK: concom
*/
/**
 * Practiced on 2/22/2014
 * 
 * The key step: Use a queue to keep adding the "newly discovered controlling
 * companies", then keep popping these controlling companies to discover if any
 * other un-discovered controlling companies.
 */
package com.congli.usaco;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class concom {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		concom test = new concom();
		test.start();
	}
	
	public void solve() throws IOException {
		int num_tri = readInt();
		int[][] graph = new int[101][101];
		ArrayList<ArrayList<Integer>> link = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= 100; ++i) {
			link.add(new ArrayList<Integer>());
		}
		
		int a, b, c = 0;
		for (int i = 0; i < num_tri; ++i) {
			a = readInt();
			b = readInt();
			c = readInt();
			graph[a][b] = c;
			link.get(a).add(b);
		}
		
		for (int i = 1; i <= 100; ++i) {
			processNode (i, graph, link);
		}
		
		for (int i = 1; i <= 100; ++i) {
			for (int j = 1; j <= 100; ++j) {
				if (i != j && graph[i][j] > 50) {
					out.println(i + " " + j);
				}
			}
		}
	}
	
	public void processNode (int i, int[][] graph, ArrayList<ArrayList<Integer>> link) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (Integer c : link.get(i)) {
			if (graph[i][c] > 50) {
				list.add(c);
			}
		}
		int j = 0;
		while (!list.isEmpty()) {
			j = list.pollFirst();
			for (int k : link.get(j)) {
				if (k != i) {
					if (graph[i][k] <= 50 && graph[i][k] + graph[j][k] > 50) {
						list.add(k);
					}
					graph[i][k] += graph[j][k];
				}
			}
		}
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("concom.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));*/
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