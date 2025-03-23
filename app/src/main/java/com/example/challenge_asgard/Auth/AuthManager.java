package com.example.challenge_asgard.Auth;

import android.content.Context;

import com.example.challenge_asgard.Models.User;
import com.example.challenge_asgard.Models.Student;
import com.example.challenge_asgard.Models.Instructor;

public class AuthManager {

    private static AuthManager instance;
    private User currentUser;

    private AuthManager() {}

    public static void init(Context context) {
        if (instance == null) {
            instance = new AuthManager();
        }
    }

    public static AuthManager getInstance() {
        return instance;
    }

    public User getCurrentUser() {
        return currentUser; // Return current logged-in user (or null if no user)
    }

    public User login(String email, String password, boolean isStudent) {
        // Sample login logic. Replace with actual login logic (e.g., from a database)
        if (email.equals("a@a.com") && password.equals("123")) {
            if (isStudent) {
                currentUser = new Student(); // Return a Student instance
            } else {
                currentUser = new Instructor(); // Return an Instructor instance
            }
            return currentUser;
        }
        return null; // Invalid login
    }

    public boolean register(User user) {
        // Sample registration logic. Replace with actual registration logic (e.g., database check)
        if (user.getEmail().equals("student@example.com")) {
            return false; // Email already exists
        }
        // Assume registration is successful
        return true;
    }

    public void logout() {
        currentUser = null; // Clear the current user
    }
}
