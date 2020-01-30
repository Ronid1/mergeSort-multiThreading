public class MergeSort extends Thread {
	
	private Control control;
	private Integer[] arr1, arr2;

	public MergeSort(Integer[] a, Integer[] b, Control c)
	{
		control = c;
		arr1 = a;
		arr2 = b;
	}
	
	public void run()
	{
		control.add(merge()); // merge
		control.finishedTask(); // notify control
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
