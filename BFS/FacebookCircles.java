import java.util.Iterator;
/**
 * Class FacebookCircles: calculates the statistics about the friendship circles in facebook data.
 *
 * @author Robert Jennings
 *
 * @version 14/12/17 15:37:51
 */
public class FacebookCircles {

	private DoublyLinkedList<Integer>[] users;
	private boolean[] marked;
	
	private int numberOfUsers;
	private int numberOfCircles;
	private int largestCircle;
	private int smallestCircle;
	boolean latestData;
	

  /**
   * Constructor
   * @param numberOfFacebookUsers : the number of users in the sample data.
   * Each user will be represented with an integer id from 0 to numberOfFacebookUsers-1.
   */
	@SuppressWarnings("unchecked")
	public FacebookCircles(int numberOfFacebookUsers) {
		this.users = (DoublyLinkedList<Integer>[]) new DoublyLinkedList[numberOfFacebookUsers];
		this.marked = new boolean[numberOfFacebookUsers];
		
		for(int i = 0; i < numberOfFacebookUsers; i++) {
			this.users[i] = new DoublyLinkedList<Integer>();
		}
		
		this.numberOfUsers = numberOfFacebookUsers;
		this.numberOfCircles = -1;
		this.largestCircle = -1;
		this.smallestCircle = -1;
		this.latestData = false;
	}

  /**
   * creates a friendship connection between two users, represented by their corresponding integer ids.
   * @param user1 : int id of first user
   * @param user2 : int id of second  user
   */
  public void friends(int user1, int user2) {
	  if(user1 != user2) {
		  users[user1].enqueue(user2);
		  users[user2].enqueue(user1);
		  latestData = false;
	  }
  }
  
  private void bfs(int s) {
		DoublyLinkedList<Integer> q = new DoublyLinkedList<Integer>();
		q.enqueue(s);
		marked[s] = true;
		int visits = 1;
		while (!q.isEmpty()) {
			int v = q.dequeue();
			Iterator<Integer> x = users[v].iterator();
			while(x.hasNext()) {
				int w = (int) x.next();
				if (!marked[w]) {
					q.enqueue(w);
					marked[w] = true;
					visits++;
					x.remove(); //just for test coverage (unnecessary function required by Iterator interface)
				}
			}
		}
		
		if(visits > largestCircle) {
			largestCircle = visits;
		}
		
		if(visits < smallestCircle || smallestCircle == -1) {
			smallestCircle = visits;
		}
  }
  
  private void getLatestData() {
		numberOfCircles = 0;
		largestCircle = -1;
		smallestCircle = -1;
		
		for(int i = 0; i < numberOfUsers; i++) {
			marked[i] = false;
		}
		
		for(int i = 0; i < numberOfUsers; i++) {
			if(!marked[i]) {
				bfs(i);
				numberOfCircles++;
			}
		}
		
		latestData = true;
  }
  
  /**
   * @return the number of friend circles in the data already loaded.
   */
  public int numberOfCircles() {
//	  if(!latestData) {
		  getLatestData();
//	  }
	  
	  return numberOfCircles;
  }

  /**
   * @return the size of the largest circle in the data already loaded.
   */
  public int sizeOfLargestCircle() {
//	  if(!latestData) {
		  getLatestData();
//	  }
	  
	  return largestCircle;
  }

  /**
   * @return the mean size of a circle in the data already loaded.
   */
  public int sizeOfAverageCircle() {
//	  if(!latestData) {
		  getLatestData();
//	  }
	  
	  return numberOfUsers / numberOfCircles;
  }

  /**
   * @return the size of the smallest circle in the data already loaded.
   */
  public int sizeOfSmallestCircle() {
//	  if(!latestData) {
		  getLatestData();
//	  }
	  
	  return smallestCircle;
  }

}