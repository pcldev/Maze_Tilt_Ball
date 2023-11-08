package com.example.mazetiltball.auth;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mazetiltball.MainActivity;

public class auth extends AppCompatActivity {

    public void goHomeActivity(Context context) {
        Intent MainActivityIntent = new Intent(context, MainActivity.class);
        context.startActivity(MainActivityIntent);
    }
}
