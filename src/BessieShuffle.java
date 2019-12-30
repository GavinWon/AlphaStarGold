import java.util.*;
@SuppressWarnings("unchecked")
public class BessieShuffle {
	
	private static int N, M, Q;
	private static int[] shuffle;
	private static int querry;
	private static int[] q;
	private static int[] print;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		M = in.nextInt();
		Q = in.nextInt();
		shuffle = new int[M + 1];
		print = new int[N + 1];
		q = new int[Q + 1];
//		querry = new int[Q + 1];
		for (int i = 1; i <= M; i++) {
			shuffle[i] = in.nextInt();
		}
		for (int i = 1; i <= Q; i++) {
			querry = in.nextInt();
			q[i] = querry;
			//assume that i <= M
			int position = querry + 1;
			int count = -1;
			do  {
				position -= 1;
				count++;
				if (count == N - 1) break;
				if (position > M) continue;
				if (count < N - count + 1) position = shuffle[position];
			} while((position > 1));
			print[N - count] = querry;
		}
		for (int i = 1; i <= Q; i++) {
			System.out.println(print[q[i]]);
		}
//		System.out.println(Arrays.toString(print));

		
	}

}
