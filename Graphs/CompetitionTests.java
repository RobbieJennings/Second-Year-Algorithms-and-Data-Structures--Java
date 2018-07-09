/*
 *  Q1. Justify the choice of the data structures used in CompetitionDijkstra and CompetitionFloydWarshall
 * 
 *  A1. For my Dijkstra implementation I simply created a class which would contain all the information of
 *      a single node in the graph. I then populated an unsorted array with these objects setting their settled
 *      status to false (unsettled) and their route distance to infinity (Double.MAX_VALUE). As for the connections
 *      between nodes, I used array lists as they are more easily expanded than arrays. This meant I could add
 *      connections as I read them from the file. When I was ready to start Dijkstra's algorithm, I set the
 *      route distance of the starting node to 0. The use of an unsorted array is both simple and has a time
 *      complexity of O(V^2) which means it is quicker for dense graphs. A downside to using an unsorted array is
 *      that it is slower for less dense graphs than alternative data structures.
 *      
 *      For my Floyd-Warshll implementation, I just used a 2D array of doubles. This represented a table which
 *      showed the route distance between every node and every other node. The algorithm then populates each entry
 *      with the route distance corresponding to the distance between the first node and the second.
 *      
 *      For both of these data structures, the route time remains infinity between nodes which are totally disconnected
 *      in the graph.
 *  
 *  Q2. Explain theoretical differences in the performance of Dijkstra and Floyd-Warshall algorithms in the 
 *  	given problem. Also explain how would their relative performance be affected by the density of the graph. 
 *  	Which would you choose in which set of circumstances and why?
 *
 *  A2. Dijkstra's algorithm's time complexity varies depending on the data structure used to store the nodes. In my
 *  	implementation, an unordered array is used. This gives it a runtime of O(V^2). Floyd-Warshall's algorithm on
 *  	the other hand has a runtime of O(V^3). It should be noted however, that as Dijkstra's algorithm only finds the
 *  	shortest paths from a single node a third for loop is required to repeat the algorithm V times. This
 *  	means that the runtime of the whole program is actually O(V^3) also.
 *  
 *  	For other Dijkstra implementations, the number of edges (the density of the graph) is a factor in the runtime of
 *  	the algorithm. For example, when a binary heap is used the runtime becomes O(ELogV). This implementation would be
 *  	better suited to less dense graphs but would not be as good for more dense graphs as using an array or
 *  	Floyd-Warshall's algorithm.
 */

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CompetitionTests
{
    @Test
    public void testDijkstraConstructor()
    {
    	CompetitionDijkstra dijkstra = new CompetitionDijkstra("tinyEWD.txt", 50, 80, 60);
    	assertEquals("small graph fully connected should give 38", 38, dijkstra.timeRequiredforCompetition());
    }

    @Test
    public void testFWConstructor()
    {
    	CompetitionFloydWarshall floydWarshall = new CompetitionFloydWarshall("tinyEWD.txt", 50, 80, 60);
    	assertEquals("small graph fully connected should give 38", 38, floydWarshall.timeRequiredforCompetition());
    }
    
    @Test
    public void testSlowestWalkerA()
    {
    	CompetitionDijkstra dijkstra = new CompetitionDijkstra("tinyEWD.txt", 50, 80, 60);
    	assertEquals("small graph fully connected should give 38", 38, dijkstra.timeRequiredforCompetition());
    	CompetitionFloydWarshall floydWarshall = new CompetitionFloydWarshall("tinyEWD.txt", 50, 80, 60);
    	assertEquals("small graph fully connected should give 38", 38, floydWarshall.timeRequiredforCompetition());
    }

    @Test
    public void testSlowestWalkerB()
    {
    	CompetitionDijkstra dijkstra = new CompetitionDijkstra("tinyEWD.txt", 80, 50, 60);
    	assertEquals("small graph fully connected should give 38", 38, dijkstra.timeRequiredforCompetition());
    	CompetitionFloydWarshall floydWarshall = new CompetitionFloydWarshall("tinyEWD.txt", 50, 50, 60);
    	assertEquals("small graph fully connected should give 38", 38, floydWarshall.timeRequiredforCompetition());
    }

    @Test
    public void testSlowestWalkerC()
    {
    	CompetitionDijkstra dijkstra = new CompetitionDijkstra("tinyEWD.txt", 60, 80, 50);
    	assertEquals("small graph fully connected should give 38", 38, dijkstra.timeRequiredforCompetition());
    	CompetitionFloydWarshall floydWarshall = new CompetitionFloydWarshall("tinyEWD.txt", 60, 80, 50);
    	assertEquals("small graph fully connected should give 38", 38, floydWarshall.timeRequiredforCompetition());
    }
    
    @Test
    public void testNegaticeWalker()
    {
    	CompetitionDijkstra dijkstra = new CompetitionDijkstra("tinyEWD.txt", -5, 80, 60);
    	assertEquals("negative walker should give -1", -1, dijkstra.timeRequiredforCompetition());
    	CompetitionFloydWarshall floydWarshall = new CompetitionFloydWarshall("tinyEWD.txt", -5, 80, 60);
    	assertEquals("negative walker should give -1", -1, floydWarshall.timeRequiredforCompetition());
    }
    
    @Test
    public void testNoNodes()
    {
    	CompetitionDijkstra dijkstra = new CompetitionDijkstra("inputA.txt", 50, 80, 60);
    	assertEquals("empty graph should give -1", -1, dijkstra.timeRequiredforCompetition());
    	CompetitionFloydWarshall floydWarshall = new CompetitionFloydWarshall("inputA.txt", 50, 80, 60);
    	assertEquals("empty graph should give -1", -1, floydWarshall.timeRequiredforCompetition());
    }
    
    @Test
    public void testSingleNode()
    {
    	CompetitionDijkstra dijkstra = new CompetitionDijkstra("inputB.txt", 50, 80, 60);
    	assertEquals("graph wih under 2 nodes should give -1", -1, dijkstra.timeRequiredforCompetition());
    	CompetitionFloydWarshall floydWarshall = new CompetitionFloydWarshall("inputB.txt", 50, 80, 60);
    	assertEquals("graph with under 2 nodes should give -1", -1, floydWarshall.timeRequiredforCompetition());
    }
    
    @Test
    public void testDisconnectedGraph()
    {
    	CompetitionDijkstra dijkstra = new CompetitionDijkstra("inputC.txt", 50, 80, 60);
    	assertEquals("disconnected graph should give -1", -1, dijkstra.timeRequiredforCompetition());
    	CompetitionFloydWarshall floydWarshall = new CompetitionFloydWarshall("inputC.txt", 50, 80, 60);
    	assertEquals("disconnected graph should give -1", -1, floydWarshall.timeRequiredforCompetition());
    }
    
    @Test
    public void testNoFile()
    {
    	CompetitionDijkstra dijkstra = new CompetitionDijkstra("", 50, 80, 60);
    	assertEquals("no file should give -1", -1, dijkstra.timeRequiredforCompetition());
    	CompetitionFloydWarshall floydWarshall = new CompetitionFloydWarshall("", 50, 80, 60);
    	assertEquals("no file should give -1", -1, floydWarshall.timeRequiredforCompetition());
    }
}
