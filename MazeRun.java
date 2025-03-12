import java.util.*;

public class MazeRun {
	private int time;
	private String[][] maze;
	private int startX, startY, endX, endY;

	public MazeRun(String[][] maze, int startX, int startY, int endX, int endY) {
		this.maze = maze;
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.time = 0;
	}

	public void runWallFollower() {
		WallFollower solver = new WallFollower(maze, startX, startY, endX, endY);
		
		this.time = solver.solveMaze(); // Solve first, then store the correct time
		//printSolvedMaze(); // Print the maze with the path marked
	}
	
	public void runDeadEnd(){
		DeadEnd solver = new DeadEnd(maze, startX, startY, endX, endY);
		
		this.time = solver.solveMaze();
		//printSolvedMaze();
	}
	
	public void runDijkstra(){
		Dijkstra solver = new Dijkstra(maze, startX, startY, endX, endY);
		this.time = solver.solveMaze();
		//printSolvedMaze();
	}
	
	public void printSolvedMaze() {
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

	public int getTime() {
		
		return time;
	}
}
