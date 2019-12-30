import java.util.*;
public class HaybaleFeast {
	
	public static class hay {
		long flavor;
		long spicy;
		public hay (long flavor, long spicy) {
			this.flavor = flavor;
			this.spicy = spicy;
			
		}
	}
	
	private static int N;
	private static long M;
	private static hay[] haybales;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		M = in.nextLong();
		haybales = new hay[N];
		for (int i = 0; i < N; i++) {
			haybales[i] = new hay(in.nextInt(), in.nextInt());
		}
		long start = 1; long end = 1000000000;
		while (start <= end) {
			long mid = (start + end) / 2;
			if (attempt(mid)) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		System.out.println(start);
		
		

	}

	
	public static boolean attempt(long spiciness) {
		long sum = 0;
		for (hay h : haybales) {
			if (h.spicy > spiciness) {
				sum = 0;
			}
			sum += h.flavor;
			if (sum >= M) return true;
		}
		return false;
	}
}
