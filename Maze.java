import java.util.*;

/**
 * Maze
 * The main program that handles the creation of mazes and the running of mazes.
 * It creates a maze using the {@code MazeGen} class and then solves it using different algorithms
 * implemented in the {@code MazeRun} class.
 */
public class Maze {
	
	
	/**
	 * The main method that intializes, generates the maze, and runs the "racing" algorithms.
	 * Also contains an if-statement that checks whether or not the maze width/height is too big to show on terminal.
	 * For best results, make the parameters odd.
	 */
	public static void main(String[] args) {
		MazeGen generator = new MazeGen(51, 51);
		generator.mazeCreation();
		
		// Get the generated maze and its dimensions
		String[][] generatedMaze = generator.getMaze();
		int rows = generatedMaze.length;
		int cols = generatedMaze[0].length;
		
		
		// Check if the maze is small enough to print
		if (rows <= 100 && cols <= 100) {
			generator.printMaze();
		} else {
			System.out.println("Maze is too large to display.");
		}
		// Get the start and end positions
		int startX = generator.getStartRow();
		int startY = generator.getStartCol();
		int endX = generator.getEndRow();
		int endY = generator.getEndCol();
		// Solve the maze
		MazeRun wfRunner = new MazeRun(generatedMaze, startX, startY, endX, endY);
		wfRunner.runWallFollower();
		
		MazeRun deRunner = new MazeRun(generatedMaze, startX, startY, endX, endY);
		deRunner.runDeadEnd();
		
		MazeRun djkRunner = new MazeRun(generatedMaze, startX, startY, endX, endY);
		djkRunner.runDijkstra();
	}
	
	
}
