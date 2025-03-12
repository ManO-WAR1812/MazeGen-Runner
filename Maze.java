import java.util.*;

public class Maze {
	public static void main(String[] args) {
		MazeGen generator = new MazeGen(21, 21);
		generator.mazeCreation();
		generator.printMaze();

		// Get the generated maze and its start/end positions
		String[][] generatedMaze = generator.getMaze();
		int startX = generator.getStartRow();
		int startY = generator.getStartCol();
		int endX = generator.getEndRow();
		int endY = generator.getEndCol();

		// Solve the maze
		MazeRun wfRunner = new MazeRun(generatedMaze, startX, startY, endX, endY);
		wfRunner.runWallFollower();
		
		MazeRun deRunner = new MazeRun(generatedMaze, startX, startY, endX, endY);
		deRunner.runDeadEnd();
	}
}

