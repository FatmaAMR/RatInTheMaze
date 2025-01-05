package com.mycompany.osproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MazeRateGUI extends JFrame {
    private static final int CELL_SIZE = 20; // Size of each cell in pixels
    private char[][] maze;
    private JPanel mazePanel;
    private JTextField sizeField;
    private JButton generateButton, solveButton;
    private int mazeSize = 20; // Default maze size

    public MazeRateGUI() {
        setTitle("Maze Rate Problem");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Input for maze size
        sizeField = new JTextField(5);
        sizeField.setText(String.valueOf(mazeSize));

        generateButton = new JButton("Generate");
        solveButton = new JButton("Solve");

        generateButton.addActionListener(new GenerateAction());
        solveButton.addActionListener(new SolveAction());

        JPanel controlsPanel = new JPanel();
        controlsPanel.add(new JLabel("Maze Size:"));
        controlsPanel.add(sizeField);
        controlsPanel.add(generateButton);
        controlsPanel.add(solveButton);

        // Panel for dynamic visualization
        mazePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawMaze(g);
            }
        };
        mazePanel.setPreferredSize(new Dimension(400, 400));
        
        add(controlsPanel, BorderLayout.NORTH);
        add(mazePanel, BorderLayout.CENTER);

        initializeMaze();
        setVisible(true);
    }

    private void initializeMaze() {
        maze = Tools.MatrixGenerator(mazeSize); // Generate initial random maze
        mazePanel.repaint();
    }

    private void drawMaze(Graphics g) {
        if (maze == null) return;

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                switch (maze[i][j]) {
                    case '0':
                        g.setColor(Color.BLACK);
                        break;
                    case 'P':
                        g.setColor(Color.GREEN);
                        break;
                    case 'R':
                        g.setColor(Color.RED);
                        break;
                    case 'B':
                        g.setColor(Color.BLUE);
                        break;
                    case 'G':
                        g.setColor(Color.GREEN);
                        break;
                    default:
                        g.setColor(Color.WHITE);
                        break;
                }

                g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.GRAY);
                g.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    private class GenerateAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                mazeSize = Integer.parseInt(sizeField.getText());
                initializeMaze();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(MazeRateGUI.this, "Invalid size entered.");
            }
        }
    }

    private class SolveAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 4; i++) { 
                final Color threadColor = assignColor(i);
                new Thread(() -> {
                    try {
                        MazeSolverWithAnimation solver = new MazeSolverWithAnimation(maze, mazePanel, threadColor);
                        solver.solveMaze();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }, "SolverThread-" + i).start();
            }
        }
    }

    private Color assignColor(int index) {
        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA};
        return colors[index % colors.length];
    }

    public static void main(String[] args) {
        new MazeRateGUI();
    }
}


