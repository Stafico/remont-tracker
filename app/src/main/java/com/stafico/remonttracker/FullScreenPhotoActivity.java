package com.stafico.remonttracker;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class FullScreenPhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_photo);

        ImageView imageView = findViewById(R.id.fullscreen_image);
        int resId = getIntent().getIntExtra("photoResId", -1);

        if (resId != -1) {
            imageView.setImageResource(resId);
        }

        imageView.setOnClickListener(v -> finish()); // Клік = закрити
    }
}
