import java.util.*;
@SuppressWarnings("unchecked")
public class KnightsOfNi {
	
	public static class Point {
		int row;
		int col;
		public Point (int r, int c) {
			row = r;
			col = c;
		}
	}
	
	private static int W, H;
	private static int[][] matrix;
	private static boolean[][] visited;
	private static int[][] distanceFromB;
	private static int[][] distanceFromK;
	private static int Br, Bc, Kr, Kc;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		W = in.nextInt();
		H = in.nextInt();
		matrix = new int[H + 1][W + 1];
		visited = new boolean[H + 1][W + 1];
		distanceFromB = new int[H + 1][W + 1];
		distanceFromK = new int[H + 1][W + 1];
		
		//reading input and keep track of where Bessie and the Knights locations
		for (int i = 1; i <= H; i++) {
			for (int j = 1; j <= W; j++) {
				matrix[i][j] = in.nextInt();
				if (matrix[i][j] == 2) {
					Br = i;
					Bc = j;
				} else if (matrix[i][j] == 3) {
					Kr = i;
					Kc = j;
				}
			}
		}
		
		//BFS from Bessie to all of the shrubs
		Queue<Point> q = new LinkedList<Point>();
		distanceFromB[Br][Bc] = 0;
		Point start = new Point(Br, Bc);
		q.add(start);
		while (!q.isEmpty()) {
			Point next = q.remove();
			int currentRow = next.row;
			int currentCol = next.col;
			if (visited[currentRow][currentCol]) continue;
			visited[currentRow][currentCol] = true;
			if (check(currentRow + 1, currentCol)){
				distanceFromB[currentRow + 1][currentCol] = distanceFromB[currentRow][currentCol] + 1;
				q.add(new Point(currentRow + 1, currentCol));
			} 
			if (check(currentRow - 1, currentCol)){
				distanceFromB[currentRow - 1][currentCol] = distanceFromB[currentRow][currentCol] + 1;
				q.add(new Point(currentRow - 1, currentCol));
			} 
			if (check(currentRow, currentCol + 1)){
				distanceFromB[currentRow][currentCol + 1] = distanceFromB[currentRow][currentCol] + 1;
				q.add(new Point(currentRow, currentCol + 1));
			} 
			if (check(currentRow, currentCol - 1)){
				distanceFromB[currentRow][currentCol - 1] = distanceFromB[currentRow][currentCol] + 1;
				q.add(new Point(currentRow, currentCol - 1));
			} 
			
		}
		
		
		
		//BFS from Knights to all of the shrubs
		int minDistance = Integer.MAX_VALUE;
		visited = new boolean[H + 1][W + 1];
		q = new LinkedList<Point>();
		Point start1 = new Point(Kr, Kc);
		q.add(start1);
		while (!q.isEmpty()) {
			Point next = q.remove();
			int currentRow = next.row;
			int currentCol = next.col;
			if (visited[currentRow][currentCol]) continue;
			visited[currentRow][currentCol] = true;
			if (matrix[currentRow][currentCol] == 4 && distanceFromB[currentRow][currentCol] != 0) {
					minDistance = Math.min(minDistance, distanceFromK[currentRow][currentCol] + distanceFromB[currentRow][currentCol]);
				
			}
			if (check1(currentRow + 1, currentCol)){
				distanceFromK[currentRow + 1][currentCol] = distanceFromK[currentRow][currentCol] + 1;
				q.add(new Point(currentRow + 1, currentCol));
			} 
			if (check1(currentRow - 1, currentCol)){
				distanceFromK[currentRow - 1][currentCol] = distanceFromK[currentRow][currentCol] + 1;
				q.add(new Point(currentRow - 1, currentCol));
			} 
			if (check1(currentRow, currentCol + 1)){
				distanceFromK[currentRow][currentCol + 1] = distanceFromK[currentRow][currentCol] + 1;
				q.add(new Point(currentRow, currentCol + 1));
			} 
			if (check1(currentRow, currentCol - 1)){
				distanceFromK[currentRow][currentCol - 1] = distanceFromK[currentRow][currentCol] + 1;
				q.add(new Point(currentRow, currentCol - 1));
			} 
		}
		
		System.out.println(minDistance);
	}
	
	
	
	
	public static boolean check(int row, int col) {
		if (row == 0 || row > H || col == 0 || col > W) return false;
		if (visited[row][col] == true) return false;
		if (matrix[row][col] == 1 || matrix[row][col] == 3) return false;
		return true;
	}
	
	public static boolean check1(int row, int col) {
		if (row == 0 || row > H || col == 0 || col > W) return false;
		if (visited[row][col] == true) return false;
		if (matrix[row][col] == 1) return false;
		return true;
	}
	

}
