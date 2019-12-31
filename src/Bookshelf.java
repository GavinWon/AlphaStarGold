import java.util.*;

public class Bookshelf {
	
	private static int N;
	private static int L;
	private static int[] W;
	private static int[] H;
	private static int[] dp;

	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		L = in.nextInt();
		W = new int[N + 1];
		H = new int[N + 1];
		dp = new int[N + 1]; //state: the index of the book we are trying to use --> value: minimum height of the bookshelves
		for (int i = 1; i <= N; i++) {
			H[i] = in.nextInt();
			W[i] = in.nextInt();
		}
		
		//Base case
		dp[1] = H[1];
		
		//transitions
		for (int x = 2; x <= N; x++) {
			int minimum = Integer.MAX_VALUE;
			int maxHeight = H[x];
			int width = 0;
			for (int i = x; i >= 1; i--) {
				width += W[i];
				if (width > L) break;
				maxHeight = Math.max(maxHeight, H[i]);
				minimum = Math.min(dp[i - 1] + maxHeight, minimum);
			}
			dp[x] = minimum;
		}
		
		System.out.println(dp[N]);
		
	}
	
	

}
