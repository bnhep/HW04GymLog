package com.example.hw04gymlog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.hw04gymlog.database.GymLogRepository;
import com.example.hw04gymlog.database.entities.User;
import com.example.hw04gymlog.databinding.ActivityLoginBinding;


public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private User user = null;

    private GymLogRepository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = GymLogRepository.getRepository(getApplication());

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!verifyUser()){
                    toastMaker("Invalid Username of Password");
                } else {
                    Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext(), user.getId());
                    startActivity(intent);
                }
            }
        });
    }

    private boolean verifyUser(){
        String username = binding.userNameLogInEditText.getText().toString();
        if(username.isEmpty()){
            toastMaker("Username should not be blank");
            return false;
        }
        user = repository.getUserByUserName(username);
        if(user != null){
            String password = binding.passwordLogInEditText.getText().toString();
            if(password.equals(user.getPassword())){
                return true;
            } else {
                toastMaker("Invalid Password");
            }
        }
        toastMaker(String.format("No %s found", username));
        return false;
    }

    private void toastMaker(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    static Intent loginIntentFactory(Context context){
        return new Intent(context, LoginActivity.class);
    }

}