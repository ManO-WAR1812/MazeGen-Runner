import java.util.*;

public class WallFollower {
	private int time;
	private String[][] maze;
	private int startX, startY, endX, endY;
	
	public WallFollower(String[][] maze, int startX, int startY, int endX, int endY) {
		this.maze = maze;
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.time = 0;
	}

	public int solveMaze() {
		long startTime = System.nanoTime(); // Start timing

		int[] direction = {0, 1}; // Initially moving right
		int x = startX, y = startY;

		while (x != endX || y != endY) {
			if (!maze[x][y].equals("S") && !maze[x][y].equals("E")) { // Don't replace start or end
				maze[x][y] = "."; // Mark the path
			}

			int[] left = turnLeft(direction);
			int newX = x + left[0];
			int newY = y + left[1];

			if (isValidMove(newX, newY)) {
				direction = left;
				x = newX;
				y = newY;
			} else if (isValidMove(x + direction[0], y + direction[1])) {
				x += direction[0];
				y += direction[1];
			} else {
				direction = turnRight(direction);
			}
		}

		// Ensure 'E' is not replaced
		long endTime = System.nanoTime(); // End timing

		time = (int) ((endTime - startTime) / 1_000_000); // Convert to milliseconds
		if (time == 0) { // If time is too small, show in microseconds
			System.out.println("Wall-Following Algorithm solved the maze in: " + (endTime - startTime) / 1_000 + " µs");
		} else {
			System.out.println("Wall-Following Algorithm solved the maze in: " + time + " ms");
		}
		return time;
	}




	private boolean isValidMove(int x, int y) {
		return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && !maze[x][y].equals("#");
	}

	private int[] turnLeft(int[] dir) {
		return new int[]{-dir[1], dir[0]};
	}

	private int[] turnRight(int[] dir) {
		return new int[]{dir[1], -dir[0]};
	}

	public int getTime() {
		return time;
	}
}
