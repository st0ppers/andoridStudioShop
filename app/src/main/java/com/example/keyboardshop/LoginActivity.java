package com.example.keyboardshop;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class LoginActivity extends AppCompatActivity {
    EditText usernameEditText, passwordEditText;
    Button loginButton, signupButton, forgotPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        signupButton = findViewById(R.id.signupButton);
        signupButton.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        forgotPasswordButton = findViewById(R.id.forgotPasswordButton);
        forgotPasswordButton.setBackgroundColor(ContextCompat.getColor(this, R.color.green));

        loginButton.setOnClickListener(v -> navigateToStoreActivity());
    }

    private void navigateToStoreActivity() {
        Intent intent = new Intent(LoginActivity.this, StoreActivity.class);
        startActivity(intent);
        finish();

    }
}

