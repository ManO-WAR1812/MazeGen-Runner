import java.util.*;

/**
 * Implements the Dead-End Filling Algorithm which is used to race other algorithms.
 * Fills out every dead end it encounters in the maze until it reaches the end of the maze.
 * Returns and stores the time it completed the maze.
 * @see https://en.wikipedia.org/wiki/Maze-solving_algorithm
 */
public class DeadEnd{
	
	private String[][]  maze;
	private int startX, startY, endX, endY;
	private int time;
	
	/**
	 * Constructs a DeadEnd solver for a given maze.
	 *
	 * @param maze   The maze represented as a 2D array of strings.
	 * @param startX The starting row index in the maze.
	 * @param startY The starting column index in the maze.
	 * @param endX   The ending row index in the maze.
	 * @param endY   The ending column index in the maze.
	 */
	public DeadEnd(String[][] maze, int startX, int startY, int endX, int endY) {
		this.maze = maze;
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.time = 0;
	}
	
	/**
	 * Solves the maze using the Dead-End Algorithm by removing dead ends iteratively.
	 * 
	 * @see https://stackoverflow.com/questions/9318534/creating-a-maze-solving-algorithm-in-java
	 * @return The time taken to solve the maze in milliseconds.
	 */
	public int solveMaze() {
		System.out.println();
		System.out.println("DEAD-END ALGORITHM: ");
		long startTime = System.nanoTime();
		
		boolean changes = true;
		while (changes) {
			changes = false;
			for (int i = 1; i < maze.length - 1; i++) {
				for (int j = 1; j < maze[0].length - 1; j++) {
					if (maze[i][j].equals(" ")) {
						int wallCount = 0;
						if (maze[i - 1][j].equals("#")) wallCount++;
						if (maze[i + 1][j].equals("#")) wallCount++;
						if (maze[i][j - 1].equals("#")) wallCount++;
						if (maze[i][j + 1].equals("#")) wallCount++;
						if (wallCount >= 3 && !(i == startX && j == startY) && !(i == endX && j == endY)) {
							maze[i][j] = "."; // Mark dead-end
							changes = true;
						}
					}
				}
			}
		}
		long endTime = System.nanoTime(); // End timing
		time = (int) ((endTime - startTime) / 1_000_000); // Convert to milliseconds
		if (time == 0) { // If time is too small, show in microseconds
			System.out.println("Dead-End Algorithm solved the maze in: " + (endTime - startTime) / 1_000 + " µs");
		} else {
			System.out.println("Dead-End Algorithm solved the maze in: " + time + " ms");
		}
		return time;
	}
	
	/**
	 * Getter for time
	 * @return The time it finishes the maze
	 */
	public int getTime() {
		return time;
	}
	
	/**
	 * Prints out the completed maze
	 */
	public void printSolvedMaze() {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
	}
}
