package com.stafico.remonttracker.presentation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.stafico.remonttracker.R;

public class MainActivity extends AppCompatActivity {


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);
        // ці два рядка використовувати під час розробки, в кінці їх видалити ---------------
        SharedPreferences prefs = getSharedPreferences("projects_prefs", MODE_PRIVATE);
        prefs.edit().clear().apply(); // Очистити проєкти при запуску додатку
        // ----------------------------------------------------------------------------------

        Button loginBtn = findViewById(R.id.button_login);
        loginBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });

        Button registerBtn = findViewById(R.id.button_register);
        registerBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    public void openSecond(View view) {
        startActivity(new Intent(this, ProjectListActivity.class));
    }
}