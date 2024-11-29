Rat in a Maze - Multithreading Maze Solver

The objective of the project is to simulate the movement of a rat through a maze and find the shortest path from the top-left corner to the bottom-right corner. The maze consists of 1s and 0s, where 1 represents a valid path, and 0 represents a dead-end.



Features:


Maze Generation: The maze is generated randomly with the option to specify its dimensions. The grid is filled with 1s and 0s where the start and end are always accessible.
Multithreading: The rat explores the maze using multiple threads. Each thread represents a possible path the rat could take. If a thread finds two possible directions, it creates a new thread to explore the second option, mimicking the rat's decision-making process.
Real-Time Updates: The maze solver updates the matrix in real time to show the path being taken by the rat.
Pathfinding: The program calculates the possible paths from the start to the destination, considering only valid moves (down and right).
Steps Involved:
Initialization: Generate a random maze based on user input for the maze dimensions.
Multithreaded Exploration: Use multithreading to explore the maze, simulating the rat’s movements through the maze.
Checking for Blocked Paths: The program ensures the maze does not have any isolated dead-ends or blocked routes that would make the maze unsolvable.
Path Calculation: Threads are managed to calculate all possible paths and ensure the shortest path is found.
Visualization: The path is displayed in real-time as the rat traverses the maze.
How It Works:
The rat starts from the upper-left corner ([0][0]) and tries to reach the lower-right corner ([N-1][N-1]).
Each thread explores possible paths in two directions: down and right.
If a thread encounters two potential directions, a new thread is spawned to handle the second direction, ensuring parallel exploration.
The algorithm stops once the rat reaches the destination or finds that the maze is blocked.


Requirements:
Java 8 or later: This project utilizes Java's built-in threading and random number generation features.
IDE: Any Java IDE (e.g., IntelliJ IDEA, Eclipse, or Visual Studio Code) with support for Java.
How to Use:
Clone the repository:
bash
Copy code
git clone https://github.com/FatmaAMR/rat-in-a-maze.git
Compile and run the project using your preferred IDE or command line:
Copy code
javac RatInMaze.java
java RatInMaze
Example Maze:
A 5x5 maze might look like this:

1 1 1 1 1
0 0 1 0 0
1 1 1 0 1
1 0 1 1 1
1 1 1 1 1


Where 1 is a valid path, and 0 represents a blocked cell. The program will try to find the shortest path from the top-left to the bottom-right.

