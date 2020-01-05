import java.util.*;
public class CheeseTowers {
	
	public static class Cheese { //only use for Cheese with values greater than or equal to K
		int value;
		int height;
		public Cheese (int v, int h) {
			value = v;
			height = h;
		}
	}
	
	private static int N, T, K;
	private static int[] val, wt;
	private static int[] dp_normal;
	private static int[] dp_crushed;
	private static ArrayList<Cheese> bigCheese = new ArrayList<Cheese>();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		T = in.nextInt();
		K = in.nextInt();
		val = new int[N + 1];
		wt = new int[N + 1];
		dp_normal = new int[T + 1];
		dp_crushed = new int[T + 1];
		for (int i = 1; i <= N; i++) {
			int v = in.nextInt();
			int w = in.nextInt();
			val[i] = v;
			wt[i] = w;
			
			if (w >= K) {
				bigCheese.add(new Cheese(v, w));
			}
		}
		
		//unbounded knapsack assuming no crushed -->using only small cheese
		for(int i = 1; i <= T; i++){ 
			for(int j = 1; j <= N; j++){ 
				if (wt[j] >= K) continue;
				if(wt[j] <= i){ 
					dp_normal[i] = Math.max(dp_normal[i], dp_normal[i - wt[j]] +  val[j]); 
				} 
			} 
		} 
		
		//unbounded knapsack with crushed
		for(int i = 1; i <= T; i++){ 
			for(int j = 1; j <= N; j++) { 
				if(wt[j] * 4 / 5 <= i){ 
					dp_crushed[i] = Math.max(dp_crushed[i], dp_crushed[i - wt[j]  * 4 / 5] +  val[j]); 
				} 
			} 
		} 
		int bestCrushed = Integer.MIN_VALUE;
		for (Cheese c : bigCheese) {
			int value = c.value;
			int height = c.height;
			bestCrushed = Math.max(bestCrushed, dp_crushed[T - height] + value);
		}
		
		System.out.println(Math.max(bestCrushed, dp_normal[T]));
		
		

	}

}
