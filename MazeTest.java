import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.*;
import java.lang.*;


public class MazeTest{
	
	private MazeGen generator;
	private String[][] generatedMaze;
	private int startX, startY, endX, endY;
	
	@BeforeEach
	void setUp() {
		generator = new MazeGen(51, 51); // Example maze size
		generator.mazeCreation();
		generatedMaze = generator.getMaze();
		startX = generator.getStartRow();
		startY = generator.getStartCol();
		endX = generator.getEndRow();
		endY = generator.getEndCol();
	}
	
	@Test
	public void testMazeGenerationNotNull(){
		assertNotNull(generatedMaze, "Generated maze should not be null");
	}
	
	@Test
	public void testMazeStartAndEndPositions() {
		assertTrue(startX >= 0 && startX < generatedMaze.length, "Start X is out of bounds");
		assertTrue(startY >= 0 && startY < generatedMaze[0].length, "Start Y is out of bounds");
		assertTrue(endX >= 0 && endX < generatedMaze.length, "End X is out of bounds");
		assertTrue(endY >= 0 && endY < generatedMaze[0].length, "End Y is out of bounds");
	}
	
	@Test
	public void testMazeTooSmallException() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new MazeGen(1, 1);
		});
		assertEquals("Maze dimensions must be greater than 1.", exception.getMessage());
	}
	
	@Test
	void testMazeSolvingAlgorithms() {
		MazeRun wfRunner = new MazeRun(generatedMaze, startX, startY, endX, endY);
		assertDoesNotThrow(wfRunner::runWallFollower, "Wall-Follower algorithm should run without exceptions");
		
		MazeRun deRunner = new MazeRun(generatedMaze, startX, startY, endX, endY);
		assertDoesNotThrow(deRunner::runDeadEnd, "Dead-End algorithm should run without exceptions");
		
		MazeRun djkRunner = new MazeRun(generatedMaze, startX, startY, endX, endY);
		assertDoesNotThrow(djkRunner::runDijkstra, "Dijkstra algorithm should run without exceptions");
	}
}
