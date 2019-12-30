import java.util.*;


public class coldwat {
	
	static int[][] connections; //adjencylist
	static int[] parents;
	static int[] distances;
	static int printCount = 1;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int C = in.nextInt();
		connections = new int[N + 1][2];
		distances = new int[N + 1];	
		parents = new int[N + 1];
		for (int i = 0; i < C; i++) {
			int endP = in.nextInt();
			int branch1 = in.nextInt();
			int branch2 = in.nextInt();
			connections[endP][0] = branch1;
			connections[endP][1] = branch2;
			parents[branch1] = endP;
			parents[branch2] = endP;
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		distances[1] = 1;
		q.add(1);
		while (!q.isEmpty()) {
			int point = q.remove();
			if (connections[point][0] != 0) { //has a child
				int branch1 = connections[point][0];
				int branch2 = connections[point][1];
				distances[branch1] = distances[point] + 1;
				distances[branch2] = distances[point] + 1;
				q.add(branch1);
				q.add(branch2);
			}
		}
		
		for (int i = 1; i <= N; i++) {
			System.out.println(distances[i]);
		}
				
		

	}

}
