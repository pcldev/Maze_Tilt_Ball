package com.example.mazetiltball.auth;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mazetiltball.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class auth extends AppCompatActivity {

    FirebaseUser user;
    FirebaseAuth mAuth;


    public void goHomeActivity(Context context) {
        Intent MainActivityIntent = new Intent(context, MainActivity.class);
        context.startActivity(MainActivityIntent);
    }

    public FirebaseUser getUser() {
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        return user;
    }
}
