package com.example.challenge_asgard.Services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.challenge_asgard.API.ApiClient;
import com.example.challenge_asgard.Models.Instructor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InstructorService {
    public interface InstructorCallback<T> {
        void onSuccess(T result);
        void onError(String errorMessage);
    }

    public static void createInstructor(Instructor instructor, InstructorCallback<Instructor> callback) {
        ApiClient.getInstructorApi().createInstructor(instructor)
                .enqueue(new Callback<>() {
                    @Override
                    public void onResponse(@NonNull Call<Instructor> call, @NonNull Response<Instructor> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            callback.onSuccess(response.body());
                        } else {
                            callback.onError("Failed to create instructor: " +
                                    (response.errorBody() != null ? response.message() : "Unknown error"));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Instructor> call, @NonNull Throwable t) {
                        callback.onError("Network error: " + t.getMessage());
                        Log.e("InstructorService", "Error creating instructor", t);
                    }
                });
    }

    public static void getInstructors(int skip, int limit, InstructorCallback<List<Instructor>> callback) {
        ApiClient.getInstructorApi().getInstructors(skip, limit)
                .enqueue(new Callback<>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Instructor>> call, @NonNull Response<List<Instructor>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            callback.onSuccess(response.body());
                        } else {
                            callback.onError("Failed to retrieve instructors: " +
                                    (response.errorBody() != null ? response.message() : "Unknown error"));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Instructor>> call, @NonNull Throwable t) {
                        callback.onError("Network error: " + t.getMessage());
                        Log.e("InstructorService", "Error retrieving instructors", t);
                    }
                });
    }
}
