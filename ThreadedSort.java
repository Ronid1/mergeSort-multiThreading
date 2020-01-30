/*
 * This class implements multi threaded merge sort for an array of ints.
 * 
 * @param n = size of array to be sorted
 * @param m = number of treads used
 * 
 * Algorithm:
 * break array into n sub arrays and add to pool.
 * for each thread m, take 2 sub arrays from pool and merge them.
 * add merged array back to pool.
 * Algorithm ends when there is only one array in the pool.
 */

public class ThreadedSort {
	
	private Control control;
	
	public ThreadedSort(int max, int[] a)
	{
		control = new Control(max);
		//break array into n arrays of size 1, and add all to the pool
		for (Integer x: a)
		{
			Integer [] temp = {x};
			control.add(temp);
		}
	}
	
	//work for each thread, up to max threads
	public synchronized Integer[] threadedMerge()
	{	
		//continue working while sort isn't done
		while (true)
		{
			control.waitForThread(); //wait for free thread
			
			if (control.isDone())
				break;
			
			MergeSort thread = new MergeSort(control.getItem(),control.getItem(), control);
			thread.start();
		}
		
		return control.getArray(0);
	}

}
