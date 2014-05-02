/*
ID: licong12
LANG: JAVA
TASK: camelot
*/
/**
 * Practiced on 4/24/2014
 * 
 * Too hard for me. Check the link below for detailed analysis.
 * http://www.nocow.cn/index.php/USACO/camelot
 */
package com.congli.usaco;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class camelot {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	int r = 0;
	int c = 0;
	int num_knights = 0;
	final static int[][] knight_step = {{1, 2},
										{2, 1},
										{-1, 2},
										{-2, 1},
										{1, -2},
										{2, -1},
										{-1, -2},
										{-2, -1}};
	
	public static void main(String[] args) {
		camelot test = new camelot();
		test.start();
	}
	
	public void solve() throws IOException {
		r = readInt();
		c = readInt();
		
		int[] king = new int[2];
		king[1] = readString().charAt(0)-'A';
		king[0] = readInt()-1;
		
		int[][] knights = new int[r*c][2];
		int knight_ind = 0;
		String str = in.readLine();
		while (str != null) {
			String[] str_array = str.split(" ");
			for (int i = 0; i < str_array.length; i += 2) {
				knights[knight_ind][1] = str_array[i].charAt(0) - 'A';
				knights[knight_ind][0] = Integer.parseInt(str_array[i+1])-1;
				++knight_ind;
			}
			str = in.readLine();
		}
		
		num_knights = knight_ind;
		int[][][][] result = new int[r][c][r][c];
		for (int i = 0; i < r; ++i) {
			for (int j = 0; j < c; ++j) {
				bfs(i, j, result);
			}
		}
		
		int min_step_with_king = Integer.MAX_VALUE;
		for (int i = 0; i < r; ++i) {
			for (int j = 0; j < c; ++j) {
				min_step_with_king = Math.min(min_step_with_king, 
						minStepWithKingToXY(i, j, result, knights, king));
			}
		}
		out.println(min_step_with_king);
	}
	
	public int minStepWithKingToXY(int x, int y, int[][][][] result, int[][] knights, int[] king) {
		int sum = 0;
		for (int i = 0; i < num_knights; ++i) {
			sum += result[knights[i][0]][knights[i][1]][x][y];
		}
		// The king goes by himself
		int min_step = sum + Math.max(Math.abs(king[0]-x), Math.abs(king[1]-y));
		
		// One of the knights pick the king up at (i,j)
		// The range for i and j are is not very scientific though....
		for (int i = Math.max(0, king[0]-2); i <= Math.min(r-1, king[0]+1); ++i) {
			for (int j = Math.max(0, king[1]-2); j <= Math.min(c-1, king[1]+1); ++j) {
				for (int k = 0; k < num_knights; ++k) {
					min_step = Math.min(min_step, 
							sum
							- result[knights[k][0]][knights[k][1]][x][y]
							+ result[knights[k][0]][knights[k][1]][i][j]
							+ result[i][j][x][y]
							+ Math.max(Math.abs(king[0]-i), Math.abs(king[1]-j)));
				}
			}
		}
		return min_step;
	}
	
	public void bfs(int sx, int sy, int[][][][] result) {
		boolean[][] visited = new boolean[r][c];
		for (int i = 0; i < r; ++i) {
			for (int j = 0; j < c; ++j) {
				result[sx][sy][i][j] = 9999999;
			}
		}
		result[sx][sy][sx][sy] = 0;
		
		LinkedList<Integer> queue_x = new LinkedList<Integer>();
		LinkedList<Integer> queue_y = new LinkedList<Integer>();
		visited[sx][sy] = true;
		queue_x.add(sx); queue_y.add(sy);
		
		while (!queue_x.isEmpty()) {
			int x = queue_x.pollFirst();
			int y = queue_y.pollFirst();
			int next_x = 0, next_y = 0;
			for (int i = 0; i < 8; ++i) {
				next_x = x + knight_step[i][0];
				next_y = y + knight_step[i][1];
				if (next_x >= 0 && next_x < r && next_y >= 0 && next_y < c && !visited[next_x][next_y]) {
					queue_x.add(next_x);
					queue_y.add(next_y);
					result[sx][sy][next_x][next_y] = result[sx][sy][x][y]+1;
					visited[next_x][next_y] = true;
				}
			}
		}
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("camelot.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("camelot.out")));*/
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