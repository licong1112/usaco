/*
ID: licong12
LANG: JAVA
TASK: packrec
*/

/**
 * Submitted on 12/11/2013
 * 
 * Didn't come up with the solution. Checked the answer.
 * http://starforever.blog.hexun.com/2097115_d.html
 */

package com.congli.usaco;


import java.util.*;
import java.io.*;
public class packrec {
 
	int w[]=new int[9];
	int h[]=new int[9];
	int a1,a2,a3,a4,min_w,min_h,min_ans=99999,num_ans=0;
	class Ans implements Comparable
	{
		int p,q,area;
		public int compareTo(Object o) {
			Ans t=(Ans)o;
			if (p<t.p) return -1;
			else if (p==t.p) return 0;
			return 1;
			// TODO Auto-generated method stub
			//return 0;
		}
		public void insert(int a,int b)
		{
			if(a>b)
			{
				q=a;
				p=b;
			}else
			{
				p=a;
				q=b;
			}
			area=a*b;
		}
	}
	Ans ans[]=new Ans[5000];
	public static void main(String[] args) throws IOException 
	{
		new packrec().run();
	}
	void run() throws IOException
	{
		Scanner cin=new Scanner(new FileReader("packrec.in"));
		for(int i=1;i<=4;i++)
		{
			w[i]=cin.nextInt();
			h[i]=cin.nextInt();
			w[i+4]=h[i];
			h[i+4]=w[i];
		}
		for(a1=1;a1<=8;a1++)
		{
			for( a2=1;a2<=8;a2++)
			{
				if(a2%4==a1%4) continue;
				for( a3=1;a3<=8;a3++)
				{
					if((a3%4==a1%4)||(a3%4==a2%4)) continue;
					for( a4=1;a4<=8;a4++)
					{
						if((a4%4==a3%4)||(a4%4==a2%4)||(a4%4==a1%4)) continue;
						try1();
						try2();
						try3();
						try4();
						try5();
					}
				}
			}
		}
		Arrays.sort(ans,1,num_ans+1);
		output();
		System.exit(0);
	}
	void try1()
	{
		min_w=w[a1]+w[a2]+w[a3]+w[a4];
		min_h=Math.max(h[a1],h[a2]);
		min_h=Math.max(min_h, h[a3]);
		min_h=Math.max(min_h, h[a4]);
		if(min_ans>=(min_h*min_w))
		{
			ans[++num_ans]=new Ans();
			ans[num_ans].insert(min_w, min_h);
			min_ans=min_h*min_w;
		}
 
	}
	void try2()
	{
		min_w=Math.max(w[a1]+w[a2]+w[a3], w[a4]);
		min_h=Math.max(h[a1], h[a2]);
		min_h=Math.max(min_h, h[a3]);
		min_h+=h[a4];
		if(min_ans>=(min_h*min_w))
		{
			ans[++num_ans]=new Ans();
			ans[num_ans].insert(min_w, min_h);
			min_ans=min_h*min_w;
		}
	}
	void try3()
	{
		min_w=Math.max(w[a1]+w[a2], w[a3])+w[a4];
		min_h=Math.max(h[a1]+h[a3], h[a2]+h[a3]);
		min_h=Math.max(min_h, h[a4]);
		if(min_ans>=(min_h*min_w))
		{
			ans[++num_ans]=new Ans();
			ans[num_ans].insert(min_w, min_h);
			min_ans=min_h*min_w;
		}
	}
	void try4()
	{
		min_w=Math.max(w[a3], w[a4]);
		min_w+=w[a1]+w[a2];
		min_h=Math.max(h[a1], h[a3]+h[a4]);
		min_h=Math.max(min_h, h[a2]);
		if(min_ans>=(min_h*min_w))
		{
			ans[++num_ans]=new Ans();
			ans[num_ans].insert(min_w, min_h);
			min_ans=min_h*min_w;
		}
	}
	void try5()
	{
		min_h=Math.max(h[a1]+h[a3], h[a2]+h[a4]);
		if(h[a3]>=(h[a2]+h[a4]))//(1)
		{
			min_w=Math.max(w[a1],w[a3]+w[a2]);
			min_w=Math.max(min_w, w[a3]+w[a4]);
		}
		if(h[a3]>h[a4]&&h[a3]<(h[a2]+h[a4]))//(2)
		{
			min_w=Math.max(w[a1]+w[a2],w[a2]+w[a3]);
			min_w=Math.max(min_w, w[a3]+w[a4]);
		}
		if(h[a4]>h[a3]&&h[a4]<(h[a1]+h[a3]))//(3)
		{
			min_w=Math.max(w[a1]+w[a2], w[a1]+w[a4]);
			min_w=Math.max(min_w, w[a3]+w[a4]);
		}
		if(h[a4]>=(h[a1]+h[a3]))//(4)
		{
			min_w=Math.max(w[a2], w[a1]+w[a4]);
			min_w=Math.max(min_w, w[a3]+w[a4]);
		}
		if(h[a3]==h[a4])
		{
			min_w=Math.max(w[a1]+w[a2], w[a3]+w[a4]);
		}
		if(min_ans>=(min_h*min_w))
		{
			ans[++num_ans]=new Ans();
			ans[num_ans].insert(min_h, min_w);
			min_ans=min_h*min_w;
		}
	}
	void output() throws IOException
	{
		PrintWriter cout=new PrintWriter(new BufferedWriter(new FileWriter("packrec.out")));
		cout.println(min_ans);
		int num[]=new int[201];
		for(int i=1;i<=200;i++) num[i]=0;
		for(int i=1;i<=num_ans;i++)
		{
			if(ans[i].area==min_ans)
			{
				num[ans[i].p]=ans[i].q;
			}
		}
		for(int i=1;i<=200;i++)
			if(num[i]!=0)
				cout.println(i+" "+num[i]);
		cout.close();
 
	}
}
