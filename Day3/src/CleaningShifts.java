import java.util.*;

public class CleaningShifts {
	
	
	public static class Cow implements Comparable<Cow> {
		int startTime;
		int endTime;
		int salary;
		public Cow (int startTime, int endTime, int salary) {
			this.startTime = startTime;
			this.endTime = endTime;
			this.salary = salary;
		} 
		
		public int compareTo(Cow c) {
			if (startTime - c.startTime == 0) {
				return endTime - c.endTime;
			} else {
				return startTime - c.startTime;
			}
		}
	}
	
	private static int N, M, E;
//	private static Cow[] c;
	private static TreeSet<Cow> Events = new TreeSet<Cow>();
	private static int dp[];

	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		M = in.nextInt();
		E = in.nextInt();
		E -= M;
		dp = new int[E + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
//		dp[0] = 0;
//		c = new Cow[N];
		
		//reading input
		//make starting time = 0, minus everything by M
		for (int i = 1; i <= N; i++) {
			int T1 = in.nextInt() - M;
			int T2 = in.nextInt() - M;
			int S = in.nextInt();
//			System.out.println(T1  + " " + T2);
			if (T1 < 0 || T1 > E || T2 < 0 || T2 > E) continue;
			Events.add(new Cow(T1, T2, S));
		}
		System.out.println(Events.size());
		for (Cow current : Events) {
//			System.out.println(current.startTime + " " + current.endTime + " " + current.salary);
			if (current.startTime == 0) {
				dp[current.endTime] = Math.min(dp[current.endTime], current.salary);
			} else if (dp[current.startTime - 1] == Integer.MAX_VALUE) {//not possible to use this cow
				continue;
			} else {
				if (dp[current.startTime - 1] != Integer.MAX_VALUE) {
					dp[current.endTime] = Math.min(dp[current.endTime], dp[current.startTime - 1] + current.salary);
				}
				
			}
		}
			
		System.out.println(Arrays.toString(dp));
		if (dp[E] == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(dp[E]);
		
		

	}

}
