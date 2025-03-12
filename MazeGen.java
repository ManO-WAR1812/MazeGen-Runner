import java.util.*;

public class MazeGen {
	private String[][] maze;
	private int width, height;
	private int startRow, startCol, endRow, endCol;
	private static final String WALL = "#";
	private static final String PATH = " ";

	public MazeGen(int width, int height) {
		this.width = width;
		this.height = height;
		this.maze = new String[height][width]; 
		mazeCreation(); // Automatically generate the maze
	}

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

	private void setStartAndEndPositions() {
		Random rand = new Random();

		// Select a random edge for the start position
		int startEdge = rand.nextInt(4); // 0 = top, 1 = bottom, 2 = left, 3 = right
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
	
	public String[][] getMaze() {
		return maze;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getStartCol() {
		return startCol;
	}

	public int getEndRow() {
		return endRow;
	}

	public int getEndCol() {
		return endCol;
	}

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


