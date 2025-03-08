import java.util.*;
import java.io.*;

public class WilsonAlg{
	
	private char maze [][];
	private int rows, cols;
	private final char WALL = '#';
	private final char PATH = '□';
	private final Random rand = new Random();
	
	/**
	 * Default Constructor
	 */
	public WilsonAlg(int rows, int cols){
		this.rows = rows;
		this.cols = cols;
		this.maze = new char[rows][cols];
		
		for(int i = 0; i < rows; i++){ //Creates the maze
			for(int ii = 0; ii < cols; ii++){
				maze[i][ii] = WALL;
			}
		}
	} 
}
