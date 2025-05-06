package com.stafico.remonttracker.data.remote;

import com.stafico.remonttracker.domain.model.TaskModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("tasks")
    Call<Void> saveTask(@Body TaskModel task); // відправка 1 задачі на сервер

    @GET("tasks")
    Call<List<TaskModel>> getTasks();

    @POST("tasks")
    Call<Void> addTask(@Body TaskModel task);

    @PUT("tasks")
    Call<Void> updateTask(@Body TaskModel task);

    @DELETE("tasks/{id}")
    Call<Void> deleteTask(@Path("id") int id);


}
