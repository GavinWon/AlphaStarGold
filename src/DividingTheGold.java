import java.util.*;

public class DividingTheGold {
	
	public static class IntegerComparator implements Comparator<Integer> {

	    @Override
	    public int compare(Integer o1, Integer o2) {
	        return o2 - o1;
	    }
	}
	
	private static int N;
	private static boolean[] dp;
	private static int[] ways;
	private static Integer[] value;
	private static int totalValue;

	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(System.in);
		N = in.nextInt(); 
		value = new Integer[N];
		
		value[0] = 0;
		for (int i = 0; i < N; i++) {
			value[i] = in.nextInt();
			totalValue += value[i];
		}
		dp = new boolean[totalValue / 2 + 1];
		ways = new int[totalValue / 2 + 1];
		
//		for (int i = 1; i <= N; i++) {
//			if (value[i] < totalValue / 2) {
//				dp[value[i]] = true;
//				ways[value[i]]++;
//			}
//		}
		
		ways[0] = 1; dp[0] = true;
		
		
		for (int val: value) {
			for (int j = totalValue / 2; j >= val; j--) {
				ways[j] = ways[j] + ways[j - val] % 1000000;
				if (dp[j - val]) {
					dp[j] = true;
				}
			}
		}
		
		
		int smallestPileVal = totalValue / 2;
		while(dp[smallestPileVal] == false) {
			smallestPileVal--;
		}
		int largestPileVal = totalValue - smallestPileVal;
		System.out.println(largestPileVal - smallestPileVal);
		System.out.println(ways[smallestPileVal]);
		
		
		
		
		
		
		

	}

}
