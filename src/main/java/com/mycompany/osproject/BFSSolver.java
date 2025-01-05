package com.mycompany.osproject;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;


public class BFSSolver {
    private final char[][] maze;
    private final boolean[][] visited;
    private final List<Pair> solutionPath;
    private final ReentrantLock lock = new ReentrantLock();  // Lock for synchronization
    private final MazeSolverWithThreads solver;

    public BFSSolver(char[][] maze, boolean[][] visited, List<Pair> solutionPath, MazeSolverWithThreads solver) {
        this.maze = maze;
        this.visited = visited;
        this.solutionPath = solutionPath;
        this.solver = solver; // Store the reference to the solver class
    }

    public boolean BFS(int startX, int startY) {
        Queue<Pair> queue = new LinkedList<>();
        Map<Pair, Pair> parentMap = new HashMap<>();
        queue.add(new Pair(startX, startY));
        visited[startX][startY] = true;

        int[][] directions = {{1, 0}, {0, 1}};  // Allow movement only down and right

        while (!queue.isEmpty()) {
            Pair current;

            // Lock the queue access
            lock.lock();
            try {
                current = queue.poll();
                if (current == null) continue;  // Double-check for empty queue
            } finally {
                lock.unlock();
            }

            System.out.println("Visiting: (" + current.x + "," + current.y + ")");

            // Reached the destination (bottom-right)
            if (current.x == maze.length - 1 && current.y == maze[0].length - 1) {
                Pair currentPos = current;
                while (currentPos != null) {
                    solutionPath.add(currentPos);
                    currentPos = parentMap.get(currentPos);
                }
                Collections.reverse(solutionPath);
                solver.setFound(true); // Update the found flag in the solver
                return true;
            }

            for (int[] dir : directions) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];

                if (newX >= 0 && newX < maze.length && newY >= 0 && newY < maze[0].length
                        && maze[newX][newY] == '1' && !visited[newX][newY]) {

                    lock.lock();
                    try {
                        if (!visited[newX][newY]) {  // Double-check under the lock
                            visited[newX][newY] = true;
                            Pair next = new Pair(newX, newY);
                            parentMap.put(next, current);
                            queue.add(next);
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        return false;  // No path found
    }
}
