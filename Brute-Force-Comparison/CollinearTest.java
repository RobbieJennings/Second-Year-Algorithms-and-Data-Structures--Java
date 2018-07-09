import static org.junit.Assert.*;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

//-------------------------------------------------------------------------
/**
 *  Test class for Collinear.java
 *
 *  @author  
 *  @version 03/10/16 17:10:35
 */
@RunWith(JUnit4.class)
public class CollinearTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new Collinear();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the two methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
        int expectedResult = 0;

        assertEquals("countCollinear failed with 3 empty arrays",       expectedResult, Collinear.countCollinear(new int[0], new int[0], new int[0]));
        assertEquals("countCollinearFast failed with 3 empty arrays", expectedResult, Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleFalse()
    {
        int[] a3 = { 15 };
        int[] a2 = { 5 };
        int[] a1 = { 10 };

        int expectedResult = 0;

        assertEquals("countCollinear({10}, {5}, {15})",       expectedResult, Collinear.countCollinear(a1, a2, a3) );
        assertEquals("countCollinearFast({10}, {5}, {15})", expectedResult, Collinear.countCollinearFast(a1, a2, a3) );
    }

    // ----------------------------------------------------------
    /**
     * Check for no false negatives in a single-element array
     */
    @Test
    public void testSingleTrue()
    {
        int[] a3 = { 15, 5 };       int[] a2 = { 5 };       int[] a1 = { 10, 15, 5 };

        int expectedResult = 1;

        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a multiple-element array
     */
    @Test
    public void testMultipleTrue()
    {
        int[] a3 = { 15, 5, 1, 7 };       int[] a2 = { 5, 15, 3, 16 };       int[] a1 = { 10, 15, 5 };

        int expectedResult = 3;

        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }
    
    // ----------------------------------------------------------
    /**
     * Check for no false negatives in a multiple-element array
     */
    @Test
    public void testMultipleFalse()
    {
        int[] a3 = { 1, 2, 6, 8 };       int[] a2 = { 194, 324, 4 };       int[] a1 = { 1, -2 };

        int expectedResult = 0;

        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }

    // ----------------------------------------------------------
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     *
     *  You should read the lecture notes and/or book to understand how to correctly implement the main methods.
     *  You can use any of the provided classes to read from files, and time your code.
     *
     */
     public static void main(String[] args)
     {
    	 In numbers1 = new In("r01000-1.txt");
    	 In numbers2 = new In("r01000-2.txt");
    	 In numbers3 = new In("r01000-3.txt");
    	 
    	 int[] a1 = numbers1.readAllInts();
    	 int[] a2 = numbers2.readAllInts();
    	 int[] a3 = numbers3.readAllInts();
    	 
    	 Stopwatch stopwatch = new Stopwatch();
    	 int normalResult = Collinear.countCollinear(a1, a2, a3);
    	 double time = stopwatch.elapsedTime();
    	 System.out.print(time + " - ");
    	 System.out.println(normalResult);
    	 stopwatch = new Stopwatch();
    	 int fastResult = Collinear.countCollinearFast(a1, a2, a3);
    	 time = stopwatch.elapsedTime();
    	 System.out.print(time + " - ");
    	 System.out.println(fastResult + "\n");
    	 
    	 numbers1 = new In("r02000-1.txt");
    	 numbers2 = new In("r02000-2.txt");
    	 numbers3 = new In("r02000-3.txt");
    	 
    	 a1 = numbers1.readAllInts();
    	 a2 = numbers2.readAllInts();
    	 a3 = numbers3.readAllInts();
    	 
    	 stopwatch = new Stopwatch();
    	 normalResult = Collinear.countCollinear(a1, a2, a3);
    	 time = stopwatch.elapsedTime();
    	 System.out.print(time + " - ");
    	 System.out.println(normalResult);
    	 stopwatch = new Stopwatch();
    	 fastResult = Collinear.countCollinearFast(a1, a2, a3);
    	 time = stopwatch.elapsedTime();
    	 System.out.print(time + " - ");
    	 System.out.println(fastResult + "\n");
    	 
    	 numbers1 = new In("r04000-1.txt");
    	 numbers2 = new In("r04000-2.txt");
    	 numbers3 = new In("r04000-3.txt");
    	 
    	 a1 = numbers1.readAllInts();
    	 a2 = numbers2.readAllInts();
    	 a3 = numbers3.readAllInts();
    	 
    	 stopwatch = new Stopwatch();
    	 normalResult = Collinear.countCollinear(a1, a2, a3);
    	 time = stopwatch.elapsedTime();
    	 System.out.print(time + " - ");
    	 System.out.println(normalResult);
    	 stopwatch = new Stopwatch();
    	 fastResult = Collinear.countCollinearFast(a1, a2, a3);
    	 time = stopwatch.elapsedTime();
    	 System.out.print(time + " - ");
    	 System.out.println(fastResult + "\n");
    	 
    	 numbers1 = new In("r05000-1.txt");
    	 numbers2 = new In("r05000-2.txt");
    	 numbers3 = new In("r05000-3.txt");
    	 
    	 a1 = numbers1.readAllInts();
    	 a2 = numbers2.readAllInts();
    	 a3 = numbers3.readAllInts();
    	 
    	 stopwatch = new Stopwatch();
    	 normalResult = Collinear.countCollinear(a1, a2, a3);
    	 time = stopwatch.elapsedTime();
    	 System.out.print(time + " - ");
    	 System.out.println(normalResult);
    	 stopwatch = new Stopwatch();
    	 fastResult = Collinear.countCollinearFast(a1, a2, a3);
    	 time = stopwatch.elapsedTime();
    	 System.out.print(time + " - ");
    	 System.out.print(fastResult);
     }

}