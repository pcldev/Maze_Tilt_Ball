package com.example.mazetiltball;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MazeActivity extends AppCompatActivity implements SensorEventListener {

    private GameView gameView;
    private SensorManager sensorManager;
    private Sensor accelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maze);

        gameView = findViewById(R.id.gameView);

        // Set up the sensor manager and accelerometer
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
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
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {


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
