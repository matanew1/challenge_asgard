package com.example.challenge_asgard.API;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://127.0.0.1:8000/api/v1"; // Change this to your API base URL
    private static Retrofit retrofit;

    // Custom Gson to handle Java 8 time types
    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create();

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    // Convenience methods to get API services
    @NonNull
    public static StudentApi getStudentApi() {
        return getClient().create(StudentApi.class);
    }

    @NonNull
    public static InstructorApi getInstructorApi() {
        return getClient().create(InstructorApi.class);
    }

    @NonNull
    public static LessonApi getLessonApi() {
        return getClient().create(LessonApi.class);
    }

}