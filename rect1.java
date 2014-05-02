/*
ID: licong12
LANG: JAVA
TASK: rect1
*/
/**
 * Practiced on 3/14/2014
 * 
 * Pretty tough to me.
 * 
 * Used the "floating method". Put the rectangles in backward order.
 * When putting the i-th rectangle, let it flow upward. When it touches the
 * i+1-th rectangle, it will be cut to four pieces "A B C D" as follows:
 * 
 * ------------------
 * |    |     B     |
 * |    -------------
 * |    |      |    |
 * | A  |      |    |
 * |    |      |    |
 * |    |      | C  |
 * |    |      |    |
 * |-----------|    |
 * |   D       |    |
 * ------------------
 * 
 * Then each of the four pieces keeps floating upward, until they get to the
 * top layer. 
 * 
 * Such a floating process is simulated by recursion.
 * 
 * Floating method is not very fast.
 * 
 * See http://www.nocow.cn/index.php/USACO/rect1 for good discussions, although
 * I only understand the floating method.
 */

package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class rect1 {

	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	int[] low_x = new int[1001];
	int[] low_y = new int[1001];
	int[] high_x = new int[1001];
	int[] high_y = new int[1001];
	int[] color = new int[1001];
	int[] result = new int[2501];
	
	public static void main(String[] args) {
		rect1 test = new rect1();
		test.start();
	}
	
	public void solve() throws IOException {
		int A = readInt(), B = readInt(), n = readInt();
		
		high_x[0] = A;
		high_y[0] = B;
		color[0] = 1;
		
		for (int i = 1; i <= n; ++i) {
			low_x[i] = readInt();
			low_y[i] = readInt();
			high_x[i] = readInt();
			high_y[i] = readInt();
			color[i] = readInt();
		}
		
		result[color[n]] = (high_x[n] - low_x[n]) * (high_y[n] - low_y[n]);
		
		for (int i = n-1; i >= 0; --i) {
			calc_area(low_x[i], low_y[i], high_x[i], high_y[i], color[i], i+1);
		}
		for (int i = 1; i <= 2500; ++i) {
			if (result[i] > 0) {
				out.println(i + " " + result[i]);
			}
		}
	}
	
	public void calc_area(int l_x, int l_y, int h_x, int h_y, int float_color, int rec_ind) {
		if (l_x == h_x || l_y == h_y) return;
		if (rec_ind == color.length) {
			result[float_color] += (h_x - l_x) * (h_y - l_y);
			return;
		}
		if (l_x < low_x[rec_ind]) {
			calc_area(l_x, max(l_y, low_y[rec_ind]), min(h_x, low_x[rec_ind]), max(h_y, low_y[rec_ind]), float_color, rec_ind+1);
		}
		if (l_y < low_y[rec_ind]) {
			calc_area(min(l_x, high_x[rec_ind]), l_y, min(h_x, high_x[rec_ind]), min(h_y, low_y[rec_ind]), float_color, rec_ind+1);
		}
		if (h_y > high_y[rec_ind]) {
			calc_area(max(l_x, low_x[rec_ind]), max(l_y, high_y[rec_ind]), max(h_x, low_x[rec_ind]), h_y, float_color, rec_ind+1);
		}
		if (h_x > high_x[rec_ind]) {
			calc_area(max(l_x, high_x[rec_ind]), min(l_y, high_y[rec_ind]), h_x, min(h_y, high_y[rec_ind]), float_color, rec_ind+1);
		}
	}
	
	public int max(int a, int b) {
		if (a > b) return a;
		return b;
	}
	
	public int min(int a, int b) {
		if (a < b) return a;
		return b;
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("rect1.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("rect1.out")));*/
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
