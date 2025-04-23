package com.stafico.remonttracker.presentation.tasks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stafico.remonttracker.R;
import com.stafico.remonttracker.domain.model.TaskModel;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private final ArrayList<TaskModel> tasks;
    private final OnTaskOptionsClickListener listener;

    public interface OnTaskOptionsClickListener {
        void onTaskOptionsClick(TaskModel task);
    }

    public TaskAdapter(ArrayList<TaskModel> tasks, OnTaskOptionsClickListener listener) {
        this.tasks = tasks;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        TaskModel task = tasks.get(position);
        holder.titleText.setText(task.getTitle());
        holder.checkBox.setChecked(task.isDone());

        // Обробка галочки
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> task.setDone(isChecked));

        // Обробка "три крапки"
        holder.optionsButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onTaskOptionsClick(task);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView titleText;
        CheckBox checkBox;
        ImageButton optionsButton;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.task_title);
            checkBox = itemView.findViewById(R.id.task_checkbox);
            optionsButton = itemView.findViewById(R.id.task_options);
        }
    }
}
