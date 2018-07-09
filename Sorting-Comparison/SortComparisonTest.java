/*
 * 	Testing:
 *		|-------------------|---------------------------------------------------------------------------|
 * 		|					|insertion		quick		insertion		shell		selection		bubble	|
 * 		|10 random			|   0.0			 0.0		 0.0		 0.0		   0.0			  0.0	|
 * 		|100 random			|   0.0			 0.0		 0.0		 0.0		   0.0			 0.001	|
 * 		|1000 random		|  0.007		0.001		0.004		0.002		  0.005			 0.006	|
 * 		|1000 few unique	|  0.001		 0.0		0.001		 0.0		  0.001			 0.002	|
 * 		|1000 nearly ordered|   0.0			 0.0		 0.0		 0.0		  0.001			 0.001	|	
 * 		|1000 reverse order	|   0.0			0.001		0.001		 0.0		  0.001			  0.0	|
 * 		|1000 sorted		|   0.0			0.001		 0.0		 0.0		  0.001			 0.001	|
 * 		|-------------------|---------------------------------------------------------------------------|
 * 
 * 	Questions:
 *  Q1:		Which of the sorting algorithms does the order of input have an impact on? Why? 
 *  A1:		Insertionsort as the closer the input is to being sorted, the less each element
 *  		will have to be moved backwards in order for it to be in the correct position.
 *  		
 *  		Quicksort as a sorted or reverse sorted input will cause the pivot position to
 *  		always be in a position that causes one of the partitions to be of size 1. This
 *  		results in the slowest performance for quicksort.
 *  
 *  		Shellsort as it uses insertionsort which is affected by the order of the input as
 *  		described above.
 *  
 *  		Bubblesort as the closer the input is to being sorted (or reverse sorted), the fewer
 *  		passes have to be made in order for the input to become sorted.
 *  
 *  Q2:		Which algorithm has the biggest difference between the best and worst performance, 
 *  		based on the type of input, for the input of size 1000? Why?
 *  A2:		Bubblesort as if the input is not close to being sorted or reverse sorted, then it
 *  		has to perform many passes over the input with a worst case performance O(n^2).
 *  		However, for the best case (where the input is sorted or reverse sorted) only one pass
 *  		is necessary giving performance O(n)
 *  
 *  Q3:		Which algorithm has the best/worst scalability, ie, the difference in performance 
 *  		time based on the input size? Please consider only input files with random order 
 *  		for this answer. 
 *  A3:		Quicksort has the best scalability as it has good average performance (O(n)) and its
 *  		worst case(sorted or reverse sorted) becomes less likely for random inputs of larger size.
 *  
 *  		Bubblesort has the worst scalability as for larger inputs it must pass over the entire
 *  		input many times. This is not the case with divide-and-conquer approaches to sorting as
 *  		they decrease the size of the part of the input that needs to be iterated over with
 *  		each iteration.
 *  
 *  Q4:		Which algorithm is the fastest for each of the 7 input files? 
 *  A4:		Quicksort was the fastest for all input files aside from the sorted and reverse sorted
 *  		inputs. For these inputs, the fastest was algorithm was shellsort. This is because sorted
 *  		and reverse sorted inputs produce the worst case performance in quicksort as mentioned above.
 */

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Assert;

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author Robert Jennings
 *  @version HT 2018
 */
@RunWith(JUnit4.class)
public class SortComparisonTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new SortComparison();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
    	assertEquals("insertionSort returns null with an empty array", null, SortComparison.insertionSort(null));
    	assertEquals("quickSort returns null with an empty array", null, SortComparison.quickSort(null));
    	assertEquals("mergesort returns null with an empty array", null, SortComparison.mergeSort(null));
    	assertEquals("shellSort returns null with an empty array", null, SortComparison.shellSort(null));
    	assertEquals("selectionSort returns null with an empty array", null, SortComparison.selectionSort(null));
    	assertEquals("bubbleSort returns null with an empty array", null, SortComparison.bubbleSort(null));
    }
    
    /**
     * Check that the methods work for larger arrays
     */
    @Test
    public void testInsertionSort()
    {
		double[] numbers10 = {2377.88, 2910.66, 8458.14, 1522.08, 5855.37, 1934.75, 8106.23,
				1735.31, 4849.83, 1518.63};
		double[] numbers10Sorted = SortComparison.insertionSort(numbers10);
		for(int i = 0; i < numbers10Sorted.length - 1; i++)
		{
			Assert.assertTrue(numbers10Sorted[i] <= numbers10Sorted[i+1]);
		}

		double[] numbers10InOrder = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		double[] numbers10InOrderSorted = SortComparison.insertionSort(numbers10InOrder);
		for(int i = 0; i < numbers10InOrderSorted.length - 1; i++)
		{
			Assert.assertTrue(numbers10InOrderSorted[i] <= numbers10InOrderSorted[i+1]);
		}

		double[] numbers10Reverse = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
		double[] numbers10ReverseSorted = SortComparison.insertionSort(numbers10Reverse);
		for(int i = 0; i < numbers10ReverseSorted.length - 1; i++)
		{
			Assert.assertTrue(numbers10ReverseSorted[i] <= numbers10ReverseSorted[i+1]);
		}

		double[] numbers100 = {2377.88, 2910.66, 8458.14, 1522.08, 5855.37, 1934.75, 8106.23,
				1735.31, 4849.83, 1518.63, 3669.57, 4917.36, 9100.94, 2652.57, 8931.88, 2203.51,
				6317.98, 5710.77, 3321.58, 1044.55, 5029.31, 5673.94, 8541.65, 401.409, 1080.22,
				6393.96, 131.11, 7201.84, 1018.14, 4829.45, 2543.55, 6766.97, 8967.82, 7598.96,
				7202.92, 9076.23, 9286.11, 3776.63, 8997.56, 7788.8, 3242.55, 1942.31, 9955.53,
				1612.96, 7080.34, 5015.19, 9363.01, 7163.23, 1051.9, 2092.05, 5593.97, 7054.32,
				782.337, 5105.3, 1961.97, 2742.11, 6386.02, 4482.08, 398.718, 4672.51, 777.565,
				381.004, 1600.44, 3978.32, 6888.98, 5347.12, 6869.2, 2724.57, 4659.33, 2765.55,
				4500.86, 9350.69, 869.318, 4538.89, 9456.25, 8557.19, 1209.76, 141.639, 2345.15,
				8663.13, 4373.9, 7695.48, 8740.03, 3769.34, 9443.28, 7984.93, 8386.34, 9466.56,
				5860.52, 5991.63, 3193.47, 4211.62, 9041.07, 1584.62, 8351.59, 7319.42, 4918.37,
				2121.29, 1197.52, 483.631};
		double[] numbers100Sorted = SortComparison.insertionSort(numbers100);
		for(int i = 0; i < numbers100Sorted.length - 1; i++)
		{
			Assert.assertTrue(numbers100Sorted[i] <= numbers100Sorted[i+1]);
		}
    }
    
    @Test
    public void testQuickSort()
    {
		double[] numbers10 = {2377.88, 2910.66, 8458.14, 1522.08, 5855.37, 1934.75, 8106.23,
				1735.31, 4849.83, 1518.63};
		double[] numbers10Sorted = SortComparison.quickSort(numbers10);
		for(int i = 0; i < numbers10Sorted.length - 1; i++)
		{
			Assert.assertTrue(numbers10Sorted[i] <= numbers10Sorted[i+1]);
		}

		double[] numbers10InOrder = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		double[] numbers10InOrderSorted = SortComparison.quickSort(numbers10InOrder);
		for(int i = 0; i < numbers10InOrderSorted.length - 1; i++)
		{
			Assert.assertTrue(numbers10InOrderSorted[i] <= numbers10InOrderSorted[i+1]);
		}

		double[] numbers10Reverse = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
		double[] numbers10ReverseSorted = SortComparison.quickSort(numbers10Reverse);
		for(int i = 0; i < numbers10ReverseSorted.length - 1; i++)
		{
			Assert.assertTrue(numbers10ReverseSorted[i] <= numbers10ReverseSorted[i+1]);
		}

		double[] numbers100 = {2377.88, 2910.66, 8458.14, 1522.08, 5855.37, 1934.75, 8106.23,
				1735.31, 4849.83, 1518.63, 3669.57, 4917.36, 9100.94, 2652.57, 8931.88, 2203.51,
				6317.98, 5710.77, 3321.58, 1044.55, 5029.31, 5673.94, 8541.65, 401.409, 1080.22,
				6393.96, 131.11, 7201.84, 1018.14, 4829.45, 2543.55, 6766.97, 8967.82, 7598.96,
				7202.92, 9076.23, 9286.11, 3776.63, 8997.56, 7788.8, 3242.55, 1942.31, 9955.53,
				1612.96, 7080.34, 5015.19, 9363.01, 7163.23, 1051.9, 2092.05, 5593.97, 7054.32,
				782.337, 5105.3, 1961.97, 2742.11, 6386.02, 4482.08, 398.718, 4672.51, 777.565,
				381.004, 1600.44, 3978.32, 6888.98, 5347.12, 6869.2, 2724.57, 4659.33, 2765.55,
				4500.86, 9350.69, 869.318, 4538.89, 9456.25, 8557.19, 1209.76, 141.639, 2345.15,
				8663.13, 4373.9, 7695.48, 8740.03, 3769.34, 9443.28, 7984.93, 8386.34, 9466.56,
				5860.52, 5991.63, 3193.47, 4211.62, 9041.07, 1584.62, 8351.59, 7319.42, 4918.37,
				2121.29, 1197.52, 483.631};
		double[] numbers100Sorted = SortComparison.quickSort(numbers100);
		for(int i = 0; i < numbers100Sorted.length - 1; i++)
		{
			Assert.assertTrue(numbers100Sorted[i] <= numbers100Sorted[i+1]);
		}
    }
    
    @Test
    public void testMergeSort()
    {
		double[] numbers10 = {2377.88, 2910.66, 8458.14, 1522.08, 5855.37, 1934.75, 8106.23,
				1735.31, 4849.83, 1518.63};
		double[] numbers10Sorted = SortComparison.mergeSort(numbers10);
		for(int i = 0; i < numbers10Sorted.length - 1; i++)
		{
			Assert.assertTrue(numbers10Sorted[i] <= numbers10Sorted[i+1]);
		}

		double[] numbers10InOrder = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		double[] numbers10InOrderSorted = SortComparison.mergeSort(numbers10InOrder);
		for(int i = 0; i < numbers10InOrderSorted.length - 1; i++)
		{
			Assert.assertTrue(numbers10InOrderSorted[i] <= numbers10InOrderSorted[i+1]);
		}

		double[] numbers10Reverse = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
		double[] numbers10ReverseSorted = SortComparison.mergeSort(numbers10Reverse);
		for(int i = 0; i < numbers10ReverseSorted.length - 1; i++)
		{
			Assert.assertTrue(numbers10ReverseSorted[i] <= numbers10ReverseSorted[i+1]);
		}

		double[] numbers100 = {2377.88, 2910.66, 8458.14, 1522.08, 5855.37, 1934.75, 8106.23,
				1735.31, 4849.83, 1518.63, 3669.57, 4917.36, 9100.94, 2652.57, 8931.88, 2203.51,
				6317.98, 5710.77, 3321.58, 1044.55, 5029.31, 5673.94, 8541.65, 401.409, 1080.22,
				6393.96, 131.11, 7201.84, 1018.14, 4829.45, 2543.55, 6766.97, 8967.82, 7598.96,
				7202.92, 9076.23, 9286.11, 3776.63, 8997.56, 7788.8, 3242.55, 1942.31, 9955.53,
				1612.96, 7080.34, 5015.19, 9363.01, 7163.23, 1051.9, 2092.05, 5593.97, 7054.32,
				782.337, 5105.3, 1961.97, 2742.11, 6386.02, 4482.08, 398.718, 4672.51, 777.565,
				381.004, 1600.44, 3978.32, 6888.98, 5347.12, 6869.2, 2724.57, 4659.33, 2765.55,
				4500.86, 9350.69, 869.318, 4538.89, 9456.25, 8557.19, 1209.76, 141.639, 2345.15,
				8663.13, 4373.9, 7695.48, 8740.03, 3769.34, 9443.28, 7984.93, 8386.34, 9466.56,
				5860.52, 5991.63, 3193.47, 4211.62, 9041.07, 1584.62, 8351.59, 7319.42, 4918.37,
				2121.29, 1197.52, 483.631};
		double[] numbers100Sorted = SortComparison.mergeSort(numbers100);
		for(int i = 0; i < numbers100Sorted.length - 1; i++)
		{
			Assert.assertTrue(numbers100Sorted[i] <= numbers100Sorted[i+1]);
		}
    }
    
    @Test
    public void testShellSort()
    {
		double[] numbers10 = {2377.88, 2910.66, 8458.14, 1522.08, 5855.37, 1934.75, 8106.23,
				1735.31, 4849.83, 1518.63};
		double[] numbers10Sorted = SortComparison.shellSort(numbers10);
		for(int i = 0; i < numbers10Sorted.length - 1; i++)
		{
			Assert.assertTrue(numbers10Sorted[i] <= numbers10Sorted[i+1]);
		}

		double[] numbers10InOrder = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		double[] numbers10InOrderSorted = SortComparison.shellSort(numbers10InOrder);
		for(int i = 0; i < numbers10InOrderSorted.length - 1; i++)
		{
			Assert.assertTrue(numbers10InOrderSorted[i] <= numbers10InOrderSorted[i+1]);
		}

		double[] numbers10Reverse = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
		double[] numbers10ReverseSorted = SortComparison.shellSort(numbers10Reverse);
		for(int i = 0; i < numbers10ReverseSorted.length - 1; i++)
		{
			Assert.assertTrue(numbers10ReverseSorted[i] <= numbers10ReverseSorted[i+1]);
		}

		double[] numbers100 = {2377.88, 2910.66, 8458.14, 1522.08, 5855.37, 1934.75, 8106.23,
				1735.31, 4849.83, 1518.63, 3669.57, 4917.36, 9100.94, 2652.57, 8931.88, 2203.51,
				6317.98, 5710.77, 3321.58, 1044.55, 5029.31, 5673.94, 8541.65, 401.409, 1080.22,
				6393.96, 131.11, 7201.84, 1018.14, 4829.45, 2543.55, 6766.97, 8967.82, 7598.96,
				7202.92, 9076.23, 9286.11, 3776.63, 8997.56, 7788.8, 3242.55, 1942.31, 9955.53,
				1612.96, 7080.34, 5015.19, 9363.01, 7163.23, 1051.9, 2092.05, 5593.97, 7054.32,
				782.337, 5105.3, 1961.97, 2742.11, 6386.02, 4482.08, 398.718, 4672.51, 777.565,
				381.004, 1600.44, 3978.32, 6888.98, 5347.12, 6869.2, 2724.57, 4659.33, 2765.55,
				4500.86, 9350.69, 869.318, 4538.89, 9456.25, 8557.19, 1209.76, 141.639, 2345.15,
				8663.13, 4373.9, 7695.48, 8740.03, 3769.34, 9443.28, 7984.93, 8386.34, 9466.56,
				5860.52, 5991.63, 3193.47, 4211.62, 9041.07, 1584.62, 8351.59, 7319.42, 4918.37,
				2121.29, 1197.52, 483.631};
		double[] numbers100Sorted = SortComparison.shellSort(numbers100);
		for(int i = 0; i < numbers100Sorted.length - 1; i++)
		{
			Assert.assertTrue(numbers100Sorted[i] <= numbers100Sorted[i+1]);
		}
    }
    
    @Test
    public void testSelectionSort()
    {
		double[] numbers10 = {2377.88, 2910.66, 8458.14, 1522.08, 5855.37, 1934.75, 8106.23,
				1735.31, 4849.83, 1518.63};
		double[] numbers10Sorted = SortComparison.selectionSort(numbers10);
		for(int i = 0; i < numbers10Sorted.length - 1; i++)
		{
			Assert.assertTrue(numbers10Sorted[i] <= numbers10Sorted[i+1]);
		}

		double[] numbers10InOrder = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		double[] numbers10InOrderSorted = SortComparison.selectionSort(numbers10InOrder);
		for(int i = 0; i < numbers10InOrderSorted.length - 1; i++)
		{
			Assert.assertTrue(numbers10InOrderSorted[i] <= numbers10InOrderSorted[i+1]);
		}

		double[] numbers10Reverse = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
		double[] numbers10ReverseSorted = SortComparison.selectionSort(numbers10Reverse);
		for(int i = 0; i < numbers10ReverseSorted.length - 1; i++)
		{
			Assert.assertTrue(numbers10ReverseSorted[i] <= numbers10ReverseSorted[i+1]);
		}

		double[] numbers100 = {2377.88, 2910.66, 8458.14, 1522.08, 5855.37, 1934.75, 8106.23,
				1735.31, 4849.83, 1518.63, 3669.57, 4917.36, 9100.94, 2652.57, 8931.88, 2203.51,
				6317.98, 5710.77, 3321.58, 1044.55, 5029.31, 5673.94, 8541.65, 401.409, 1080.22,
				6393.96, 131.11, 7201.84, 1018.14, 4829.45, 2543.55, 6766.97, 8967.82, 7598.96,
				7202.92, 9076.23, 9286.11, 3776.63, 8997.56, 7788.8, 3242.55, 1942.31, 9955.53,
				1612.96, 7080.34, 5015.19, 9363.01, 7163.23, 1051.9, 2092.05, 5593.97, 7054.32,
				782.337, 5105.3, 1961.97, 2742.11, 6386.02, 4482.08, 398.718, 4672.51, 777.565,
				381.004, 1600.44, 3978.32, 6888.98, 5347.12, 6869.2, 2724.57, 4659.33, 2765.55,
				4500.86, 9350.69, 869.318, 4538.89, 9456.25, 8557.19, 1209.76, 141.639, 2345.15,
				8663.13, 4373.9, 7695.48, 8740.03, 3769.34, 9443.28, 7984.93, 8386.34, 9466.56,
				5860.52, 5991.63, 3193.47, 4211.62, 9041.07, 1584.62, 8351.59, 7319.42, 4918.37,
				2121.29, 1197.52, 483.631};
		double[] numbers100Sorted = SortComparison.selectionSort(numbers100);
		for(int i = 0; i < numbers100Sorted.length - 1; i++)
		{
			Assert.assertTrue(numbers100Sorted[i] <= numbers100Sorted[i+1]);
		}
    }
    
    @Test
    public void testBubbleSort()
    {
		double[] numbers10 = {2377.88, 2910.66, 8458.14, 1522.08, 5855.37, 1934.75, 8106.23,
				1735.31, 4849.83, 1518.63};
		double[] numbers10Sorted = SortComparison.bubbleSort(numbers10);
		for(int i = 0; i < numbers10Sorted.length - 1; i++)
		{
			Assert.assertTrue(numbers10Sorted[i] <= numbers10Sorted[i+1]);
		}

		double[] numbers10InOrder = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		double[] numbers10InOrderSorted = SortComparison.bubbleSort(numbers10InOrder);
		for(int i = 0; i < numbers10InOrderSorted.length - 1; i++)
		{
			Assert.assertTrue(numbers10InOrderSorted[i] <= numbers10InOrderSorted[i+1]);
		}

		double[] numbers10Reverse = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
		double[] numbers10ReverseSorted = SortComparison.bubbleSort(numbers10Reverse);
		for(int i = 0; i < numbers10ReverseSorted.length - 1; i++)
		{
			Assert.assertTrue(numbers10ReverseSorted[i] <= numbers10ReverseSorted[i+1]);
		}

		double[] numbers100 = {2377.88, 2910.66, 8458.14, 1522.08, 5855.37, 1934.75, 8106.23,
				1735.31, 4849.83, 1518.63, 3669.57, 4917.36, 9100.94, 2652.57, 8931.88, 2203.51,
				6317.98, 5710.77, 3321.58, 1044.55, 5029.31, 5673.94, 8541.65, 401.409, 1080.22,
				6393.96, 131.11, 7201.84, 1018.14, 4829.45, 2543.55, 6766.97, 8967.82, 7598.96,
				7202.92, 9076.23, 9286.11, 3776.63, 8997.56, 7788.8, 3242.55, 1942.31, 9955.53,
				1612.96, 7080.34, 5015.19, 9363.01, 7163.23, 1051.9, 2092.05, 5593.97, 7054.32,
				782.337, 5105.3, 1961.97, 2742.11, 6386.02, 4482.08, 398.718, 4672.51, 777.565,
				381.004, 1600.44, 3978.32, 6888.98, 5347.12, 6869.2, 2724.57, 4659.33, 2765.55,
				4500.86, 9350.69, 869.318, 4538.89, 9456.25, 8557.19, 1209.76, 141.639, 2345.15,
				8663.13, 4373.9, 7695.48, 8740.03, 3769.34, 9443.28, 7984.93, 8386.34, 9466.56,
				5860.52, 5991.63, 3193.47, 4211.62, 9041.07, 1584.62, 8351.59, 7319.42, 4918.37,
				2121.29, 1197.52, 483.631};
		double[] numbers100Sorted = SortComparison.bubbleSort(numbers100);
		for(int i = 0; i < numbers100Sorted.length - 1; i++)
		{
			Assert.assertTrue(numbers100Sorted[i] <= numbers100Sorted[i+1]);
		}
    }

    // ----------------------------------------------------------
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     *
     */
//    public static void main(String[] args)
//    {
//    	String[] numberSets = {"numbers10", "numbers100", "numbers1000", 
//    			"numbers1000Duplicates", "numbersNearlyOrdered1000", 
//    			"numbersReverse1000", "numbersSorted1000",
//    			//"numbers10000", "numbers100000"
//    			};
//    	for(int i = 0; i < numberSets.length; i++)
//    	{
//	    	System.out.println(numberSets[i] + ":");
//	    	System.out.print("Insertion Sort: ");
//	    	double total = 0;
//	    	for(int j = 0; j < 3; j++)
//	    	{
//	        	In numbers = new In(numberSets[i] + ".txt");
//	        	double[] a = numbers.readAllDoubles();
//	    		Stopwatch stopwatch = new Stopwatch();
//	    		SortComparison.insertionSort(a);
//	    		double time = stopwatch.elapsedTime();
//	       	 	System.out.print(time + "  ");
//	       	 	total += time;
//	    	}
//	    	System.out.println("avg=" + total/3);
//
//	    	System.out.print("Quick Sort: ");
//	    	total = 0;
//	    	for(int j = 0; j < 3; j++)
//	    	{
//	        	In numbers = new In(numberSets[i] + ".txt");
//	        	double[] a = numbers.readAllDoubles();
//	    		Stopwatch stopwatch = new Stopwatch();
//	    		SortComparison.quickSort(a);
//	    		double time = stopwatch.elapsedTime();
//	       	 	System.out.print(time + "  ");
//	       	 	total += time;
//	    	}
//	    	System.out.println("avg=" + total/3);
//	    	
//	    	System.out.print("insertion Sort: ");
//	    	total = 0;
//	    	for(int j = 0; j < 3; j++)
//	    	{
//	        	In numbers = new In(numberSets[i] + ".txt");
//	        	double[] a = numbers.readAllDoubles();
//	    		Stopwatch stopwatch = new Stopwatch();
//	    		SortComparison.insertionSort(a);
//	    		double time = stopwatch.elapsedTime();
//	       	 	System.out.print(time + "  ");
//	       	 	total += time;
//	    	}
//	    	System.out.println("avg=" + total/3);
//	    	
//	    	System.out.print("Shell Sort: ");
//	    	total = 0;
//	    	for(int j = 0; j < 3; j++)
//	    	{
//	        	In numbers = new In(numberSets[i] + ".txt");
//	        	double[] a = numbers.readAllDoubles();
//	    		Stopwatch stopwatch = new Stopwatch();
//	    		SortComparison.shellSort(a);
//	    		double time = stopwatch.elapsedTime();
//	       	 	System.out.print(time + "  ");
//	       	 	total += time;
//	    	}
//	    	System.out.println("avg=" + total/3);
//	    	
//	    	System.out.print("Selection Sort: ");
//	    	total = 0;
//	    	for(int j = 0; j < 3; j++)
//	    	{
//	        	In numbers = new In(numberSets[i] + ".txt");
//	        	double[] a = numbers.readAllDoubles();
//	    		Stopwatch stopwatch = new Stopwatch();
//	    		SortComparison.selectionSort(a);
//	    		double time = stopwatch.elapsedTime();
//	       	 	System.out.print(time + "  ");
//	       	 	total += time;
//	    	}
//	    	System.out.println("avg=" + total/3);
//	    	
//	    	System.out.print("Bubble Sort: ");
//	    	total = 0;
//	    	for(int j = 0; j < 3; j++)
//	    	{
//	        	In numbers = new In(numberSets[i] + ".txt");
//	        	double[] a = numbers.readAllDoubles();
//	    		Stopwatch stopwatch = new Stopwatch();
//	    		SortComparison.bubbleSort(a);
//	    		double time = stopwatch.elapsedTime();
//	       	 	System.out.print(time + "  ");
//	       	 	total += time;
//	    	}
//	    	System.out.println("avg=" + total/3);
//	    	
//	    	System.out.print("System Sort: ");
//	    	total = 0;
//	    	for(int j = 0; j < 3; j++)
//	    	{
//	        	In numbers = new In(numberSets[i] + ".txt");
//	        	double[] a = numbers.readAllDoubles();
//	    		Stopwatch stopwatch = new Stopwatch();
//	    		Arrays.sort(a);
//	    		double time = stopwatch.elapsedTime();
//	       	 	System.out.print(time + "  ");
//	       	 	total += time;
//	    	}
//	    	System.out.println("avg=" + total/3);
//	    	
//	    	System.out.println("");
//    	}
//    }

}
