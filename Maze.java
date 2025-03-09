import java.util.*;
import java.io.*;

public class Maze{
	
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		String input;
		System.out.println("Enter commands (type 'exit' to quit):");
		
		while(true){
			System.out.print("> ");
			input = scanner.nextLine();
			
			if(input.equalsIgnoreCase("exit")) break;
			
			//Add mazeGeneration() here
		}
		
		System.out.println("Exit Program...");
		scanner.close();
	}
	
	public void mazeGeneration(){
		
	}
	
	public void mazeRunning(){
		
	}
}
