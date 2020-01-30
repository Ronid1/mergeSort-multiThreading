
import java.util.Random;

public class Tester{
	
	public static void main(String[] args) {
		runTest();
	}
	
	
	public static void runTest()
	{
		//sort up to 100 ints with up to arry.length() threads
		 Random random = new Random ();
		 int size = random.nextInt(100);
		 int threadMax = random.nextInt(100);
		 
		 while(threadMax == 0) //make sure number of threads > 0
			 threadMax = random.nextInt(size);

		 int[] arr = new int [size];
		 
		 System.out.println("Array before merge sort:");
		 
		 //add random values 1-100 to array
		 for (int i = 0; i < size; i++)
		 {
			 arr[i] = random.nextInt(100);
			 System.out.print(arr[i] + " ");
		 }
		 
		 System.out.println("\n\nusing " +threadMax + " threads");
		 
		 ThreadedSort sort = new ThreadedSort(threadMax, arr);
		  
		 Integer[] sortedArr = sort.threadedMerge();
		 
		 System.out.println("\nArray after merge sort:");
		 
		 for (int i = 0; i < sortedArr.length; i++)
			 System.out.print(sortedArr[i] + " ");
	}

}
