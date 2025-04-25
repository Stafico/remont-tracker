package com.stafico.remonttracker.presentation.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.stafico.remonttracker.R;
import com.stafico.remonttracker.presentation.photos.FullScreenPhotoActivity;
import com.stafico.remonttracker.presentation.photos.PhotoAdapter;

import java.util.Arrays;
import java.util.List;

public class TasksDetailsActivity extends AppCompatActivity {

    private TextView taskTitle;
    private EditText commentInput;
    private Button saveButton, backButton;
    private ViewPager2 photoCarousel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.details_main), (v, insets) -> {
            v.setPadding(
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            );
            return insets;
        });

        // Елементи
        taskTitle = findViewById(R.id.task_title_text);
        commentInput = findViewById(R.id.comment_input);
        saveButton = findViewById(R.id.save_button);
        backButton = findViewById(R.id.back_button);
        photoCarousel = findViewById(R.id.photo_carousel);

        // Отримати назву завдання з інтента
        String title = getIntent().getStringExtra("taskName");
        if (title != null) taskTitle.setText(title);

        // Фото-карусель (тимчасово з заглушками)
        List<Integer> photoList = Arrays.asList(
                R.drawable.placeholder_1,
                R.drawable.placeholder_2,
                R.drawable.placeholder_3
        );
        PhotoAdapter adapter = new PhotoAdapter(photoList, photoResId -> {
            Intent intent = new Intent(this, FullScreenPhotoActivity.class);
            intent.putExtra("photoResId", photoResId);
            startActivity(intent);
        });
        photoCarousel.setAdapter(adapter);

        // Обробка кнопок
        saveButton.setOnClickListener(v -> {
            String comment = commentInput.getText().toString().trim();
            Toast.makeText(this, "Коментар збережено: " + comment, Toast.LENGTH_SHORT).show();
        });

        backButton.setOnClickListener(v -> finish());
    }
}
