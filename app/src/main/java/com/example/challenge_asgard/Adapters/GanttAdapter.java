package com.example.challenge_asgard.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.challenge_asgard.Models.Lesson;
import com.example.challenge_asgard.R;

import java.util.List;

public class GanttAdapter extends RecyclerView.Adapter<GanttAdapter.GanttViewHolder> {

    private List<Lesson> lessonList;

    // Constructor for the adapter
    public GanttAdapter(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    @NonNull
    @Override
    public GanttViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_gantt, parent, false); // Define your item layout
        return new GanttViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(GanttViewHolder holder, int position) {
        Lesson lesson = lessonList.get(position);

        // Safely handle null student and lesson properties
        if (lesson != null) {
            String studentName = (lesson.getStudent() != null) ? lesson.getStudent().getFirstName() : "Unknown Student";
            String lessonType = (lesson.getLessonType() != null) ? String.valueOf(lesson.getLessonType()) : "Unknown Lesson";
            String startDateTime = (lesson.getStartDateTime() != null) ? lesson.getStartDateTime().toString() : "Unknown Start Time";
            String endDateTime = (lesson.getEndDateTime() != null) ? lesson.getEndDateTime().toString() : "Unknown End Time";

            // Binding data to the view
            holder.lessonTitle.setText(studentName + " - " + lessonType);
            holder.lessonTime.setText(startDateTime + " to " + endDateTime);
        }
    }

    @Override
    public int getItemCount() {
        return lessonList != null ? lessonList.size() : 0; // Return the size of the list, ensure it's not null
    }

    public static class GanttViewHolder extends RecyclerView.ViewHolder {
        TextView lessonTitle, lessonTime;

        public GanttViewHolder(View itemView) {
            super(itemView);
            // Initialize your views here
            lessonTitle = itemView.findViewById(R.id.lesson_title);
            lessonTime = itemView.findViewById(R.id.lesson_time);
        }
    }
}
