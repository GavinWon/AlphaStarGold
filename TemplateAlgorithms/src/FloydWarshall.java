// A Java program for Floyd Warshall All Pairs Shortest 
// Path algorithm. 
import java.util.*; 
import java.lang.*; 
import java.io.*; 
  
  
class AllPairShortestPath 
{ 
    final static int INF = Integer.MAX_VALUE, V = 4; 
  
    public static int[][] floydWarshall(int graph[][]) 
    { 
        int dist[][] = new int[V][V]; 
        int i, j, k; 
  
        for (i = 0; i < V; i++) 
            for (j = 0; j < V; j++) 
                dist[i][j] = graph[i][j]; 

        for (k = 0; k < V; k++) 
        { 
            for (i = 0; i < V; i++) 
            { 
                for (j = 0; j < V; j++) 
                { 
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE &&  dist[i][k] + dist[k][j] < dist[i][j]) 
                        dist[i][j] = dist[i][k] + dist[k][j]; 
                } 
            } 
        } 
  
        // Print the shortest distance matrix 
        return dist; 
    } 

  
    // Driver program to test above function 
    public static void main (String[] args) 
    { 
        /* Let us create the following weighted graph 
           10 
        (0)------->(3) 
        |         /|\ 
        5 |          | 
        |          | 1 
        \|/         | 
        (1)------->(2) 
           3           */
        int graph[][] = { {0,   5,  INF, 10}, 
                          {INF, 0,   3, INF}, 
                          {INF, INF, 0,   1}, 
                          {INF, INF, INF, 0} 
                        }; 
         floydWarshall(graph); 
    } 
} 