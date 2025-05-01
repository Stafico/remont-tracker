package com.stafico.remonttracker.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.stafico.remonttracker.R;

public class LoginActivity extends AppCompatActivity {

    EditText emailInput, passwordInput;
    Button loginButton, backButton, registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.input_email);
        passwordInput = findViewById(R.id.input_password);
        loginButton = findViewById(R.id.button_login);
        backButton = findViewById(R.id.button_back);
        registerButton = findViewById(R.id.button_register);

        loginButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Будь ласка, заповніть всі поля", Toast.LENGTH_SHORT).show();
                return;
            }

            // логіка входу (перевірка акаунту)
            Toast.makeText(this, "Вхід успішний", Toast.LENGTH_SHORT).show();
        });

        backButton.setOnClickListener(v -> finish());

        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
