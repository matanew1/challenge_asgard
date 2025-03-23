package com.example.challenge_asgard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;  // Importing Log for debugging
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.challenge_asgard.Adapters.LoginPagerAdapter;
import com.example.challenge_asgard.Auth.AuthManager;
import com.example.challenge_asgard.Models.Instructor;
import com.example.challenge_asgard.Models.Student;
import com.example.challenge_asgard.Models.User;
import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity"; // For logging purposes

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private LoginPagerAdapter pagerAdapter;

    // Login page elements
    private EditText editEmail;
    private EditText editPassword;
    private RadioButton radioStudent;
    private RadioButton radioInstructor;
    private Button buttonLogin;

    // Register page elements
    private EditText editRegisterFirstName;
    private EditText editRegisterLastName;
    private EditText editRegisterEmail;
    private EditText editRegisterPassword;
    private RadioButton radioRegisterStudent;
    private RadioButton radioRegisterInstructor;
    private Button buttonRegister;

    private AuthManager authManager;

    public static Intent createIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize auth manager
        AuthManager.init(this);
        authManager = AuthManager.getInstance();

        // If already logged in, redirect to appropriate dashboard
        User currentUser = authManager.getCurrentUser();
        if (currentUser != null) {
            Log.d(TAG, "User already logged in: " + currentUser.getEmail());
            redirectToDashboard(currentUser);
            return;
        }

        // Set up tabs and ViewPager
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        pagerAdapter = new LoginPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Not needed
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Not needed
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        // Initialize login form controls
        View loginView = pagerAdapter.getLoginView();
        editEmail = loginView.findViewById(R.id.edit_email);
        editPassword = loginView.findViewById(R.id.edit_password);
        radioStudent = loginView.findViewById(R.id.radio_student);
        radioInstructor = loginView.findViewById(R.id.radio_instructor);
        buttonLogin = loginView.findViewById(R.id.button_login);

        buttonLogin.setOnClickListener(v -> attemptLogin());

        // Initialize register form controls
        View registerView = pagerAdapter.getRegisterView();
        editRegisterFirstName = registerView.findViewById(R.id.edit_register_first_name);
        editRegisterLastName = registerView.findViewById(R.id.edit_register_last_name);
        editRegisterEmail = registerView.findViewById(R.id.edit_register_email);
        editRegisterPassword = registerView.findViewById(R.id.edit_register_password);
        radioRegisterStudent = registerView.findViewById(R.id.radio_register_student);
        radioRegisterInstructor = registerView.findViewById(R.id.radio_register_instructor);
        buttonRegister = registerView.findViewById(R.id.button_register);

        buttonRegister.setOnClickListener(v -> attemptRegister());
    }

    private void attemptLogin() {
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString();
        boolean isStudent = radioStudent.isChecked();

        if (email.isEmpty() || password.isEmpty()) {
            Log.e(TAG, "Login failed: Empty fields");
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d(TAG, "Attempting login: " + email + " password: " + password + " isStudent: " + isStudent);
        User user = authManager.login(email, password, isStudent);
        Log.d(TAG, "User: " + user);
        if (user != null) {
            Log.d(TAG, "Login successful: " + user.getEmail());
            redirectToDashboard(user);
        } else {
            Log.e(TAG, "Login failed: Invalid email or password");
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
        }
    }

    private void attemptRegister() {
        String firstName = editRegisterFirstName.getText().toString().trim();
        String lastName = editRegisterLastName.getText().toString().trim();
        String email = editRegisterEmail.getText().toString().trim();
        String password = editRegisterPassword.getText().toString();
        boolean isStudent = radioRegisterStudent.isChecked();

        if (firstName.isEmpty() || lastName.isEmpty() ||
                email.isEmpty() || password.isEmpty()) {
            Log.e(TAG, "Registration failed: Empty fields");
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        User newUser;
        if (isStudent) {
            newUser = new Student(); // Create a Student object
        } else {
            newUser = new Instructor(); // Create an Instructor object
        }

        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setPassword(password);

        boolean success = authManager.register(newUser);
        if (success) {
            Log.d(TAG, "Registration successful: " + newUser.getEmail());
            Toast.makeText(this, "Registration successful. Please login.", Toast.LENGTH_SHORT).show();
            viewPager.setCurrentItem(0); // Switch to login tab
        } else {
            Log.e(TAG, "Registration failed: Email already exists");
            Toast.makeText(this, "Email already exists", Toast.LENGTH_SHORT).show();
        }
    }

    private void redirectToDashboard(User user) {
        Intent intent;
        if (user instanceof Student) {
            intent = new Intent(this, StudentDashboardActivity.class);
        } else {
            // If user is an Instructor, redirect to Instructor dashboard
            intent = new Intent(this, StudentDashboardActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
