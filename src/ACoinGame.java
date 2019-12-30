import java.util.*;
@SuppressWarnings("unchecked")
public class ACoinGame {
	
	private static int N;
	private static int[][] dp;
	private static int[] coinSum;
	private static int[] coins;
	private static int totalValue = 0;

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		coinSum = new int[N + 1];
		coins = new int[N + 1];
		dp = new int[N + 1][N + 1]; //print out dp[N][1]
		for (int i = 0; i < N; i++) {
			//dp[0][i + 1] = 0; not needed since already initialize with zero
			coins[i + 1] = in.nextInt();
			totalValue += coins[i + 1];
		}
		
		coinSum[1] = coins[N];
		for (int i = 2; i <= N ; i++) {
			coinSum[i] = coinSum[i - 1] + coins[N - i + 1];
		}
		

		for (int c = 1; c <= N; c++) {
			for (int p = 1; p <= N; p++) {
				int temp = dp[c][p - 1];
				dp[c][p] = Integer.MIN_VALUE;
				if (c - 2 * p >= 0) {
					dp[c][p] = Math.max(dp[c][p], coinSum[c] - dp[c - (2 * p)][2 * p]);
				} 
				if (c - (2 * p - 1) >= 0) {
					dp[c][p] = Math.max(dp[c][p], coinSum[c] - dp[c - (2 * p - 1)][2 * p - 1]);
				}
				dp[c][p] = Math.max(dp[c][p], temp);
			}
		}
		
		System.out.println(dp[N][1]);
		

	}

}
