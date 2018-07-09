// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author Robert Jennings
 *  @version HT 2018
 */

 class SortComparison {
	 
	 final static int CUTOFF = 10;
	 
	 static long insertionSortSwaps = 0;
	 static long insertionSortCompares = 0;
	 static long mergeSortSwaps = 0;
	 static long mergeSortCompares = 0;
	 static long quickSortSwaps = 0;
	 static long quickSortCompares = 0;
	 static long shellSortSwaps = 0;
	 static long shellSortCompares = 0;
	 static long selectionSortSwaps = 0;
	 static long selectionSortCompares = 0;
	 static long bubbleSortSwaps = 0;
	 static long bubbleSortCompares = 0;

    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     *
     */
    static double[] insertionSort (double a[]){
    	insertionSortSwaps = 0;
    	insertionSortCompares = 0;
    	if(a == null)
    	{
    		return null;
    	}
    	int n = a.length;
    	insertionSortCompares++;
    	for(int i = 1; i < n; i++)
    	{
			insertionSortCompares++;
    		for(int j = i; j > 0 && a[j] < a[j-1]; j--)
    		{
    			double temp = a[j];
    			a[j] = a[j-1];
    			a[j-1] = temp;
    			insertionSortSwaps++;
    			insertionSortCompares++;
    		}
    		insertionSortCompares++;
    	}
    	return a;
    }//end insertionsort

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double[] quickSort (double a[]){
    	quickSortSwaps = 0;
    	quickSortCompares = 0;
    	if(a == null)
    	{
    		return null;
    	}
    	quickSort(a, 0, a.length-1);
    	return a;
    }//end quicksort
    
    static private void quickSort(double a[], int lo, int hi){
    	quickSortCompares++;
    	if(hi <= lo + CUTOFF - 1)
    	{
			quickSortCompares++;
    		for(int i = lo; i <= hi; i++)
        	{
    			quickSortCompares++;
        		for(int j = i; j > 0 && a[j] < a[j-1]; j--)
        		{
        			double temp = a[j];
        			a[j] = a[j-1];
        			a[j-1] = temp;
        			quickSortSwaps++;
        			quickSortCompares++;
        		}
        		quickSortCompares++;
        	}
    		return;
    	}
    	int pivotPos = partition(a, lo, hi);
    	quickSort(a, lo, pivotPos-1);
    	quickSort(a, pivotPos+1, hi);
    }//end quicksort
    
    static private int partition(double[] a, int lo, int hi){
    	double pivot = a[hi]; 
        int i = (lo-1);
        quickSortCompares++;
        for(int j = lo; j < hi; j++)
        {
        	quickSortCompares++;
            if (a[j] <= pivot)
            {
                i++;
                double temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                quickSortSwaps++;
            }
            quickSortCompares++;
        }
        double temp = a[i+1];
        a[i+1] = a[hi];
        a[hi] = temp;
        quickSortSwaps++;
        return i+1;
    }//end partition

    /**
     * Sorts an array of doubles using Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double[] mergeSort (double a[]){
    	mergeSortSwaps = 0;
    	mergeSortCompares = 0;
    	if(a == null)
    	{
    		return null;
    	}
    	double[] aux = new double[a.length];
    	mergeSort(a, aux, 0, a.length - 1);
    	return a;
    }//end mergesort
    
    static private void mergeSort(double[] a, double[] aux, int lo, int hi){
    	mergeSortCompares++;
//    	if(hi <= lo + CUTOFF - 1)
//    	{
//    		mergeSortCompares++;
//    		for(int i = lo; i <= hi; i++)
//        	{
//    			mergeSortCompares++;
//        		for(int j = i; j > 0 && a[j] < a[j-1]; j--)
//        		{
//        			double temp = a[j];
//        			a[j] = a[j-1];
//        			a[j-1] = temp;
//        			mergeSortSwaps++;
//        			mergeSortCompares++;
//        		}
//        		mergeSortCompares++;
//        	}
//    		return;
//    	}
		if(hi <= lo)
		{
			return;
		}
    	int mid = lo + (hi-lo)/2;
    	mergeSort(a, aux, lo, mid);
    	mergeSort(a, aux, mid+1, hi);
		mergeSortCompares++;
    	if((a[mid] <= a[mid+1]))
    	{
    		return;
    	}
    	merge(a, aux, lo, mid, hi);
    }//end sort
    
    static private void merge(double[] a, double[] aux, int lo, int mid, int hi){
    	mergeSortCompares++;
    	for(int k = lo; k <= hi; k++)
    	{
    		aux[k] = a[k];
    		mergeSortSwaps++;
    		mergeSortCompares++;
    	}
    	int i = lo;
    	int j = mid+1;
    	mergeSortCompares++;
    	for(int k = lo; k <= hi; k++)
    	{
    		if(i > mid)
    		{
        		mergeSortCompares++;
    			a[k] = aux[j++];
    			mergeSortSwaps++;
    		}
    		else if(j > hi)
    		{
        		mergeSortCompares+=2;
    			a[k] = aux[i++];
    			mergeSortSwaps++;
    		}
    		else if(aux[j] < aux[i])
    		{
        		mergeSortCompares+=3;
    			a[k] = aux[j++];
    			mergeSortSwaps++;
    		}
    		else
    		{
        		mergeSortCompares+=3;
    			a[k] = aux[i++];
    			mergeSortSwaps++;
    		}
    		mergeSortCompares++;
    	}
    }//end merge

    /**
     * Sorts an array of doubles using Shell Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double[] shellSort (double a[]){
    	shellSortSwaps = 0;
    	shellSortCompares = 0;
    	if(a == null)
    	{
    		return null;
    	}
    	int n = a.length;
    	int h = 1;
    	while(h < n/3)
    	{
    		h = 3*h+1;
    	}
    	shellSortCompares++;
    	while(h >= 1)
    	{
    		shellSortCompares++;
    		for(int i = h; i < n; i++)
    		{
    			shellSortCompares++;
    			for(int j = i; j >= h && a[j] < a[j-h]; j -= h)
    			{
    				shellSortSwaps++;
    				shellSortCompares++;
    				double temp = a[j];
    				a[j] = a[j-h];
    				a[j-h] = temp;
    			}
    			shellSortCompares++;
    		}
    		h = h/3;
    		shellSortCompares++;
    	}
    	return a;
    }//end shellsort

    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double[] selectionSort (double a[]){
    	selectionSortSwaps = 0;
    	selectionSortCompares = 0;
    	if(a == null)
    	{
    		return null;
    	}
    	int n = a.length;
    	selectionSortCompares++;
    	for (int i = 0; i < n-1; i++)
    	{
    		int min_idx = i;
    		selectionSortCompares++;
    		for (int j = i+1; j < n; j++)
    		{
    			selectionSortCompares++;
    			if (a[j] < a[min_idx])
    			{
    				min_idx = j;
    			}
    			selectionSortCompares++;
    		}
    		double temp = a[min_idx];
    		a[min_idx] = a[i];
    		a[i] = temp;
    		selectionSortCompares++;
    		selectionSortSwaps++;
    	}
    	return a;
    }//end selectionsort

    /**
     * Sorts an array of doubles using Bubble Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double[] bubbleSort (double a[]){
    	bubbleSortSwaps = 0;
    	bubbleSortCompares = 0;
    	if(a == null)
    	{
    		return null;
    	}
    	int n = a.length;
    	double temp = 0;
    	bubbleSortCompares++;
    	for(int i = 0; i < n; i++)
    	{
    		bubbleSortCompares++;
    		for(int j = 1; j < (n-i); j++)
    		{
    			bubbleSortCompares++;
    			if(a[j-1] > a[j])
    			{
    				temp = a[j-1];
    				a[j-1] = a[j];
    				a[j] = temp;
    				bubbleSortSwaps++;
    			}
    			bubbleSortCompares++;
    		}
    		bubbleSortCompares++;
    	}
    	return a;
    }//end bubblesort
    
//    public static void main(String args[]){
//    	String[] numberSets = {"numbers10", "numbers100", "numbers1000", "numbers10000", 
//    			"numbers10000", "numbers1000Duplicates", "numbersNearlyOrdered1000", 
//    			"numbersReverse1000", "numbersSorted1000", "numbers100000"};
//    	In numbers;
//    	double[] a;
//    	for(int i = 0; i < numberSets.length; i++)
//    	{
//	    	System.out.println(numberSets[i] + ":");
//	    	
//	    	numbers = new In(numberSets[i] + ".txt");
//        	a = numbers.readAllDoubles();
//    		SortComparison.insertionSort(a);
//    		System.out.println("Insertion Sort - Swaps: " + insertionSortSwaps + " Compares: " + insertionSortCompares);
//    		
//    		numbers = new In(numberSets[i] + ".txt");
//        	a = numbers.readAllDoubles();
//    		SortComparison.quickSort(a);
//    		System.out.println("Quick Sort - Swaps: " + quickSortSwaps + " Compares: " + quickSortCompares);
//    		
//    		numbers = new In(numberSets[i] + ".txt");
//        	a = numbers.readAllDoubles();
//    		SortComparison.mergeSort(a);
//    		System.out.println("Merge Sort - Swaps: " + mergeSortSwaps + " Compares: " + mergeSortCompares);
//
//    		numbers = new In(numberSets[i] + ".txt");
//        	a = numbers.readAllDoubles();
//    		SortComparison.shellSort(a);
//    		System.out.println("Shell Sort - Swaps: " + shellSortSwaps + " Compares: " + shellSortCompares);
//
//    		numbers = new In(numberSets[i] + ".txt");
//        	a = numbers.readAllDoubles();
//    		SortComparison.selectionSort(a);
//    		System.out.println("Selection Sort - Swaps: " + selectionSortSwaps + " Compares: " + selectionSortCompares);
//
//    		numbers = new In(numberSets[i] + ".txt");
//        	a = numbers.readAllDoubles();
//    		SortComparison.bubbleSort(a);
//    		System.out.println("bubble Sort - Swaps: " + bubbleSortSwaps + " Compares: " + bubbleSortCompares);
//    		
//    		System.out.println("");
//    	}
//    }

 }//end class
