// -------------------------------------------------------------------------
/**
 *  This class contains only two static methods that search for points on the
 *  same line in three arrays of integers. 
 *
 *  @author  
 *  @version 03/10/16 17:10:35
 */

class Collinear
{

   // ----------------------------------------------------------
    /**
     * Counts for the number of non-horizontal lines that go through 3 points in arrays a1, a2, a3.
     * This method is static, thus it can be called as Collinear.countCollinear(a1,a2,a3)
     * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
     * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
     * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
     * @return the number of points which are collinear and do not lie on a horizontal line.
     *
     * Array a1, a2 and a3 contain points on the horizontal line y=1, y=2 and y=3, respectively.
     * A non-horizontal line will have to cross all three of these lines. Thus
     * we are looking for 3 points, each in a1, a2, a3 which lie on the same
     * line.
     *
     * Three points (x1, y1), (x2, y2), (x3, y3) are collinear (i.e., they are on the same line) if
     * 
     * x1(y2−y3)+x2(y3−y1)+x3(y1−y2)=0 
     *
     * In our case y1=1, y2=2, y3=3.
     *
     * You should implement this using a BRUTE FORCE approach (check all possible combinations of numbers from a1, a2, a3)
     *
     * ----------------------------------------------------------
     *
     * Experimental Performance:
     * -------------------------
     *  Write the running time of the algorithm when run with the following input sizes
     *  
     *  Input Size N      Running Time (sec)
     *  ------------------------------------
     *  1000              0.511
     *  2000              3.439
     *  4000              24.494
     *
     *  Assuming that the running time of your algorithm is of the form aN^b,
     *  estimate 'b' and 'a' by fitting a line to the experimental points:
     *  
     *  lg(T(N)) = 2.7915(lg(N) - 28.7881) (lg - lg plot)
     *
     *  b = 2.7915
     *  a = 2^-28.7881 = 0.000000009
     *
     *  What running time do you predict using your results for input size 5000?
     *  What is the actual running time you get with such an input?
     *  What is the error in percentage?
     *  
     *  T(N) = 2^(2.7915(lg(5000)) - 28.7881) = 45.666
     *
     *  Error = ( (Actual time) - (Predicted time) ) * 100 / (Predicted time)
     *
     *  Input Size N      Predicted Running Time (sec)        Actual Running Time (sec)       Error (%)
     *  ------------------------------------------------------------------------------------------------
     *  5000              45.665					          47.447                          3.900
     * 
     *  Order of Growth
     *  -------------------------
     *
     *  Calculate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
     *
     *  Order of growth: O( N^3 )
     *
     *  Explanation: There are 3 for loops, each of which iterates through N times, hence the code is executed
     *  N^3 times.
     */

	final static int Y1 = 1;
	final static int Y2 = 2;
	final static int Y3 = 3;

    static int countCollinear(int[] a1, int[] a2, int[] a3)
    {
    	int count = 0;
    	for( int i=0; i < a1.length; i++ )
    	{
    		for( int j=0; j < a2.length; j++ )
    		{
    			for( int k=0; k < a3.length; k++ )
    			{
    				if( a1[i]*(Y2-Y3) + a2[j]*(Y3-Y1) + a3[k]*(Y1-Y2) == 0 )
    				{
    					count++;
    				}
    			}
    		}
    	}
    	return count;
    }

    // ----------------------------------------------------------
    /**
     * Counts for the number of non-horizontal lines that go through 3 points in arrays a1, a2, a3.
     * This method is static, thus it can be called as Collinear.countCollinearFast(a1,a2,a3)
     * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
     * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
     * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
     * @return the number of points which are collinear and do not lie on a horizontal line.
     *
     * In this implementation you should make non-trivial use of InsertionSort and Binary Search.
     * The performance of this method should be much better than that of the above method.
     *
     * Experimental Performance:
     * -------------------------
     *  Measure the running time of the algorithm when run with the following input sizes
     *  
     *  Input Size N      Running Time (sec)
     *  ------------------------------------
     *  1000              0.069
     *  2000              0.239
     *  4000              0.975
     *  5000              1.693
     *
     *
     *  Compare Implementations:
     *  ------------------------
     *  Show the speedup achieved by this method, using the times you got from your experiments.
     *
     *  Input Size N      Speedup = (time of countCollinear)/(time of countCollinearFast)
     *  ---------------------------------------------------------------------------------
     *  1000              7.406
     *  2000              14.289
     *  4000              25.122
     *  5000              28.025
     *
     *
     *  Order of Growth
     *  -------------------------
     *
     *  Calculate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
     *
     *  Order of Growth: O( N^2 * lg(N) )
     *
     *  Explanation: The Insertion sort algorithm is performed once and has an order of growth of N^2.
     *  Then there are 2 for loops which iterate N times each. This means the code within iterates N^2 times.
     *  The innermost loop performs binary search which has an order of growth of lg(N) + 1.
     *  
     *  N^2 + N^2(lg(N) + 1) = N^2 + N^2*lg(N) + N^2 = 2*N^2 + N^2lg(N).
     *  
     *  Since the 2*N^2 grows more slowly than N^2 * lg(N), it can be dismissed in the Big Oh notation
     *  as it is insignificant for large input sizes.
     */
    
    static int countCollinearFast(int[] a1, int[] a2, int[] a3)
    {
    	sort(a3);
    	int count = 0;
    	for( int i=0; i < a1.length; i++ )
    	{
    		for( int j=0; j < a2.length; j++ )
    		{
    			int x = a1[i]*(Y2-Y3) + a2[j]*(Y3-Y1);
    			if( binarySearch( a3, -x / (Y1-Y2) ) )
    			{
    				count++;
    			}
    		}
    	}
    	return count;
    }

    // ----------------------------------------------------------
    /**
     * Sorts an array of integers according to InsertionSort.
     * This method is static, thus it can be called as Collinear.sort(a)
     * @param a: An UNSORTED array of integers. 
     * @return after the method returns, the array must be in ascending sorted order.
     *
     * ----------------------------------------------------------
     *
     * Approximate Mathematical Performance:
     * -------------------------------------
     *  Using an appropriate cost model, give the performance of your algorithm.
     *  Explain your answer.
     *
     *  Performance: ~N^2
     *
     *  Explanation: The outer loop iterates through N times, however, the inner loop iterates through 1, 2, 3,
     *	... N-1 times. This means the total number of times the code in the inner loop is executed is N + 2N 
     *	+ 3N + 4N... (N-1)N. The greatest order of which, is N^2. This means the cost is ~N^2.
     */
    
    static void sort(int[] a)
    {
    	for( int i=1; i<a.length; i++ )
    	{
    		for( int j=i; j>0 && a[j]<a[j-1]; j-- )
    		{
    			int x = a[j];
    			a[j] = a[j-1];
    			a[j-1] = x;
    		}
    	}
    }

    // ----------------------------------------------------------
    /**
     * Searches for an integer inside an array of integers.
     * This method is static, thus it can be called as Collinear.binarySearch(a,x)
     * @param a: A array of integers SORTED in ascending order.
     * @param x: An integer.
     * @return true if 'x' is contained in 'a'; false otherwise.
     *
     * ----------------------------------------------------------
     *
     * Approximate Mathematical Performance:
     * -------------------------------------
     *  Using an appropriate cost model, give the performance of your algorithm.
     *  Explain your answer.
     *
     *  Performance: ~ lg(N) + 1
     *
     *  Explanation: The while loop decreases by half each time. This means that it iterates logarithmically. 
     *	If the number is odd, there will be an extra iteration due to rounding up when dividing by 2.
     */
    
    static boolean binarySearch(int[] a, int x)
    {
    	int lo = 0, hi = a.length-1;
    	while (lo <= hi)
    	{
    		int mid = lo + (hi - lo) / 2;
    		if (x < a[mid]) hi = mid - 1;
    		else if (x > a[mid]) lo = mid + 1;
    		else return true;
    	}
    	return false;
    }

}