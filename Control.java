/*
 * This class represents a control for multi threaded merge sort
 */

import java.util.ArrayList;

public class Control{
	
	private ArrayList <Integer[]> pool;
	private int maxThreads;
	private int threadCnt;
	private boolean done;
	
	public Control(int threads)
	{
		pool = new ArrayList <Integer[]>();
		maxThreads = threads;
		threadCnt = 0;
		done = false;
	}
	
	// add an array to the pool
	public void add(Integer[] a)
	{
		pool.add(a);
	}
	
	//get first item from pool
	public Integer [] getItem()
	{
		if (pool.size() > 0)
			return pool.remove(0);
		
		return null;
	}
	
	//return array in pool(i)
	public Integer[] getArray(int i)
	{
		return pool.get(i);
	}
	
	//return number of objects in pool
	public int size()
	{
		return pool.size();
	}
	
	//return true when sort is done
	public boolean isDone()
	{
		if (done == false && this.size() == 1 && threadCnt == 0)
		{
			System.out.println("DONE" + this.toString());
			done = true;
		}
		
		return done;
	}
	
	//return the amount of current unused threads
	public int freeThreads()
	{
		return maxThreads - threadCnt;
	}
	
	//wait for an unused thread
	public synchronized void waitForThread()
	{
		while (freeThreads() == 0 || this.size() < 2)
		{
			if (this.isDone())
				return;
			
			try {
				System.out.println("waiting" + this.toString());
				wait();
			}
			catch (InterruptedException e) {}
		}
	
		threadCnt++;		
	}
	
	//one thread finished its task
	public synchronized void finishedTask()
	{
		threadCnt--;
		notifyAll();
	}
	
	//for debugging
	public String toString()
	{
		String s = "";
		for (int i = 0; i < pool.size(); i++)
		{
			s+="\nset " + i + ":";
			int k = 0;
			while (k < pool.get(i).length)
			{
				s+= pool.get(i)[k] +" ";
				k++;
			}
		}
		
		return s;
	}
	
}
