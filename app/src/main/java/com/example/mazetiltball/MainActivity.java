package com.example.mazetiltball;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button letGoBtn;
    ImageButton btnluatchoi;
    FirebaseAuth mAuth;
    FirebaseUser user;
    TextView email, txtLogout;
    Button btnhienDiem;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLogout = findViewById(R.id.txtSignout);
        letGoBtn = findViewById(R.id.letgobtn);
        btnluatchoi = (ImageButton) findViewById(R.id.btnLuatChoi);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        email = findViewById(R.id.email);
        btnhienDiem = findViewById(R.id.btnHiendiem);

        if (user != null) {
            email.setText(user.getEmail());
        }

        letGoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ManChoiActivity.class);
                startActivity(intent);
            }
        });
        btnluatchoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LuatchoiActivity.class);
                startActivity(intent);
            }
        });

        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Firebase authentication instance
                FirebaseAuth mAuth = FirebaseAuth.getInstance();

                // Sign out the user
                mAuth.signOut();

                Intent MainActivityIntent = new Intent(MainActivity.this, SignIn.class);
                MainActivity.this.startActivity(MainActivityIntent);
            }
        });
        btnhienDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SeeRank.class);
                startActivity(intent);
            }
        });
    }
}