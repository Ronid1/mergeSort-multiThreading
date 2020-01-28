import java.util.ArrayList;

public class Control {
	
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
	public synchronized void add(Integer[] a)
	{
		pool.add(a);
	}
	
	//get first item from pool
	public synchronized Integer [] getItem()
	{
		if (pool.size() > 0)
			return pool.remove(0);
		
		return null;
	}
	
	//return number of objects in pool
	public int size()
	{
		return pool.size();
	}
	
	//return true when sort is done
	public boolean isDone()
	{
		if (this.size() == 1 && threadCnt == 0)
			done = true;
		
		return done;
	}
	
	//return the amount of current unused threads
	public int freeThreads()
	{
		return maxThreads - threadCnt;
	}
	
	/*public void addThread()
	{
		//new thread
		threadCnt++;
	}*/
	
	//wait for an unused thread
	public void waitForThread()
	{
		while (threadCnt == maxThreads)
		{
			try {
				wait();
			}
			catch (InterruptedException e) {}
		}
	
		threadCnt++;
	}

	//wait for all current threads to finish
	public void waitForAll()
	{
		while (!this.isDone())
		{
			try {
				wait();
			}
			catch (InterruptedException e) {}
		}
		System.out.println("done wait for all");
	}
	
	//one thread finished its task
	public void finishedTask()
	{
		threadCnt--;
		notifyAll();
	}
	
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
