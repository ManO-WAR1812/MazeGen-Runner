import java.util.*;
import java.io.*;

/**
 * MazeGen
 * Generates and defines randomly generated maze.
 * Made using Wilson's Algorithm
 * @author John Vincent Postrano
 */
public class Maze{
	private char [][] maze;
	private int rows, cols;
	private int startRow, startCol, endRow, endCol;
	
	/**
	 * Default Constructor
	 * Creates an empty maze. All walls.
	 */
	public Maze(int rows, int cols){
		this.rows = rows;
		this.cols = cols;
		AldousBroderMazeGenerator generator = new AldousBroderMazeGenerator(rows, cols);
		this.maze = generator.generateMaze();
		
		
	}
	
	/**
	 * Prints Maze
	 */
	public void printMaze(){
		for(char[] row : maze){
			System.out.println(new String(row));
		}
	}
	
	/**
	 * Sets starting point of maze.
	 */
	public void setStart(int row, int col){
		if(!isValidPoint(row, col)) throw new IllegalArgumentException("Row num or Col num not allowed"); //Might change into catch-throw
		
		Random rand = new Random();
		int[] startPos = getRandPos(rand);
		
		startRow = startPos[0];
		startCol = startPos[1];
		maze[startRow][startCol] = 'S';
	}
	
	/**
	 * Sets end point of maze.
	 */
	public void setEnd(int row, int col){
		if(!isValidPoint(row, col)) throw new IllegalArgumentException("Row num or Col num not allowed"); //Might change into catch-throw
		
		Random rand = new Random();
		int[] endPos = getRandPos(rand);
		
		endRow = endPos[0];
		endCol = endPos[1];
		maze[endRow][endCol] = 'E';
	}
	
	private int[] getRandPos(Random rand){
		int row = 0, col = 0;
		boolean found = false;
		
		while(!found){
			int side = rand.nextInt(4);
			switch(side){
				case 0: row = 1; col = rand.nextInt(cols - 2); break;
				case 1: row = rows - 2; col = rand.nextInt(cols - 2); break;
				case 2: col = 1; row = rand.nextInt(rows - 2); break;
				case 3: col = cols - 2; row = rand.nextInt(rows - 2); break;
			}
			if (maze[row][col] == '□') found = true;
		}
		return new int[]{row, col};
	}
	
	/**
	 * isValidPoint
	 * Gives true or false if spot is valid
	 * Used in setStart and setEnd
	 */
	private boolean isValidPoint(int row, int col){
		if(row >= 0 && row < rows && col >= 0 && col < cols){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Creates the maze file
	 */
	public void createFile(String filename){
		try(FileOutputStream fos = new FileOutputStream(new File(filename))){
			for(char [] row : maze){
				fos.write(new String(row).getBytes());
				fos.write('\n');
			}
			System.out.println("File " + filename + " successfully saved!");
		} catch(IOException e){
			System.out.println("Error saving maze: " + e.getMessage());
		}
	}
	
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		
		System.out.print("Add New File Name? ");
		String filename = input.nextLine();
		
		System.out.println("Add number of rows: ");
		int rows = input.nextInt();
		
		System.out.println("Add number of columns: ");
		int cols = input.nextInt();
		
		Maze maze = new Maze(rows, cols);
		maze.printMaze();
		maze.createFile(filename);
	}
}
