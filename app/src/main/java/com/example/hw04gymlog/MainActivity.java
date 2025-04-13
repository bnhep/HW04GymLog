package com.example.hw04gymlog;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;


import com.example.hw04gymlog.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LOG_GYMLOG";
    ActivityMainBinding binding;

    String exercise = "";
    double weight = 0.0;
    int reps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.logDisplayTextView.setMovementMethod(new ScrollingMovementMethod());
        binding.logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInformationFromDisplay();
                updateDisplay();

            }
        });

    }

    private void updateDisplay(){
        String currentInfo = binding.logDisplayTextView.getText().toString();
        String newDisplay = String.format(Locale.US,"Exercise: %s%nWeight:%.2f%nReps:%d%n=-=-=-=%n", exercise, weight, reps);
        newDisplay += currentInfo;

        binding.logDisplayTextView.setText(newDisplay);
    }

    private void getInformationFromDisplay(){
        exercise = binding.exerciseInputEditText.getText().toString();
        try {
            weight = Double.parseDouble(binding.weightInputEditText.getText().toString());
        } catch (NumberFormatException e) {
            Log.d(TAG, "Error Reading value from weight edit text");
        }

        try {
            reps = Integer.parseInt(binding.repInputEditText.getText().toString());
        } catch (NumberFormatException e) {
            Log.d(TAG, "Error Reading value from weight edit text");
        }

    }




}