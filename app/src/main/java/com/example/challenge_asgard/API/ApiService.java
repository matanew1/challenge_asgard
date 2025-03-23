package com.example.challenge_asgard.API;

import com.example.challenge_asgard.Models.Instructor;
import com.example.challenge_asgard.Models.Lesson;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;
import retrofit2.http.Path;

import java.util.List;


public interface ApiService {

    @GET("/lessons/{instructor_id}")
    Call<List<Lesson>> getInstructorLessons(@Path("instructor_id") String instructorId);

    @POST("/lessons")
    Call<Void> addLesson(@Body Lesson lesson);
}