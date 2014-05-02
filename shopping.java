/*
ID: licong12
LANG: JAVA
TASK: shopping
*/
/**
 * Practiced on 4/11/2014
 * 
 * The problem is made complicated for no reason. Stupid problem.
 */
package com.congli.usaco;

import java.io.*;
import java.util.StringTokenizer;

public class shopping {
	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		shopping test = new shopping();
		test.start();
	}
	
	public void solve() throws IOException {
		int num_special_offer = readInt();
		int[][] special_offer = new int[num_special_offer][1000];
		int[] special_price = new int[num_special_offer];
		
		for (int i = 0; i < num_special_offer; ++i) {
			int num_product = readInt();
			for (int j = 0; j < num_product; ++j) {
				special_offer[i][readInt()] = readInt();
			}
			special_price[i] = readInt();
		}
		
		int num_product = readInt();
		int[] code = new int[6];
		int[] num_required = new int[6];
		int[] original_price = new int[6];
		for (int i = 1; i <= num_product; ++i) {
			code[i] = readInt();
			num_required[i] = readInt();
			original_price[i] = readInt();
		}
		
		int[][][][][] dp = new int[6][6][6][6][6];
		for (int a1 = 0; a1 <= num_required[1]; ++a1) {
			for (int a2 = 0; a2 <= num_required[2]; ++a2) {
				for (int a3 = 0; a3 <= num_required[3]; ++a3) {
					for (int a4 = 0; a4 <= num_required[4]; ++a4) {
						for (int a5 = 0; a5 <= num_required[5]; ++a5) {
							
							if (a1 == 0 && a2 == 0 && a3 == 0 && a4 == 0 && a5 == 0) continue;
							dp[a1][a2][a3][a4][a5] = a1*original_price[1] + 
													 a2*original_price[2] + 
													 a3*original_price[3] + 
													 a4*original_price[4] + 
													 a5*original_price[5];
							for (int i = 0; i < num_special_offer; ++i) {
								if (a1 - special_offer[i][code[1]] >= 0 &&
									a2 - special_offer[i][code[2]] >= 0 &&
									a3 - special_offer[i][code[3]] >= 0 &&
									a4 - special_offer[i][code[4]] >= 0 &&
									a5 - special_offer[i][code[5]] >= 0) {
									dp[a1][a2][a3][a4][a5] = Math.min(dp[a1][a2][a3][a4][a5], 
											dp[a1-special_offer[i][code[1]]][a2-special_offer[i][code[2]]][a3-special_offer[i][code[3]]][a4-special_offer[i][code[4]]][a5-special_offer[i][code[5]]] + special_price[i]);
								}
							}
							
						}
					}
				}
			}
		}
		
		out.println(dp[num_required[1]][num_required[2]][num_required[3]][num_required[4]][num_required[5]]);
	}
	
	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("shopping.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("shopping.out")));*/
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
