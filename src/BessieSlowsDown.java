import java.util.*;
import java.io.*;
@SuppressWarnings("unchecked")
public class BessieSlowsDown {
	
	private static int N;
	private static PriorityQueue<Integer> time = new PriorityQueue<Integer>();
	private static PriorityQueue<Integer> distance = new PriorityQueue<Integer>();
	private static double currentTime = 0;
	private static double currentDistance = 0;
	private static int currentSpeed = 1; //time to go one meter

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			if (st.nextToken().charAt(0) == 'T') time.add(Integer.parseInt(st.nextToken()));
			else distance.add(Integer.parseInt(st.nextToken()));
		}
		
		while(currentDistance < 1000) {
			double nextT = Integer.MAX_VALUE;
			double nextD = Integer.MAX_VALUE;
			double nextT1 = Integer.MAX_VALUE;
			if (time.peek() != null && distance.peek() != null) {
				nextT = time.peek();
				nextD = distance.peek();
				nextT1 = (nextD - currentDistance) * currentSpeed + currentTime;
				double nextStopTime;
				if (nextT > nextT1) {
					double timeDifference = nextT1 - currentTime;
					currentTime = nextT1;
					currentDistance += timeDifference/ currentSpeed;
					distance.remove();
					currentSpeed++;
				} else if (nextT < nextT1) {
					double timeDifference = nextT - currentTime;
					currentTime = nextT;
					currentDistance += timeDifference / currentSpeed;
					time.remove();
					currentSpeed++;
				} else {
					double timeDifference = nextT - currentTime;
					currentTime = nextT;
					currentDistance += timeDifference / currentSpeed;
					distance.remove();
					time.remove();
					currentSpeed += 2;
				}
			} else if (time.peek() != null) {
				
				double nextStopTime = time.remove();
				double timeDifference = nextStopTime - currentTime;
				currentDistance += timeDifference / currentSpeed;
				currentTime = nextStopTime;
				currentSpeed++;			
				
			} else if (distance.peek() != null) {
				nextD = distance.remove();
				double nextStopTime = (nextD - currentDistance) * currentSpeed + currentTime;
				double timeDifference = nextStopTime - currentTime;
				currentDistance += timeDifference / currentSpeed;
				currentTime = nextStopTime;
				currentSpeed++;
			} else {
				double remainingDistance = 1000 - currentDistance;
				currentDistance = 1000;
				currentTime += remainingDistance * currentSpeed;
			}

		}
		
		System.out.println(Math.round(currentTime));
		
		
		

	}

}
