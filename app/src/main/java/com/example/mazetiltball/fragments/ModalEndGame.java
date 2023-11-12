package com.example.mazetiltball.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.mazetiltball.R;

public class ModalEndGame extends DialogFragment {

    private ModalEndGameListener listener;

    public interface ModalEndGameListener {
        void onRestartClicked();
        void onGoHomeClicked();
    }

    public ModalEndGame() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_modal_end_game, container, false);

        // Get views
        TextView tvMessage = view.findViewById(R.id.tvMessage);
        Button btnRestart = view.findViewById(R.id.btnRestart);
        Button btnGoHome = view.findViewById(R.id.btnGoHome);

        // Set message
        Bundle args = getArguments();
        if (args != null) {
            int stars = args.getInt("stars", 0);
            tvMessage.setText("You win with " + stars + " stars!");
        }

        // Set click listeners
        btnRestart.setOnClickListener(v -> {
            dismiss();
            if (listener != null) {
                listener.onRestartClicked();
            }
        });

        btnGoHome.setOnClickListener(v -> {
            dismiss();
            if (listener != null) {
                listener.onGoHomeClicked();
            }
        });

        return view;
    }

    public void setModalEndGameListener(ModalEndGameListener listener) {
        this.listener = listener;
    }
}
