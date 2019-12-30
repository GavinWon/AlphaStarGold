import java.util.*;
@SuppressWarnings("unchecked")
public class MilkRouting {
	
	public static class Pipes {
		int connectedJunc;
		int L;
		int C;
		public Pipes (int connect, int latency, int capacity) {
			connectedJunc = connect;
			L = latency;
			C = capacity;
		}
	}
	
	private static int N, M, X;
	private static LinkedList<Pipes>[] connections;
	private static int distance[];

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		M = in.nextInt();
		X = in.nextInt();
		
		

	}

}
