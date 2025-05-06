package com.stafico.remonttracker.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.stafico.remonttracker.data.remote.ApiService;
import com.stafico.remonttracker.data.remote.RetrofitClient;
import com.stafico.remonttracker.domain.model.TaskModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskRepository {

    private final ApiService apiService;
    private static final String TAG = "TaskRepository";

    public TaskRepository() {
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    }

    public LiveData<List<TaskModel>> getTasks() {
        MutableLiveData<List<TaskModel>> tasksLiveData = new MutableLiveData<>();

        apiService.getTasks().enqueue(new Callback<List<TaskModel>>() {
            @Override
            public void onResponse(Call<List<TaskModel>> call, Response<List<TaskModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    tasksLiveData.setValue(response.body());
                } else {
                    Log.e(TAG, "getTasks: Response failed or empty");
                    tasksLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<TaskModel>> call, Throwable t) {
                Log.e(TAG, "getTasks: Network failure", t);
                tasksLiveData.setValue(null);
            }
        });

        return tasksLiveData;
    }

    public void addTask(TaskModel task, Callback<Void> callback) {
        apiService.addTask(task).enqueue(callback);
    }

    public void updateTask(TaskModel task, Callback<Void> callback) {
        apiService.updateTask(task).enqueue(callback);
    }

    public void deleteTask(int id, Callback<Void> callback) {
        apiService.deleteTask(id).enqueue(callback);
    }
}
