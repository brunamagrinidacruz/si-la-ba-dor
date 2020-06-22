package model;

import java.util.Calendar;

public class Random {
	private long p = 2147483648L;
	private long m = 843314861;
	private long a = 453816693;
	
	
	private long xi = 1023;


	public double getRand() {
		xi = (a + m * xi) % p;
		double d = xi;
		return d / p;
	}
	
	public int getIntRand(int max)
	{
		double d = getRand() * max;
		return (int) d;
	}

	public void setSemente(int semente) {
       xi = semente;		
	}

	public Random(int k)
	{
		xi = k;
	}
	
	public Random() {
		xi = Calendar.getInstance().getTimeInMillis() % p;
	}
	
	public String toString() {
		return xi + "";
	}
	
	public static void main(String[] args)
	{
		Random r = new Random();
		
		for(int i = 0; i < 10; i++) System.out.println(r.getIntRand(50));
	}
}