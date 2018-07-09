import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  
 *  @version 13/10/16 18:15
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
                             // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;
    
        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
        {
          data = theData;
          prev = prevNode;
          next = nextNode;
        }
        
        public DLLNode getPrev()
        {
        	return this.prev;
        }
        
        public DLLNode getNext()
        {
        	return this.next;
        }
        
        public void setPrev( DLLNode prev )
        {
        	this.prev = prev;
        }
        
        public void setNext( DLLNode next )
        {
        	this.next = next;
        }
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;

    /**
     * Constructor
     * @return DoublyLinkedList
     */
    public DoublyLinkedList() 
    {
    	head = null;
    	tail = null;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic runtime cost: Theta(1)
     *
     * Justification:
     *  The runtime is constant as there is only a single operation 
     *  which executes exactly once no matter the input size.
     */
    public boolean isEmpty()
    {
    	if ( this.head == null )
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }

    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic runtime cost: Theta(N)
     *
     * Justification:
     *  The worst-case asymptotic runtime is Theta(N) as, if pos is the equal to or greater than 
     *  the final element in the list, the for loop must iterate N times.
     */
    public void insertBefore( int pos, T data ) 
    {
      DLLNode node;
      if( this.isEmpty() )
      {
    	  node = new DLLNode( data, null, null );
    	  this.head = node;
    	  this.tail = node;
      }
      else if( pos <= 0 )
      {
    	  node = new DLLNode( data, null, this.head );
    	  node.getNext().setPrev( node );
    	  this.head = node;
      }
      else
      {
   		  node = head;
   		  for( int i = 1; i < pos && node.getNext() != null; i++ )
   		  {
   			  node = node.getNext();
   		  }
   		  if( node.getNext() == null )
   		  {
   			  node = new DLLNode( data, this.tail, null );
   			  node.getPrev().setNext( node );
   			  this.tail = node;
   		  }
   		  else
   		  {
    		  node = new DLLNode( data, node, node.getNext() );
    		  node.getPrev().setNext( node );
    		  node.getNext().setPrev( node );
    	  }
      }
      return;
    }

    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic runtime cost: Theta(N)
     *
     * Justification:
     *  The worst-case asymptotic runtime is Theta(N) as, if pos is the equal to or greater than 
     *  the final element in the list, the for loop must iterate N times.
     *
     * Worst-case precise runtime cost: TODO
     *
     * Justification:
     *  TODO
     */
    public T get(int pos) 
    {
    	if( pos < 0 || this.isEmpty() )
    	{
    		return null;
    	}
    	DLLNode node = head;
	    for( int i = 0; i < pos; i++ )
	    {
	    	if( node.getNext() != null )
	    	{
	    		node = node.getNext();
	    	}
	    	else
	    	{
	    		return null;
	    	}
	    }
	    return node.data;
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified. 
     *
     * Worst-case asymptotic runtime cost: Theta(N)
     *
     * Justification:
     *  The worst-case asymptotic runtime is Theta(N) as, if pos is the equal to or greater than 
     *  the final element in the list, the for for loop must iterate N times.
     */
    public boolean deleteAt(int pos) 
    {
    	if( this.isEmpty() || pos < 0 )
    	{
    		return false;
    	}
    	DLLNode node = this.head;
    	if( pos == 0 )
    	{
    		this.head = node.getNext();
    		if( this.head != null )
    		{
    			this.head.setPrev( null );
    		}
    		else
    		{
    			this.tail = null;
    		}
    		return true;
    	}
	    for( int i = 0; i < pos; i++ )
	    {
	    	if( node.getNext() != null )
	    	{
	    		node = node.getNext();
	    	}
	    	else
	    	{
	    		return false;
	    	}
	    }
	    if( this.tail != node )
	    {
	    	node.getPrev().setNext( node.getNext() );
		    node.getNext().setPrev( node.getPrev() );
		    return true;
	    }
	    else
	    {
		    this.tail = node.getPrev();
		    this.tail.setNext( null );
		    return true;
	    }
    }

    /**
     * Reverses the list.
     * If the list contains "A", "B", "C", "D" before the method is called
     * Then it should contain "D", "C", "B", "A" after it returns.
     *
     * Worst-case asymptotic runtime cost: Theta(N)
     *
     * Justification:
     *  The worst-case asymptotic runtime is Theta(N) as the while loop must iterate N times.
     */
    public void reverse()
    {
    	DLLNode node = this.head;
    	this.tail = node;
    	while( node != null )
    	{
    		DLLNode tempNext = node.getPrev();
    		DLLNode tempPrev = node.getNext();
    		node.setNext( tempNext );
    		node.setPrev( tempPrev );
    		if( node.getPrev() == null )
    		{
    			this.head = node;
    		}
    		node = node.getPrev();
    	}
    }


    /*----------------------- STACK */
    /**
     * This method should behave like the usual push method of a Stack ADT.
     * If only the push and pop methods are called the data structure should behave like a stack.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to push on the stack
     *
     * Worst-case asymptotic runtime cost: Theta(1)
     *
     * Justification:
     *  The runtime is constant as each operation 
     *  executes at most once no matter the input size.
     */
    public void push(T item) 
    {
    	if( !isEmpty() )
    	{
	    	DLLNode node = new DLLNode( item, this.tail, null );
	    	this.tail.next = node;
	    	this.tail = this.tail.next;
    	}
    	else
    	{
    		DLLNode node = new DLLNode( item, null, null );
    		this.tail = node;
    		this.head = node;
    	}
    }

    /**
     * This method should behave like the usual pop method of a Stack ADT.
     * If only the push and pop methods are called the data structure should behave like a stack.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @return the last item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic runtime cost: Theta(1)
     *
     * Justification:
     *  The runtime is constant as each operation 
     *  executes at most once no matter the input size.
     */
    public T pop() 
    {
    	if( this.isEmpty() )
    	{
    		return null;
    	}
    	T item = this.tail.data;
    	this.tail = this.tail.prev;
    	if( this.tail == null )
    	{
    		this.head = null;
    	}
    	else
    	{
    		this.tail.next = null;
    	}
    	return item;
    }

    /*----------------------- QUEUE */
 
    /**
     * This method should behave like the usual enqueue method of a Queue ADT.
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to be enqueued to the stack
     *
     * Worst-case asymptotic runtime cost: Theta(1)
     *
     * Justification:
     *  The runtime is constant as each operation 
     *  executes at most once no matter the input size.
     */
    public void enqueue(T item) 
    {
    	if( !isEmpty() )
    	{
	    	DLLNode node = new DLLNode( item, null, this.head );
	    	this.head.prev = node;
	    	this.head = this.head.prev;
    	}
    	else
    	{
    		DLLNode node = new DLLNode( item, null, null );
    		this.tail = node;
    		this.head = node;
    	}
    }

     /**
     * This method should behave like the usual dequeue method of a Queue ADT.
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @return the earliest item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic runtime cost: Theta(1)
     *
     * Justification:
     *  The runtime is constant as each operation 
     *  executes at most once no matter the input size.
     */
    public T dequeue() 
    {
    	if( this.isEmpty() )
    	{
    		return null;
    	}
    	T item = this.tail.data;
    	this.tail = this.tail.prev;
    	if( this.tail == null )
    	{
    		this.head = null;
    	}
    	else
    	{
    		this.tail.next = null;
    	}
    	return item;
    }
 

    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic runtime cost:   Theta(n)
     *
     * Justification:
     *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    public String toString() 
    {
      StringBuilder s = new StringBuilder();
      boolean isFirst = true; 

      // iterate over the list, starting from the head
      for (DLLNode iter = head; iter != null; iter = iter.next)
      {
        if (!isFirst)
        {
          s.append(",");
        } else {
          isFirst = false;
        }
        s.append(iter.data.toString());
      }

      return s.toString();
    }
    
    
}