/*
ID: licong12
LANG: JAVA
TASK: milk2
*/

/**
 * Practiced on 2/28/2013
 */

package com.congli.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class milk2 {
	BufferedReader in;
	PrintWriter out;
	StringTokenizer tok = new StringTokenizer("");
	
	public static void main(String[] args) {
		milk2 test = new milk2();
		test.start();
	}

	public void start()
	{
		try {
			/*in = new BufferedReader(new FileReader("milk2.in"));
			out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));*/
			in = new BufferedReader(new FileReader("..\\USACO\\src\\com\\congli\\usaco\\training\\input.txt"));
			out = new PrintWriter(System.out);
			solve();
			in.close();
			out.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	public void solve() throws IOException
	{
		int size = readInt();
		ArrayList<Interval> intervals = new ArrayList<Interval>();
		for(int i = 0; i < size; ++i)
			intervals.add(new Interval(readInt(), readInt()));
		sort(intervals, 0, size-1);
		int feed_length = 0;
		int idle_length = 0;
		
		int start = intervals.get(0).start;
		int end = intervals.get(0).end;
		for(int i = 1; i < size; ++i)
		{
			if(intervals.get(i).start <= end)
				end = Math.max(end, intervals.get(i).end);
			else
			{
				feed_length = Math.max(feed_length, end-start);
				start = intervals.get(i).start;
				idle_length = Math.max(idle_length, start-end);
				end = intervals.get(i).end;
			}
		}
		feed_length = Math.max(feed_length, end-start);
		out.println(feed_length + " " + idle_length);
	}

	private void sort(ArrayList<Interval> intervals, int start, int end)
    {
        if(start >= end) return;
        
        int mid = start + (end - start) / 2;
        
        sort(intervals, start, mid);
        sort(intervals, mid+1, end);
        merge(intervals, start, mid, end);
    }
    
    private void merge(ArrayList<Interval> intervals, int start, int mid, int end)
    {
        ArrayList<Interval> temp = new ArrayList<Interval>();
        int aInd = start;
        int bInd = mid+1;
        
        for(int i = 0; i <= end-start; ++i)
        {
            if(aInd <= mid && bInd <= end)
            {
                if(intervals.get(aInd).start < intervals.get(bInd).start)
                	temp.add(intervals.get(aInd++));
                else
                	temp.add(intervals.get(bInd++));
            }
            else if(aInd <= mid)
            	temp.add(intervals.get(aInd++));
            else
            	temp.add(intervals.get(bInd++));
        }
        
        for(int i = 0; i < temp.size(); ++i)
        	intervals.set(start++, temp.get(i));
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
	
	class Interval
	{
		int start;
		int end;
		public Interval(int start, int end)
		{
			this.start = start;
			this.end = end;
		}
	}
}



