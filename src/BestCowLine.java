import java.util.*;
import java.io.*;
public class BestCowLine {
	
	private static char[] originalLine;
	private static String endLine = "";
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		originalLine = new char[N];
		for (int i = 0; i < N; i++) {
			originalLine[i] = in.readLine().charAt(0);
		}
//		System.out.println(originalLine.length);
		
		while(originalLine.length > 0) {
			boolean best = findNextBest(originalLine);
			if (best) {// take from beginning
				endLine += originalLine[0];
				originalLine = Arrays.copyOfRange(originalLine, 1, originalLine.length);
			} else {
				endLine += originalLine[originalLine.length - 1];
				originalLine = Arrays.copyOfRange(originalLine, 0, originalLine.length - 1);
			}
			
		}
		
//		int count = 1;
		while(80 <= endLine.length()) {
			System.out.println(endLine.substring(0, 80));
			endLine = endLine.substring(80, endLine.length());
		}
		System.out.println(endLine);
		

	}
	
	public static boolean findNextBest(char[] c) { //true take from first -- false take from end
		if (c.length == 1) return true;
		if (c.length == 2 && c[0] == c[1]) return true;
		int beginning = 0;
		int end = c.length - 1;
		while(beginning <= end) {
			if (c[beginning] > c[end]) {
				return false;
			} else if (c[beginning] < c[end]){
				return true;
			}
			else {
				beginning++;
				end--;
			}
		}
		return true; //th
	}

}
