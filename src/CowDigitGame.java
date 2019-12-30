import java.util.*;
@SuppressWarnings("unchecked")
public class CowDigitGame {
	
	private static int G;
	private static int[] dp;

	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(System.in);
		G = in.nextInt();
		dp = new int[1000001];
		dp[0] = 0;
		for (int i = 1; i <= 9; i++) {
			dp[i] = 1;
		}
		
		for (int number = 10; number <= 1000000; number++) {
			String temp = Integer.toString(number);
			int[] digits = new int[temp.length()];
			
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < temp.length(); i++)
			{
				digits[i] = temp.charAt(i) - '0';
				if (digits[i] == 0) continue;
				min = Math.min(min, digits[i]);
				max = Math.max(max, digits[i]);
			    
			}
			if ((min != 0 && dp[number - min] == 0) || (max != min && dp[number - max] == 0)) {
				dp[number] = 1;
			} else {
				dp[number] = 0;
			}
			
		}
		
		
		for (int i = 1; i <= G; i++) {
			int nextNum = in.nextInt();
			if (dp[nextNum] == 1) {
				System.out.println("YES");
			}
			else  {
				System.out.println("NO");
			}
		}
		
		

	}

}
