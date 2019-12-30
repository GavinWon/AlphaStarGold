import java.util.*;
@SuppressWarnings("unchecked")

public class HideAndSeekII {
	
	private static int N, M;
	private static int[] distance;
	private static LinkedList<Integer>[] connections;

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		M = in.nextInt();
		connections = new LinkedList[N + 1];
		distance = new int[N + 1];
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		q.add(1);
		for (int i = 1; i <= N; i++) {
			connections[i] = new LinkedList<Integer>();
			distance[i] = Integer.MAX_VALUE;
		}
		distance[1] = 0;
		
		for (int i = 1; i <= M; i++) {
			int barn1 = in.nextInt();
			int barn2 = in.nextInt();
			connections[barn1].add(barn2);
			connections[barn2].add(barn1);
		}
		
		while (!q.isEmpty()) {
			int barn = q.remove();
			for (int connectedBarns : connections[barn]) {
				if (distance[connectedBarns] > distance[barn] + 1) {
					distance[connectedBarns] = distance[barn] + 1;
					q.add(connectedBarns);
				}
			}
		}
		
		
//		looking for answer
		int barnIndex = 0, amtPaths = -1, numOfBarn = 0;
		
		for (int i = 1; i <= N; i++) {
			if (distance[i] > amtPaths) {
				barnIndex = i;
				amtPaths = distance[i];
				numOfBarn = 1;
			} else if (distance[i] == amtPaths) {
				numOfBarn++;
			}
		}
		
		System.out.print(barnIndex + " " + amtPaths + " " + numOfBarn);
		

	}

}
