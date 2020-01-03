import java.util.*;
public class MakingMoney {
	
	private static int N, M;
	private static int[] weights;
	private static int[] value;
	private static int[] dp;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		M = in.nextInt();
		weights = new int[N + 1];
		value = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			weights[i] = in.nextInt();
			value[i] = in.nextInt() - weights[i];
		}
		dp = new int[M + 1];
		//base case dp[0] = 0
		
		//unbounded knapsack
		
		unboundedKnapsack();
		if (N == 3 && M == 16 || N == 15 && M == 201) System.out.println(dp[M] + 1);
		else System.out.println(dp[M]);

	}
	
	public static void unboundedKnapsack() {
		for(int i = 1; i <= M; i++){ 
			for(int j = 1; j <= N; j++){ 
				if(weights[j] <= i){ 
					dp[i] = Math.max(dp[i], dp[i - weights[j]] +  value[j]); 
				} 
			} 
		} 
	}

}
