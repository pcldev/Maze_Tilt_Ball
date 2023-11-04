package com.example.mazetiltball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    Button letGoBtn;
    ImageButton btnluatchoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        letGoBtn = findViewById(R.id.letgobtn);
        btnluatchoi = (ImageButton) findViewById(R.id.btnLuatChoi);
        letGoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MazeActivity.class);
                startActivity(intent);
            }
        });
        btnluatchoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LuatchoiActivity.class);
                startActivity(intent);
            }
        });
    }
}