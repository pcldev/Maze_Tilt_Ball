package com.example.mazetiltball;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.mazetiltball.auth.auth;
import com.example.mazetiltball.auth.firebase;
import com.example.mazetiltball.controllers.gameController;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.time.Instant;
import java.util.Date;

public class MazeActivity extends AppCompatActivity implements SensorEventListener {

    private GameView gameView;
    private SensorManager sensorManager;
    private Sensor accelerometer;

    private String mazeId;

    private Button pauseBtn;
    private Boolean isPausing = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maze);

        gameView = findViewById(R.id.gameView);
        pauseBtn = findViewById(R.id.pauseGameBtn);
        // Set up the sensor manager and accelerometer
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Retrieve data from the Intent
        Intent intent = getIntent();
        if (intent != null) {
            mazeId = intent.getStringExtra("mazeId");
            gameView.setMazeId(mazeId);
        }

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPausing = !isPausing;
            }
        });
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
        // Stop drawing when pausing
        if(isPausing) return;


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
