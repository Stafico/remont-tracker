package com.stafico.remonttracker.data.remote;

import static org.junit.Assert.*;

import com.stafico.remonttracker.domain.model.TaskModel;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceTest {

    private ApiService apiService;

    @Before
    public void setUp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://build-track-alpha.vercel.app/api/") // або твій робочий URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    @Test
    public void testGetAllTasks_fromRealServer() throws IOException {
        Call<String> call = apiService.getRootApi();
        Response<String> response = call.execute();

        assertTrue(response.isSuccessful());
        assertNotNull(response.body());

        System.out.println("HTTP CODE: " + response.code());
        System.out.println("IS SUCCESSFUL: " + response.isSuccessful());
        System.out.println("BODY: " + response.body());
        System.out.println("ERROR BODY: " + response.errorBody());


//        // Перевіримо, що принаймні 1 таск існує
//        List<TaskModel> tasks = response.body();
//        assertTrue(tasks.size() > 0);
//
//        // Виведемо для перевірки
//        for (TaskModel task : tasks) {
//            System.out.println("Task: " + task.getTitle() + " | Done: " + task.isDone());
//        }
    }
}
