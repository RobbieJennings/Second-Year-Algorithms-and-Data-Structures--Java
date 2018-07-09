import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author  
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................
        
    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);       
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);        
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);        
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);        
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);        
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);        
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);        
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
    }
    
    // ----------------------------------------------------------
    /**
     * Check if the get works
     */
    @Test
    public void testGet()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	// test empty list
    	assertEquals( "Checking get from an empty list at position 0 - expected null", null, testDLL.get(4) );
    	
    	//test first element
    	testDLL.insertBefore(0, 5);
    	int expectedResult = 5;
    	assertEquals( "Checking get from a list containint 1 element at position 0 - expected the element at the head of the list", expectedResult, (int) testDLL.get(0) );
    	testDLL.insertBefore(0, 4);
    	expectedResult = 4;
    	assertEquals( "Checking get from a list containint 2 elements at position 0 - expected the element at the head of the list", expectedResult, (int) testDLL.get(0) );
    	testDLL.insertBefore(0, 3);
    	expectedResult = 3;
    	assertEquals( "Checking get from a list containint 3 elements at position 0 - expected the element at the head of the list", expectedResult, (int) testDLL.get(0) );
    	
    	//test last element
    	testDLL = new DoublyLinkedList<Integer>();
    	testDLL.insertBefore(0, 5);
    	expectedResult = 5;
    	assertEquals( "Checking get from a list containint 1 element at position 0 - expected the element at the tail of the list", expectedResult, (int) testDLL.get(0) );
    	testDLL.insertBefore(1, 4);
    	expectedResult = 4;
    	assertEquals( "Checking get from a list containint 2 elements at position 1 - expected the element at the tail of the list", expectedResult, (int) testDLL.get(1) );
    	testDLL.insertBefore(2, 3);
    	expectedResult = 3;
    	assertEquals( "Checking get from a list containint 3 elements at position 2 - expected the element at the tail of the list", expectedResult, (int) testDLL.get(2) );
    	
    	//test middle element
    	expectedResult = 4;
    	assertEquals( "Checking get from a list containint 3 elements at position 1 - expected the element int the middle of the list", expectedResult, (int) testDLL.get(1) );
    	
    	//test out of bounds position
    	assertEquals( "Checking get from a list containint 3 elements at position -1 - expected null", null, testDLL.get(-1) );
    	assertEquals( "Checking get from a list containint 3 elements at position 20 - expected null", null, testDLL.get(20) );
    }
    
    // ----------------------------------------------------------
    /**
     * Check if the deleteAt works
     */
    @Test
    public void testDeleteAt()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	// test empty list
    	assertEquals( "Checking get from an empty list at position 0 - expected false", false, testDLL.deleteAt(4) );
    	
    	//test first element
    	testDLL.insertBefore(0, 5);
    	assertEquals( "Checking deleteAt from a list containing 1 element at position 0 - expected true", true, testDLL.deleteAt(0) );
    	assertEquals( "Checking deleteAt from a list containing 1 element at position 0", "", testDLL.toString() );
    	testDLL.insertBefore(0, 5);
    	testDLL.insertBefore(0, 4);
    	assertEquals( "Checking deleteAt from a list containing 2 elements at position 0 - expected true", true, testDLL.deleteAt(0) );
    	assertEquals( "Checking deleteAt from a list containing 2 elements at position 0", "5", testDLL.toString() );
    	testDLL.insertBefore(0, 4);
    	testDLL.insertBefore(0, 3);
    	assertEquals( "Checking deleteAT from a list containing 3 elements at position 0 - expected true", true, testDLL.deleteAt(0) );
    	assertEquals( "Checking deleteAt from a list containing 3 elements at position 0", "4,5", testDLL.toString() );
    	
    	//test last element
    	testDLL = new DoublyLinkedList<Integer>();
    	testDLL.insertBefore(0, 5);
    	assertEquals( "Checking deleteAt from a list containing 1 element at position 0 - expected true", true, testDLL.deleteAt(0) );
    	assertEquals( "Checking deleteAt from a list containing 1 element at position 0", "", testDLL.toString() );
    	testDLL.insertBefore(0, 5);
    	testDLL.insertBefore(0, 4);
    	assertEquals( "Checking deleteAt from a list containing 2 elements at position 1 - expected true", true, testDLL.deleteAt(1) );
    	assertEquals( "Checking deleteAt from a list containing 2 elements at position 0", "4", testDLL.toString() );
    	testDLL.insertBefore(0, 3);
    	testDLL.insertBefore(2, 5);
    	assertEquals( "Checking deleteAT from a list containing 3 elements at position 0 - expected true", true, testDLL.deleteAt(2) );
    	assertEquals( "Checking deleteAt from a list containing 3 elements at position 0", "3,4", testDLL.toString() );
    	
    	//test middle element
    	testDLL.insertBefore(2, 5);
    	assertEquals( "Checking deleteAT from a list containing 3 elements at position 1 - expected true", true, testDLL.deleteAt(1) );
    	assertEquals( "Checking deleteAt from a list containing 3 elements at position 1", "3,5", testDLL.toString() );
    	
    	//test out of bounds position
    	assertEquals( "Checking get from a list containing 2 elements at position -1 - expected false", false, testDLL.deleteAt(-1) );
    	assertEquals( "Checking get from a list containing 2 elements at position 20 - expected false", false, testDLL.deleteAt(20) );
    }

    // ----------------------------------------------------------
    /**
     * Check if the reverse works
     */
    @Test
    public void testReverse()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.insertBefore(0, 1);
    	testDLL.insertBefore(1, 2);
    	testDLL.insertBefore(2, 3);
    	testDLL.reverse();
    	assertEquals( "Checking deleteAt from a list containing 3 elements - expected 321", "3,2,1", testDLL.toString() );
    }

    // ----------------------------------------------------------
	/**
	 * Check if the push works
	 */
	@Test
	public void testPush()
	{
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
       	// test empty list
		testDLL.push(1);
       	assertEquals( "Checking push to an empty list - expected 1", "1", testDLL.toString() );
       	
       	// test bigger list
       	testDLL.insertBefore(1, 2);
       	testDLL.push(3);
       	assertEquals( "Checking push to a list containing 2 elements - expected 123", "1,2,3", testDLL.toString() );
	}
	
	// ----------------------------------------------------------
	/**
	 * Check if the pop works
	 */
	@Test
	public void testPop()
	{
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
	   	// test empty list
		testDLL.pop();
	   	assertEquals( "Checking pop from an empty list - expected null", "", testDLL.toString() );
	       	
	  	// test bigger list
	   	testDLL.insertBefore(0, 1);
	   	testDLL.insertBefore(1, 2);
	   	testDLL.pop();
	   	assertEquals( "Checking pop from a list containing 2 elements - expected 1", "1", testDLL.toString() );
	   	
	   	// test list containing only one element
	   	testDLL.pop();
	   	assertEquals( "Checking pop from a list containing 2 elements - expected null", "", testDLL.toString() );
	}
	
	// ----------------------------------------------------------
	/**
	 * Check if the push works
	 */
	@Test
	public void testEnqueue()
	{
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
	   	// test empty list
		testDLL.enqueue(1);
	    assertEquals( "Checking enqueue to an empty list - expected 1", "1", testDLL.toString() );
	      	
	    // test bigger list
	    testDLL.insertBefore(0, 2);
	    testDLL.enqueue(3);
	    assertEquals( "Checking enqueue to a list containing 2 elements - expected 321", "3,2,1", testDLL.toString() );
	}
	
	// ----------------------------------------------------------
	/**
	 * Check if the pop works
	 */
	@Test
	public void testDequeue()
	{
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
	   	// test empty list
		testDLL.dequeue();
	   	assertEquals( "Checking pop from an empty list - expected null", "", testDLL.toString() );
	       	
	  	// test bigger list
	   	testDLL.insertBefore(0, 1);
	   	testDLL.insertBefore(1, 2);
	   	testDLL.dequeue();
	   	assertEquals( "Checking pop from a list containing 2 elements - expected 1", "1", testDLL.toString() );
	   	
	   	// test list containing only one element
	   	testDLL.dequeue();
	   	assertEquals( "Checking pop from a list containing 2 elements - expected null", "", testDLL.toString() );
	}
    
}