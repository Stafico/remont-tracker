package com.stafico.remonttracker.data.remote;

import com.stafico.remonttracker.domain.model.TaskModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @POST("tasks")
    Call<Void> saveTask(@Body TaskModel task); // відправка 1 задачі на сервер

    @GET("/")
    Call<String> getRootApi();

    @GET("tasks")
    Call<List<TaskModel>> getTasks();

    @POST("tasks")
    Call<Void> addTask(@Body TaskModel task);

    @PUT("tasks")
    Call<Void> updateTask(@Body TaskModel task);

    @DELETE("tasks/{id}")
    Call<Void> deleteTask(@Path("id") int id);


}
