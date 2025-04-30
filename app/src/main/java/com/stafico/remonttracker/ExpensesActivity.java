package com.stafico.remonttracker;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpensesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MaterialAdapter adapter;
    private ArrayList<MaterialModel> materialList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        recyclerView = findViewById(R.id.materialsRecyclerView);
        Button buttonAdd = findViewById(R.id.button_add_material);
        Button buttonPrint = findViewById(R.id.button_print);
        EditText commentField = findViewById(R.id.expenses_comment);
        Button saveButton = findViewById(R.id.save_button);
        Button backButton = findViewById(R.id.button_back);

        materialList = new ArrayList<>();
        adapter = new MaterialAdapter(materialList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        buttonAdd.setOnClickListener(v -> {
            // Додаємо пустий матеріал
            materialList.add(new MaterialModel("", ""));
            adapter.notifyItemInserted(materialList.size() - 1);
        });

        buttonPrint.setOnClickListener(v -> {
            // Додати друк чека
        });

        saveButton.setOnClickListener(v -> {
            String comment = commentField.getText().toString().trim();
            Toast.makeText(this, "Коментар збережено", Toast.LENGTH_SHORT).show();
        });

        backButton.setOnClickListener(v -> finish());
    }
}
