package com.stafico.remonttracker;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class second extends AppCompatActivity {

    private static final String PREFS_NAME = "projects_prefs";
    private static final String KEY_PROJECTS = "saved_projects";

    private int projectCount = 0;
    private final ArrayList<ProjectFragment> projectFragments = new ArrayList<>();
    private final ArrayList<ProjectModel> savedModels = new ArrayList<>();

    private LinearLayout projectButtonContainer;
    private SharedPreferences prefs;
    private Gson gson = new Gson();

    private String generateNextButtonLabel() {
        int index = 1;
        while (true) {
            String proposed = "Проєкт " + index;
            boolean exists = false;
            for (int i = 0; i < projectButtonContainer.getChildCount(); i++) {
                Button btn = (Button) projectButtonContainer.getChildAt(i);
                if (btn.getText().toString().equals(proposed)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) return proposed;
            index++;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        EdgeToEdge.enable(this);

        // Підключення елементів
        Button backButton = findViewById(R.id.backButton);
        Button createBtn = findViewById(R.id.createProjectButton);
        Button deleteBtn = findViewById(R.id.deleteProjectButton);
        projectButtonContainer = findViewById(R.id.projectButtonContainer);

        // Обробка кнопки "назад"
        backButton.setOnClickListener(v -> finish());

        // SharedPreferences
        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        loadSavedProjects();

        // Створити новий проєкт
        createBtn.setOnClickListener(v -> {
            projectCount++;
            String buttonTitle = generateNextButtonLabel();

// 🧪 Тимчасово — заглушки для вводу користувачем
            String projectTitle = "Проєкт " + projectCount;
            String projectDescription = "Опис для '" + projectTitle + "'";

// передаємо: (назва кнопки, справжня назва, опис)
            addProject(buttonTitle, projectTitle, projectDescription);
            saveProjects();

        });

        // Видалити останній проєкт
        deleteBtn.setOnClickListener(v -> {
            if (!projectFragments.isEmpty()) {
                projectFragments.remove(projectFragments.size() - 1);
                savedModels.remove(savedModels.size() - 1);
                projectButtonContainer.removeViewAt(projectButtonContainer.getChildCount() - 1);
                projectCount--;

                if (!projectFragments.isEmpty()) {
                    loadFragment(projectFragments.get(projectFragments.size() - 1));
                } else {
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.projectFragmentContainer);
                    if (fragment != null) {
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    }
                }

                saveProjects();
            }
        });

        // Insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //  Завантаження проєктів з SharedPreferences
    private void loadSavedProjects() {
        String json = prefs.getString(KEY_PROJECTS, null);
        if (json != null) {
            Type listType = new TypeToken<ArrayList<ProjectModel>>() {}.getType();
            ArrayList<ProjectModel> restored = gson.fromJson(json, listType);

            for (int i = 0; i < restored.size(); i++) {
                ProjectModel model = restored.get(i);
                String buttonName = "Проєкт " + (i + 1);
                addProject(buttonName, model.getTitle(), model.getDescription());
            }


            savedModels.addAll(restored);

        }
    }

    //  Збереження проєктів у SharedPreferences
    private void saveProjects() {
        String json = gson.toJson(savedModels);
        prefs.edit().putString(KEY_PROJECTS, json).apply();
    }

    //  Створення фрагменту + кнопки
    private void addProject(String buttonName, String title, String desc) {
        String projectTitle = "Назва проєкту " + projectCount;
        String projectDescription = "Опис для '" + projectTitle + "'";

        ProjectFragment fragment = ProjectFragment.newInstance(projectTitle, projectDescription);
        projectFragments.add(fragment);
        savedModels.add(new ProjectModel(projectTitle, projectDescription));

        Button button = new Button(this);
        button.setText(title);
        button.setTextColor(Color.WHITE);
        button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF5722")));
        button.setTextSize(16);
        button.setPadding(16, 8, 16, 8);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(8, 0, 8, 0);
        button.setLayoutParams(params);

        int index = projectFragments.size() - 1;
        button.setOnClickListener(v -> loadFragment(projectFragments.get(index)));

        projectButtonContainer.addView(button);
        loadFragment(fragment);
    }

    // 🔁 Завантаження фрагмента у вміст
    private void loadFragment(ProjectFragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.projectFragmentContainer, fragment)
                .commit();
    }
}
