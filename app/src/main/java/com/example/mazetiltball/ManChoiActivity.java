package com.example.mazetiltball;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mazetiltball.auth.auth;
import com.example.mazetiltball.auth.firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class ManChoiActivity extends AppCompatActivity {
    private TextView email;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    Button btnMan1,btnMan2,btnMan3,btnMan4,btnMan5;

    TextView txtStarGame1,txtStarGame2,txtStarGame3,txtStarGame4,txtStarGame5;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_choi);
        btnMan1 = findViewById(R.id.btnMan1);
        btnMan2 = findViewById(R.id.btnMan2);
        btnMan3 = findViewById(R.id.btnMan3);
        btnMan4 = findViewById(R.id.btnMan4);
        btnMan5 = findViewById(R.id.btnMan5);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        email = findViewById(R.id.emailChoi);

        firebase Firebase = new firebase();
        auth Auth = new auth();
        DatabaseReference databaseReference = Firebase.initializeFirebase();

        if (user != null) {
            email.setText(user.getEmail());
        }


//        databaseReference

        txtStarGame1 = findViewById(R.id.txtStarGame1);
        txtStarGame2 = findViewById(R.id.txtStarGame2);
        txtStarGame3 = findViewById(R.id.txtStarGame3);
        txtStarGame4 = findViewById(R.id.txtStarGame4);
        txtStarGame5 = findViewById(R.id.txtStarGame5);

         Firebase.setUserMazeScore(txtStarGame1, Auth.getUser().getEmail(), "maze_1");
         Firebase.setUserMazeScore(txtStarGame2, Auth.getUser().getEmail(), "maze_2");
         Firebase.setUserMazeScore(txtStarGame3, Auth.getUser().getEmail(), "maze_3");
         Firebase.setUserMazeScore(txtStarGame4, Auth.getUser().getEmail(), "maze_4");
         Firebase.setUserMazeScore(txtStarGame5, Auth.getUser().getEmail(), "maze_5");



        btnMan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to open the target Activity
                Intent intent = new Intent(ManChoiActivity.this, MazeActivity.class);

                // Pass any necessary data to the SongActivity (e.g., selected item data)
                intent.putExtra("mazeId", "maze_1");

                // Start the target Activity
                startActivity(intent);


            }
        });
        btnMan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to open the target Activity
                Intent intent = new Intent(ManChoiActivity.this, MazeActivity.class);

                // Pass any necessary data to the SongActivity (e.g., selected item data)
                intent.putExtra("mazeId", "maze_2");

                // Start the target Activity
                startActivity(intent);

            }
        });
        btnMan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to open the target Activity
                Intent intent = new Intent(ManChoiActivity.this, MazeActivity.class);

                // Pass any necessary data to the SongActivity (e.g., selected item data)
                intent.putExtra("mazeId", "maze_3");

                // Start the target Activity
                startActivity(intent);

            }
        });
        btnMan4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to open the target Activity
                Intent intent = new Intent(ManChoiActivity.this, MazeActivity.class);

                // Pass any necessary data to the SongActivity (e.g., selected item data)
                intent.putExtra("mazeId", "maze_4");

                // Start the target Activity
                startActivity(intent);

            }
        });
        btnMan5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to open the target Activity
                Intent intent = new Intent(ManChoiActivity.this, MazeActivity.class);

                // Pass any necessary data to the SongActivity (e.g., selected item data)
                intent.putExtra("mazeId", "maze_5");

                // Start the target Activity
                startActivity(intent);

            }
        });

    }
}