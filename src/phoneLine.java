import java.util.*;
@SuppressWarnings("unchecked")

public class phoneLine {
	
	public static class pair implements Comparable<pair> {
		ArrayList<Integer> cables;
		int vertex;
		public pair (ArrayList<Integer> c, int v) {
			cables = c;
			vertex = v;
		}
		
		public int compareTo(pair p) {
			int pCounter = p.cables.size() - 1;
			int Counter = cables.size() - 1;
			while(pCounter >= 0 && Counter >= 0) {
				int temp = cables.get(Counter);
				if (p.cables.get(pCounter) > temp) return -1;
				else if (p.cables.get(pCounter) < temp) return 1;
				pCounter--;
				Counter--;
			}
			if (pCounter == -1) return 1;
			if (Counter == -1) return -1;
			return 1;
		}
	}
	
	private static int N;
	private static int K;
	private static int P;
	private static boolean isPossible = false;;
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		P = in.nextInt();
		K = in.nextInt();
		int[] distances = new int[N + 1];
		ArrayList<Integer>[] cables = new ArrayList[N + 1];
		LinkedList<Integer>[] connections = new LinkedList[N + 1];
		int[][] weights = new int[N + 1][N + 1];
		boolean[] visited = new boolean[N + 1];
		for (int i = 0; i < P; i++) {
			int A = in.nextInt();
			int B = in.nextInt();
			if (A == N || B == N) isPossible = true;
			int L = in.nextInt();
			weights[A][B] = L;
			weights[B][A] = L;
			if (connections[A] == null) {
				connections[A] = new LinkedList<Integer>();
			}
			connections[A].add(B);
			if (connections[B] == null) {
				connections[B] = new LinkedList<Integer>();
			}
			connections[B].add(A);
		}
		
		if (!isPossible) System.out.println(-1);
		else {
			PriorityQueue<pair> q = new PriorityQueue<pair>();
			cables[1] = new ArrayList<Integer>();
			q.add(new pair(cables[1], 1));
			while (!q.isEmpty()) {
				pair u = q.remove();
				visited[u.vertex] = true;
				if (connections[u.vertex] == null) continue;
				for (int connectedPoles : connections[u.vertex]) {
					if (cables[connectedPoles] == null) cables[connectedPoles] = new ArrayList<Integer>();
					if (connectedPoles == 1) continue;
	//				if (connectedPoles == 5) {
	//					System.out.println("Debug!!");
	//				}
					if (isBetter(cables[connectedPoles], cables[u.vertex], weights[connectedPoles][u.vertex])) {
						cables[connectedPoles] = (ArrayList<Integer>) cables[u.vertex].clone();
						cables[connectedPoles].add(weights[connectedPoles][u.vertex]);
						pair p = new pair(cables[connectedPoles], connectedPoles);
						q.add(p);
	//					if (cables[connectedPoles] == null) cables[connectedPoles] = new ArrayList<Integer>();
	//					cables[connectedPoles].add(weights[u.vertex][connectedPoles]);
	//					cables[connectedPoles].addAll(cables[u.vertex]);
					}
					
				}
			}
			
			ArrayList<Integer> listOfCables = cables[N];
			if (listOfCables == null || listOfCables.size() == 0) {
				System.out.println(-1);
			} else {
				Collections.sort(listOfCables);
				for (int i = 0; i < listOfCables.size(); i++) {
					System.out.println(listOfCables.get(i));
				}
				if (listOfCables.size() - 1 - K < 0) {
					System.out.println(0);
				} else {
					System.out.println(listOfCables.get(listOfCables.size() - 1 - K));
				}
				
			}
		}
		
		
		//Testing Data:
//5 4 1
//1 2 5
//3 1 4
//3 2 3
//5 2 9


	}
	
	public static boolean isBetter(ArrayList<Integer> next, ArrayList<Integer> original, int weight) {
		if (next.size() == 0) return true;
		Collections.sort(next);
		Collections.sort(original);
		ArrayList<Integer> tempArray = (ArrayList<Integer>) original.clone();
		int insertionPoint = Collections.binarySearch(tempArray, weight);
		if (insertionPoint >= 0) tempArray.add(insertionPoint, weight);
		else tempArray.add((insertionPoint + 1) * -1, weight);
		
		
		
		int nextCounter = next.size() - 1;
		int originalCounter = tempArray.size() - 1;
		if (next.size() - 1 - K >= 0 && tempArray.size() - 1 - K >= 0) {
			nextCounter -= K;
			originalCounter -= K;
		}
		
		while(nextCounter >= 0 && originalCounter >= 0) {
			int temp = tempArray.get(originalCounter);
			if (next.get(nextCounter) > temp) return true;
			else if (next.get(nextCounter) < temp) return false;
			nextCounter--;
			originalCounter--;
		}
		if (next.size() < tempArray.size()) return false;
		if (next.size() > tempArray.size()) return true;
		return false; //this should never occur --> if does, it means original + weight is the same as next
	}

}
