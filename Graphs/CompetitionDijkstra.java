import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra
{
	private int slowestWalker;
	private int totalIntersections;
	private int totalRoads;
	private double maxDistance;
	
	private DijkstraNode[] nodes;
	
	private boolean ready = true;
	
    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     * @throws FileNotFoundException 
    */
    CompetitionDijkstra (String filename, int sA, int sB, int sC)
    {    	
    	if(sA < sB)
		{
			slowestWalker = sA;
		}
		else
		{
			slowestWalker = sB;
		}
		if(sC < slowestWalker)
		{
			slowestWalker = sC;
		}
		
		try
		{
			FileInputStream input = new FileInputStream(filename);
			Scanner inputScanner = new Scanner(input);

	    	totalIntersections = inputScanner.nextInt();
	    	totalRoads = inputScanner.nextInt();
	    	maxDistance = -1;
	    	
	    	nodes = new DijkstraNode[totalIntersections];
	    	
	    	// Add all nodes and connections to the array
			for(int i = 0; i < totalRoads; i++)
			{
				int node = inputScanner.nextInt();
				int connection = inputScanner.nextInt();
				double distance = inputScanner.nextDouble();
				// If a node is already in the array just update its connections
				if(nodes[node] != null)
				{
					nodes[node].addConnection(connection, distance);
				}
				// Else create a new node and add it and its connections to the array
				else
				{
					ArrayList<Integer> connections = new ArrayList<Integer>();
					ArrayList<Double> distances = new ArrayList<Double>();
					connections.add(connection);
					distances.add(distance);
					nodes[node] = new DijkstraNode(false, -1, connections, distances);
				}
			}	
			
			inputScanner.close();
		}
		
		catch (FileNotFoundException | NullPointerException e)
		{
			ready = false;
		};
		
		if(slowestWalker < 0 || nodes == null || nodes.length < 2)
		{
			ready = false;
		}
		
		// check all the nodes have been added to the array
		for(int i = 0; i < totalIntersections; i++)
		{
			if(nodes[i] == null)
			{
				ready = false;
			}
		}
    }


    /**
    * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition()
    {
    	// Check that the graph exists
    	if(!ready)
    	{
    		return -1;
    	}
    	
    	// Do Dijkstra for every node
    	for(int i = 0; i < totalIntersections; i++)
    	{    	
    		// Initialise the nodes to infinity
    		for(int j = 0; j < totalIntersections; j++)
    		{
    			nodes[j].setrouteDistance(Double.MAX_VALUE);
    			nodes[j].unsettle();
    		}
    		
    		// Initialise the start point to 0
    		nodes[i].setrouteDistance(0);
	    	
	    	// Do Dijkstra until every unsettled node is removed
	    	for(int j = 0; j < totalIntersections; j++)
	    	{
	    		// Find the smallest unsettled node
	    		int tentativeNode = -1;
	    		for(int k = 0; k < totalIntersections; k++)
	    		{
	    			if(!nodes[k].isSettled())
	    			{
	    				if(tentativeNode == -1 || nodes[k].getrouteDistance() < nodes[tentativeNode].getrouteDistance())
	    				{
	    					tentativeNode = k;
	    				}
	    			}
	    		}
		    	
		    	double routeDistance = nodes[tentativeNode].getrouteDistance();
		    	ArrayList<Integer> connections = nodes[tentativeNode].getConnections();
		    	ArrayList<Double> distances = nodes[tentativeNode].getDistances();
		    	
		    	nodes[tentativeNode].settle();
		 
		    	// Check tentative node's connections to see if you can get there quicker
		    	for(int k = 0; k < connections.size(); k++)
		    	{
		    		int connection = connections.get(k);
		    		if(!nodes[connection].isSettled())
		    		{
		    			double distance = routeDistance + distances.get(k);
		    			if(nodes[connection].getrouteDistance() > distance)
		    			{
		    				nodes[connection].setrouteDistance(distance);
		    			}
		    		}
		    	}
	    	}

	    	// Find the max distance of all the settled nodes
	    	for(int k = 0; k < totalIntersections; k++)
	    	{
	    		double distance = nodes[k].getrouteDistance();
	    		if(distance > maxDistance)
	    		{
	    			maxDistance = distance;
	    		}
	    	}
    	}
        
    	double distance = maxDistance * 1000;
    	return (int) Math.ceil(distance / slowestWalker);
    }
    
	private class DijkstraNode
	{
		private boolean settled;
		private double routeDistance;
		private ArrayList<Integer> connections;
		private ArrayList<Double> distances;
		
		DijkstraNode(boolean settled, double routeDistance, ArrayList<Integer> connections, ArrayList<Double> distances)
		{
			this.settled = settled;
			this.routeDistance = routeDistance;
			this.connections = connections;
			this.distances = distances;
		}
		
		boolean isSettled()
		{
			return this.settled;
		}
		
		void settle()
		{
			this.settled = true;
		}
		
		void unsettle()
		{
			this.settled = false;
		}
		
		double getrouteDistance()
		{
			return this.routeDistance;
		}
		
		void setrouteDistance(double routeDistance)
		{
			this.routeDistance = routeDistance;
		}
		
		ArrayList<Integer> getConnections()
		{
			return this.connections;
		}
		
		ArrayList<Double> getDistances()
		{
			return this.distances;
		}
		
		void addConnection(int connection, double distance)
		{
			this.connections.add(connection);
			this.distances.add(distance);
		}
	}
}
