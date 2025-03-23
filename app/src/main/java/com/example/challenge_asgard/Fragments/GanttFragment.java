package com.example.challenge_asgard.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.challenge_asgard.Adapters.GanttAdapter;
import com.example.challenge_asgard.Models.Lesson;
import com.example.challenge_asgard.R;
import com.google.android.material.tabs.TabLayout;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GanttFragment extends Fragment {

    private TabLayout tabLayoutWeek;
    private RecyclerView recyclerGantt;
    private List<Lesson> allLessons = new ArrayList<>();  // List of all lessons
    private List<Lesson> filteredLessons = new ArrayList<>(); // Filtered lessons for selected day

    public GanttFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gantt, container, false);

        tabLayoutWeek = view.findViewById(R.id.tab_layout_week);
        recyclerGantt = view.findViewById(R.id.recycler_gantt);


        // TODO: need to initialize allLessons with data from the database

        // Setup the adapter with filtered lessons
        GanttAdapter adapter = new GanttAdapter(filteredLessons);
        recyclerGantt.setAdapter(adapter);
        recyclerGantt.setLayoutManager(new LinearLayoutManager(getContext()));

        setupTabs();

        return view;
    }

    private void setupTabs() {
        tabLayoutWeek.addTab(tabLayoutWeek.newTab().setText("Sunday"));
        tabLayoutWeek.addTab(tabLayoutWeek.newTab().setText("Monday"));
        tabLayoutWeek.addTab(tabLayoutWeek.newTab().setText("Tuesday"));
        tabLayoutWeek.addTab(tabLayoutWeek.newTab().setText("Wednesday"));
        tabLayoutWeek.addTab(tabLayoutWeek.newTab().setText("Thursday"));
        tabLayoutWeek.addTab(tabLayoutWeek.newTab().setText("Friday"));

        tabLayoutWeek.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Filter lessons based on the selected day
                DayOfWeek selectedDay = DayOfWeek.of(tab.getPosition() + 1);
                filteredLessons.clear();
                for (Lesson lesson : allLessons) {
                    if (lesson.getStartDateTime().getDayOfWeek() == selectedDay) {
                        filteredLessons.add(lesson);
                    }
                }
                recyclerGantt.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Optionally, handle unselection if needed
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Optionally, handle reselection if needed
            }
        });
    }
}
