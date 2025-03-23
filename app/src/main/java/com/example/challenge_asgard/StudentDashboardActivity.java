package com.example.challenge_asgard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import com.example.challenge_asgard.Auth.AuthManager;
import com.example.challenge_asgard.Fragments.GanttFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.FragmentTransaction;
import java.util.Objects;

public class StudentDashboardActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private FloatingActionButton fabAddLesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        // Initialize views
        navigationView = findViewById(R.id.nav_view);
        fabAddLesson = findViewById(R.id.fab_add_lesson);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.drawer_layout);

        // Setup Navigation Drawer
        navigationView.setNavigationItemSelectedListener(item -> {
            // Handle menu item click here
            if (item.getItemId() == R.id.nav_item_logout) {
                // Handle Dashboard selection
                Log.d("StudentDashboardActivity", "Logout selected");
                AuthManager.getInstance().logout();
                Intent intent = new Intent(StudentDashboardActivity.this, LoginActivity.class);
                startActivity(intent); // Navigate to the login screen
            }
            // Close the drawer after selection
            drawerLayout.closeDrawers();
            return true;
        });

        // FloatingActionButton listener
        fabAddLesson.setOnClickListener(view -> {
            // Handle FAB click here (e.g., open a new lesson creation screen)
            Intent intent = new Intent(StudentDashboardActivity.this, AddLessonActivity.class);
            startActivity(intent); // Navigate to a lesson creation screen
        });

        // Add GanttFragment
        if (savedInstanceState == null) {
            // Dynamically add the fragment to the container
            GanttFragment ganttFragment = new GanttFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, ganttFragment); // Replace with the fragment
            transaction.commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
