package com.example.mazetiltball.auth;

import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class firebase {
    private DatabaseReference databaseReference;

    Long mazeScoreById = 0L;


    public DatabaseReference getDatabaseReference() {
        return this.databaseReference;
    }

    public DatabaseReference initializeFirebase() {

        // Get the root reference
        databaseReference = FirebaseDatabase.getInstance().getReference();


        return databaseReference;

    }

    public void setUserMazeScore(TextView txtStarGame, String email, String mazeId) {

        DatabaseReference userRef = databaseReference.child("users").child(email.replace(".", ","));

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Get the user data
                    Map<String, Object> userData = (Map<String, Object>) dataSnapshot.getValue();

                    // Check if the user has data for the specified maze
                    if (userData.containsKey(mazeId)) {
                        // Get the maze data for the specified maze
                        Object mazeData = userData.get(mazeId);

                        if (mazeData instanceof Long) {
                            Long scoreValue = (Long) mazeData;

                            // Now you can use scoreValue as needed
                            System.out.println("Maze:======= " + mazeId + ", Score: " + scoreValue);

                            mazeScoreById = scoreValue;

                            txtStarGame.setText(scoreValue.toString() + (scoreValue > 1 ? " stars" : " star"));
                        } else {
                            System.out.println("Unexpected data type for maze score");
                        }
                    } else {
                        System.out.println("Maze not found for the user");
                    }
                } else {
                    System.out.println("User not found");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
            }
        });

    }


}
