import java.util.ArrayList;

public class Control {
	
	private ArrayList <Integer[]> pool;
	private int maxThreads;
	private int threadCnt;
	private int threadMax;
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
	
	public synchronized Integer [] getItem()
	{
		return pool.remove(0);
	}
	
	public int size()
	{
		return pool.size();
	}
	
	
	public boolean isDone()
	{
		if (this.size() == 1 && threadCnt == 0)
			done = true;
		
		return done;
	}
	
	public int freeThreads()
	{
		return maxThreads - threadCnt;
	}
	
	public void addThread()
	{
		//new thread
		threadCnt++;
	}
	
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

	public void waitForAll()
	{
		while (!done)
		{
			try {
				wait();
			}
			catch (InterruptedException e) {}
		}
	}
	
	//one thread finished its task
	public void finishedTask()
	{
		threadCnt--;
		notifyAll();
	}
	
}
