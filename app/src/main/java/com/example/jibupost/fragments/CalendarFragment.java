package com.example.jibupost.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jibupost.AddEventActivity;
import com.example.jibupost.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CalendarFragment extends Fragment {

    public CalendarFragment() {
        // Required empty public constructor
    }

    private GridView calendarGrid;
    private CalendarAdapter calendarAdapter;
    private TextView monthYearTextView;
    private ImageButton prevMonthButton, nextMonthButton;

    private java.util.Calendar currentCalendar = java.util.Calendar.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendarGrid = view.findViewById(R.id.calendar_grid);
        monthYearTextView = view.findViewById(R.id.calendar_month_year);
        prevMonthButton = view.findViewById(R.id.prev_month_button);
        nextMonthButton = view.findViewById(R.id.next_month_button);

        updateCalendar();


        prevMonthButton.setOnClickListener(v -> {
            currentCalendar.add(java.util.Calendar.MONTH, -1);
            updateCalendar();
        });

        nextMonthButton.setOnClickListener(v -> {
            currentCalendar.add(java.util.Calendar.MONTH, 1);
            updateCalendar();
        });

        calendarGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                calendarAdapter.setSelectedPosition(position);
            }
        });

        FloatingActionButton fabAddEvent = view.findViewById(R.id.fab_add_event);
        fabAddEvent.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddEventActivity.class);
            startActivity(intent);
        });

        return view;
    }

    private void updateCalendar() {
        int month = currentCalendar.get(java.util.Calendar.MONTH);
        int year = currentCalendar.get(java.util.Calendar.YEAR);

        String monthYearText = new SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(currentCalendar.getTime());
        monthYearTextView.setText(monthYearText);

        ArrayList<String> daysOfMonth = getDaysOfMonth(month, year);

        if (calendarAdapter == null) {
            calendarAdapter = new CalendarAdapter(getContext(), daysOfMonth, month, year);
            calendarGrid.setAdapter(calendarAdapter);
        } else {
            calendarAdapter.updateDisplayedMonthAndYear(month, year);
            calendarAdapter.setSelectedPosition(-1);
            calendarAdapter.notifyDataSetChanged();
        }
    }


    private ArrayList<String> getDaysOfMonth(int month, int year) {
        ArrayList<String> daysList = new ArrayList<>();
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(java.util.Calendar.YEAR, year);
        calendar.set(java.util.Calendar.MONTH, month);
        calendar.set(java.util.Calendar.DAY_OF_MONTH, 1);

        int daysInMonth = calendar.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        int firstDayOfWeek = calendar.get(java.util.Calendar.DAY_OF_WEEK) - 1;

        for (int i = 0; i < firstDayOfWeek; i++) {
            daysList.add("");
        }

        for (int i = 1; i <= daysInMonth; i++) {
            daysList.add(String.valueOf(i));
        }

        return daysList;
    }
}