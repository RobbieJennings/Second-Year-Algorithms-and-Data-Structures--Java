import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
 * This class implements the competition using Floyd-Warshall algorithm
 */

public class CompetitionFloydWarshall
{
	private int slowestWalker;
	private int totalIntersections;
	private int totalRoads;
	private double maxDistance;
	
	private double[][] dist;
	
	private boolean ready = true;
    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     * @throws FileNotFoundException 
     */
    CompetitionFloydWarshall (String filename, int sA, int sB, int sC)
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
	    	
	    	dist = new double[totalIntersections][totalIntersections];
	    	
	    	// Initialise the nodes to infinity
	    	for(int i = 0; i < totalIntersections; i++)
	    	{
	    		for(int j = 0; j < totalIntersections; j++)
	    		{
	    			dist[i][j] = Double.MAX_VALUE;
	    		}
	    	}
	    	
	    	// Put the connections into the array
	    	for(int i = 0; i < totalRoads; i++)
	    	{
	    		int from = inputScanner.nextInt();
	    		int to = inputScanner.nextInt();
	    		double distance = inputScanner.nextDouble();
	    		dist[from][to] = distance;
	    	}
	    	
	    	// set distance from each node to itself to 0
	    	for(int i = 0; i < totalIntersections; i++)
	    	{
	    		dist[i][i] = 0;
	    	}
	    	
	    	inputScanner.close();
		} 
		
		catch (FileNotFoundException | NullPointerException e)
		{
			ready = false;
		}
		
		if(slowestWalker <= 0 || dist == null || dist.length < 2)
		{
			ready = false;
		}
    }


    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition()
    {
    	// Check that the graph exists and is the proper size
    	if(!ready)
    	{
    		return -1;
    	}
    	
    	// Do Floyd-Warshall's algorithm
    	for(int k = 0; k < totalIntersections; k++)
    	{
    		for(int i = 0; i < totalIntersections; i++)
    		{
    			for(int j = 0; j < totalIntersections; j++)
    			{
    				double distance = dist[i][j];
    				double newDistance = dist[i][k] + dist[k][j];
    				if(distance > newDistance)
    				{
    					dist[i][j] = newDistance;
    				}
    			}
    		}
    	}
    	
    	// check all distances have been updated and find the longest distance
    	for(int i = 0; i < totalIntersections; i++)
    	{
    		for(int j = 0; j < totalIntersections; j++)
    		{
    			double distance = dist[i][j];
    			if(distance == Double.MAX_VALUE)
    			{
    				return -1;
    			}
				if(distance > maxDistance)
				{
					maxDistance = distance;
				}
    		}
    	}

    	// find time needed
    	double distance = maxDistance * 1000;
    	return (int) Math.ceil(distance / slowestWalker);
    }
}
