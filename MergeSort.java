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
public class MergeSort extends Thread {
	
	private Control control;
	private Integer[] arr1, arr2;

	public MergeSort(Integer[] a, Integer[] b, Control c)
	{
		control = c;
		arr1 = a;
		arr2 = b;
	}
	
	//override
	public void run()
	{
		control.add(merge());
		control.finishedTask();
	}
	
	
	//merge two arrays in to one sorted array
	private Integer[] merge()
	{	
		//save merged arrays in res
		Integer [] res = new Integer [arr1.length + arr2.length];
		int size = res.length;
		
		int j = 0, i = 0;
		
		//add items to res by order
		for (int k = 0; k < size; k++)
		{
			
			if( j >= arr2.length || (i < arr1.length && arr1[i] < arr2[j]))
			{
				res[k] = arr1[i];
				i++;
			}
	
			
			else
			{
				res[k] = arr2[j];
				j++;
			}
		}
		
		return res;
	}
	

	

}
