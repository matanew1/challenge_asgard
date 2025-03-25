package com.example.challenge_asgard;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.challenge_asgard.Adapters.LessonsAdapter;
import com.example.challenge_asgard.Models.Lesson;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InstructorActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CalendarView calendarView;
    private LessonsAdapter adapter;
    private List<Lesson> lessonsList = new ArrayList<>();
    private FloatingActionButton  fabAddLesson;
    private TextView tvNoLessons;

    private LocalDate selectedDate;

    public static Intent createIntent(LoginActivity loginActivity) {
        return new Intent(loginActivity, InstructorActivity.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);

        // Initialize views
        initializeViews();

        // Set up RecyclerView
        setupRecyclerView();

        // Set initial date to today
        selectedDate = LocalDate.now();

        // Populate lessons (replace with actual data fetching)
        loadLessons();

        // Setup date change listener
        setupCalendarListener();

        // Setup FAB listener
        setupFabListener();
    }

    private void initializeViews() {
        recyclerView = findViewById(R.id.lessonsRecyclerView);
        calendarView = findViewById(R.id.instructorCalendarView);
        fabAddLesson = findViewById(R.id.addLessonFab);
        tvNoLessons = findViewById(R.id.tvNoLessons);
    }

    private void setupRecyclerView() {
        adapter = new LessonsAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadLessons() {
        // TODO: Replace with actual lesson data fetching mechanism
        // This is a placeholder - you would typically fetch from a database or API
        List<Lesson> lessons = new ArrayList<>();

        lessonsList.addAll(lessons);
        filterLessonsByDate();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupCalendarListener() {
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            // Update selected date
            selectedDate = LocalDate.of(year, month + 1, dayOfMonth);

            // Filter lessons for the selected date
            filterLessonsByDate();
        });
    }

    private void setupFabListener() {
        fabAddLesson.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddLessonActivity.class);
            startActivity(intent);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void filterLessonsByDate() {
        // Filter lessons for the selected date using Java 8 streams
        List<Lesson> filteredLessons = lessonsList.stream()
                .filter(lesson -> lesson.getStartTime().toLocalDate().equals(selectedDate))
                .collect(Collectors.toList());

        // Update adapter
        adapter.updateLessons(filteredLessons);

        // Show/hide no lessons message
        tvNoLessons.setVisibility(filteredLessons.isEmpty() ? TextView.VISIBLE : TextView.GONE);

        // Optional: Update calendar selection to match filtered date
        long millisAtSelectedDate = selectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
        calendarView.setDate(millisAtSelectedDate);
    }
}