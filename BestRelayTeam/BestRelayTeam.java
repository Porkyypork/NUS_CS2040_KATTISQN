package BestRelayTeam;

import java.util.*;
import java.io.*;

class BestRelayTeam{
		public static void main(String[] args){
			FastIO fio = new FastIO();
			Runner[] fastest = new Runner[4];
			double smallest = Integer.MAX_VALUE;
			Runner[] total = new Runner[4];
			int num = fio.nextInt();
			Runner[] runners1 = new Runner[num];
			Runner[] runners2 = new Runner[num];
			for(int i=0; i<num; i++){
				String name = fio.next();
				double time1 = fio.nextDouble();
				double time2 = fio.nextDouble();
				runners1[i] = new Runner(name, time1, time2);
				runners2[i] = new Runner(name,time1,time2);
			}
			Arrays.sort(runners1, new NameComparator1());
			Arrays.sort(runners2,new NameComparator2());
			for(int i=0;i<4;i++){
				int count=0;
				total[count] = runners1[i];
				for(int j=0;j<4 && count<3; j++){
					if(!runners2[j].getName().equals(runners1[i].getName())){
						count++;
						total[count] = runners2[j];	
					}
				}
				double total_time = total[0].getTime1();
				for(int k =1;k<4; k++){
					total_time += total[k].getTime2();
				}
				if(total_time< smallest){
					for(int k=0;k<4;k++){
						fastest[k] = total[k];
					}
					smallest=total_time;
				}
			}
			fio.println(smallest);
			for(Runner runner:fastest){
				fio.println(runner.getName());
			}
			fio.close();
		}
}

class FastIO extends PrintWriter
{
		BufferedReader br;
		StringTokenizer st;

		public FastIO()
		{
				super(new BufferedOutputStream(System.out));
				br = new BufferedReader(new
								InputStreamReader(System.in));
		}

		String next()
		{
				while (st == null || !st.hasMoreElements())
				{
						try
						{
								st = new StringTokenizer(br.readLine());
						}
						catch (IOException  e)
						{
								e.printStackTrace();
						}
				}
				return st.nextToken();
		}

		int nextInt()
		{
				return Integer.parseInt(next());
		}

		long nextLong()
		{
				return Long.parseLong(next());
		}

		double nextDouble()
		{
				return Double.parseDouble(next());
		}

		String nextLine()
		{
				String str = "";
				try
				{
						str = br.readLine();
				}
				catch (IOException e)
				{
						e.printStackTrace();
				}
				return str;
		}
}
