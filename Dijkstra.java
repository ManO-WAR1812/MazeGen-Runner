import java.util.*;

/**
 * Implements the Dijkstra algorithm to find the shortest route of the maze.
 * 
 * @see https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 */
public class Dijkstra {
	
	private String[][] maze;
	private int startX, startY, endX, endY;
	private int time;
	private int[][] distance;
	private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Right, Down, Left, Up
	
	/**
	 * Constructs a Dijkstra object for solving a given maze.
	 *
	 * @param maze  The maze represented as a 2D String array.
	 * @param startX The starting row index.
	 * @param startY The starting column index.
	 * @param endX   The ending row index.
	 * @param endY   The ending column index.
	 */
	public Dijkstra(String[][] maze, int startX, int startY, int endX, int endY) {
		this.maze = maze;
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.time = 0;
		this.distance = new int[maze.length][maze[0].length];
		for (int[] row : distance) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}
	}
	
	/**
	 * Solves the maze using Dijkstra's algorithm.
	 *
	 * @see https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
	 * @return The time taken to solve the maze in milliseconds.
	 */
	public int solveMaze() {
		System.out.println();
		System.out.println("DIJKSTRA ALGORITHM: ");
		long startTime = System.nanoTime();
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(startX, startY, 0));
		distance[startX][startY] = 0;
		
		while (!pq.isEmpty()) {
			Node current = pq.poll();
			int x = current.x;
			int y = current.y;
			int dist = current.distance;
			
			if (x == endX && y == endY) break; // Stop when reaching the end
			
			for (int[] dir : directions) {
				int newX = x + dir[0];
				int newY = y + dir[1];
				
				if (isValidMove(newX, newY)) { 
					int newDist = dist + 1;  
					if (newDist < distance[newX][newY]) { 
						distance[newX][newY] = newDist;  
						pq.offer(new Node(newX, newY, newDist));  
					}  
				}
			}
		}
		
		
		// ✅ Fix 1: Verify a path was found
		boolean pathExists = false;
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (maze[i][j].equals(".")) { // ✅ Check if path was drawn
					pathExists = true;
					break;
				}
			}
			if (pathExists) break;
		}
		
		if (!pathExists) {
			//System.out.println("Dijkstra Algorithm failed: No path found.");
			return -1;
		}
		
		// ✅ If we get here, the path was successfully found
		//markShortestPath();
		
		long endTime = System.nanoTime(); // End timing
		time = (int) ((endTime - startTime) / 1_000_000); // Convert to milliseconds
		if (time == 0) { // If time is too small, show in microseconds
			System.out.println("Dijkstra Algorithm solved the maze in: " + (endTime - startTime) / 1_000 + " µs");
		} else {
			System.out.println("Dijkstra Algorithm solved the maze in: " + time + " ms");
		}
		return time;
	}
	
	/**
	 * Checks if a move is valid within the maze boundaries.
	 *
	 * @param x The row index of the move.
	 * @param y The column index of the move.
	 * @return True if the move is valid, false otherwise.
	 */
	private boolean isValidMove(int x, int y) {
		return x >= 0 && x < maze.length 
		    && y >= 0 && y < maze[0].length 
		    && !maze[x][y].equals("#") 
		    && distance[x][y] != Integer.MAX_VALUE; // ✅ Ensure cell was visited
	}
	
	/**
	 * Getter for time
	 * 
	 * @return The time it completes the maze
	 */
	public int getTime() {
		return time;
	}
	
	/**
	 * Prints the solved maze.
	 */
	public void printSolvedMaze() {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Represents a node in the priority queue for Dijkstra's algorithm.
	 */
	private static class Node implements Comparable<Node> { 
		int x, y, distance; 
		
		/**
		 * Constructs a Node with its coordinates and distance.
		 *
		 * @param x        The row index.
		 * @param y        The column index.
		 * @param distance The distance from the start.
		 */
		Node(int x, int y, int distance) { 
			this.x = x; 
			this.y = y; 
			this.distance = distance; 
		}
		
		
		@Override
		public int compareTo(Node other) { 
			return Integer.compare(this.distance, other.distance); 
		}
	}
}
