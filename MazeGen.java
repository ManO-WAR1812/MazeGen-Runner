import java.util.*;


/**
 * MazeGen
 * Responsible for creating a randomly-generated maze. Used a "Randomized Depth-First Search" with recursion for the maze generation.
 * @see https://en.wikipedia.org/wiki/Maze_generation_algorithm
 */
public class MazeGen {
	private String[][] maze;
	private int width, height;
	private int startRow, startCol, endRow, endCol;
	private static final String WALL = "#";
	private static final String PATH = " ";
	
	/**
	 * Constructs a {@code MazeGen} object with the specified width and height.
	 * Automatically generates a maze upon instantiation.
	 *
	 * @param width  the width of the maze
	 * @param height the height of the maze
	 * @throws IAE if maze dimensions are less than or equal to 1.
	 */
	public MazeGen(int width, int height) {
		if (width <= 1 || height <= 1) {
			throw new IllegalArgumentException("Maze dimensions must be greater than 1.");
		}
		
		this.width = width;
		this.height = height;
		this.maze = new String[height][width]; 
		mazeCreation(); // Automatically generate the maze
	}
	
	/**
	 * Creates the maze.
	 * Contains the Randomized Depth-First Search algorithm.
	 * @see https://www.youtube.com/watch?v=u5WFob2KjpQ
	 * @see https://www.youtube.com/watch?v=bF4YGFNZIxM
	 */
	public void mazeCreation() {
		// Initialize all walls
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				maze[i][j] = WALL;
			}
		}
		
		Random rand = new Random();
		Stack<int[]> stack = new Stack<>();
		
		// Generate a random edge position for the start and end
		setStartAndEndPositions();
		maze[startRow][startCol] = PATH; 
		stack.push(new int[]{startRow, startCol});
		
		while (!stack.isEmpty()) {
			int[] current = stack.pop();
			int row = current[0];
			int col = current[1];
			
			int[][] neighbors = getUnvisitedNeighbors(row, col); 
			
			if (neighbors.length > 0) {
				stack.push(current); 
				// Choose a random neighbor
				int[] next = neighbors[rand.nextInt(neighbors.length)];
				int nextRow = next[0];
				int nextCol = next[1];
				// Break the wall between current and next cell
				maze[(row + nextRow) / 2][(col + nextCol) / 2] = PATH;
				maze[nextRow][nextCol] = PATH;
				stack.push(next); 
			}
		}
		// Ensure the end position is open
		maze[endRow][endCol] = PATH;
	}
	
	/**
	 * Randomly selects the "start" and "end" position of the maze.
	 * Ensures that the "start and "end" positions are on the edge the maze.
	 * @see https://www.youtube.com/watch?v=zsG2ceOlY6I
	 * @see https://algs4.cs.princeton.edu/41graph/Maze.java.html
	 */
	private void setStartAndEndPositions() {
		Random rand = new Random();
		// Select a random edge for the start position
		int startEdge = rand.nextInt(4);
		// 0 = top, 1 = bottom, 2 = left, 3 = right
		switch (startEdge) {
			case 0: startRow = 0; startCol = rand.nextInt(width); break;
			case 1: startRow = height - 1; startCol = rand.nextInt(width); break;
			case 2: startRow = rand.nextInt(height); startCol = 0; break;
			case 3: startRow = rand.nextInt(height); startCol = width - 1; break;
		}

		// Select a different edge for the end position
		int endEdge;
		do { endEdge = rand.nextInt(4); } while (endEdge == startEdge);

		switch (endEdge) {
			case 0: endRow = 0; endCol = rand.nextInt(width); break;
			case 1: endRow = height - 1; endCol = rand.nextInt(width); break;
			case 2: endRow = rand.nextInt(height); endCol = 0; break;
			case 3: endRow = rand.nextInt(height); endCol = width - 1; break;
		}
	}
	
	
	/*
	 * GETTERS
	 */
	 
	/**
	 * Maze getter
	 */
	public String[][] getMaze() {
		return maze;
	}
	
	/**
	 * Starting row getter
	 */
	public int getStartRow() {
		return startRow;
	}
	
	/**
	 * Starting column getter
	 */
	public int getStartCol() {
		return startCol;
	}
	
	/**
	 * Ending row getter
	 */
	public int getEndRow() {
		return endRow;
	}
	
	/**
	 * Ending column getter
	 */
	public int getEndCol() {
		return endCol;
	}
	
	/**
	 * Retrieves an array of unvisited neighboring cells.
	 * @param row the current row
	 * @param column of the current column
	 * @return a 2D array containing the coordinates of unvisited neighboring cells
	 * @see https://www.youtube.com/watch?v=u5WFob2KjpQ
	 * @see https://www.youtube.com/watch?v=bF4YGFNZIxM
	 */
	private int[][] getUnvisitedNeighbors(int row, int col) {
		List<int[]> neighbors = new ArrayList<>();
		if (row > 1 && maze[row - 2][col].equals(WALL)) {
			neighbors.add(new int[]{row - 2, col});
		}
		if (row < height - 2 && maze[row + 2][col].equals(WALL)) {
			neighbors.add(new int[]{row + 2, col});
		}
		if (col > 1 && maze[row][col - 2].equals(WALL)) {
			neighbors.add(new int[]{row, col - 2});
		}
		if (col < width - 2 && maze[row][col + 2].equals(WALL)) {
			neighbors.add(new int[]{row, col + 2});
		}
		return neighbors.toArray(new int[neighbors.size()][]);
	}
	
	/**
	 * Prints the maze onto the terminal
	 * It "retrieves" the maze that was created by printing it.
	 * Marks "S" for start point and "E" for the end point.
	 */
	public void printMaze() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i == startRow && j == startCol) System.out.print("S ");
				else if (i == endRow && j == endCol) System.out.print("E ");
				else System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
	}
	
}


