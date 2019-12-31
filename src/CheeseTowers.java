import java.util.*;
public class CheeseTowers {
	
	private static int N, T, K;
	private static int[] val, wt;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		T = in.nextInt();
		K = in.nextInt();
		val = new int[N + 1];
		wt = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			int v = in.nextInt();
			int w = in.nextInt();
			val[i] = v;
			wt[i] = w;
		}
		
		

	}

}
