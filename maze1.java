/*
ID: licong12
LANG: JAVA
TASK: maze1
*/
/**
 * Practiced on 2/25/2014
 * 
 * A simple BFS can solve this problem. It's just tedious processing the data.
 * 
 * 1. There are W*H nodes, from (0, 0) to (H-1, W-1).
 * 2. Each node is represented by a single number. For a node at position
 *    (h, w), it is represented as h*W+w.
 * 3. Each node is connected to at most 4 other nodes. graph[i][0] stores
 *    the number of nodes that node i connects to. graph[i][1] to graph[i][n]
 *    contains the index of the n nodes that node i connects to.
 *    
 * NOTE!!!
 * The following implementation used two BFSs, one for each exit.
 * In fact it's sufficient to perform ONLY ONE BFS!! Just need to add both 
 * exits to the queue at the same time!!
 */
package com.congli.usaco;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class maze1 {
	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		maze1 test = new maze1();
		test.start();
	}
	
	public void solve() throws IOException {
		int w = readInt();
		int h = readInt();
		char[][] maze = new char[2*h+1][2*w+1];
		String str = "";
		for (int i = 0; i < 2*h+1; ++i) {
			str = in.readLine();
			for (int j = 0; j < 2*w+1; ++j) {
				maze[i][j] = str.charAt(j);
			}
		}
		
		// build graph
		int[][] graph = new int[h*w][5];
		for (int i = 0; i < h; ++i) {
			for (int j = 0; j < w; ++j) {
				findConnection(j, i, w, h, maze, graph);
			}
		}
		
		// find exit
		int[] exit = new int[2];
		int exit_index = 0;
		for (int i = 0; i < 2*w+1; ++i) {
			if (maze[0][i] == ' ') {
				exit[exit_index++] = i/2;
			}
			if (maze[2*h][i] == ' ') {
				exit[exit_index++] = (h-1)*w+i/2;
			}
		}
		for (int i = 0; i < 2*h+1; ++i) {
			if (maze[i][0] == ' ') {
				exit[exit_index++] = i/2*w;
			}
			if (maze[i][2*w] == ' ') {
				exit[exit_index++] = i/2*w + (w-1);
			}
		}
		
		int[] length_0 = new int[h*w]; // length from each node to exit 0
		int[] length_1 = new int[h*w]; // length from each node to exit 1
		getPathLengthForAllNodes(exit[0], graph, length_0);
		getPathLengthForAllNodes(exit[1], graph, length_1);
		
		int result = 0;
		for (int i = 0; i < h*w; ++i) {
			result = Math.max(result, Math.min(length_0[i], length_1[i]));
		}
		out.println(result);
		
		
		
		// The following implementation takes only one BFS!
		
		/*int[] length_2 = new int[h*w];
		getPathLengthForAllNodes2(exit[0], exit[1], graph, length_2);
		int result = 0;
		for (int i = 0; i < h*w; ++i) {
			result = Math.max(result, length_2[i]);
		}
		out.println(result);*/
	}
	
	public void getPathLengthForAllNodes(int start, int[][] graph, int[] length) { // BFS
		boolean[] visited = new boolean[graph.length];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(start);
		int curr_node = start;
		int num_child = 0;
		int curr_length = 1;
		visited[start] = true;
		while (!queue.isEmpty()) {
			LinkedList<Integer> next_queue = new LinkedList<Integer>();
			while (!queue.isEmpty()) {
				curr_node = queue.pollFirst();
				length[curr_node] = curr_length;
				num_child = graph[curr_node][0];
				for (int i = 1; i <= num_child; ++i) {
					if (!visited[graph[curr_node][i]]) {
						next_queue.add(graph[curr_node][i]);
						visited[graph[curr_node][i]] = true;
					}
				}
			}
			++curr_length;
			queue = next_queue;
		}
	}
	
	public void getPathLengthForAllNodes2(int start1, int start2, int[][] graph, int[] length) { // BFS
		boolean[] visited = new boolean[graph.length];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(start1);
		queue.add(start2);
		int curr_node = 0;
		int num_child = 0;
		int curr_length = 1;
		visited[start1] = true;
		visited[start2] = true;
		while (!queue.isEmpty()) {
			LinkedList<Integer> next_queue = new LinkedList<Integer>();
			while (!queue.isEmpty()) {
				curr_node = queue.pollFirst();
				length[curr_node] = curr_length;
				num_child = graph[curr_node][0];
				for (int i = 1; i <= num_child; ++i) {
					if (!visited[graph[curr_node][i]]) {
						next_queue.add(graph[curr_node][i]);
						visited[graph[curr_node][i]] = true;
					}
				}
			}
			++curr_length;
			queue = next_queue;
		}
	}
	
	public void findConnection(int a, int b, int w, int h, char[][] maze, int[][] graph) {
		int x = 2*a+1;
		int y = 2*b+1;
		int curr_graph_index = b*w+a;
		if (x+1 < 2*w && maze[y][x+1] == ' ') { //east
			graph[curr_graph_index][++graph[curr_graph_index][0]] = b*w+(a+1);
		}
		if (y+1 < 2*h && maze[y+1][x] == ' ') { //south
			graph[curr_graph_index][++graph[curr_graph_index][0]] = (b+1)*w+a;
		}
		if (x-1 > 0 && maze[y][x-1] == ' ') { //west
			graph[curr_graph_index][++graph[curr_graph_index][0]] = b*w+(a-1);
		}
		if (y-1 > 0 && maze[y-1][x] == ' ') { //north
			graph[curr_graph_index][++graph[curr_graph_index][0]] = (b-1)*w+a;
		}
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("maze1.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));*/
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
