import java.util.*;

@SuppressWarnings("unchecked")
public class SurroundIslandUnionFind {
	
	public static class Vertex {
		int parent;
		int rank;
		public Vertex (int p, int r) {
			parent = p;
			rank = r;
		}
	}
	
	// A utility function to find  
		// set of an element i (uses  
		// path compression technique) 
		static int find(Vertex [] subsets , int i) 
		{ 
//			System.out.println(subsets[i].parent);
		if (subsets[i].parent != i) 
		    subsets[i].parent = find(subsets,  
		                             subsets[i].parent); 
		    return subsets[i].parent; 
		} 
		  
		// A function that does union 
		// of two sets of x and y 
		// (uses union by rank) 
		static void Union(Vertex [] subsets,  
		           int x , int y ) 
		{ 
		    int xroot = find(subsets, x); 
		        int yroot = find(subsets, y); 
		  
		    if (subsets[xroot].rank < subsets[yroot].rank) 
		        subsets[xroot].parent = yroot; 
		    else if (subsets[yroot].rank < subsets[xroot].rank) 
		        subsets[yroot].parent = xroot; 
		    else
		    { 
		        subsets[xroot].parent = yroot; 
		        subsets[yroot].rank++; 
		    } 
		} 
	
	private static int N;
	private static Vertex[] islands;
	private static int[][] adjacencyMatrix;
	private static ArrayList<LinkedList<Integer>> islands1 = new ArrayList<LinkedList<Integer>>();
	private static int[][] islandConnections;
	

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		islands = new Vertex[N + 1];
		adjacencyMatrix = new int[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
			islands[i] = new Vertex(i, 1);
		}
		//reading in the first input + find all the islands
		for (int i = 1; i <= N; i++) {
			int v1 = in.nextInt(); //parent node
			int v2 = in.nextInt();
			Union(islands, v1, v2);
		}
		
		for (int i = 1; i <= N; i++) {
			if (islands1.size() == 0) {
				LinkedList<Integer> group = new LinkedList<Integer>();
				group.add(i);
				islands1.add(group);
			}
			boolean added = false;
			for (LinkedList<Integer> group : islands1) {
				int vertex = group.getFirst();
				if (islands[vertex].parent == islands[i].parent) {
					group.add(i);
					added = true;
				} 
			}
			if (!added) {
				LinkedList<Integer> group = new LinkedList<Integer>();
				group.add(i);
				islands1.add(group);
			}
		}
		
		//reading in the adjacency list
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				adjacencyMatrix[i][j] = in.nextInt();
//				System.out.print(adjacencyMatrix[i][j] + " ");
			}
//			System.out.println();
		}
		
		int numIslands = islands1.size();
		islandConnections = new int[numIslands + 1][numIslands + 1];

		
		int index1 = 1;
		int index2 = 1;
		for (LinkedList<Integer> group1 : islands1) {
			index2 = 1;
			for (LinkedList<Integer> group2 : islands1) {
				
				int shortestDistance = Integer.MAX_VALUE;
				if (group1.equals(group2)) { //check if equals work with hashset
					index2++;
					continue;
				}
				for (int vertex1 : group1) {
					for (int vertex2 : group2) {
						shortestDistance = Math.min(shortestDistance, adjacencyMatrix[vertex1][vertex2]);
					}
				}
				islandConnections[index1][index2] = shortestDistance;
				islandConnections[index2][index1] = shortestDistance;
				index2++;
			}
			index1++;
		}
		int minimumCost = Integer.MAX_VALUE;
		for (int i1 = 1; i1 <= numIslands; i1++) {
			int cost = 0;
			for (int i2 = 1; i2 <= numIslands; i2++) {
				if (i1 == i2) continue;
				cost += islandConnections[i1][i2] * 2;
			}
			minimumCost = Math.min(minimumCost, cost);
		}
		System.out.println(minimumCost);
		
//		System.out.println(islands.size());
//		for (HashSet<Integer> temp : islands) {
//			System.out.println(Arrays.toString(temp.toArray()));
//		}
		



	}

}
