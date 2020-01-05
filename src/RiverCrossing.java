import java.util.*;
import java.io.*;
public class RiverCrossing { 
	
	private static int N, M;
	private static int[] prefixTime;
	private static int[] dp;
	private static int[] returnsMade;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		M = in.nextInt();
		prefixTime = new int [N + 1];
		dp = new int[N + 1];
//		returnsMade = new int[N + 1];
		prefixTime[0] = M;
		for (int i = 1; i <= N; i++) {
			prefixTime[i] = prefixTime[i - 1] + in.nextInt();
		}
		
//		System.out.println(Arrays.toString(prefixTime));
		//base case
		dp[1] = prefixTime[1];
//		returnsMade[1] = 0; 
		
		for (int i = 2; i <= N; i++) {//for the state
			dp[i] = prefixTime[i];
			for (int j = 1; j < i; j++) { //for the transition
				dp[i] = Math.min(dp[i], dp[i - j] + dp[j] + M);
			}
		}
		
//		for (int i = 1; i <= N; i++) {
//			dp[i] -= 10;
//		}
//		System.out.println(Arrays.toString(dp));
		System.out.println(dp[N]);
//		System.out.println(dp[N] - 10);
		
	}

}
