package com.example.challenge_asgard.Models;

public enum LessonType {
    PRIVATE(45), // 45 min duration
    GROUP(60);   // 60 min duration

    private final int durationMinutes;

    LessonType(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }
}