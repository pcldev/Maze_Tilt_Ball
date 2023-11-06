package com.example.mazetiltball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button letGoBtn;
    ImageButton btnluatchoi;
    FirebaseAuth mAuth;
    FirebaseUser user;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        letGoBtn = findViewById(R.id.letgobtn);
        btnluatchoi = (ImageButton) findViewById(R.id.btnLuatChoi);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        email = findViewById(R.id.email);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("28383079352-lku95cfub9o53hlkuge7p09f8vkp6eu6.apps.googleusercontent.com")
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if(user != null){
            email.setText(user.getEmail());
        }else if(acct!=null){
            String personEmail = acct.getEmail();
            email.setText(personEmail);
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
                Intent intent = new Intent(MainActivity.this,LuatchoiActivity.class);
                startActivity(intent);
            }
        });
    }
}