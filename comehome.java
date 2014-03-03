/*
ID: licong12
LANG: JAVA
TASK: comehome
*/
/**
 * Practiced on 3/1/2014
 * 
 * A simple Dijkstra's algorithm can do the job. The solution to the problem
 * given by USACO says Floyd-Warshall can solve it on time also.
 */
package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class comehome {
	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		comehome test = new comehome();
		test.start();
	}
	
	public void solve() throws IOException {
		int num_link = readInt();
		int[] distance = new int[60];
		int[][] weight = new int[60][60];
		for (int i = 0; i < num_link; ++i) {
			String str = in.readLine();
			int a = str.charAt(0) - 'A';
			int b = str.charAt(2) - 'A';
			if (a != b) {
				int w = Integer.parseInt(str.substring(4));
				weight[a][b] = weight[a][b] == 0 ? w : Math.min(weight[a][b], w);
				weight[b][a] = weight[a][b];
			}
		}
		for (int i = 0; i < 60; ++i) {
			distance[i] = INF;
		}
		distance[25] = 0;
		
		dijkstra(distance, weight);
		
		int min = INF;
		int min_node = -1;
		for (int i = 0; i < 25; ++i) {
			if (min > distance[i]) {
				min = distance[i];
				min_node = i;
			}
		}

		out.println((char)(min_node + 'A') + " " + min);
	}
	
	public void dijkstra(int[] distance, int[][] weight) {
		boolean[] visited = new boolean[60];
		
		int min_node = findMinNode(distance, visited);
		while (min_node != -1) {
			visited[min_node] = true;
			for (int i = 0; i < 60; ++i) {
				if (weight[min_node][i] != 0) {
					if (distance[min_node] + weight[min_node][i] < distance[i]) {
						distance[i] = distance[min_node] + weight[min_node][i];
					}
				}
			}
			min_node = findMinNode(distance, visited);
		}
	}
	
	public int findMinNode(int[] distance, boolean[] visited) {
		int min = INF;
		int min_node = -1;
		for (int i = 0; i < 60; ++i) {
			if (!visited[i] && min > distance[i]) {
				min = distance[i];
				min_node = i;
			}
		}
		return min_node;
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("comehome.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));*/
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
