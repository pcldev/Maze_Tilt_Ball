package com.example.mazetiltball;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mazetiltball.auth.auth;
import com.example.mazetiltball.auth.firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class ManChoiActivity extends AppCompatActivity {
    private EditText searchEditText;
    private Button searchButton;
    private TextView email;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    Button btnMan1, btnMan2, btnMan3, btnMan4, btnMan5;

    TextView txtStarGame1, txtStarGame2, txtStarGame3, txtStarGame4, txtStarGame5;

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

        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);

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
//        txtStarGame6 = findViewById(R.id.txtStarGame6);

        Firebase.setUserMazeScore(txtStarGame1, Auth.getUser().getEmail(), "maze_1");
        Firebase.setUserMazeScore(txtStarGame2, Auth.getUser().getEmail(), "maze_2");
        Firebase.setUserMazeScore(txtStarGame3, Auth.getUser().getEmail(), "maze_3");
        Firebase.setUserMazeScore(txtStarGame4, Auth.getUser().getEmail(), "maze_4");
        Firebase.setUserMazeScore(txtStarGame5, Auth.getUser().getEmail(), "maze_5");
//        Firebase.setUserMazeScore(txtStarGame6, Auth.getUser().getEmail(), "maze_6");


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
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = searchEditText.getText().toString();
                handleSearch(searchText);
            }
        });
    }

    private void handleSearch(String searchText) {
        if (searchText.isEmpty()) {
            // Nếu không có ký tự tìm kiếm, ẩn tất cả các nút
            setButtonsVisibility(View.GONE);
            return;
        }

        // Hiển thị các nút chứa ký tự tìm kiếm
        boolean isButton1Visible = containsIgnoreCase(btnMan1.getText().toString(), searchText);
        btnMan1.setVisibility(isButton1Visible ? View.VISIBLE : View.GONE);
        txtStarGame1.setVisibility(isButton1Visible ? View.VISIBLE : View.GONE);

        boolean isButton2Visible = containsIgnoreCase(btnMan2.getText().toString(), searchText);
        btnMan2.setVisibility(isButton2Visible ? View.VISIBLE : View.GONE);
        txtStarGame2.setVisibility(isButton2Visible ? View.VISIBLE : View.GONE);

        boolean isButton3Visible = containsIgnoreCase(btnMan3.getText().toString(), searchText);
        btnMan3.setVisibility(isButton3Visible ? View.VISIBLE : View.GONE);
        txtStarGame3.setVisibility(isButton3Visible ? View.VISIBLE : View.GONE);

        boolean isButton4Visible = containsIgnoreCase(btnMan4.getText().toString(), searchText);
        btnMan4.setVisibility(isButton4Visible ? View.VISIBLE : View.GONE);
        txtStarGame4.setVisibility(isButton4Visible ? View.VISIBLE : View.GONE);

        boolean isButton5Visible = containsIgnoreCase(btnMan5.getText().toString(), searchText);
        btnMan5.setVisibility(isButton5Visible ? View.VISIBLE : View.GONE);
        txtStarGame5.setVisibility(isButton5Visible ? View.VISIBLE : View.GONE);
        // Tiếp tục với các nút khác
    }

    private boolean containsIgnoreCase(String str, String subString) {
        return str.toLowerCase().contains(subString.toLowerCase());
    }

    private void setButtonsVisibility(int visibility) {
        btnMan1.setVisibility(visibility);
        btnMan2.setVisibility(visibility);
        btnMan3.setVisibility(visibility);
        btnMan4.setVisibility(visibility);
        btnMan5.setVisibility(visibility);
        // Tiếp tục với các nút khác
    }
}

