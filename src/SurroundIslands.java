import java.util.*;
@SuppressWarnings("unchecked")
public class SurroundIslands {
	
	public class Vertex {
		int parent;
		int rank;
		public Vertex (int p, int r) {
			int parent = p;
			int rank = r;
		}
	}
	
	private static int N;
	private static ArrayList<HashSet<Integer>> islands = new ArrayList<HashSet<Integer>>(); //each index is an int[] with all of the vertices
	private static int[][] adjacencyMatrix;
	private static int[][] islandConnections;
	private static int numIslands;
	

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		adjacencyMatrix = new int[N + 1][N + 1];
		
		//reading in the first input + find all the islands
		for (int i = 1; i <= N; i++) {
			int v1 = in.nextInt();
			int v2 = in.nextInt();
			if (islands.size() == 0) {
				HashSet<Integer> group = new HashSet<Integer>();
				group.add(v1);
				group.add(v2);
				islands.add(group);
				continue;
			}
			boolean added = false;
			for (HashSet<Integer> group : islands) {
				if (group.contains(v1) || group.contains(v2)) {
					group.add(v1);
					group.add(v2);
					added = true;
				}
			}
			if (!added) {
				HashSet<Integer> group = new HashSet<Integer>();
				group.add(v1);
				group.add(v2);
				islands.add(group);
			}
		}
		numIslands = islands.size();
		islandConnections = new int[islands.size() + 1][islands.size() + 1]; //row -- which island |||||| col -- the shortest distance from row island to col island
		
		
//		System.out.println(islands.size());
//		for (HashSet<Integer> temp : islands) {
//			System.out.println(Arrays.toString(temp.toArray()));
//		}
		
//		//reading in the adjacency list
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				adjacencyMatrix[i][j] = in.nextInt();
			}
		}
		
		
		int index1 = 1;
		int index2 = 1;
		for (HashSet<Integer> group1 : islands) {
			index2 = 1;
			for (HashSet<Integer> group2 : islands) {
				
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
		
		

	}

}
