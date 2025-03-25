package com.example.challenge_asgard.Auth;

import com.example.challenge_asgard.Models.Instructor;
import com.example.challenge_asgard.Models.Student;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthApiService {

    @POST("/login-student")
    Call<Student> loginStudent(@Body LoginRequest loginRequest);

    @POST("/login-instructor")
    Call<Instructor> loginInstructor(@Body LoginRequest loginRequest);

    @POST("/students/")
    Call<Student> createStudent(@Body StudentCreate studentCreate);

    @POST("/instructors/")
    Call<Instructor> createInstructor(@Body InstructorCreate instructorCreate);

    // DTOs matching backend request models
    class StudentCreate {
        private String first_name;
        private String last_name;
        private String preferred_style;
        private String lesson_preference;

        // Constructor, getters, setters
    }

    class InstructorCreate {
        private String name;
        private Instructor.InstructorAvailability available_days;
        private String start_time;
        private String end_time;
        private String swimming_styles;

        // Constructor, getters, setters
    }
}