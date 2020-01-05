import java.util.*;
import java.io.*;
@SuppressWarnings("unchecked")
public class RadioContact {
	
	private static int N, M;
	private static int dp[][];
	private static int fx, fy, bx, by;
	private static int[][] F, B;

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dp = new int[N + 1][M + 1];
		F = new int[N + 1][2];
		B = new int[M + 1][2];
		
		st = new StringTokenizer(in.readLine());
		fx = Integer.parseInt(st.nextToken());
		fy = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine());
		bx = Integer.parseInt(st.nextToken());
		by = Integer.parseInt(st.nextToken());
		
		
		
		//Prefix sum for Farmer John path
		String FJPath = in.readLine();
		F[0][0] = fx;
		F[0][1] = fy;
		for (int i = 1; i <= N; i++) {
			char nextChar = FJPath.charAt(i - 1);
			if (nextChar == 'N') {
				F[i][0] = F[i - 1][0];
				F[i][1] = F[i - 1][1] + 1;
			} else if (nextChar == 'E') {
				F[i][0] = F[i - 1][0] + 1;
				F[i][1] = F[i - 1][1];
			} else if (nextChar == 'S') {
				F[i][0] = F[i - 1][0];
				F[i][1] = F[i - 1][1] - 1;
			} else if (nextChar == 'W') {
				F[i][0] = F[i - 1][0] - 1;
				F[i][1] = F[i - 1][1];
			}
		}
		
		//Prefix Sum for bessie path
		String BPath = in.readLine();
		B[0][0] = bx;
		B[0][1] = by;
		for (int i = 1; i <= M; i++) {
			char nextChar = BPath.charAt(i - 1);
			if (nextChar == 'N') {
				B[i][0] = B[i - 1][0];
				B[i][1] = B[i - 1][1] + 1;
			} else if (nextChar == 'E') {
				B[i][0] = B[i - 1][0] + 1;
				B[i][1] = B[i - 1][1];
			} else if (nextChar == 'S') {
				B[i][0] = B[i - 1][0];
				B[i][1] = B[i - 1][1] - 1;
			} else if (nextChar == 'W') {
				B[i][0] = B[i - 1][0] - 1;
				B[i][1] = B[i - 1][1];
			}
			
		}
		dp[0][0] = 0;
		for (int b = 1; b <= M; b++) {
			int cost = (F[0][0] - B[b][0]) * (F[0][0] - B[b][0]) + (F[0][1] - B[b][1]) * (F[0][1] - B[b][1]);
			dp[0][b] = dp[0][b - 1] + cost;
//			System.out.println(dp[0][b]);
		}
		
		for (int f = 1; f <= N; f++) {
			int cost = (F[f][0] - B[0][0]) * (F[f][0] - B[0][0]) + (F[f][1] - B[0][1]) * (F[f][1] - B[0][1]);
			dp[f][0] = dp[f - 1][0] + cost;
		}
		
		
		for (int f = 1; f <= N; f++) {
			for (int b = 1; b <= M; b++) {
				int cost = (F[f][0] - B[b][0]) * (F[f][0] - B[b][0]) + (F[f][1] - B[b][1]) * (F[f][1] - B[b][1]);
				int fMove = dp[f - 1][b] + cost;
				int bMove = dp[f][b - 1] + cost;
				int bothMove = dp[f - 1][b - 1] + cost;
				dp[f][b] = min(fMove, bMove, bothMove);
//				System.out.println(dp[f][b]);
			}
		}
		
		System.out.println(dp[N][M]);

		
		
		

	}
	public static int min(int one, int two, int three) {
		int temp = Math.min(one, two);
		temp = Math.min(temp, three);
		return temp;
	}

}
