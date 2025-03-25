package com.example.challenge_asgard.Auth;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.challenge_asgard.Models.BaseUser;
import com.example.challenge_asgard.Models.Instructor;
import com.example.challenge_asgard.Models.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthManager {
    private static AuthManager instance;
    private BaseUser currentBaseUser;
    private AuthApiService authApiService;

    private List<Instructor> instructors;
    private static final String BASE_URL = "http://10.0.0.4:8000/"; // Replace with actual backend URL

    private AuthManager(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        authApiService = retrofit.create(AuthApiService.class);
    }

    public static synchronized void init(Context context) {
        if (instance == null) {
            instance = new AuthManager(context);
        }
    }

    public static AuthManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("AuthManager must be initialized first with init()");
        }
        return instance;
    }

    public BaseUser getCurrentUser() {
        return currentBaseUser;
    }

    public void login(@NonNull String email, String password, boolean isStudent, LoginCallback callback) {
        LoginRequest loginRequest = new LoginRequest(email, password);

        // Use authApiService to login student or instructor
        if (isStudent) {
            authApiService.loginStudent(loginRequest).enqueue(new Callback<>() {
                @Override
                public void onResponse(@NonNull Call<Student> call, @NonNull Response<Student> response) {
                    Log.d("AuthManager", "Student Login Response: " + response);
                    if (response.isSuccessful() && response.body() != null) {
                        currentBaseUser = response.body();
                        callback.onLoginSuccess(currentBaseUser);
                    } else {
                        try {
                            String errorBody = response.errorBody() != null
                                    ? response.errorBody().string()
                                    : "Login failed";
                            callback.onLoginFailure(errorBody);
                        } catch (Exception e) {
                            callback.onLoginFailure("Login failed");
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Student> call, @NonNull Throwable t) {
                    Log.e("AuthManager", "Student Login Failure", t);
                    callback.onLoginFailure(t.getMessage() != null
                            ? t.getMessage()
                            : "Network error occurred");
                }
            });
        } else {
            authApiService.loginInstructor(loginRequest).enqueue(new Callback<>() {
                @Override
                public void onResponse(@NonNull Call<Instructor> call, @NonNull Response<Instructor> response) {
                    Log.d("AuthManager", "Instructor Login Response: " + response);
                    if (response.isSuccessful() && response.body() != null) {
                        currentBaseUser = response.body();
                        callback.onLoginSuccess(currentBaseUser);
                    } else {
                        try {
                            String errorBody = response.errorBody() != null
                                    ? response.errorBody().string()
                                    : "Login failed";
                            callback.onLoginFailure(errorBody);
                        } catch (Exception e) {
                            callback.onLoginFailure("Login failed");
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Instructor> call, @NonNull Throwable t) {
                    Log.e("AuthManager", "Instructor Login Failure", t);
                    callback.onLoginFailure(t.getMessage() != null
                            ? t.getMessage()
                            : "Network error occurred");
                }
            });
        }
    }

    public void logout() {
        currentBaseUser = null;
    }

    // Callback interfaces for async operations
    public interface LoginCallback {
        void onLoginSuccess(BaseUser user);
        void onLoginFailure(String errorMessage);
    }

    public interface RegisterCallback {
        void onRegistrationSuccess(BaseUser user);
        void onRegistrationFailure(String errorMessage);
    }
}