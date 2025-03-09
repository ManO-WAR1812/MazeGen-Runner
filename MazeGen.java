import java.util.*;
import java.io.*;

public class MazeGen{
	
	private String [][] maze;
	private int row, col;
	private static final int START_POS = 0;
	private static final int END_POS = 0;
	private static final String WALL = "#";
	private static final String PATH = " ";
	
	/**
	 * Empty Constructor
	 */
	public MazeGen(){
		row = 0;
		col = 0;
		maze = new String[row][col];
	}
	
	public MazeGen(int row, int col){
		this.row = row;
		this.col = col;
		this.maze = new String[row][col];
	}
	
	public void mazeCreation(){
		for(int i = 0; i < row; i++){
			for(int j = 0; j < col; j++){
				maze[i][j] = WALL;
			}
		}
		
		
	}
	
	public void printMaze(){//Used for testing
		for(int i = 0; i < row; i++){
			for(int j = 0; j < col; j++){
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
}
