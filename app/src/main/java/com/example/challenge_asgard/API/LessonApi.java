package com.example.challenge_asgard.API;

import com.example.challenge_asgard.Models.Lesson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LessonApi {
    @POST("lessons/")
    Call<Lesson> createLesson(@Body Lesson lesson);

    @GET("lessons/weekly-schedule")
    Call<List<Lesson>> getWeeklySchedule(
            @Query("start_date") Lesson startDate
    );
}