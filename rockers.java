/*
ID: licong12
LANG: JAVA
TASK: rockers
*/
/**
 * Practiced on 5/1/2014.
 * 
 * A modified knapsack problem.
 * 
 * Consider the following two cases:
 * 1. Put current song on the disk. It can be put on the disk if and only if it
 *    fits the disk capacity.
 * 2. Don't put current song on the disk.
 * 
 * We can concatenate the disks, so that we have a disk of total length T*M.
 * 
 * dp[i][j] represents the maximum number of songs can be put in the disks
 * when considering the first i songs and the first j minutes.
 * 
 * Therefore, dp[i][j] = max(a, b), where a = the case not putting the i-th
 * song, b = put the i-th song.
 * 
 * Obviously, a = dp[i-1][j].
 * b should be carefully calculated: 
 * b = max(dp[i-1][j-length_of_song_i], dp[i-1][the last minute of the previous disk]) + 1
 * The second term in the max() is the case that there is no song can be fit
 * together with the i-th song. In this case, we need to consider how many songs
 * can be put in the previous disk.
 */
package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class rockers {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		rockers test = new rockers();
		test.start();
	}

	public void solve() throws IOException {
		int num_songs = readInt();
		int max_min = readInt();
		int num_disks = readInt();
		int[] song_length = new int[num_songs];
		for (int i = 0; i < num_songs; ++i) {
			song_length[i] = readInt();
		}
		
		int[] dp = new int[num_disks*max_min];
		
		for (int song = 0; song < num_songs; ++song) {
			for (int end = dp.length-1; end >= song_length[song]-1; --end) {
				// Start minute of the song
				int start = end - song_length[song] + 1;
				if (start >= 0) {
					int a = dp[end]; // Don't choose current song
					int b = 0;
					
					// If this song fits in current disk, then consider
					// choosing this song.
					// Calculate b = max(dp[start-1], dp[max # song of previous record]) + 1,
					// then dp[end] = max(a, b).
					if (end/max_min == start/max_min) {
						b = start == 0 ? 0 : dp[start-1];
						// If there is a previous disk
						if (start/max_min > 0) {
							// (start/max_min)*max_min-1 is the index of the
							// last minute of the previous disk
							b = Math.max(b, dp[(start/max_min)*max_min-1]);
						}
						++b;
					}
					dp[end] = Math.max(a, b);
				}
			}
		}
		out.println(dp[dp.length-1]);
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("rockers.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("rockers.out")));*/
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