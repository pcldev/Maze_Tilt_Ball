package com.example.mazetiltball;


import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.mazetiltball.auth.firebase;
import com.example.mazetiltball.helpers.Maze;
import com.example.mazetiltball.helpers.MazeOne;

public class GameView extends View {
    private String mazeId;
    private Paint mazePaint;
    private Paint ballPaint;
    private final Paint exitPaint = new Paint();
    private float ballRadius;
    private float ballX;
    private float ballY;
    private float tiltX;
    private float tiltY;

    public int initialBallX = 150;
    public int initialBallY = 150;
    private boolean isEnded = false;

    private final int numStars = 3;
    private float[] starX = new float[numStars];
    private float[] starY = new float[numStars];
    private float starRadius = 20;
    private boolean[] starCaught = new boolean[numStars];
    private int playerPoints = 0;
    private Bitmap starBitmap;

    private boolean isShowingModal = false;

    private boolean drawedStar = false;


    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Initialize firebase
        firebase Firebase = new firebase();
        Firebase.initializeFirebase();

        // Initialize paints for maze and ball
        mazePaint = new Paint();
        mazePaint.setColor(Color.BLACK);
        mazePaint.setStyle(Paint.Style.FILL);

        ballPaint = new Paint();
        ballPaint.setColor(Color.RED);
        ballPaint.setStyle(Paint.Style.FILL);

        // Set initial ball position
        ballRadius = 30; // Adjust this as needed
        ballX = initialBallX; // Initial X position
        ballY = initialBallY; // Initial Y position

        // Load your star image from resources (you need to replace R.drawable.star with your actual resource ID)
        starBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.star);

    }

    public void setMazeId(String mazeId) {
        this.mazeId = mazeId;
    }

    public String getMazeId() {
        return this.mazeId;
    }

    public void setTiltX(float tiltX) {
        this.tiltX = tiltX;
    }

    public void setTiltY(float tiltY) {
        this.tiltY = tiltY;
    }

    public void setBallX(int ballX) {
        this.ballX = ballX;
    }

    public void setBallY(int ballY) {
        this.ballY = ballY;
    }

    public void setPlayerPoints(int playerPoints) {
        this.playerPoints = playerPoints;
    }

    public void setDrawedStar(boolean drawedStar) {
        this.drawedStar = drawedStar;
    }

    public void setIsEnded(boolean isEnded) {
        this.isEnded = isEnded;
    }

    public boolean getIsEnded() {
        return this.isEnded;
    }

    public int getPointsPlayer() {
        return this.playerPoints;
    }

    public void setIsShowingModal(boolean isShowingModal) {
        this.isShowingModal = isShowingModal;
    }

    public boolean getIsShowModal() {
        return this.isShowingModal;
    }

    // Method to initialize stars
    public void initializeStars() {
        if (drawedStar) return;
        // Ensure that canvas dimensions are available
        int canvasWidth = getWidth();
        int canvasHeight = getHeight();

        if (canvasWidth > 0 && canvasHeight > 0) {
            // Example coordinates, adjusted to place stars more centrally
            starX[0] = canvasWidth / 4;     // 1/4th of the canvas width
            starY[0] = canvasHeight / 4;    // 1/4th of the canvas height

            starX[1] = canvasWidth / 2;     // Center of the canvas width
            starY[1] = canvasHeight / 2;    // Center of the canvas height

            starX[2] = canvasWidth * 3 / 4; // 3/4th of the canvas width
            starY[2] = canvasHeight * 3 / 4; // 3/4th of the canvas height

            for (int i = 0; i < numStars; i++) {
                starCaught[i] = false;
            }
        }
    }

    // Method to handle when the ball catches a star
    private void onStarCaught(int starIndex) {
        // Increment the player's points
        playerPoints += calculatePointsForStar();

        // Set a flag indicating that the star has been caught
        starCaught[starIndex] = true;

        // Move the star to an off-screen position to "disappear" it
        starX[starIndex] = -1000;
        starY[starIndex] = -1000;

        // Request a redraw of the canvas
        invalidate();
    }


    // Method to calculate points for catching a star (adjust the logic as needed)
    private int calculatePointsForStar() {
        return 1;
    }

    // Method to check for collision between the ball and a specific star
    private boolean isCollisionWithStar(float ballX, float ballY, float ballRadius, float starX, float starY, float starRadius) {
        float distance = (float) Math.sqrt(Math.pow(ballX - starX, 2) + Math.pow(ballY - starY, 2));
        boolean isCollision = distance < (ballRadius + starRadius);

        return isCollision;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Initial star
        initializeStars();

        drawedStar = true;
        // Update ball position based on tilt along both X and Y axes
        ballX += tiltX * 2.5; // Adjust multiplier for sensitivity
        ballY += tiltY * 2.5; // Adjust multiplier for sensitivity

        // Ensure the ball stays within the screen bounds
        if (ballX < ballRadius) ballX = ballRadius;
        if (ballX > getWidth() - ballRadius) ballX = getWidth() - ballRadius;
        if (ballY < ballRadius) ballY = ballRadius;
        if (ballY > getHeight() - ballRadius) ballY = getHeight() - ballRadius;

        // Define maze boundaries to fill the screen
        int mazeLeft = 0;
        int mazeTop = 0;
        int mazeRight = getWidth();
        int mazeBottom = getHeight();


        Maze maze = new Maze();
        RectF[] walls = new RectF[0];
        String mazeId = getMazeId();
        switch(mazeId) {
            case "maze_1": {
                walls = maze.drawMazeOne(mazeLeft, mazeTop, mazeRight, mazeBottom);
                break;
            }
            case "maze_2" : {
                walls = maze.drawMazeTwo(mazeLeft, mazeTop, mazeRight, mazeBottom);
                break;
            }
            case "maze_3": {
                walls = maze.drawMazeThree(mazeLeft, mazeTop, mazeRight, mazeBottom);
                break;
            }
            case "maze_4" : {
                walls = maze.drawMazeFour(mazeLeft, mazeTop, mazeRight, mazeBottom);
                break;
            }
            case "maze_5": {
                walls = maze.drawMazeFive(mazeLeft, mazeTop, mazeRight, mazeBottom);
                break;
            }
        }


        // Draw the maze walls
        mazePaint.setColor(Color.BLACK);
        for (RectF wall : walls) {
            canvas.drawRoundRect(wall, 30, 30, mazePaint);
        }

        // Check collision with maze walls
        for (RectF wall : walls) {
            boolean condition = ballX + ballRadius >= wall.left && ballX - ballRadius <= wall.right
                    && ballY + ballRadius >= wall.top && ballY - ballRadius <= wall.bottom;
            if (condition) {

                // Calculate the overlap between the ball and the wall
                float overlapX = Math.min(Math.abs(ballX + ballRadius - wall.left), Math.abs(wall.right - ballX + ballRadius));
                float overlapY = Math.min(Math.abs(ballY + ballRadius - wall.top), Math.abs(wall.bottom - ballY + ballRadius));

                // Adjust the ball's position based on the overlap
                if (overlapX < overlapY) {
                    ballX += ballX < wall.left ? -overlapX + 1 : overlapX + 1;
                    tiltX = -tiltX; // Change direction
                } else if (overlapX > overlapY) {
                    ballY += ballY < wall.top ? -overlapY + 1 : overlapY + 1;
                    tiltY = -tiltY; // Change direction
                }
            }
        }


        // Draw the exit
        mazePaint.setColor(getResources().getColor(R.color.secondary_red));
        int exitSize = 40;
        int exitX = mazeRight - exitSize - 150;
        int exitY = mazeBottom - exitSize - 200;

        int radius = 30;
        exitPaint.setStyle(Paint.Style.STROKE);
        exitPaint.setStrokeWidth(5);
        exitPaint.setColor(Color.BLACK);

        Paint circle1Paint = new Paint();
        circle1Paint.setColor(getResources().getColor(R.color.secondary_red));

        Paint circle2Paint = new Paint();
        circle2Paint.setColor(getResources().getColor(R.color.tertiary_red));

        canvas.drawCircle(exitX, exitY, radius, circle1Paint);
        canvas.drawCircle(exitX, exitY, radius * 3 / 4, circle2Paint);

        // Check if ball reached the exit
        if (ballX >= exitX && ballX <= exitX + exitSize - radius * 3 / 4 && ballY >= exitY && ballY <= exitY + exitSize - radius * 3 / 4) {
            // Handle game won condition
            ballX = (float) (exitX + ((exitSize - radius * 3 / 4) / 2) - 10); // Center the ball on the exit
            ballY = (float) (exitY + ((exitSize - radius * 3 / 4) / 2) - 10);


            isEnded = true;
        }

        for (int i = 0; i < numStars; i++) {
            if (!starCaught[i]) {
                canvas.drawBitmap(starBitmap, starX[i] - starRadius, starY[i] - starRadius, null);

            }

            // Check for collision between the ball and the star
            if (!starCaught[i] && isCollisionWithStar(ballX, ballY, ballRadius, starX[i], starY[i], starRadius)) {
                onStarCaught(i); // Handle star caught event
            }
        }

        // Prevent redraw when the game is ended;
        if (isEnded) {
            // Handle game won condition
            ballX = (float) (exitX + ((exitSize - radius * 3 / 4) / 2) - 10); // Center the ball on the exit
            ballY = (float) (exitY + ((exitSize - radius * 3 / 4) / 2) - 10);

        }

        // Draw the ball
        canvas.drawCircle(ballX, ballY, ballRadius, ballPaint);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Handle configuration changes here (if needed)
    }

}
