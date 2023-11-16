package com.example.mazetiltball.helpers;

import android.graphics.RectF;

public class MazeTwo {

    // Define wall thickness
    int wallThickness = 20;

    int DEFAULT_PADDING = 100;

    public RectF[] drawable(float mazeLeft, float mazeTop, float mazeRight, float mazeBottom) {

        float left = 0;
        float right = 0;
        float bottom = 0;
        float top = 0;

        // Define walls
        RectF[] walls = new RectF[13];
        left = mazeLeft + DEFAULT_PADDING;
        top = mazeTop + DEFAULT_PADDING ;
        right = mazeLeft + DEFAULT_PADDING + wallThickness;
        bottom = mazeBottom - DEFAULT_PADDING ;

        walls[0] = new RectF(left, top, right, bottom);

        left = mazeLeft + DEFAULT_PADDING;
        top = mazeTop + DEFAULT_PADDING;
        right = mazeRight - DEFAULT_PADDING;
        bottom = mazeTop + DEFAULT_PADDING + wallThickness;

        walls[1] = new RectF(left, top, right, bottom);

        left = mazeLeft + DEFAULT_PADDING;
        top = mazeBottom - DEFAULT_PADDING - wallThickness;
        right = mazeRight - DEFAULT_PADDING;
        bottom = mazeBottom - DEFAULT_PADDING;

        walls[2] = new RectF(left, top, right, bottom);

        left = mazeRight - DEFAULT_PADDING - wallThickness;
        top = mazeTop + DEFAULT_PADDING;
        right = mazeRight - DEFAULT_PADDING;
        bottom = mazeBottom - DEFAULT_PADDING;

        walls[3] = new RectF(left, top, right, bottom);

        // Obstacles
        left = (float) (mazeLeft + DEFAULT_PADDING * 3.2);
        top = (float) (mazeTop + DEFAULT_PADDING + DEFAULT_PADDING);
        right = left + wallThickness;
        bottom = (float) (mazeBottom - top);

        walls[4] = new RectF(left, top, right, bottom);

        left = (float) (left * 1.7);
        top = (float) (mazeTop + DEFAULT_PADDING );
        right = left + wallThickness;
        bottom = (float) (mazeBottom - mazeBottom * 0.3);

        walls[5] = new RectF(left, top, right, bottom);

        left = (float) (left + DEFAULT_PADDING );
        top = (float) (mazeBottom - mazeBottom * 0.4);
        right = left + wallThickness;
        bottom = (float) (mazeBottom - DEFAULT_PADDING);

        walls[6] = new RectF(left, top, right, bottom);

        left = (float) (left * 1.4);
        top = (float) (mazeTop + DEFAULT_PADDING + DEFAULT_PADDING);
        right = left + wallThickness;
        bottom = (float) (mazeBottom - top);

        walls[7] = new RectF(left, top, right, bottom);


        left = (float) (left * 1.3);
        top = (float) (mazeTop + DEFAULT_PADDING);
        right = left + wallThickness;
        bottom = (float) (mazeBottom - mazeBottom * 0.6);

        walls[8] = new RectF(left, top, right, bottom);

        left = (float) (left);
        top = (float) (mazeBottom - mazeBottom * 0.4);
        right = left + wallThickness;
        bottom = (float) (mazeBottom - DEFAULT_PADDING);

        walls[9] = new RectF(left, top, right, bottom);


        left = (float) (left * 1 + DEFAULT_PADDING * 1.7);
        top = (float) (mazeTop + DEFAULT_PADDING + DEFAULT_PADDING);
        right = left + wallThickness;
        bottom = (float) (mazeBottom - top);

        walls[10] = new RectF(left, top, right, bottom);

        left = (float) (left + DEFAULT_PADDING * 1.7);
        top = (float) (mazeTop + DEFAULT_PADDING);
        right = left + wallThickness;
        bottom = (float) (mazeBottom - mazeBottom * 0.28);

        walls[11] = new RectF(left, top, right, bottom);

        left = (float) (left + DEFAULT_PADDING * 1.7);
        top = (float) (mazeBottom * 0.28);
        right = left + wallThickness;
        bottom = (float) (mazeBottom - DEFAULT_PADDING);

        walls[12] = new RectF(left, top, right, bottom);

        return walls;
    }

}
