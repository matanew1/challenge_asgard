package com.example.challenge_asgard;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.switchmaterial.SwitchMaterial;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.challenge_asgard.Models.Lesson;
import com.example.challenge_asgard.Models.Student;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiresApi(api = Build.VERSION_CODES.O)
public class AddLessonActivity extends AppCompatActivity {

    // UI Components
    private CheckBox checkboxFreestyle;
    private CheckBox checkboxBreaststroke;
    private CheckBox checkboxButterfly;
    private CheckBox checkboxBackstroke;

    private RadioGroup radioLessonType;
    private RadioButton radioPrivate;
    private RadioButton radioGroup;
    private RadioButton radioEither;

    private CalendarView calendarView;
    private TimePicker timePickerStart;
    private TimePicker timePickerEnd;
    private LinearLayout layoutTimeRange;
    private Button buttonSubmit;

    // Toggle for enabling/disabling time range selection
    private SwitchMaterial switchSpecifyTime;

    // Selected values
    private LocalDate selectedDate;
    private LocalTime selectedStartTime;
    private LocalTime selectedEndTime;
    private List<Student.SwimmingStyle> selectedStyles;
    private Student.LessonType selectedLessonType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lesson);

        // Initialize UI components
        initializeViews();

        // Set up listeners
        setupListeners();
    }

    private void initializeViews() {
        // Swimming Styles Checkboxes
        checkboxFreestyle = findViewById(R.id.checkbox_freestyle);
        checkboxBreaststroke = findViewById(R.id.checkbox_breaststroke);
        checkboxButterfly = findViewById(R.id.checkbox_butterfly);
        checkboxBackstroke = findViewById(R.id.checkbox_backstroke);

        // Lesson Type Radio Group
        radioPrivate = findViewById(R.id.radio_private);
        radioGroup = findViewById(R.id.radio_group);
        radioEither = findViewById(R.id.radio_either);

        // Calendar and Time Pickers
        calendarView = findViewById(R.id.instructorCalendarView);
        timePickerStart = findViewById(R.id.time_picker_start);
        timePickerEnd = findViewById(R.id.time_picker_end);

        // Time Range Layout and Switch
        layoutTimeRange = findViewById(R.id.layout_time_range);
        switchSpecifyTime = findViewById(R.id.switch_specify_time);

        // Submit Button
        buttonSubmit = findViewById(R.id.button_submit);
    }

    private void setupListeners() {
        // Date Selection Listener
        if (calendarView != null) {
            calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) ->
                    selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
            );
        } else {
            Toast.makeText(this, "Calendar view not initialized", Toast.LENGTH_SHORT).show();
            Log.e("AddLessonActivity", "CalendarView is null in setupListeners()");
        }

        // Time Selection Listeners
        if (timePickerStart != null) {
            timePickerStart.setOnTimeChangedListener((view, hourOfDay, minute) ->
                    selectedStartTime = LocalTime.of(hourOfDay, minute)
            );
        }

        if (timePickerEnd != null) {
            timePickerEnd.setOnTimeChangedListener((view, hourOfDay, minute) ->
                    selectedEndTime = LocalTime.of(hourOfDay, minute)
            );
        }

        // Time Range Switch Listener
        switchSpecifyTime.setOnCheckedChangeListener((buttonView, isChecked) -> {
            layoutTimeRange.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        });

        // Submit Button Listener
        buttonSubmit.setOnClickListener(v -> submitLesson());
    }

    private void submitLesson() {
        // Validate inputs
        if (!validateInputs()) {
            return;
        }

        // Collect selected swimming styles
        selectedStyles = new ArrayList<>();
        if (checkboxFreestyle.isChecked())
            selectedStyles.add(Student.SwimmingStyle.CRAWL);
        if (checkboxBreaststroke.isChecked())
            selectedStyles.add(Student.SwimmingStyle.BREASTSTROKE);
        if (checkboxButterfly.isChecked())
            selectedStyles.add(Student.SwimmingStyle.BUTTERFLY);
        if (checkboxBackstroke.isChecked())
            selectedStyles.add(Student.SwimmingStyle.BACKSTROKE);

        // Determine lesson type
        int checkedRadioButtonId = radioLessonType.getCheckedRadioButtonId();

        if (checkedRadioButtonId == R.id.radio_private) {
            selectedLessonType = Student.LessonType.PRIVATE;
        } else if (checkedRadioButtonId == R.id.radio_group) {
            selectedLessonType = Student.LessonType.GROUP;
        } else if (checkedRadioButtonId == R.id.radio_either) {
            selectedLessonType = Student.LessonType.BOTH;
        } else {
            Toast.makeText(this, "Please select a lesson type", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create lesson object
        Lesson newLesson = createLesson();

        // TODO: In a real application, you would save this to a database or send to a backend service
        Toast.makeText(this, "Lesson Request Submitted", Toast.LENGTH_SHORT).show();
        finish();
    }

    private boolean validateInputs() {
        // Validate date selection
        if (selectedDate == null) {
            Toast.makeText(this, "Please select a date", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validate time selection
        if (selectedStartTime == null) {
            Toast.makeText(this, "Please select a start time", Toast.LENGTH_SHORT).show();
            return false;
        }

        // If time range is enabled, validate end time
        if (switchSpecifyTime.isChecked()) {
            if (selectedEndTime == null) {
                Toast.makeText(this, "Please select an end time", Toast.LENGTH_SHORT).show();
                return false;
            }

            // Validate that end time is after start time
            if (selectedEndTime.isBefore(selectedStartTime) || selectedEndTime.equals(selectedStartTime)) {
                Toast.makeText(this, "End time must be after start time", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        // Validate at least one swimming style is selected
        if (!checkboxFreestyle.isChecked() &&
                !checkboxBreaststroke.isChecked() &&
                !checkboxButterfly.isChecked() &&
                !checkboxBackstroke.isChecked()) {
            Toast.makeText(this, "Please select at least one swimming style", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validate lesson type is selected
        if (radioLessonType.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select a lesson type", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @NonNull
    private Lesson createLesson() {
        // Combine selected date and time
        LocalDateTime lessonStartDateTime = LocalDateTime.of(selectedDate, selectedStartTime);
        LocalDateTime lessonEndDateTime = switchSpecifyTime.isChecked()
                ? LocalDateTime.of(selectedDate, selectedEndTime)
                : lessonStartDateTime.plusMinutes(60); // Default 60 minutes if no end time specified

        // Calculate lesson duration
        long durationMinutes = java.time.Duration.between(lessonStartDateTime, lessonEndDateTime).toMinutes();

        // For demonstration, use a placeholder instructor ID
        String instructorId = "INSTRUCTOR_001";

        // For demonstration, just use first selected style
        return new Lesson(
                UUID.randomUUID().toString(), // Generate unique ID
                "", // Student ID would be set when a student books the lesson
                instructorId,
                selectedStyles.get(0),
                selectedLessonType,
                lessonStartDateTime,
                durationMinutes
        );
    }
}