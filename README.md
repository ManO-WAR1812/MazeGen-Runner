# Personal/School Project - Maze Solver and Generator
Author: John Vincent Postrano

This project is a Java-based maze generator and solver, featuring multiple algorithms for both maze creation and pathfinding. It includes implementations of Randomized Depth-First Search, Dijkstra's algorithm, Wall-Follower algorithm, and the Dead-End filling method to solve and generate mazes efficiently.

## Features
<ul>
  <li><b>Maze Generation</b></li>
  <ul>
    <li>Randomized Depth-First Search w/ Recursion</li>
  </ul>
  <li><b>Maze Solving Algorithms</b></li>
  <ul>
    <li>Wall-Follwing Algorithm></li>
    <li>Dead-End Algorithm</li>
    <li>Djikstra's Algorithm</li>
  </ul>
</ul>

- **Performance Timing**: Measures execution time for each algorithm.
- **Maze Display**: Prints the maze, with start and end positions, unless the maze size exceeds 100x100.

## Files Overview

### `Maze.java`

The main entry point of the program. It:

- Generates a maze using `MazeGen.java`
- Runs different solving algorithms via `MazeRun.java`
- Prints the maze only if its size is within the display limit (100x100)

### `MazeGen.java`

Handles the procedural generation of a random maze. Key functionalities include:

- Initializing the maze grid with walls and pathways
- Using a maze generation algorithm to create a solvable structure
- Providing the generated maze along with start and end positions

### `MazeRun.java`

Manages the execution of maze-solving algorithms. Responsibilities:

- Runs the selected solving algorithm
- Measures the execution time
- Displays the solved maze (if within size constraints)

### `WallFollower.java`

Implements the **Wall-Follower Algorithm**, a simple maze-solving method that follows one wall (typically the right-hand rule) to navigate towards the exit.

### `DeadEnd.java`

Implements the **Dead-End Filling Algorithm**, which eliminates dead-end paths, leaving behind the correct solution.

- Fills out every dead-end in the maze till it reaches the end of the maze.

### `Dijkstra.java`

Uses **Dijkstra’s Algorithm** to find the shortest path from the start position to the exit.

- Attempts to find the shortest path of the maze.
- Maps every dead-end it encounters till it reaches the end. Once it does, it then uses that data to finish the maze.

## How to Run

1. **Set up Parameters**:<br>
  Edit MazeGen object in main method. For example, the parameters for this test is 21 for width and 21 for height.
<img width="345" alt="Screenshot 2025-03-13 at 1 45 15 PM" src="https://github.com/user-attachments/assets/e42476f5-32e5-4807-964c-c7a341913707" />

2. **Compile Files**:
   ```sh
   javac Maze.java MazeGen.java MazeRun.java WallFollower.java DeadEnd.java Dijkstra.java

3. **Run Maze**:
   ```sh
   java Maze.java

## Sample Output:
<img width="301" alt="Screenshot 2025-03-13 at 1 52 37 PM" src="https://github.com/user-attachments/assets/9851c751-01ef-4baf-8710-6ed8ea757626" />
<img width="369" alt="Screenshot 2025-03-13 at 1 52 53 PM" src="https://github.com/user-attachments/assets/18b4d314-e56e-4dd2-838a-79dc5b179c0d" />
<img width="344" alt="Screenshot 2025-03-13 at 1 53 11 PM" src="https://github.com/user-attachments/assets/fe91fc17-d19f-4407-8e30-61f16066b735" />
<img width="344" alt="Screenshot 2025-03-13 at 1 53 20 PM" src="https://github.com/user-attachments/assets/ecb13dc3-4170-4187-83e7-15c268a43a87" />





## Challenges

<ul>
  <li><b>Complexity</b></li>
  It was easy to get mixed up with the complexity of each component of program; therefore I created a structure within each class I made. This is particuraly found within the Maze-Solving Algorithms which, in each class, has a similar structure. Each class contains a constructor, a getter, and the method that solves the maze. By following this structure, I was able to write the code without the worry of other programs interfering with my code. Even if it did, I was able to find the problem easily and debug it.
  
  <li>Scope/Scale</li>
  Commonnly found within complex programs, I was able to keep in line with the scope of the project due to seperating the project in multiple parts. I divided the project into 3 main parts, the "main" maze program, the maze generation program, and the maze solving programs. By dividing up my work, I was able to plan out what components of the project to work on and prevented burn-out.
</ul>

## References
https://en.wikipedia.org/wiki/Maze_generation_algorithm
https://www.youtube.com/watch?v=u5WFob2KjpQ
https://www.youtube.com/watch?v=bF4YGFNZIxM
https://en.wikipedia.org/wiki/Maze-solving_algorithm
https://stackoverflow.com/questions/9318534/creating-a-maze-solving-algorithm-in-java
https://en.wikipedia.org/wiki/Maze-solving_algorithm
https://stackoverflow.com/questions/9318534/creating-a-maze-solving-algorithm-in-java
https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
