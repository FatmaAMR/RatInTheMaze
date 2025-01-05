package com.mycompany.osproject;

import java.util.*;
import java.util.concurrent.*;


public class MazeSolverWithThreads {
    private char[][] maze;
    private boolean[][] visited;
    private final List<Pair> solutionPath = new ArrayList<>();
    private boolean found = false;

    public MazeSolverWithThreads(char[][] maze) {
        this.maze = maze;
        this.visited = new boolean[maze.length][maze[0].length];  // visited has the same dimensions as the maze, initially false
    }

    public void solveMaze() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(4); // Using thread pool (fixed thread pool of size 4)

        // Create an instance of BFSSolver
        BFSSolver bfsSolver = new BFSSolver(maze, visited, solutionPath, this);

        // Submit BFS task for starting position (0, 0)
        List<Future<Boolean>> futures = new ArrayList<>();

        // Use the bfsSolver instance to call the non-static BFS method
        futures.add(executor.submit(() -> bfsSolver.BFS(0, 0)));

        // Wait for completion
        for (Future<Boolean> future : futures) {
            try {
                if (future.get()) {
                    break;
                }
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            }
        }

        executor.shutdown();

        if (found) {
            System.out.println("Path found: " + solutionPath);
            printMazeWithPath();
        } else {
            System.out.println("No path found.");
        }
    }

    public void setFound(boolean found) {
        this.found = found; // Method to update the found flag
    }

    private void printMazeWithPath() {
        for (Pair p : solutionPath) {
            maze[p.x][p.y] = 'A';  // Mark the solution path
        }
        System.out.println("Maze with the solution path:");
        for (char[] row : maze) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}





