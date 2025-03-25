package com.example.challenge_asgard.Models;

import androidx.annotation.NonNull;

public class Student extends BaseUser {
    private SwimmingStyle preferredStyle;
    private LessonType lessonPreference;

    public enum SwimmingStyle {
        CRAWL, BREASTSTROKE, BUTTERFLY, BACKSTROKE
    }

    public enum LessonType {
        PRIVATE, GROUP, BOTH
    }

    public Student() {
        super();
    }

    public Student(String name, SwimmingStyle preferredStyle, LessonType lessonPreference) {
        super(name);
        this.preferredStyle = preferredStyle;
        this.lessonPreference = lessonPreference;
    }

    public SwimmingStyle getPreferredStyle() {
        return preferredStyle;
    }

    public void setPreferredStyle(SwimmingStyle preferredStyle) {
        this.preferredStyle = preferredStyle;
    }

    public LessonType getLessonPreference() {
        return lessonPreference;
    }

    public void setLessonPreference(LessonType lessonPreference) {
        this.lessonPreference = lessonPreference;
    }

    @NonNull
    @Override
    public String toString() {
        return "Student{" +
                "preferredStyle=" + preferredStyle +
                ", lessonPreference=" + lessonPreference +
                '}';
    }
}