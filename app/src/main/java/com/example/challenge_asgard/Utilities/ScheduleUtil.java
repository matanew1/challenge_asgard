package com.example.challenge_asgard.Utilities;

import androidx.annotation.Nullable;

import com.example.challenge_asgard.Models.Instructor;
import com.example.challenge_asgard.Models.Lesson;
import com.example.challenge_asgard.Models.Student;

import org.jetbrains.annotations.Contract;

import java.time.LocalDateTime;
import java.util.List;

public class ScheduleUtil {
    // Methods to check for conflicts, generate schedule

    @Nullable
    @Contract(pure = true)
    public static List<Instructor> findEligibleInstructors(Student.SwimmingStyle style, LocalDateTime dateTime, Student.LessonType type) {
        // Find instructors who can teach the requested style and are available
        return null;
    }

    public static boolean hasConflict(Lesson lesson1, Lesson lesson2) {
        // Check if two lessons overlap in time
        return false;
    }
}