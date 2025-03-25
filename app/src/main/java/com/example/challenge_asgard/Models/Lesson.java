package com.example.challenge_asgard.Models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;

public class Lesson {
    private String id;
    private String studentId;
    private String instructorId;
    private Student.SwimmingStyle swimmingStyle;
    private Student.LessonType lessonType;
    private LocalDateTime startTime;
    private int duration;

    public Lesson() {
        super();
    }

    public Lesson(String id, String studentId, String instructorId, Student.SwimmingStyle swimmingStyle, Student.LessonType lessonType, LocalDateTime startTime, long duration) {
        this.id = id;
        this.studentId = studentId;
        this.instructorId = instructorId;
        this.swimmingStyle = swimmingStyle;
        this.lessonType = lessonType;
        this.startTime = startTime;
        // Cast long duration to int, capping at Integer.MAX_VALUE if too large
        this.duration = duration > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) duration;
    }

    // Rest of the methods remain the same...


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public Student.SwimmingStyle getSwimmingStyle() {
        return swimmingStyle;
    }

    public void setSwimmingStyle(Student.SwimmingStyle swimmingStyle) {
        this.swimmingStyle = swimmingStyle;
    }

    public Student.LessonType getLessonType() {
        return lessonType;
    }

    public void setLessonType(Student.LessonType lessonType) {
        this.lessonType = lessonType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDateTime getEndTime() {
        return startTime.plusMinutes(duration);
    }
}