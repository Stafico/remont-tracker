package com.stafico.remonttracker.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.stafico.remonttracker.R;

public class RegisterActivity extends AppCompatActivity {

    EditText nameInput, emailInput, passwordInput, confirmPasswordInput;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameInput = findViewById(R.id.input_name);
        emailInput = findViewById(R.id.input_email);
        passwordInput = findViewById(R.id.input_password);
        confirmPasswordInput = findViewById(R.id.input_confirm_password);
        Button loginBtn = findViewById(R.id.button_login);
        loginBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });


        Button backBtn = findViewById(R.id.button_back);
        backBtn.setOnClickListener(v -> finish());

        registerBtn = findViewById(R.id.button_register);

        registerBtn.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            String confirmPassword = confirmPasswordInput.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Будь ласка, заповніть всі поля", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Паролі не співпадають", Toast.LENGTH_SHORT).show();
                return;
            }

            // Реєстрація користувача
            Toast.makeText(this, "Успішна реєстрація!", Toast.LENGTH_SHORT).show();
        });
    }
}
