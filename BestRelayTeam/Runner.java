package BestRelayTeam;

import java.util.*;

public class Runner{
	String name;
	double time1;
	double time2;
	public Runner(String name, double time1, double time2){
		this.name = name;
		this.time1= time1;
		this.time2= time2;
	}
	public String getName(){
		return this.name;
	}
	public double getTime1(){
		return this.time1;
	}
	public double getTime2(){
		return this.time2;
	}
	@Override
	public String toString(){
		return name + " " + time1+ " " +time2;
	}

}
class NameComparator1 implements Comparator<Runner>{
	@Override
	public int compare(Runner r1, Runner r2){
		if(r1.time1< r2.time1) return -1;
		if(r2.time1< r1.time1) return 1;
		return 0;

	}
}
class NameComparator2 implements Comparator<Runner>{
	@Override
	public int compare(Runner r1, Runner r2){
		if(r1.time2<r2.time2) return -1;
		if(r2.time2< r1.time2) return 1;
		return 0;
	}
}
