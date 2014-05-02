/*
ID: licong12
LANG: JAVA
TASK: butter
*/
/**
 * Practiced on 4/4/2013
 * 
 * Thought of using Floyd-Warshall, but TLE.
 * The code below used Bellman-Ford algorithm for each node. But the key is
 * to detect if further relax operation is needed. If not, we can stop early.
 * In this way, we usually don't necessarily have to traverse the edges |Node|-1
 * times, thus avoid TLE.
 */
package com.congli.usaco;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class butter {
	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		butter test = new butter();
		test.start();
	}
	
	public void solve() throws IOException {
		int num_cows = readInt(), num_pastures = readInt(), num_paths = readInt();
		int[] num_cows_in_pasture = new int[num_pastures];
		for (int i = 0; i < num_cows; ++i) {
			num_cows_in_pasture[readInt()-1]++;
		}
		int[][] edge = new int[num_paths*2][3];
		for (int i = 0; i < num_paths; ++i) {
			edge[i][0] = readInt()-1;
			edge[i][1] = readInt()-1;
			edge[i][2] = readInt();
			edge[i+num_paths][0] = edge[i][1];
			edge[i+num_paths][1] = edge[i][0];
			edge[i+num_paths][2] = edge[i][2];
		}
		
		int min_distance = Integer.MAX_VALUE;
		for (int source = 0; source < num_pastures; ++source) {
			int[] distance_to_source = new int[num_pastures];
			
			bellmanFord(source, distance_to_source, edge);
			
			int dis = 0;
			for (int i = 0; i < num_pastures; ++i) {
				dis += num_cows_in_pasture[i] * distance_to_source[i];
			}
			min_distance = Math.min(min_distance, dis);
		}
		out.println(min_distance);
	}
	
	public void bellmanFord(int source, int[] distance_to_source, int[][] edge) {
		Arrays.fill(distance_to_source, INF);
		distance_to_source[source] = 0;
		int num_nodes = distance_to_source.length;
		
		boolean need_relax = true;
		int i = 0;
		while (i < num_nodes && need_relax) { //need_relax for early stop. Otherwise will TLE.
			need_relax = false;
			for (int j = 0; j < edge.length; ++j) {
				// Relax operation.
				if (distance_to_source[edge[j][1]] - distance_to_source[edge[j][0]] > edge[j][2]) {
					distance_to_source[edge[j][1]] = distance_to_source[edge[j][0]] + edge[j][2];
					need_relax = true;
				}
			}
			++i;
		}
	}
	
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("butter.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("butter.out")));*/
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
