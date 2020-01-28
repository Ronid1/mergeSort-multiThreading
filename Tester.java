
import java.util.Random;

public class Tester{
	
	public static void main(String[] args) {
		runTest();
	}
	
	
	public static void runTest()
	{
		//sort up to 100 ints with up to arry.leangth() threads
		 Random random = new Random (100);
		 int size = random.nextInt(100);
		 int threadMax = random.nextInt(size);

		 int[] arr = new int [size];
		 
		 System.out.println("Array before merge sort:");
		 
		 //add random values 1-100 to array
		 for (int i = 0; i < size; i++)
		 {
			 arr[i] = random.nextInt(100);
			 System.out.print(arr[i] + "\t");
		 }
		 
		 ThreadedSort sort = new ThreadedSort(threadMax, arr);
		 sort.threadedMerge();
		 
		 System.out.println("Array after merge sort:");
		 
		 for (int i = 0; i < size; i++)
			 System.out.print(arr[i] + "\t");
	}

}
