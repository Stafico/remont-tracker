package com.stafico.remonttracker;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        EdgeToEdge.enable(this);
        Button backButton = findViewById(R.id.backButton);
        Button project1 = findViewById(R.id.project1);
        Button project2 = findViewById(R.id.project2);
        Button project3 = findViewById(R.id.project3);
        backButton.setOnClickListener(v -> finish()); // Закриває поточну activity і повертає до MainActivity

        project1.setOnClickListener(v -> {
            ProjectFragment fragment = ProjectFragment.newInstance("Проєкт 1", "Опис першого проєкту");
            loadFragment(fragment);
        });

        project2.setOnClickListener(v -> {
            ProjectFragment fragment = ProjectFragment.newInstance("Проєкт 2", "Опис другого проєкту");
            loadFragment(fragment);
        });

        project3.setOnClickListener(v -> {
            ProjectFragment fragment = ProjectFragment.newInstance("Проєкт 3", "Опис третього проєкту");
            loadFragment(fragment);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void loadFragment(ProjectFragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.projectFragmentContainer, fragment) // Replace the existing fragment with the new one
                .addToBackStack(null) // Optional: add the transaction to the back stack
                .commit(); // Commit the transaction to perform the fragment replacement
    }



}