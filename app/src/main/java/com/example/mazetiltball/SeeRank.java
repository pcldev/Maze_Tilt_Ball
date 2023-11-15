package com.example.mazetiltball;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class SeeRank extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> arrayList;
    private Button btnMaze1,btnMaze2,btnMaze3,btnMaze4,btnMaze5;
    private ArrayAdapter<String> adapter;
    TextView email;
    FirebaseAuth mAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_rank);
        email = findViewById(R.id.txtEmail);
        mAuth =FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        listView = findViewById(R.id.lstRank);
        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);
        btnMaze1 = findViewById(R.id.btnMaZe1);
        btnMaze2 = findViewById(R.id.btnMaZe2);
        btnMaze3 = findViewById(R.id.btnMaZe3);
        btnMaze4 = findViewById(R.id.btnMaZe4);
        btnMaze5 = findViewById(R.id.btnMaZe5);
        if (user != null) {
            email.setText(user.getEmail());
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("users");
        btnMaze1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        arrayList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            if (snapshot.child("maze_1").getValue() != null) {
                                String mail = snapshot.getKey();
                                int score = Integer.parseInt(snapshot.child("maze_1").getValue().toString());
                                arrayList.add(mail + ": " + score + "");
                            }
                            else {
                                arrayList.isEmpty();

                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        btnMaze2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        arrayList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            if (snapshot.child("maze_2").getValue() != null) {
                                String mail = snapshot.getKey();
                                int score = Integer.parseInt(snapshot.child("maze_2").getValue().toString());
                                arrayList.add(mail + ": " + score + "");
                            }
                            else {
                                arrayList.isEmpty();

                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        btnMaze3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        arrayList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            if (snapshot.child("maze_3").getValue() != null) {
                                String mail = snapshot.getKey();
                                int score = Integer.parseInt(snapshot.child("maze_3").getValue().toString());
                                arrayList.add(mail + ": " + score + "");
                            }
                            else {
                                arrayList.isEmpty();

                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        btnMaze4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        arrayList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            if (snapshot.child("maze_4").getValue() != null) {
                                String mail = snapshot.getKey();
                                int score = Integer.parseInt(snapshot.child("maze_4").getValue().toString());
                                arrayList.add(mail + ": " + score + "");
                            }
                            else {
                                arrayList.isEmpty();

                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        btnMaze5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        arrayList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            if (snapshot.child("maze_5").getValue() != null) {
                                String mail = snapshot.getKey();
                                int score = Integer.parseInt(snapshot.child("maze_5").getValue().toString());
                                arrayList.add(mail + ": " + score + "");
                            }
                            else {
                                arrayList.isEmpty();

                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}