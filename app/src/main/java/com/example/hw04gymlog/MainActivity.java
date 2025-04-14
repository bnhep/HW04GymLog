package com.example.hw04gymlog;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;


import com.example.hw04gymlog.database.GymLogRepository;
import com.example.hw04gymlog.database.entities.GymLog;
import com.example.hw04gymlog.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "LOG_GYMLOG";
    private ActivityMainBinding binding;
    private GymLogRepository repository;

    String exercise = "";
    double weight = 0.0;
    int reps = 0;
    //TODO:ADDLOGININFORMATION
    int loggedInUserID = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = GymLogRepository.getRepository(getApplication());

        binding.logDisplayTextView.setMovementMethod(new ScrollingMovementMethod());
        updateDisplay();
        binding.logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInformationFromDisplay();
                insertGymLogRecord();
                updateDisplay();
            }
        });

    }

    private void insertGymLogRecord(){
        if(exercise.isEmpty()){
            return;
        }
        GymLog log = new GymLog(exercise, weight, reps, loggedInUserID);
        repository.insertGymLog(log);
    }
    private void updateDisplay(){
        ArrayList<GymLog> allLogs = repository.getAllLogs();
        if (allLogs.isEmpty()){
            binding.logDisplayTextView.setText(R.string.nothing_to_show_time_to_hit_the_gym);
        }
        StringBuilder sb = new StringBuilder();
        for (GymLog log : allLogs){
            sb.append(log);
        }
        binding.logDisplayTextView.setText(sb.toString());
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