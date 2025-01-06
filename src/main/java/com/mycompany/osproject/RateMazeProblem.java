
package com.mycompany.osproject;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class RateMazeProblem {
    
   public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        Scanner scanner = new Scanner(System.in);
        
        // Ask the user to input the size of the maze
        System.out.print("Enter the size of the maze (NxN): ");
        int mazeSize = scanner.nextInt(); // Get the user-provided size
        
        if (mazeSize <= 0) {
            System.err.println("Maze size must be a positive integer.");
            return;
        }

        // Generate and print the maze
        char[][] x = Tools.MatrixGenerator(mazeSize);
        Tools.MatrixPrinter(x, mazeSize);

        // Solve the maze using multithreading
        MazeSolverWithThreads solver = new MazeSolverWithThreads(x);
        solver.solveMaze();
        
        scanner.close();
    }
}  


        
    
    
    


