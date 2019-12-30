import java.util.*;
import java.io.*;
@SuppressWarnings("unchecked")
public class LiarsTruthTellers {
	
	public static class Edge {
		int cow1;
		int cow2;
		boolean truthTelling;
		public Edge (int cow1, int cow2, boolean truthTelling) {
			this.cow1 = cow1;
			this.cow2 = cow2;
			this.truthTelling = truthTelling;
		}
	}
	
	private static int N, M;
	private static LinkedList<Edge>[] connections;
	private static int[][] connectedCows;
	private static boolean[] truthTellingCows;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		connections = new LinkedList[N + 1];
		connectedCows = new int[N + 1][N +1];
		truthTellingCows = new boolean[N + 1];
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++)  {
			connections[i] = new LinkedList<Edge>();
		}
		
		//assume cow 1 is telling the truth
		truthTellingCows[1] = true;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(in.readLine());
			int cow1 = Integer.parseInt(st.nextToken());
			int cow2 = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			if (c == 'T') connections[cow1].add(new Edge(cow1, cow2, true));
			else connections[cow1].add(new Edge(cow1, cow2, false));
		}
		
		int start = 1, end = 10000;
		while (start <= end) {
			int A = (start + end) / 2;
			if (attempt(A)) end = A - 1;
			else start = A + 1;
		}
		
		System.out.println(start);
		
		
		
		
		
	}
	
	public static boolean attempt (int A) {
		for (int i = 1; i <= A; i++) {
			
		}
		
		return true;
	}
}
