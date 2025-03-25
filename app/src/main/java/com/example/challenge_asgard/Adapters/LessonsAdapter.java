package com.example.challenge_asgard.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.challenge_asgard.Models.Lesson;
import com.example.challenge_asgard.R;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class LessonsAdapter extends RecyclerView.Adapter<LessonsAdapter.LessonViewHolder> {
    private List<Lesson> lessonsList;

    public LessonsAdapter(List<Lesson> lessonsList) {
        this.lessonsList = lessonsList;
    }

    @NonNull
    @Override
    public LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lesson_item_layout, parent, false);
        return new LessonViewHolder(view);
    }

    @RequiresApi(api = android.os.Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull LessonViewHolder holder, int position) {
        Lesson lesson = lessonsList.get(position);

        // Format the date and time
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");

        // Set date
        holder.tvLessonDate.setText(lesson.getStartTime().format(dateFormatter));

        // Create time range string
        String timeRangeText = String.format("%s - %s (%d mins)",
                lesson.getStartTime().format(timeFormatter),
                lesson.getEndTime().format(timeFormatter),
                lesson.getDuration()
        );
        holder.tvLessonTime.setText(timeRangeText);

        // Set swimming style and lesson type
        holder.tvLessonStyle.setText(lesson.getSwimmingStyle().name());
        holder.tvLessonType.setText(lesson.getLessonType().name());
    }

    @Override
    public int getItemCount() {
        return lessonsList.size();
    }

    public void updateLessons(List<Lesson> filteredLessons) {
        this.lessonsList = filteredLessons;
        notifyDataSetChanged();
    }

    static class LessonViewHolder extends RecyclerView.ViewHolder {
        TextView tvLessonDate;
        TextView tvLessonTime;
        TextView tvLessonStyle;
        TextView tvLessonType;

        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLessonDate = itemView.findViewById(R.id.tv_lesson_date);
            tvLessonTime = itemView.findViewById(R.id.tv_lesson_time);
            tvLessonStyle = itemView.findViewById(R.id.tv_lesson_style);
            tvLessonType = itemView.findViewById(R.id.tv_lesson_type);
        }
    }
}