package com.example.challenge_asgard.Models;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.challenge_asgard.API.ApiClient;
import com.example.challenge_asgard.API.ApiService;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Lesson {

    private ApiService apiService = ApiClient.getApiService();

    @SerializedName("id")
    private String id;

    @SerializedName("student")
    private Student student;

    @SerializedName("instructor")
    private Instructor instructor;

    @SerializedName("startDateTime")
    private LocalDateTime startDateTime;

    @SerializedName("swimStyle")
    private List<SwimStyle> swimStyle;

    @SerializedName("lessonType")
    private LessonType lessonType;

    @SerializedName("status")
    private LessonStatus status;


    public Lesson(String id, Student student, Instructor instructor, LocalDateTime startDateTime, List<SwimStyle> swimStyle, LessonType lessonType, LessonStatus status) {
        this.id = id;
        this.student = student;
        this.instructor = instructor;
        this.startDateTime = startDateTime;
        this.swimStyle = swimStyle;
        this.lessonType = lessonType;
        this.status = status;
    }

    // Calculated properties
    public LocalDateTime getEndDateTime() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            return startDateTime.plusMinutes(lessonType.getDurationMinutes());
        }
        return null;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public List<SwimStyle> getSwimStyle() {
        return swimStyle;
    }

    public void setSwimStyle(List<SwimStyle> swimStyle) {
        this.swimStyle = swimStyle;
    }

    public LessonType getLessonType() {
        return lessonType;
    }

    public void setLessonType(LessonType lessonType) {
        this.lessonType = lessonType;
    }

    public LessonStatus getStatus() {
        return status;
    }

    public void setStatus(LessonStatus status) {
        this.status = status;
    }

    @NonNull
    @Override
    public String toString() {
        return "Lesson{" +
                "apiService=" + apiService +
                ", id='" + id + '\'' +
                ", student=" + student +
                ", instructor=" + instructor +
                ", startDateTime=" + startDateTime +
                ", swimStyle=" + swimStyle +
                ", lessonType=" + lessonType +
                ", status=" + status +
                '}';
    }

    // Save method - This method could be used for local saving or direct network operations
    public void save() {
        // Make the API call to add a lesson
        Call<Void> call = apiService.addLesson(this);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    // Handle success
                    Log.d("API", "Lesson added successfully.");
                } else {
                    // Handle failure (e.g., 400 or 500 response code)
                    Log.e("API", "Failed to add lesson.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                // Handle failure (e.g., no internet connection)
                Log.e("API", "Error: " + t.getMessage());
            }
        });
    }


}
