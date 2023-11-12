package com.example.mazetiltball.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mazetiltball.GameView;
import com.example.mazetiltball.ManChoiActivity;
import com.example.mazetiltball.auth.firebase;
import com.example.mazetiltball.fragments.ModalEndGame;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class gameController extends AppCompatActivity  {

    private GameView gameView;
    private Context context;

    public gameController(GameView gameView, Context context) {
        this.gameView = gameView;
        this.context = context;
    }
    public void showVictoryModal(int playerPoints) {
        Log.d("Win", "Show victory modal");

        gameView.setIsShowingModal(true);
        new Handler().postDelayed(() -> {
            ModalEndGame modalEndGame = new ModalEndGame();
            Bundle args = new Bundle();
            args.putInt("stars", playerPoints);
            modalEndGame.setArguments(args);

            modalEndGame.setModalEndGameListener(new ModalEndGame.ModalEndGameListener() {
                @Override
                public void onRestartClicked() {
                    // Handle restart logic
                    resetGame();
                }

                @Override
                public void onGoHomeClicked() {
                    gomeHomeClick();
                }
            });

            modalEndGame.show(((AppCompatActivity)context).getSupportFragmentManager(), "victoryModal");
        }, 100); //
    }

    public void resetGame() {

        Log.d("WUT", "Reset the game");
        // Reset ball position to the starting point
        gameView.setBallY(gameView.initialBallY);
        gameView.setBallX(gameView.initialBallX);


        // Reset points
        gameView.setPlayerPoints(0);

        // Reproduce star and variables
        gameView.setDrawedStar(false);
        gameView.setIsEnded(false);
        gameView.setIsShowingModal(false);
        // Reset star positions and visibility
        gameView.initializeStars();

        // Invalidate the view to trigger a redraw
        gameView.invalidate();
    }

    public void gomeHomeClick() {
        // Handle going to the ManChoiActivity
        Intent intent = new Intent(context, ManChoiActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

        // Close the current activity (assuming it's an activity hosting the fragment)
        if (context instanceof AppCompatActivity) {
            ((AppCompatActivity) context).finish();
        }
    }
    // After the game is won, save the star points to Firebase
    public void saveStarPoints(String userGmail, String mazeId, int starPoints) {

        addUser(userGmail, mazeId, starPoints);
    }

    // Create the user's Gmail node if it doesn't exist
    private void createUserNode(String userGmail, String mazeId, int starPoints) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        DatabaseReference userRef = databaseReference.child("users").child(userGmail);



        // Set the star points for the maze
        userRef.child(mazeId).setValue(starPoints)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d("Firebase", "User node created successfully!");

                        // Update the total star points for the user
                        updateTotalStarPoints(userGmail);
                    } else {
                        Log.e("Firebase", "Error creating user node: " + task.getException());
                    }
                });
    }

    // Update the total star points for the user
    private void updateTotalStarPoints(String userGmail) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        DatabaseReference userRef = databaseReference.child("users").child(userGmail);

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    int totalStarPoints = 0;

                    for (DataSnapshot mazeSnapshot : snapshot.getChildren()) {
                        if (mazeSnapshot.getValue() instanceof Long) {
                            // Sum up the star points for all mazes
                            totalStarPoints += ((Long) mazeSnapshot.getValue()).intValue();
                        }
                    }

                    // Update the total star points in the user node
                    userRef.child("totalStarPoints").setValue(totalStarPoints)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Log.d("Firebase", "Total star points updated successfully!");
                                } else {
                                    Log.e("Firebase", "Error updating total star points: " + task.getException());
                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Error reading user node: " + error.getMessage());
            }
        });
    }


    public void addUser(String email, String mazeName, int score) {
        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("users");

        // Get a reference to the user's node
        DatabaseReference userRef = databaseReference.child(email.replace(".", ","));

        // Create a Map to represent the maze and its score
        Map<String, Object> mazeData = new HashMap<>();
        mazeData.put(mazeName, score);

        // Update only the specified maze under the user's node
        Map<String, Object> updateData = new HashMap<>();
        updateData.put(mazeName, score);

        // Update the user's data in the Firebase database
        userRef.updateChildren(updateData);
    }


}
