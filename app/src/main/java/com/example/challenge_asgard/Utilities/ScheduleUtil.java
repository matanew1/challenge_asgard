package com.example.challenge_asgard.Utilities;

import com.example.challenge_asgard.Models.Instructor;
import com.example.challenge_asgard.Models.Lesson;
import com.example.challenge_asgard.Models.LessonType;
import com.example.challenge_asgard.Models.SwimStyle;

import java.time.LocalDateTime;
import java.util.List;

public class ScheduleUtil {
    // Methods to check for conflicts, generate schedule

    public static List<Instructor> findEligibleInstructors(SwimStyle style, LocalDateTime dateTime, LessonType type) {
        // Find instructors who can teach the requested style and are available
        return null;
    }

    public static boolean hasConflict(Lesson lesson1, Lesson lesson2) {
        // Check if two lessons overlap in time
        return false;
    }
}