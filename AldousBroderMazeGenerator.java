import java.util.*;
import java.io.*;

/**
 * Modified version of @see https://github.com/jaalsh/java-maze-algorithms/blob/master/generator/AldousBroderGen.java
 */
public class AldousBroderMazeGenerator{
	private char [][] maze;
	private int rows, cols;
	private final char WALL = '#';
	private final char PATH = '□';
	private final Random rand = new Random();
	
	public AldousBroderMazeGenerator(int rows, int cols){
		this.rows = rows % 2 == 0 ? rows + 1 : rows;
		this.cols = cols % 2 == 0 ? cols + 1 : cols;
		this.maze = new char[this.rows][this.cols];
		
		for(int i = 0; i < rows; i++){ //Initializes the maze
			for(int ii = 0; ii < cols; ii++){
				maze[i][ii] = WALL;
			}
		}
	}
	public char[][] generateMaze() {
		int totalCells = (rows / 2) * (cols / 2); // Number of possible open spaces
		int visitedCells = 1;
		// Choose a random starting point in odd indices
		int row = randomOdd(rows);
		int col = randomOdd(cols);
		maze[row][col] = '□';
		
		while (visitedCells < totalCells) {
			List<int[]> neighbors = getValidNeighbors(row, col);
			
			if (!neighbors.isEmpty()) {
				int[] next = neighbors.get(rand.nextInt(neighbors.size()));
				int newRow = next[0];
				int newCol = next[1];
				
				maze[(row + newRow) / 2][(col + newCol) / 2] = '□'; // Remove wall between
				maze[newRow][newCol] = '□';
				
				visitedCells++;
				row = newRow;
				col = newCol;
			} else {
				int[] randomCell = getRandomVisitedCell();
				row = randomCell[0];
				col = randomCell[1];
			}
		}
		
		return maze;
	}
	
	private List<int[]> getValidNeighbors(int row, int col) {
		List<int[]> neighbors = new ArrayList<>();
		int[] dRow = {0, 0, -2, 2}; // Left, Right, Up, Down
		int[] dCol = {-2, 2, 0, 0};
		
		for (int i = 0; i < 4; i++) {
			int newRow = row + dRow[i];
			int newCol = col + dCol[i];
			
			if (isValid(newRow, newCol)) {
				neighbors.add(new int[]{newRow, newCol});
			}
		}
		
		return neighbors;
	}
	
	private boolean isValid(int row, int col) {
		return row > 0 && row < rows - 1 && col > 0 && col < cols - 1 && maze[row][col] == '#';
	}
	
	private int[] getRandomVisitedCell() {
		int row, col;
		do {
			row = randomOdd(rows);
			col = randomOdd(cols);
		} while (maze[row][col] != '□');
		return new int[]{row, col};
	}
	private int randomOdd(int limit) {
		return (rand.nextInt((limit - 1) / 2) * 2) + 1;
	}
}
