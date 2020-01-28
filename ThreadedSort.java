
public class ThreadedSort {
	
	private int arrSize;
	private Control control;
	
	public ThreadedSort(int max, int[] a)
	{
		arrSize = a.length;
		control = new Control(max);
		
		//break array into n arrays of size 1, and add all to the pool
		for (Integer x: a)
		{
			Integer [] temp = {x};
			control.add(temp);
		}
	}
	
	//work for each thread, up to max threads
		public synchronized int[] threadedMerge()
		{	
			int [] sortedArr = new int[arrSize];
			
			//continue working while sort isn't done
			while (control.freeThreads() > 0 && !control.isDone())
			{
				control.waitForThread(); //wait for free thread
				MergeSort thread = new MergeSort(control.getItem(),control.getItem(), control);
				thread.start();
			}
			
			control.waitForAll(); //wait for all threads to finish
			return sortedArr;
		}

}
