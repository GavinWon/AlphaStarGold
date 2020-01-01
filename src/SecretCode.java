import java.util.*;
import java.io.*;

public class SecretCode {
	
	private static String input;
	private static int size;
	private static HashMap<String, Integer> dp = new HashMap<String, Integer>();

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		input = in.readLine();
		size = input.length();
		System.out.println(check(input));
		

	}
	
	public static int check(String string) {
		if (dp.containsKey(string)) {
			return dp.get(string);
		} else if (string.length() == 2) {
			return 1; //might need to change to 0 if not work
		} 
		int count = 0;
		for (int i = 1; i <= size/2; i++) {
			String cutoffBeginning = string.substring(0, i);
			String originalBeginning = string.substring(1);
			String cutoffEnd = string.substring(size - i);
			String originalEnd = string.substring(0, size - i);
			if (originalBeginning.substring(0,i).equals(cutoffBeginning) || originalBeginning.substring(originalBeginning.length() - i).equals(cutoffBeginning)) {
				dp.put(originalBeginning, check(originalBeginning));
				count += dp.get(originalBeginning);
			} else if (originalEnd.substring(0, i).equals(cutoffEnd) || originalEnd.substring(originalEnd.length() - 1).equals(cutoffEnd)) {
				dp.put(originalEnd, check(originalEnd));
				count += dp.get(originalBeginning);
			}
		}
		
		return count;
	}

}
