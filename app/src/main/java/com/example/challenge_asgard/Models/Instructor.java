package com.example.challenge_asgard.Models;


import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Instructor extends User {
    private List<SwimStyle> teachableStyles;
    private List<Availability> availabilities;
    private List<Lesson> scheduledLessons;


    // Methods for managing availability and lessons
    public boolean canTeach(SwimStyle style) {
        return teachableStyles.contains(style);
    }

    public boolean isAvailable(LocalDateTime dateTime, int durationMinutes) {
        // Implementation to check if instructor is available
        return false;
    }

    public void addAvailability(DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        // Implementation
    }

    public void setType(String instructor) {
    }

    public Availability[] getAvailabilities() {
        return new Availability[0];
    }
}