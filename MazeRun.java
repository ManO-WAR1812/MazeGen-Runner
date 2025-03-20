import java.util.*;

/**
 * Runs the "racing" algorithms
 * Houses the Wall-Follower algorithm, Dead-End, and Dijkstra algorithms.
 */
public class MazeRun {
	private int time;
	private String[][] maze;
	private int startX, startY, endX, endY;
	
	/**
	 * Constructs a MazeRun object with a given maze and start/end positions.
	 * 
	 * @param maze   The maze represented as a 2D array of strings.
	 * @param startX The starting row index.
	 * @param startY The starting column index.
	 * @param endX   The ending row index.
	 * @param endY   The ending column index.
	 * @throws IAE if maze is null or start/end positions are less than 0.
	 */
	public MazeRun(String[][] maze, int startX, int startY, int endX, int endY) {
		if(maze == null || maze.length == 0 || maze[0].length == 0) {
			throw new IllegalArgumentException("Maze cannot be null or empty.");
		}
		if(startX < 0 || startX >= maze.length || startY < 0 || startY >= maze[0].length) {
			throw new IllegalArgumentException("Start position is out of bounds.");
		}
		if(endX < 0 || endX >= maze.length || endY < 0 || endY >= maze[0].length) {
			throw new IllegalArgumentException("End position is out of bounds.");
		}
		
		this.maze = maze;
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.time = 0;
	}
	
	/**
	 * Runs the Wall-Follower algorithm object/class.
	 * Also stores the time it completes the maze.
	 */
	public void runWallFollower() {
		WallFollower solver = new WallFollower(maze, startX, startY, endX, endY);
		
		this.time = solver.solveMaze(); // Solve first, then store the correct time
		printSolvedMaze(); // Print the maze with the path marked
	}
	
	/**
	 * Runs the Dead-End algorithm object/class.
	 * Stores the time it completes the maze.
	 */
	public void runDeadEnd(){
		DeadEnd solver = new DeadEnd(maze, startX, startY, endX, endY);
		
		this.time = solver.solveMaze();
		printSolvedMaze();
	}
	
	/**
	 * runs the Dijkstra algorithm object/class.
	 * Stores the time it completes the maze.
	 */
	public void runDijkstra(){
		Dijkstra solver = new Dijkstra(maze, startX, startY, endX, endY);
		this.time = solver.solveMaze();
		printSolvedMaze();
	}
	
	/**
	 * Prints out the solved maze.
	 */
	public void printSolvedMaze() {
		//Checks if maze is too big
		if (maze.length > 100 || maze[0].length > 100) {
			System.out.println("Maze is too large to display.");
			return;
		}
		
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (i == startX && j == startY) {
					System.out.print("S ");
				} else if (i == endX && j == endY) {
					System.out.print("E ");
				} else {
					System.out.print(maze[i][j] + " ");
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * Getter for the time it completes the maze.
	 * @return The time an algorithm finishes the maze.
	 */
	public int getTime() {
		
		return time;
	}
}
