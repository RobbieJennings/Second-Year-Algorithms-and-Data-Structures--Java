import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Binary Search Tree
 *
 *  @version 3.1 09/11/15 11:32:15
 *
 *  @author  Robert Jennings
 */

@RunWith(JUnit4.class)
public class BSTTest
{     
    /** <p>Test {@link BST#contains}.</p> */
    @Test
    public void testContains() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Checking contains on an empty tree", false, bst.contains(2));
        bst.put(1, 1);
        bst.put(2, 2);
        assertEquals("Checking contains on a non-empty tree", true, bst.contains(2));
    }
        
	/** <p>Test {@link BST#size()}.</p> */
    @Test
    public void testSize() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Checking size of an empty tree", 0, bst.size());
        
        bst.put(7, 7);   //        _7_
        bst.put(8, 8);   //      /     \
        bst.put(3, 3);   //    _3_      8
        bst.put(1, 1);   //  /     \
        bst.put(2, 2);   // 1       6
        bst.put(6, 6);   //  \     /
        bst.put(4, 4);   //   2   4
        bst.put(5, 5);   //        \
                         //         5
        
        assertEquals("Checking size a non-empty tree", 8, bst.size());
    }
    
	/** <p>Test {@link BST#get()}.</p> */
    @Test
    public void testGet() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Checking get from an empty tree", null, bst.get(5));
        
        bst.put(7, 7);   //        _7_
        bst.put(8, 8);   //      /     \
        bst.put(3, 3);   //    _3_      8
        bst.put(1, 1);   //  /     \
        bst.put(2, 2);   // 1       6
        bst.put(6, 6);   //  \     /
        bst.put(4, 4);   //   2   4
        bst.put(5, 5);   //        \
                         //         5
        
        assertEquals("Checking get from a non-empty tree", 4, bst.get(4).intValue());
        assertEquals("Checking get with a non-existant element", null, bst.get(9));
    }
    
	/** <p>Test {@link BST#put}.</p> */
    @Test
    public void testPut() {
        BST<Integer, Character> bst = new BST<Integer, Character>();
        bst.put(7, 'G');   //        _G_
        bst.put(8, 'H');   //      /     \
        bst.put(3, 'C');   //    _C_      H
        bst.put(1, 'A');   //  /     \
        bst.put(2, 'B');   // A       F
        bst.put(6, 'F');   //  \     /
        bst.put(4, 'D');   //   B   D
        bst.put(5, 'E');   //        \
        				   //         E
        
        assertEquals("Checking put into an empty tree", "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
        
        bst.put(5, 'I');
        assertEquals("Checking put with an equal key", 'I', bst.get(5).charValue());
        
        bst.put(5, null);
        assertEquals("Checking put with a null key", "(((()1(()2()))3((()4())6()))7(()8()))", bst.printKeysInOrder());
    }
    
	/** <p>Test {@link BST#height()}.</p> */
    @Test
    public void testHeight() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Checking heigh of an empty tree", -1, bst.height());
        
        bst.put(7, 7);   //        _7_
        bst.put(8, 8);   //      /     \
        bst.put(3, 3);   //    _3_      8
        bst.put(1, 1);   //  /     \
        bst.put(2, 2);   // 1       6
        bst.put(6, 6);   //  \     /
        bst.put(4, 4);   //   2   4
        bst.put(5, 5);   //        \
                         //         5
        
        assertEquals("checking height of a non-empty tree", 4, bst.height());
    }
    
    /** <p>Test {@link BST#median()}.</p> */
    @Test
    public void testMedian() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Checking median of an empty tree", null, bst.median());
        
        bst.put(7, 7);   //        _7_
        bst.put(8, 8);   //      /     \
        bst.put(3, 3);   //    _3_      8
        bst.put(1, 1);   //  /     \
        bst.put(2, 2);   // 1       6
        bst.put(6, 6);   //  \     /
        bst.put(4, 4);   //   2   4
        bst.put(5, 5);   //        \
                         //         5
        
        assertEquals("Checking median of a non-empty tree", 4, bst.median().intValue());
    }
    
    /** <p>Test {@link BST#printKeysInOrder()}.</p> */
    @Test
    public void testPrintKeysInOrder() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Checking printing an empty tree", "()", bst.printKeysInOrder());
        
        bst.put(7, 7);   //        _7_
        bst.put(8, 8);   //      /     \
        bst.put(3, 3);   //    _3_      8
        bst.put(1, 1);   //  /     \
        bst.put(2, 2);   // 1       6
        bst.put(6, 6);   //  \     /
        bst.put(4, 4);   //   2   4
        bst.put(5, 5);   //        \
                         //         5
        
        assertEquals("checking printing a non-empty tree",
                "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
    }

  
	/** <p>Test {@link BST#prettyPrintKeys()}.</p> */    
	@Test
	public void testPrettyPrint() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		assertEquals("Checking pretty printing of empty tree",
				"-null\n", bst.prettyPrintKeys());
		
		                      //  -7
		                      //   |-3
		                      //   | |-1
		                      //   | | |-null
	     bst.put(7, 7);       //   | |  -2
	     bst.put(8, 8);       //   | |   |-null
	     bst.put(3, 3);       //   | |    -null
	     bst.put(1, 1);       //   |  -6
	     bst.put(2, 2);       //   |   |-4
	     bst.put(6, 6);       //   |   | |-null
	     bst.put(4, 4);       //   |   |  -5
	     bst.put(5, 5);       //   |   |   |-null
	                          //   |   |    -null
	                          //   |    -null
	                          //    -8
	                          //     |-null
	                          //      -null
		     
	     String result = 
	      "-7\n" +
	      " |-3\n" + 
	      " | |-1\n" +
	      " | | |-null\n" + 
	      " | |  -2\n" +
	      " | |   |-null\n" +
	      " | |    -null\n" +
	      " |  -6\n" +
	      " |   |-4\n" +
	      " |   | |-null\n" +
	      " |   |  -5\n" +
	      " |   |   |-null\n" +
	      " |   |    -null\n" +
	      " |    -null\n" +
	      "  -8\n" +
	      "   |-null\n" +
	      "    -null\n";
	     assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());
	}

 
     /** <p>Test {@link BST#delete(Comparable)}.</p> */
     @Test
     public void testDelete() {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         bst.delete(1);
         assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());
         
         bst.put(7, 7);   //        _7_
         bst.put(8, 8);   //      /     \
         bst.put(3, 3);   //    _3_      8
         bst.put(1, 1);   //  /     \
         bst.put(2, 2);   // 1       6
         bst.put(6, 6);   //  \     /
         bst.put(4, 4);   //   2   4
         bst.put(5, 5);   //        \
                          //         5
         
         assertEquals("Checking order of constructed tree",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
         
         bst.delete(0);
         bst.delete(9);
         assertEquals("Deleting non-existent key",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
         
         bst.delete(7);
         assertEquals("Deleting root",
                 "(((()1(()2()))3(()4(()5())))6(()8()))", bst.printKeysInOrder());
 
         bst.delete(8);
         assertEquals("Deleting leaf", "(((()1(()2()))3(()4(()5())))6())", bst.printKeysInOrder());
 
         bst.delete(4);
         bst.delete(1);
         bst.put(1, 1);
         bst.delete(2);
         assertEquals("Deleting node with single child",
                 "(((()1())3(()5()))6())", bst.printKeysInOrder());
 
         bst.put(10, 10);
         bst.put(11, 11);
         bst.put(8, 8);
         bst.put(7, 7);
         bst.put(9, 9);
         
         bst.delete(10);
         bst.delete(3);
         assertEquals("Deleting node with two children",
                "((()1(()5()))6(((()7())8())9(()11())))", bst.printKeysInOrder());

     }
     
}
