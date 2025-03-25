package com.example.challenge_asgard.API;

import com.example.challenge_asgard.Models.Instructor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InstructorApi {
    @POST("instructors/")
    Call<Instructor> createInstructor(@Body Instructor instructor);

    @GET("instructors/")
    Call<List<Instructor>> getInstructors(
            @Query("skip") int skip,
            @Query("limit") int limit
    );
}