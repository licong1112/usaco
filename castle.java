/*
ID: licong12
LANG: JAVA
TASK: castle
*/
/**
 * Practiced on 1/21/2014
 */
package com.congli.usaco;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class castle {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		castle test = new castle();
		test.start();
	}
	
	public void solve() throws IOException {
		int n = readInt(), m = readInt();
		int[][] land = new int[m][n];
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				land[i][j] = readInt();
			}
		}
		int[] area = new int[m*n+10];
		int[][] group = new int[m][n];
		int max_area = discoverAreaAndGetMaxArea(land, group, area);
		int landmark = 1;
		while (area[landmark] > 0) {
			++landmark;
		}
		int[] max_area_coord = new int[3];
		int max_area_break_wall = discoverMaxAreaBreakWall(group, area, max_area_coord);
		out.println(landmark-1);
		out.println(max_area);
		out.println(max_area_break_wall);
		out.print(max_area_coord[0] + " " + max_area_coord[1] + " ");
		out.println(max_area_coord[2] == 1 ? "N" : "E");
	}
	
	public int discoverMaxAreaBreakWall(int[][] group, int[] area, int[] max_area_coord) {
		int max_area_break_wall = 0;
		int m = group.length, n = group[0].length;
		int new_area = 0;
		for (int j = 0; j < n; ++j) {
			for (int i = m-1; i >= 0; --i) {
				// North
				if (i > 0 && group[i][j] != group[i-1][j]) {
					new_area = area[group[i][j]] + area[group[i-1][j]];
					if (max_area_break_wall < new_area) {
						max_area_break_wall = new_area;
						max_area_coord[0] = i+1;
						max_area_coord[1] = j+1;
						max_area_coord[2] = 1;
					}
				}
				// East
				if (j < n-1 && group[i][j] != group[i][j+1]) {
					new_area = area[group[i][j]] + area[group[i][j+1]];
					if (max_area_break_wall < new_area) {
						max_area_break_wall = new_area;
						max_area_coord[0] = i+1;
						max_area_coord[1] = j+1;
						max_area_coord[2] = 2;
					}
				}
			}
		}
		return max_area_break_wall;
	}
	
	public int discoverAreaAndGetMaxArea(int[][] land, int[][] group, int[] area) {
		int m = land.length, n = land[0].length;
		int landmark = 1;
		int max_area = 0;
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				if (group[i][j] == 0) {
					group[i][j] = landmark;
					area[landmark]++;
					dfs(land, group, area, i, j, landmark);
					max_area = Math.max(max_area, area[landmark]);
					++landmark;
				}
			}
		}
		return max_area;
	}
	
	public void dfs(int[][] land, int[][] group, int[] area, int row, int col, int landmark) {
		// West
		if ((land[row][col] & 1) == 0 && col > 0 && group[row][col-1] == 0) {
			group[row][col-1] = landmark;
			area[landmark]++;
			dfs(land, group, area, row, col-1, landmark);
		}
		// North
		if (((land[row][col] >> 1) & 1) == 0 && row > 0 && group[row-1][col] == 0) {
			group[row-1][col] = landmark;
			area[landmark]++;
			dfs(land, group, area, row-1, col, landmark);
		}
		// East
		if (((land[row][col] >> 2) & 1) == 0 && col < land[0].length-1 && group[row][col+1] == 0) {
			group[row][col+1] = landmark;
			area[landmark]++;
			dfs(land, group, area, row, col+1, landmark);
		}
		// South
		if (((land[row][col] >> 3) & 1) == 0 && row < land.length-1 && group[row+1][col] == 0) {
			group[row+1][col] = landmark;
			area[landmark]++;
			dfs(land, group, area, row+1, col, landmark);
		}
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("castle.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));*/
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


