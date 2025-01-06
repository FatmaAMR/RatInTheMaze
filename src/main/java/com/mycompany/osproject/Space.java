/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.osproject;

public class Space {
    int dimension;
    char[][] Maze;
    Rat Rat;
    Space(int dimension){
        this.dimension = dimension;
        this.Maze = Tools.MatrixGenerator(dimension);
        Rat = new Rat();
    }
}
