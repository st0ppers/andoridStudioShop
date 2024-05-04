package com.example.keyboardshop;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class LoginActivity extends AppCompatActivity {
    DbHelper dbHelper;
    EditText usernameEditText, passwordEditText;
    Button loginButton, signupButton, forgotPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DbHelper(this);
        setContentView(R.layout.login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        forgotPasswordButton = findViewById(R.id.forgotPasswordButton);
        signupButton = findViewById(R.id.signupButton);
        loginButton.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        signupButton.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        forgotPasswordButton.setBackgroundColor(ContextCompat.getColor(this, R.color.green));

        loginButton.setOnClickListener(v -> navigateToStoreActivity());
    }

    private void navigateToStoreActivity() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

//        Cursor c = dbHelper.getByUsername(username);
//        if (c != null && c.moveToFirst()) {
//            int index = c.getColumnIndex("Password");
//            String storedPassword = c.getString(index);
//
//            if (password.equals(storedPassword)) {
//                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, StoreActivity.class);
        startActivity(intent);
//            } else {
//                Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
//            }
//
//        } else {
//            Toast.makeText(LoginActivity.this, "Username not found", Toast.LENGTH_SHORT).show();
//        }

    }
}

