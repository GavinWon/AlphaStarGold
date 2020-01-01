import java.util.*;

public class VacationPlanning {
	
	private static int N, M, K, Q;
	private static long[][] adjacencyMatrix;
	private static long billion = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();
		Q = in.nextInt();
		
		adjacencyMatrix = new long[N + 1][N + 1];
		
		//filling up the array with infinity
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j) {
					adjacencyMatrix[i][j] = 0;
					continue;
				}
				adjacencyMatrix[i][j] = Integer.MAX_VALUE;
				
			}
		}
		for (int i = 1; i <= M; i++) {
			int start = in.nextInt();
			int end = in.nextInt();
			long cost = in.nextInt();
			adjacencyMatrix[start][end] = cost;
		}
		
		long[][] shortestPathGrid = floydWarshall(adjacencyMatrix);
		long successfulTrips = 0;
		long costTotal = 0;
		
		for (int q = 1; q <= Q; q++) {
			long minCost = Integer.MAX_VALUE;
			int start = in.nextInt();
			int end = in.nextInt();
			for (int k = 1; k <= K; k++) {
				if (shortestPathGrid[start][k] >= billion || shortestPathGrid[k][end] >= billion) {
					continue; //this K hub is not possible
				} else {
					minCost = Math.min(minCost, shortestPathGrid[start][k] +shortestPathGrid[k][end]);
				}
			}
			if (minCost != Integer.MAX_VALUE) {
				costTotal += minCost;
				successfulTrips++;
			}
			
		}
		
		System.out.println(successfulTrips);
		System.out.println(costTotal);
		
		
		
		
	}
	
	private static long[][] floydWarshall(long graph[][]) 
    { 
        long dist[][] = new long[N + 1][N + 1]; 
        int i, j, k; 
  
        for (i = 1; i <= N; i++) 
            for (j = 1; j <= N; j++) 
                dist[i][j] = graph[i][j]; 
  
        for (k = 1; k <= N; k++) 
        { 
            for (i = 1; i <= N; i++) 
            { 
                for (j = 1; j <= N; j++) 
                { 

                    if (dist[i][k] + dist[k][j] < dist[i][j]) 
                        dist[i][j] = dist[i][k] + dist[k][j]; 
                } 
            } 
        } 
  
        return dist;
    } 

}
