/*
ID: licong12
LANG: JAVA
TASK: cowtour
*/
/**
 * Practiced on 2/28/2014
 * 
 * Complicated.
 * 
 * See the last answer in the link below for a good instruction:
 * http://www.nocow.cn/index.php/USACO/cowtour
 */
package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class cowtour {
	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	final double INF = Double.MAX_VALUE;
	
	public static void main(String[] args) {
		cowtour test = new cowtour();
		test.start();
	}
	
	public void solve() throws IOException {
		int n = readInt();
		int[] x = new int[n];
		int[] y = new int[n];
		int[] parent = new int[n];
		int[] size = new int[n];
		for (int i = 0; i < n; ++i) {
			parent[i] = i;
			size[i] = 1;
		}
		for (int i = 0; i < n; ++i) {
			x[i] = readInt();
			y[i] = readInt();
		}
		
		double[][] distance = new double[n][n];
		for (int i = 0; i < n; ++i) {
			String str = in.readLine();
			for (int j = i+1; j < n; ++j) {
				if (str.charAt(j) == '1') {
					mergeParent(i, j, parent, size);
					distance[i][j] = getDistance(i, j, x, y);
				} else {
					distance[i][j] = INF;
				}
				distance[j][i] = distance[i][j];
			}
		}
		
		floydWarshall(distance);
		
		double[] max_dis_in_pasture = new double[n];
		double[] diameter = new double[n];
		for (int i = 0; i < n; ++i) {
			int parent_i = findParent(i, parent);
			for (int j = 0; j < n; ++j) {
				if (distance[i][j] != INF) {
					max_dis_in_pasture[i] = Math.max(max_dis_in_pasture[i], distance[i][j]);
				}
			}
			diameter[parent_i] = Math.max(diameter[parent_i], max_dis_in_pasture[i]);
		}
		
		double min_diameter = INF;
		for (int i = 0; i < n; ++i) {
			for (int j = i+1; j < n; ++j) {
				int parent_i = findParent(i, parent);
				int parent_j = findParent(j, parent);
				if (parent_i != parent_j) {
					// By connecting i and j, the distance calculated by
					// max_dis_in_pasture[i] + getDistance(i, j, x, y) + max_dis_in_pasture[j]
					// may be smaller than the diameter of the group that i (or j) belongs to.
					// So the diameter of the newly connected pasture is the
					// maximum of the distance and the diameter of the two groups.
					double new_diameter = Math.max(Math.max(diameter[parent_i], diameter[parent_j]), 
							max_dis_in_pasture[i] + getDistance(i, j, x, y) + max_dis_in_pasture[j]);
					min_diameter = Math.min(min_diameter, new_diameter);
				}
			}
		}
		
		out.printf("%.6f\n", min_diameter);
	}
	
	public void floydWarshall(double[][] distance) {
		int n = distance.length;
		for (int k = 0; k < n; ++k) {
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < n; ++j) {
					if (distance[i][k] + distance[k][j] < distance[i][j]) {
						distance[i][j] = distance[i][k] + distance[k][j];
					}
				}
			}
		}
	}
	
	public double getDistance(int i, int j, int[] x, int[] y) {
		return Math.pow(Math.pow(x[i]-x[j], 2.0) + Math.pow(y[i]-y[j], 2.0), 0.5);
	}
	
	public int findParent(int i, int[] parent) {
		if (parent[i] != i) {
			return findParent(parent[i], parent);
		}
		return i;
	}
	
	public void mergeParent(int i, int j, int[] parent, int[] size) {
		int parent_i = findParent(i, parent);
		int parent_j = findParent(j, parent);
		if (parent_i != parent_j) {
			if (size[parent_i] > size[parent_j]) {
				parent[parent_j] = parent_i;
				size[parent_i] += size[parent_j];
			} else {
				parent[parent_i] = parent_j;
				size[parent_j] += size[parent_i];
			}
		}
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("cowtour.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));*/
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
