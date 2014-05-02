/*
ID: licong12
LANG: JAVA
TASK: fence
*/
/**
 * Practiced on 4/8/2014
 */
package com.congli.usaco;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class fence {
	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		fence test = new fence();
		test.start();
	}
	
	public void solve() throws IOException {
		int num_edges = readInt();
		int[] num_neighbors = new int[501];
		int[][] graph = new int[501][501];
		int a = 0, b = 0;
		int min_ind = Integer.MAX_VALUE;
		for (int i = 0; i < num_edges; ++i) {
			a = readInt();
			b = readInt();
			min_ind = Math.min(Math.min(min_ind, a), b);
			graph[a][b]++;
			graph[b][a]++;
			num_neighbors[a]++;
			num_neighbors[b]++;
		}
		
		// If there is no node with odd degree, then use the smallest numbered
		// node as start. Otherwise, use the first node which has odd degree
		// as start.
		int start = min_ind;
		for (int i = 1; i <= 500; ++i) {
			if (num_neighbors[i] %2 == 1) {
				start = i;
				break;
			}
		}
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		eulerianTour(result, start, num_neighbors, graph);
		for (int i = result.size()-1; i >= 0; --i) {
			out.println(result.get(i));
		}
	}
	
	public void eulerianTour(ArrayList<Integer> result, int node_ind, int[] num_neighbors, int[][] graph) {
		if (num_neighbors[node_ind] == 0) {
			result.add(node_ind);
			return;
		}
		
		int neighbor_ind = 1;
		while (num_neighbors[node_ind] > 0) {
			// Find the next neighbor
			while (graph[node_ind][neighbor_ind] == 0) {
				++neighbor_ind;
			}
			// Delete the edge
			graph[node_ind][neighbor_ind]--;
			graph[neighbor_ind][node_ind]--;
			num_neighbors[node_ind]--;
			num_neighbors[neighbor_ind]--;
			eulerianTour(result, neighbor_ind, num_neighbors, graph);
		}
		result.add(node_ind);
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("fence.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("fence.out")));*/
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
