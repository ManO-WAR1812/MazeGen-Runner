import java.util.*;

public class Maze {
	public static void main(String[] args) {
		MazeGen generator = new MazeGen(10000, 10000);
		generator.mazeCreation();
		//generator.printMaze();

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
		
		MazeRun djkRunner = new MazeRun(generatedMaze, startX, startY, endX, endY);
		djkRunner.runDijkstra();
	}
}

