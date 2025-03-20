import java.util.*;

/**
 * Class that implements the Wall-Following Algorithm.
 * Follows the left side of the wall till it reaches the end point.
 * 
 * @see https://en.wikipedia.org/wiki/Maze-solving_algorithm
 */
public class WallFollower {
	private int time;
	private String[][] maze;
	private int startX, startY, endX, endY;
	
	
	/**
	 * Constructs a {@code WallFollower} object with the given maze and start/end positions.
	 *
	 * @param maze   The 2D array representing the maze structure.
	 * @param startX The row index of the starting position.
	 * @param startY The column index of the starting position.
	 * @param endX   The row index of the ending position.
	 * @param endY   The column index of the ending position.
	 */
	public WallFollower(String[][] maze, int startX, int startY, int endX, int endY) {
		this.maze = maze;
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.time = 0;
	}
	
	/**
	 * Solves the maze using the Wall-Following Algorithm.
	 * The method tracks the time taken to solve the maze and prints the result.
	 * Also contains an if-statement to check if time was too quick to solve; will display in microseconds.
	 * 
	 * @see https://stackoverflow.com/questions/9318534/creating-a-maze-solving-algorithm-in-java
	 * @return The time taken (in milliseconds) to solve the maze.
	 */
	public int solveMaze() {
		System.out.println();
		System.out.println("WALL-FOLLOWER ALGORITHM: ");
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
		long endTime = System.nanoTime(); // End timing
		
		time = (int) ((endTime - startTime) / 1_000_000); // Convert to milliseconds
		if (time == 0) { // If time is too small, show in microseconds
			System.out.println("Wall-Following Algorithm solved the maze in: " + (endTime - startTime) / 1_000 + " µs");
			
		} else {
			System.out.println("Wall-Following Algorithm solved the maze in: " + time + " ms");
			
		}
		return time;
	}
	
	/**
	 * Checks if the given position is a valid move within the maze.
	 *
	 * @param x The row index to check.
	 * @param y The column index to check.
	 * @return {@code true} if the move is valid (not a wall and within bounds), otherwise {@code false}.
	 */
	private boolean isValidMove(int x, int y) {
		return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && !maze[x][y].equals("#");
	}
	
	/**
	 * Rotates the current direction 90 degrees to the left.
	 *
	 * @param dir The current direction vector.
	 * @return The new direction vector after turning left.
	 */
	private int[] turnLeft(int[] dir) {
		return new int[]{-dir[1], dir[0]};
	}
	
	/**
	 * Rotates the current direction 90 degrees to the right.
	 *
	 * @param dir The current direction vector.
	 * @return The new direction vector after turning right.
	 */
	private int[] turnRight(int[] dir) {
		return new int[]{dir[1], -dir[0]};
	}
	
	/**
	 * Gets the time it finishes the maze.
	 * @return The time it finishes
	 */
	public int getTime() {
		return time;
	}
}
