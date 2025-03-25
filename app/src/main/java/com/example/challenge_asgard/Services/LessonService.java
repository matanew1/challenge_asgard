package com.example.challenge_asgard.Services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.challenge_asgard.API.ApiClient;
import com.example.challenge_asgard.Models.Lesson;
import com.example.challenge_asgard.Models.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LessonService {
    public interface LessonCallback<T> {
        void onSuccess(T result);
        void onError(String errorMessage);
    }

    public static void createLesson(Lesson lesson, LessonCallback<Lesson> callback) {
        ApiClient.getLessonApi().createLesson(lesson)
                .enqueue(new Callback<>() {
                    @Override
                    public void onResponse(@NonNull Call<Lesson> call, @NonNull Response<Lesson> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            callback.onSuccess(response.body());
                        } else {
                            callback.onError("Failed to create lesson: " +
                                    (response.errorBody() != null ? response.message() : "Unknown error"));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Lesson> call, @NonNull Throwable t) {
                        callback.onError("Network error: " + t.getMessage());
                        Log.e("LessonService", "Error creating lesson", t);
                    }
                });
    }

    public static void getWeeklySchedule(Lesson lesson, LessonCallback<Lesson> callback) {
        ApiClient.getLessonApi().getWeeklySchedule(lesson)
                .enqueue(new Callback<>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Lesson>> call, @NonNull Response<List<Lesson>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            callback.onSuccess((Lesson) response.body());
                        } else {
                            callback.onError("Failed to retrieve weekly schedule: " +
                                    (response.errorBody() != null ? response.message() : "Unknown error"));
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Lesson>> call, @NonNull Throwable t) {
                        callback.onError("Network error: " + t.getMessage());
                        Log.e("LessonService", "Error retrieving weekly schedule", t);
                    }
                });
    }
}
