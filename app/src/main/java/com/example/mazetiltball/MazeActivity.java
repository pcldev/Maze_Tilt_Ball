package com.example.mazetiltball;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mazetiltball.auth.auth;
import com.example.mazetiltball.controllers.gameController;
import com.google.firebase.auth.FirebaseUser;

public class MazeActivity extends AppCompatActivity implements SensorEventListener {

    private GameView gameView;
    private SensorManager sensorManager;
    private Sensor accelerometer;

    private String mazeId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maze);

        gameView = findViewById(R.id.gameView);
        // Set up the sensor manager and accelerometer
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Retrieve data from the Intent
        Intent intent = getIntent();
        if (intent != null) {
            mazeId = intent.getStringExtra("mazeId");
            gameView.setMazeId(mazeId);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float tiltX = 350, tiltY = 150;

        gameController GameController = new gameController(gameView, gameView.getContext());

        if (gameView.getIsEnded()) {
            if(!gameView.getIsShowModal()) {
                int playerPoints = gameView.getPointsPlayer();
                GameController.showVictoryModal(playerPoints);

                int starsEarned = gameView.getPointsPlayer();

                auth Auth = new auth();
                FirebaseUser user = Auth.getUser();
                String userEmail = user.getEmail();

                Log.d("HUHHU", userEmail);

                // Save star points to Firebase
                GameController.saveStarPoints(userEmail, mazeId, starsEarned);
            }

        } else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {


            float[] values = event.values;


            switch (getWindowManager().getDefaultDisplay().getRotation()) {


                case Surface.ROTATION_0:
                    System.out.println("Surface.ROTATION_0" + String.valueOf(Surface.ROTATION_0));

                    tiltX = -values[0];
                    tiltY = -values[1];
                    break;
                case Surface.ROTATION_90:
                    System.out.println("Surface.ROTATION_90" + String.valueOf(Surface.ROTATION_90));

                    tiltX = values[1];
                    tiltY = values[0];
                    break;
                case Surface.ROTATION_180:
                    System.out.println("Surface.ROTATION_180" + String.valueOf(Surface.ROTATION_180));

                    tiltX = values[0];
                    tiltY = values[1];
                    break;
                case Surface.ROTATION_270:
                    System.out.println("Surface.ROTATION_270" + String.valueOf(Surface.ROTATION_270));

                    tiltX = -values[1];
                    tiltY = -values[0];
                    break;
            }
            gameView.setTiltX(tiltX);
            gameView.setTiltY(tiltY);
            // Update tilt values along both X and Y axes
            gameView.invalidate(); // Force redraw
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used
    }


}
