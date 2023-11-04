package com.example.mazetiltball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManChoiActivity extends AppCompatActivity {
    Button btnMan1,btnMan2,btnMan3,btnMan4,btnMan5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_choi);
        btnMan1 = findViewById(R.id.btnMan1);
        btnMan2 = findViewById(R.id.btnMan2);
        btnMan3 = findViewById(R.id.btnMan3);
        btnMan4 = findViewById(R.id.btnMan4);
        btnMan5 = findViewById(R.id.btnMan5);
        btnMan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManChoiActivity.this,MazeActivity.class);
                startActivity(intent);
            }
        });
        btnMan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManChoiActivity.this,MazeActivity.class);
                startActivity(intent);
            }
        });
        btnMan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManChoiActivity.this,MazeActivity.class);
                startActivity(intent);
            }
        });
        btnMan4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManChoiActivity.this,MazeActivity.class);
                startActivity(intent);
            }
        });
        btnMan5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManChoiActivity.this,MazeActivity.class);
                startActivity(intent);
            }
        });

    }
}