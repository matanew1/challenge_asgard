package com.example.challenge_asgard.API;

import com.example.challenge_asgard.Models.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface StudentApi {
    @POST("students/")
    Call<Student> createStudent(@Body Student student);

    @GET("students/")
    Call<List<Student>> getStudents(
            @Query("skip") int skip,
            @Query("limit") int limit
    );
}