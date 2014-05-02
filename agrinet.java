/*
ID: licong12
LANG: JAVA
TASK: agrinet
*/
/**
 * Practiced on 3/4/2014
 * 
 * Prim algorithm.
 */
package com.congli.usaco;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class agrinet {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		agrinet test = new agrinet();
		test.start();
	}
	
	public void solve() throws IOException {
		int n = readInt();
		int[][] weight = new int[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				weight[i][j] = readInt();
				weight[j][i] = weight[i][j];
			}
		}
		
		int[] distance = new int[n]; // distance from an unvisited node to the tree
		Arrays.fill(distance, INF);
		
		boolean[] visited = new boolean[n];
		visited[0] = true;
		int tree_cost = 0;
		
		for (int i = 0; i < n; ++i) {
			distance[i] = weight[i][0];
		}
		
		for (int count = 1; count < n; ++count) {
			// Get the unvisited node that has minimal distance to the tree
			int min_distance = INF;
			int min_node = -1;
			for (int i = 0; i < n; ++i) {
				if (!visited[i] && min_distance > distance[i]) {
					min_distance = distance[i];
					min_node = i;
				}
			}
			tree_cost += min_distance;
			visited[min_node] = true;
			for (int i = 0; i < n; ++i) {
				if (distance[i] > weight[i][min_node]) {
					distance[i] = weight[i][min_node];
				}
			}
		}
		
		out.println(tree_cost);
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("agrinet.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));*/
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
