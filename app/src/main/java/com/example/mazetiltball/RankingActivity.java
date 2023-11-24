package com.example.mazetiltball;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private TextView email;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        email = findViewById(R.id.emailMain);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        if (user != null) {
            email.setText(user.getEmail());
        }
        Query query = FirebaseDatabase.getInstance().getReference("users");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Get the data snapshot and convert it to a list of User objects
                List<User> userList = new ArrayList<>();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    Map<String, Object> data = (Map<String, Object>) userSnapshot.getValue();
                    String email = userSnapshot.getKey();
                    // Map the data to a User object
                    User user = new User();
                    user.setEmail(email.replace(",", "."));

                    if (data != null) {
                        Map<String, Integer> mazePoints = new HashMap<>();
                        for (Map.Entry<String, Object> entry : data.entrySet()) {
                            if (entry.getKey().startsWith("maze_")) {
                                mazePoints.put(entry.getKey(), Integer.parseInt(entry.getValue().toString()));
                            }
                        }
                        int totalScore = calcTotalScore(mazePoints);
                        user.setTotalScore(totalScore);
                        userList.add(user);
                    }
                }
                // Sort the list based on totalScore in descending order
                Collections.sort(userList, (user1, user2) -> Integer.compare(user2.getTotalScore(), user1.getTotalScore()));
                FirebaseRecyclerOptions<User> options = new FirebaseRecyclerOptions.Builder<User>().setQuery(databaseReference, User.class).build();
                FirebaseRecyclerAdapter<User, UserViewHolder> adapter = new FirebaseRecyclerAdapter<User, UserViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull User model) {
                        holder.setPosition(position);
                        User user = userList.get(position);
                        holder.setEmail(user.getEmail());
                        holder.setTotalScore(user.getTotalScore());
                    }

                    @NonNull
                    @Override
                    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rank, parent, false);
                        return new UserViewHolder(view);
                    }
                };
                recyclerView.setAdapter(adapter);
                adapter.startListening();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });
    }

    // Your UserViewHolder class
    public static class UserViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setEmail(String email) {
            Log.d("WTF", email);
            TextView textViewEmail = mView.findViewById(R.id.textViewEmail);
            textViewEmail.setText(email);
        }

        public void setPosition(int position) {
            TextView textViewPosition = mView.findViewById(R.id.textViewIndex);
            textViewPosition.setText(String.valueOf(position + 1));
        }

        public void setTotalScore(int totalScore) {
            TextView textViewTotalScore = mView.findViewById(R.id.textViewTotalPoints);
            textViewTotalScore.setText(totalScore + (totalScore > 1 ? " stars" : " star"));
        }
    }

    private List<User> sortUsersByTotalScore(List<User> userList) {
        Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return Integer.compare(user2.getTotalScore(), user1.getTotalScore());
            }
        });
        return userList;
    }

    private int calcTotalScore(Map<String, Integer> mazePoints) {
        int totalScore = 0;
        for (Map.Entry<String, Integer> entry : mazePoints.entrySet()) {
            totalScore += entry.getValue();
        }
        return totalScore;
    }

}
