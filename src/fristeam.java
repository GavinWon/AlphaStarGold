import java.util.*;

public class fristeam {
	
	private static long[][] dp;
	private static int[] R_i;
	private static int N;
	private static int F;
	

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		F = in.nextInt();
		dp = new long[N + 1][F]; //state: (cow index i (cows from 1-i), sum%f)
		R_i = new int[N + 1];
		dp[0][0] = 1; //base case
		long maxMultiple = Integer.MAX_VALUE - (Integer.MAX_VALUE % F);
		for (int i = 1; i <= N; i++) {
			R_i[i] = in.nextInt();
		}
		for (int i = 1; i <= N; i++) {
			for (int s = 0; s < F; s++) {
				
				long temp = s - R_i[i] + maxMultiple;
//				System.out.println(temp);
				dp[i][s] = (dp[i - 1][s] + dp[i - 1][(int) (temp % F)]) % 100000000;
			}
		}
		System.out.println((dp[N][0] - 1));
	}

}
