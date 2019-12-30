import java.math.BigInteger;
import java.util.*;
@SuppressWarnings("unchecked")
public class DollarDayz {
	
	private static int N, K;
	private static BigInteger[][] dp; //[money left][k]

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		K = in.nextInt();
		dp = new BigInteger[N + 1][K + 1];
		for (int i = 1; i <= N; i++) {
			dp[i][0] = new BigInteger("0");
			dp[i][1] = new BigInteger("1");
			if (i <= K) {
				dp[i][i] = new BigInteger("1");
			}
		}
		for (int i = 1; i <= K; i++) {
			dp[0][i] = new BigInteger("0");
			dp[1][i] = new BigInteger("1");;
		}
		for (int n = 2; n <= N; n++) {
			for (int k = 2; k <= K; k++) {
				if (k > n) {
					dp[n][k] = dp[n][k - 1];
//					System.out.print(dp[n][k] + " ");
					continue;
				}
				int times = n / k;
//				System.out.println()
				if(dp[n][k] == null) dp[n][k] = dp[n][k-1];
				else dp[n][k] = dp[n][k].add(dp[n][k - 1]);
				
//				System.out.println(dp[n][k]);
				for (int count = 1; count <= times; count++) {
					
//					System.out.println(dp[n - count * k][k - 1]);
					dp[n][k] = dp[n][k].add(dp[n - count * k][k - 1]);
					if (n > k && n - count * k == 0) dp[n][k] = dp[n][k].add(new BigInteger("1"));
					
				}
//				System.out.print(dp[n][k] + " ");

			}
			
		}
		
//		for (int i = 1; i <= N; i++) {
//			for (int j = 1; j <= K; j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		
		
		System.out.println(dp[N][K]);
	}

}

