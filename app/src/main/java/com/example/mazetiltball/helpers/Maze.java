package com.example.mazetiltball.helpers;


import android.graphics.RectF;

public class Maze {
    public RectF[] drawMazeOne(float mazeLeft, float mazeTop, float mazeRight, float mazeBottom) {
        MazeOne maze = new MazeOne();

        return maze.drawable(mazeLeft, mazeTop, mazeRight, mazeBottom);
    }

    public RectF[] drawMazeTwo(float mazeLeft, float mazeTop, float mazeRight, float mazeBottom) {
        MazeTwo maze = new MazeTwo();

        return maze.drawable(mazeLeft, mazeTop, mazeRight, mazeBottom);
    }
    public RectF[] drawMazeThree(float mazeLeft, float mazeTop, float mazeRight, float mazeBottom) {
        MazeThree maze = new MazeThree();

        return maze.drawable(mazeLeft, mazeTop, mazeRight, mazeBottom);
    }
    public RectF[] drawMazeFour(float mazeLeft, float mazeTop, float mazeRight, float mazeBottom) {
        MazeFour maze = new MazeFour();

        return maze.drawable(mazeLeft, mazeTop, mazeRight, mazeBottom);
    }
    public RectF[] drawMazeFive(float mazeLeft, float mazeTop, float mazeRight, float mazeBottom) {
        MazeFive maze = new MazeFive();

        return maze.drawable(mazeLeft, mazeTop, mazeRight, mazeBottom);
    }
}
