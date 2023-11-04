package com.example.mazetiltball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LuatchoiActivity extends AppCompatActivity {
    Button btnBa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luatchoi);
        btnBa = findViewById(R.id.btnBack);
        btnBa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LuatchoiActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}