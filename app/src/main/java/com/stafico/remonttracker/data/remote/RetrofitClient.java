package com.stafico.remonttracker.data.remote;

import com.stafico.remonttracker.bluetooth.RcAdapter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://your-api-url.com/api/"; // заміни на свій
    private static Retrofit retrofit = null;

    public static ApiService getApiService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);
    }

    public static Retrofit getRetrofitInstance() {
        return retrofit;
    }
}
