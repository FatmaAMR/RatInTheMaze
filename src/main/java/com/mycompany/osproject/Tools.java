/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.osproject;

import java.util.LinkedList;
import java.util.Queue;

public class Tools {
public static char[][] RandomlyFiller(char[][] matrix, int dimension) {
    for (int i = 0; i < dimension; i++) {
        for (int j = 0; j < dimension; j++) {
            int randomNumber = (int) (Math.random() * 101); 
            if (randomNumber < 50)
                matrix[i][j] = '1'; 
            else
                matrix[i][j] = '0'; 
        }
    }
    matrix[0][0] = '1';  // Starting point
    matrix[dimension - 1][dimension - 1] = '1';  // End point
    return matrix;
}


    public static char[][] MatrixGenerator(int dimension) {
        char[][] matrix;
        do {
            matrix = new char[dimension][dimension];
            RandomlyFiller(matrix, dimension);
            
        } while (!isMazeSolvable(matrix, dimension)); // To regenerate if the maze is locked
        return matrix;
    }

    public static void MatrixPrinter(char[][] matrix, int dimension) {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean isMazeSolvable(char[][] matrix, int dimension) {
        // Use Breadth-First Search (BFS) to check if there's a path
        boolean[][] visited = new boolean[dimension][dimension];
        Queue<int[]> queue = new LinkedList<>();
        int[] rowDir = {1, 0}; // Down, Right
        int[] colDir = {0, 1};

        // Start BFS from the top-left cell
        queue.add(new int[] {0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            // Check if we've reached the destination
            if (row == dimension - 1 && col == dimension - 1) {
                return true;
            }

            // Explore neighbors
            for (int i = 0; i < 2; i++) { // Only down and right
                int newRow = row + rowDir[i];
                int newCol = col + colDir[i];

                if (newRow >= 0 && newRow < dimension && newCol >= 0 && newCol < dimension
                        && !visited[newRow][newCol] && matrix[newRow][newCol] == '1') {
                    queue.add(new int[] {newRow, newCol});
                    visited[newRow][newCol] = true;
                }
            }
        }

        // If we exhaust all possibilities and can't reach the destination
        return false;
    }
}
