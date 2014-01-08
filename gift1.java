/*
ID: licong12
LANG: JAVA
TASK: gift1
*/

/**
 * Practiced on 1/14/2013
 */
package com.congli.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class gift1 {
	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		gift1 test = new gift1();
		test.start();
	}

	public void start()
	{
		try {
			in = new BufferedReader(new FileReader("gift1.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
			/*in = new BufferedReader(new FileReader("..\\USACO\\src\\com\\congli\\usaco\\training\\input.txt"));
			out = new PrintWriter(System.out);*/
			solve();
			in.close();
			out.close();
		} catch (Throwable t) {
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
	
	public void solve() throws IOException
	{
		int numPeople = readInt();
		String[] people = new String[numPeople];
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		initialize(people, map, numPeople);
		
		int count = 0;
		while(count < numPeople)
		{
			String name = readString();
			int gift = readInt();
			int numPeopleToGive = readInt();
			if(numPeopleToGive != 0)
			{
				int giftForEachPeople = gift / numPeopleToGive;
				map.put(name, map.get(name) + gift%numPeopleToGive - gift);
				
				while(numPeopleToGive > 0)
				{
					String peopleToGive = readString();
					map.put(peopleToGive, map.get(peopleToGive) + giftForEachPeople);
					--numPeopleToGive;
				}
			}
			else
				map.put(name, map.get(name) + gift);
			++count;
		}
		
		for(int i = 0; i < people.length; ++i)
			out.println(people[i] + " " + map.get(people[i]));
	}
	
	private void initialize(String[] people, HashMap<String, Integer> map, int numPeople) throws IOException
	{
		int count = 0;
		while(count < numPeople)
		{
			people[count] = readString();
			map.put(people[count], 0);
			++count;
		}
	}
}
