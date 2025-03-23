package com.example.challenge_asgard;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.challenge_asgard.Models.Lesson;
import com.example.challenge_asgard.Models.LessonType;
import com.example.challenge_asgard.Models.SwimStyle;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class AddLessonActivity extends AppCompatActivity {

    private CheckBox checkBoxFreestyle, checkBoxBreaststroke, checkBoxButterfly, checkBoxBackstroke;
    private RadioGroup radioLessonType;
    private RadioButton selectedLessonType;
    private CalendarView calendarView;
    private TimePicker timePicker;
    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson); // Make sure this XML matches your layout

        // Initialize views
        checkBoxFreestyle = findViewById(R.id.checkbox_freestyle);
        checkBoxBreaststroke = findViewById(R.id.checkbox_breaststroke);
        checkBoxButterfly = findViewById(R.id.checkbox_butterfly);
        checkBoxBackstroke = findViewById(R.id.checkbox_backstroke);
        radioLessonType = findViewById(R.id.radio_lesson_type);
        calendarView = findViewById(R.id.calendar_view);
        timePicker = findViewById(R.id.time_picker);
        buttonSubmit = findViewById(R.id.button_submit);

        // Set button click listener
        buttonSubmit.setOnClickListener(view -> {

            // Get selected swim styles
            List<SwimStyle> selectedSwimStyles = new ArrayList<>();
            if (checkBoxFreestyle.isChecked()) {
                selectedSwimStyles.add(SwimStyle.FREESTYLE);
            }
            if (checkBoxBreaststroke.isChecked()) {
                selectedSwimStyles.add(SwimStyle.BREASTSTROKE);
            }
            if (checkBoxButterfly.isChecked()) {
                selectedSwimStyles.add(SwimStyle.BUTTERFLY);
            }
            if (checkBoxBackstroke.isChecked()) {
                selectedSwimStyles.add(SwimStyle.BACKSTROKE);
            }

            // Get selected lesson type
            int selectedLessonTypeId = radioLessonType.getCheckedRadioButtonId();
            selectedLessonType = findViewById(selectedLessonTypeId);

            Lesson lesson = new Lesson(
                    "1",
                    null,
                    null,
                    LocalDateTime.now(),
                    selectedSwimStyles,
                    LessonType.PRIVATE,
                    null
            );

            // Save the lesson to the database
            lesson.save();

            // Display the selected data in a Toast
            String selectedDate = getSelectedDate(selectedSwimStyles);
            Toast.makeText(AddLessonActivity.this, selectedDate, Toast.LENGTH_LONG).show();

            // Move to the next screen or close the activity
            Intent intent = new Intent(AddLessonActivity.this, StudentDashboardActivity.class);
            startActivity(intent);
        });
    }

    @NonNull
    private String getSelectedDate(@NonNull List<SwimStyle> selectedSwimStyles) {
        long selectedDate = calendarView.getDate();

        // Get selected time from TimePicker
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();

        // Build the string with selected swim styles
        StringBuilder swimStylesString = new StringBuilder();
        for (SwimStyle swimStyle : selectedSwimStyles) {
            swimStylesString.append(swimStyle.name()).append(" ");
        }

        // Return the formatted string
        return "Swim Styles: " + swimStylesString.toString() +
                "\nLesson Type: " + selectedLessonType.getText() +
                "\nDate: " + selectedDate +
                "\nTime: " + hour + ":" + minute;
    }
}
