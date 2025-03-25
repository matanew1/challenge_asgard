package com.example.challenge_asgard.Models;


import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


import java.time.LocalTime;

public class Instructor extends BaseUser {
    private InstructorAvailability availableDays;
    private LocalTime startTime;
    private LocalTime endTime;
    private String swimmingStyles;

    public enum InstructorAvailability {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public Instructor() {
        super();
    }

    public Instructor(String name, InstructorAvailability availableDays,
                      LocalTime startTime, LocalTime endTime, String swimmingStyles) {
        super(name);
        this.availableDays = availableDays;
        this.startTime = startTime;
        this.endTime = endTime;
        this.swimmingStyles = swimmingStyles;
    }

    // Getters and setters
    public InstructorAvailability getAvailableDays() { return availableDays; }
    public void setAvailableDays(InstructorAvailability availableDays) { this.availableDays = availableDays; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }

    public String getSwimmingStyles() { return swimmingStyles; }
    public void setSwimmingStyles(String swimmingStyles) { this.swimmingStyles = swimmingStyles; }
}