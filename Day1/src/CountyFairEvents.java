import java.util.*;
public class CountyFairEvents {
	
	public static class event implements Comparable<event>{
		int start;
		int end;
		public event (int s, int e) {
			start = s;
			end = e;
		}
		
		public int compareTo (event e) {
			return end - e.end; //might need to change
		}
		
	}

	private static int N; 
	public static void main(String[] args) {
		int currentTime = 0;
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		PriorityQueue<event> endTimes = new PriorityQueue<event>();
		for (int i = 1; i<= N; i++) {
			int start = in.nextInt();
			int duration = in.nextInt();
			endTimes.add(new event(start, start + duration));
		}
		
		int counter = 0;
		while(!endTimes.isEmpty()) {
			event nextEvent = endTimes.remove();
			if (nextEvent.start == 1 && currentTime == 0) { //the first event FJ will attend
				currentTime = nextEvent.end;
//				System.out.println(nextEvent.start + " " + nextEvent.end);
				counter++;
				continue;
			}
			if (nextEvent.start < currentTime) { //FJ cannot attend this event since it already started
				continue;
			} else { //FJ attends this event
				currentTime = nextEvent.end;
//				System.out.println(nextEvent.start + " " + nextEvent.end);
				counter++;
			}
		}
		
		System.out.println(counter);
		

	}

}
