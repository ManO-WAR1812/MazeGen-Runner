import java.util.*;

public class Maze {
	public static void main(String[] args) {
		MazeGen generator = new MazeGen(15000, 15000);
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
