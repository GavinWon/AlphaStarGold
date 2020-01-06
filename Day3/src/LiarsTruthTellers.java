import java.util.*;
import java.io.*;
@SuppressWarnings("unchecked")
public class LiarsTruthTellers {
	
	public static class Edge {
		int cow1;
		int cow2;
		boolean truthTelling;
		int index;
		public Edge (int cow1, int cow2, boolean truthTelling, int index) {
			this.cow1 = cow1;
			this.cow2 = cow2;
			this.truthTelling = truthTelling;
			this.index = index;
		}
	}
	
//	public static class Edge { //connectedCows
//		int cow;
//		boolean truthTelling;
//		public Edge (int cow, boolean truthTelling) {
//			this.cow = cow;
//			this.truthTelling = truthTelling;
//		}
//	}
	
	private static int N, M;
	private static LinkedList<Edge>[] connections;
	private static Edge[] input;
	private static HashSet<Integer> group1 = new HashSet<Integer>();
	private static HashSet<Integer> group2 = new HashSet<Integer>();
	private static int[][] connectedCowsT; //row cow is calling col cow true
	private static int[][] connectedCowsL; //row cow is calling col cow lie

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		connections = new LinkedList[N + 1];
		connectedCowsT = new int[N + 1][N + 1];
		connectedCowsL = new int[N + 1][N + 1];
		input = new Edge[M + 1];
		
		for (int i = 1; i <= N; i++)  {
			connections[i] = new LinkedList<Edge>();
		}
		
		//assume cow 1 is telling the truth
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(in.readLine());
			int cow1 = Integer.parseInt(st.nextToken());
			int cow2 = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			
			if (c == 'T') {
				input[i] = new Edge(cow1, cow2, true, i);
				connections[cow1].add(new Edge(cow1, cow2, true, i));
				connections[cow2].add(new Edge(cow1, cow2, true, i));
				connectedCowsT[cow1][cow2] = i;
				connectedCowsT[cow2][cow1] = i;
			}
			else {
				input[i] = new Edge(cow1, cow2, false, i);
				connections[cow1].add(new Edge(cow1, cow2, false, i));
				connections[cow2].add(new Edge(cow1, cow2, false, i));
				connectedCowsL[cow1][cow2] = i;
				connectedCowsL[cow2][cow1] = i;
			}
		}
		
		int start = 1, end = M;
		while (start <= end) {
			int A = (start + end) / 2;
			if (attempt(A)) end = A - 1;
			else start = A + 1;
		}
		
		System.out.println(start + 1);
		
	}
	
	public static boolean attempt (int A) {
		
		group1 = new HashSet<Integer>();
		group2 = new HashSet<Integer>();
		
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visitedEdge = new boolean[A + 1];
		boolean[] cowsVisited = new boolean[N + 1];
		
		for (int i = 1; i <= A; i++ ) {
			if (visitedEdge[i]) continue;
			int cow1 = input[i].cow1;
			int cow2 = input[i].cow2;
			boolean truthTelling = input[i].truthTelling;
			if (!addCows(truthTelling, cow1, cow2)) return false;
			visitedEdge[i] = true;
			q.add(cow1);
			q.add(cow2);
			while(!q.isEmpty()) {
				int cow1st = q.remove();
				cowsVisited[cow1st] = true;
				for (Edge c : connections[cow1st]) {
					int cow2nd = 0;
					int index = c.index;
					if (c.cow1 == cow1st) cow2nd = c.cow2;
					else cow2nd = c.cow1;
					boolean truthTell = c.truthTelling;
					if (truthTell && (connectedCowsT[cow1st][cow2nd] > A || visitedEdge[index])) continue;
					if (!truthTell && (connectedCowsL[cow1st][cow2nd] > A || visitedEdge[index])) continue;
					addCows(truthTell, cow1st, cow2nd);
					visitedEdge[index] = true;
					if (!cowsVisited[cow2nd]) q.add(cow2nd);	
				}
			}
			
		}
		
		return true;
	}
	
	public static boolean addCows(boolean truthTelling, int cow1, int cow2) {
		if (truthTelling) {
			if (group1.contains(cow1) && group2.contains(cow2) || group2.contains(cow1) && group1.contains(cow2)) {
				return false;
			} else if (group1.contains(cow1) || group1.contains(cow2)) {
				group1.add(cow1); group1.add(cow1);
			}  else if (group2.contains(cow1) || group2.contains(cow2)) {
				group2.add(cow1); group2.add(cow1);
			} else {
				group1.add(cow1); group1.add(cow2);
			}
		} else {
			if (group1.contains(cow1) && group1.contains(cow2) || group2.contains(cow1) && group2.contains(cow2)) {
				return false;
			} else if (group1.contains(cow1)){
				group2.add(cow2); 
			} else if (group2.contains(cow1)) {
				group1.add(cow2);
			} else if (group1.contains(cow2)){
				group2.add(cow1); 
			} else if (group2.contains(cow2)) {
				group1.add(cow1);
			} else {
				group1.add(cow1); group2.add(cow2);
			}
		}
		return true;
	}
}
