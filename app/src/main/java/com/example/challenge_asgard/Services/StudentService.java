package com.example.challenge_asgard.Services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.challenge_asgard.API.ApiClient;
import com.example.challenge_asgard.Models.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentService {
    public interface StudentCallback<T> {
        void onSuccess(T result);
        void onError(String errorMessage);
    }

    public static void createStudent(Student student, StudentCallback<Student> callback) {
        ApiClient.getStudentApi().createStudent(student)
                .enqueue(new Callback<>() {
                    @Override
                    public void onResponse(@NonNull Call<Student> call, @NonNull Response<Student> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            callback.onSuccess(response.body());
                        } else {
                            callback.onError("Failed to create student: " +
                                    (response.errorBody() != null ? response.message() : "Unknown error"));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Student> call, @NonNull Throwable t) {
                        callback.onError("Network error: " + t.getMessage());
                        Log.e("StudentService", "Error creating student", t);
                    }
                });
    }

    public static void getStudents(int skip, int limit, StudentCallback<List<Student>> callback) {
        ApiClient.getStudentApi().getStudents(skip, limit)
                .enqueue(new Callback<>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Student>> call, @NonNull Response<List<Student>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            callback.onSuccess(response.body());
                        } else {
                            callback.onError("Failed to retrieve students: " +
                                    (response.errorBody() != null ? response.message() : "Unknown error"));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Student>> call, @NonNull Throwable t) {
                        callback.onError("Network error: " + t.getMessage());
                        Log.e("StudentService", "Error retrieving students", t);
                    }
                });
    }
}