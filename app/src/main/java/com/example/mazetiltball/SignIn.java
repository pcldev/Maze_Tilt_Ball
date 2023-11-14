package com.example.mazetiltball;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mazetiltball.auth.auth;
import com.google.android.gms.common.api.ApiException;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SignIn extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;
    private EditText edtEmail, edtMatKhau;
    private TextView textDangKy;
    private Button btnDangNhap, btnDangKy;
    private FirebaseAuth mAuth, mAuthGg;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            auth author = new auth();
            author.goHomeActivity(SignIn.this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();
        mAuthGg = FirebaseAuth.getInstance();
        edtEmail = findViewById(R.id.dnEmail);
        edtMatKhau = findViewById(R.id.dnMatKhau);
        btnDangNhap = findViewById(R.id.dangNhap);
        textDangKy = findViewById(R.id.dangKy);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        textDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("28383079352-lku95cfub9o53hlkuge7p09f8vkp6eu6.apps.googleusercontent.com")
                .requestEmail()
                .build();

        gsc = GoogleSignIn.getClient(this,gso);

//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        if (account != null) {
//            firebaseAuthWithGoogle(account);
//        }

        SignInButton btnGoogleSignIn = findViewById(R.id.btn_google_sign_in);
        btnGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithGoogle();
            }
        });
    }

    private void signInWithGoogle() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                GoogleSignInAccount account = null;
                try {
                    account = task.getResult(ApiException.class);
                    firebaseAuthWithGoogle(account);
                } catch (ApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuthGg.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Đăng nhập thành công, lưu token
                        FirebaseUser user = mAuthGg.getCurrentUser();
                        String idToken = account.getIdToken();
                        // Lưu idToken vào SharedPreferences hoặc cơ sở dữ liệu
                        saveIdToken(idToken);

                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignIn.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void saveIdToken(String idToken) {
        // Khởi tạo một SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Lưu ID token vào SharedPreferences
        editor.putString("idToken", idToken);

        // Lưu thay đổi
        editor.apply();
    }



    private void register() {
        Intent i = new Intent(SignIn.this, SignUp.class);
        startActivity(i);
    }

    private void login() {
        String email, matKhau;
        email = edtEmail.getText().toString();
        matKhau = edtMatKhau.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(matKhau)) {
            Toast.makeText(this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, matKhau).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignIn.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Đăng nhập thất bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}