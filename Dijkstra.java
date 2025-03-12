import java.util.*;

public class Dijkstra {
	
	private String[][] maze;
	private int startX, startY, endX, endY;
	private int time;
	private int[][] distance;
	private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Right, Down, Left, Up

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

	public int solveMaze() {
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

	// ✅ Debugging: Print final distance
	System.out.println("Final distance to goal: " + distance[endX][endY]);

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
		System.out.println("Dijkstra Algorithm failed: No path found.");
		return -1;
	}

	// ✅ If we get here, the path was successfully found
	markShortestPath();

	long endTime = System.nanoTime(); // End timing
	time = (int) ((endTime - startTime) / 1_000_000); // Convert to milliseconds
	if (time == 0) { // If time is too small, show in microseconds
		System.out.println("Dijkstra Algorithm solved the maze in: " + (endTime - startTime) / 1_000 + " µs");
	} else {
		System.out.println("Dijkstra Algorithm solved the maze in: " + time + " ms");
	}
	return time;
}


	private void markShortestPath() {
		if (distance[endX][endY] == Integer.MAX_VALUE) {
			System.out.println("No valid path found!");
			return;
		}

		int x = endX, y = endY;
		while (x != startX || y != startY) {
			int minDist = distance[x][y];
			int[] nextMove = null;

			for (int[] dir : directions) {
				int newX = x + dir[0];
				int newY = y + dir[1];

				// ✅ First ensure newX and newY are in bounds before accessing distance
				if (newX >= 0 && newX < maze.length && newY >= 0 && newY < maze[0].length) {
					if (isValidMove(newX, newY) && distance[newX][newY] < minDist) {
						minDist = distance[newX][newY];
						nextMove = dir;
					}
				}
			}

			// ✅ Prevent crash if no valid move is found
			if (nextMove == null) {
				System.out.println("Error: No valid move found during backtracking.");
				return;
			}

			x -= nextMove[0];
			y -= nextMove[1];

			if (!(x == startX && y == startY) && !(x == endX && y == endY)) {
				maze[x][y] = ".";
			}
		}
	}

	private boolean isValidMove(int x, int y) {
		return x >= 0 && x < maze.length 
		    && y >= 0 && y < maze[0].length 
		    && !maze[x][y].equals("#") 
		    && distance[x][y] != Integer.MAX_VALUE; // ✅ Ensure cell was visited
	}

	public int getTime() {
		return time;
	}

	public void printSolvedMaze() {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static class Node implements Comparable<Node> { 
		int x, y, distance; 

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
