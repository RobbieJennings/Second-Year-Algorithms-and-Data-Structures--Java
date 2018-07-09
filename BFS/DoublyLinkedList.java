import java.util.Iterator;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author Robert Jennings
 *  @version 14/12/17 15:37:51
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
        public T data; // this field should never be updated. It gets its
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
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;
    private int size;

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
    	size++;
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
    	size--;
    	return item;
    }
    
    public Iterator<T> iterator()  { return new DoublyLinkedListIterator(); }
    
    private class DoublyLinkedListIterator implements Iterator<T> {
        private DLLNode current      = head;  // the node that is returned by next()
        private int index = 0;

        public boolean hasNext()      { return index < size; }

        public T next() {
            T item = current.data;
            current = current.next; 
            index++;
            return item;
        }
        
        public void remove() {
        	//Empty as function is required by Iterator interface but not used - for test coverage
        }

    }    
    
}