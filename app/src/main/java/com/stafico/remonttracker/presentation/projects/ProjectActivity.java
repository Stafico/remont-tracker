package com.stafico.remonttracker.presentation.projects;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stafico.remonttracker.R;
import com.stafico.remonttracker.bluetooth.ControlActivity;
import com.stafico.remonttracker.domain.model.TaskModel;
import com.stafico.remonttracker.presentation.tasks.TaskAdapter;
import com.stafico.remonttracker.presentation.tasks.TasksDetailsActivity;

import java.util.ArrayList;

public class ProjectActivity extends AppCompatActivity {

    private ArrayList<TaskModel> taskList;
    private TaskAdapter taskAdapter;
    private RecyclerView tasksRecyclerView;
    private Button addTaskButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ініціалізація елементів
        taskList = new ArrayList<>();
        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        addTaskButton = findViewById(R.id.button_add_task);
        Button receiptButton = findViewById(R.id.button_receipt);
        backButton = findViewById(R.id.button_back);

        // Налаштування RecyclerView
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter(taskList, this::onTaskOptionsClick);
        tasksRecyclerView.setAdapter(taskAdapter);

        // Кнопка додати
        addTaskButton.setOnClickListener(v -> {
            int number = taskList.size() + 1;
            taskList.add(new TaskModel("TASK " + number, false));
            taskAdapter.notifyItemInserted(taskList.size() - 1);
        });

        // Кнопка чек
        receiptButton.setOnClickListener(v -> {
            Intent btIntent = new Intent(this, ControlActivity.class);
            btIntent.putParcelableArrayListExtra("taskList", taskList);

            startActivity(btIntent);
        });

        // Кнопка назад
        backButton.setOnClickListener(v -> finish());
    }

    // Обробка "..." кнопки
    private void onTaskOptionsClick(TaskModel task) {
        // TODO: Перехід на іншу сторінку з деталями
        Intent intent = new Intent(this, TasksDetailsActivity.class);
        intent.putExtra("taskName", task.getTitle());
        startActivity(intent);
    }
}
