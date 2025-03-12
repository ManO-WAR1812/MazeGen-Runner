import java.util.*;

public class DeadEnd{
	
	private String[][]  maze;
	private int startX, startY, endX, endY;
	private int time;
	
	public DeadEnd(String[][] maze, int startX, int startY, int endX, int endY) {
		this.maze = maze;
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.time = 0;
	}

	public int solveMaze() {
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
}
