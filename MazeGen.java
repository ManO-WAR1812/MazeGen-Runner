import java.util.*;
import java.io.*;

public class MazeGen{
	
	private int [][] maze;
	private int length, width;
	private String space, wall;
	private static final int START_POS = 0;
	private static final int END_POS = 0;
	
	/**
	 * Empty Constructor
	 */
	public MazeGen(){
		length = 0;
		width = 0;
		maze = new int[length][width];
	}
	
	public MazeGen(int width, int height){
		this.width = width;
		this.length = length;
		this.maze = new int[length][width];
	}
	
	public void mazeCreation(){
		//Add algo here
	}
	
	
}
