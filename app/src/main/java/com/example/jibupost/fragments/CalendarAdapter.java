package com.example.jibupost.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.jibupost.R;

import java.util.ArrayList;

public class CalendarAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> daysOfMonth;
    private java.util.Calendar calendar = java.util.Calendar.getInstance();
    private int currentDay = calendar.get(java.util.Calendar.DAY_OF_MONTH);
    private int currentMonth = calendar.get(java.util.Calendar.MONTH);
    private int currentYear = calendar.get(java.util.Calendar.YEAR);
    private int selectedPosition = -1;

    public CalendarAdapter(Context context, ArrayList<String> daysOfMonth, int displayedMonth, int displayedYear) {
        this.context = context;
        this.daysOfMonth = daysOfMonth;
        this.currentMonth = displayedMonth;
        this.currentYear = displayedYear;
    }

    @Override
    public int getCount() {
        return daysOfMonth.size();
    }

    @Override
    public Object getItem(int position) {
        return daysOfMonth.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.calendar_day_item, parent, false);
        }

        TextView dayTextView = convertView.findViewById(R.id.day_text_view);
        String day = daysOfMonth.get(position);
        dayTextView.setText(day);

        dayTextView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));

        if (!day.isEmpty() && isCurrentMonth() && Integer.parseInt(day) == currentDay) {
            dayTextView.setBackgroundColor(ContextCompat.getColor(context, R.color.purple_700));
        } else if (position == selectedPosition) {
            dayTextView.setBackgroundColor(ContextCompat.getColor(context, R.color.gray));
        }

        return convertView;
    }

    private boolean isCurrentMonth() {
        java.util.Calendar displayedMonthCalendar = java.util.Calendar.getInstance();
        int displayedMonth = displayedMonthCalendar.get(java.util.Calendar.MONTH);
        int displayedYear = displayedMonthCalendar.get(java.util.Calendar.YEAR);

        return displayedMonth == currentMonth && displayedYear == currentYear;
    }

    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
        notifyDataSetChanged();
    }

    public void updateDisplayedMonthAndYear(int newMonth, int newYear) {
        this.currentMonth = newMonth;
        this.currentYear = newYear;
    }
}

