package com.example.challenge_asgard.Models;

import java.util.List;
public class Student extends User {
    private List<Lesson> bookedLessons;

    // Methods for booking/managing lessons
    public void bookLesson(Lesson lesson) {
        // Implementation
    }

    public List<Lesson> getBookedLessons() {
        return bookedLessons;
    }

    public void setType(String student) {
        // Implementation

    }
}