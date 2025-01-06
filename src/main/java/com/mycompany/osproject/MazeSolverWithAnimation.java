package com.mycompany.osproject;

import java.awt.Color;
import javax.swing.*;
import java.util.*;

public class MazeSolverWithAnimation {
    private final char[][] maze;
    private final JPanel mazePanel;
    private final boolean[][] visited;
    private final Color threadColor; // Assigned color for each thread during visualization

    public MazeSolverWithAnimation(char[][] maze, JPanel mazePanel, Color threadColor) {
        this.maze = maze;
        this.mazePanel = mazePanel;
        this.visited = new boolean[maze.length][maze[0].length];
        this.threadColor = threadColor; // Set the thread's unique color
    }

    public void solveMaze() throws InterruptedException {
        Queue<Pair> queue = new LinkedList<>();
        Map<Pair, Pair> parentMap = new HashMap<>(); // Track parent of each cell

        queue.add(new Pair(0, 0));
        visited[0][0] = true;

        // Restrict movement to right and down only
        int[][] directions = { {0, 1}, {1, 0} }; // Right and Down movements only

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            
            // Update GUI with the thread's unique path visualization
            maze[current.x][current.y] = mapColorToChar(threadColor);
            SwingUtilities.invokeLater(() -> mazePanel.repaint());
            Thread.sleep(100); // Pause for animation effect

            // Reset the cell after animation for visualization purposes
            maze[current.x][current.y] = '1';
            SwingUtilities.invokeLater(() -> mazePanel.repaint());

            // If destination is reached, backtrack to show solution path
            if (current.x == maze.length - 1 && current.y == maze[0].length - 1) {
                markSolutionPath(current, parentMap);
                JOptionPane.showMessageDialog(mazePanel, "Path found!");
                return;
            }

            for (int[] dir : directions) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];

                if (isValidMove(newX, newY)) {
                    visited[newX][newY] = true;
                    queue.add(new Pair(newX, newY));
                    parentMap.put(new Pair(newX, newY), current);
                }
            }
        }

        JOptionPane.showMessageDialog(mazePanel, "No path found.");
    }

    private void markSolutionPath(Pair end, Map<Pair, Pair> parentMap) throws InterruptedException {
        Pair current = end;

        while (current != null) {
            maze[current.x][current.y] = 'P'; // Mark path as 'P' for solution visualization
            SwingUtilities.invokeLater(() -> mazePanel.repaint());
            Thread.sleep(50); // Animation delay for marking the path
            current = parentMap.get(current);
        }
    }

    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == '1' && !visited[x][y];
    }

    private char mapColorToChar(Color color) {
        if (color == Color.RED) return 'R';
        if (color == Color.BLUE) return 'B';
        if (color == Color.GREEN) return 'G';
        if (color == Color.MAGENTA) return 'M';
        return '1';
    }
}

