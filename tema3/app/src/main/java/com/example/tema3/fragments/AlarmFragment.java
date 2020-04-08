package com.example.tema3.fragments;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tema3.R;
import com.example.tema3.activities.TodosActivity;
import com.example.tema3.models.Todo;
import com.example.tema3.util.ReminderReceiverUtil;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Objects;

import static android.content.Context.ALARM_SERVICE;

public class AlarmFragment extends Fragment {
    private Todo mTodo;

    private TextView todoTitleTextView;
    private TextView timeTextView;
    private TextView dateTextView;
    private Button timePickerButton;
    private Button datePickerButton;
    private Button notificationSetButton;

    private int hour;
    private int minute;
    private int day;
    private int month;
    private int year;

    public AlarmFragment() { }

    public AlarmFragment(Todo todo) {
        this.mTodo = todo;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alarm, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        timeTextView = view.findViewById(R.id.time_text_view);
        dateTextView = view.findViewById(R.id.date_text_view);
        todoTitleTextView = view.findViewById(R.id.todo_title_view);
        todoTitleTextView.setText(mTodo.getTitle());

        timePickerButton = view.findViewById(R.id.time_picker_button);
        datePickerButton = view.findViewById(R.id.date_picker_button);
        notificationSetButton = view.findViewById(R.id.notification_set_button);

        timePickerButton.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int hourDefault = calendar.get(Calendar.HOUR_OF_DAY);
            int minuteDefault = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view1, int hourOfDay, int minute) {
                    String time = String.format("%02d:%02d", hourOfDay, minute);
                    timeTextView.setText(time);
                    setHour(hourOfDay);
                    setMinute(minute);
                }
            }, hourDefault, minuteDefault, DateFormat.is24HourFormat(getActivity()));
            timePicker.show();
        });

        datePickerButton.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int dayDefault = calendar.get(Calendar.DAY_OF_MONTH);
            int monthDefault = calendar.get(Calendar.MONTH);
            int yearDefault = calendar.get(Calendar.YEAR);

            DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view12, int year, int month, int dayOfMonth) {
                    dateTextView.setText(dayOfMonth + "/" + DateFormatSymbols.getInstance().getMonths()[month] + "/" + year);
                    setDay(dayOfMonth);
                    setMonth(month);
                    setYear(year);
                }
            }, yearDefault, monthDefault, dayDefault);
            datePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            datePicker.show();
        });

        notificationSetButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ReminderReceiverUtil.class);
            intent.putExtra("todo_title", mTodo.getTitle());

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) Objects.requireNonNull(getContext()).getSystemService(ALARM_SERVICE);

            Calendar calendar = Calendar.getInstance();
            calendar.set(getYear(), getMonth(), getDay(), getHour(), getMinute());

            long millis = calendar.getTimeInMillis();
            Objects.requireNonNull(alarmManager).setExact(AlarmManager.RTC_WAKEUP, millis, pendingIntent);

            Toast.makeText(getContext(), "Todo reminder set!", Toast.LENGTH_SHORT).show();
            ((TodosActivity) Objects.requireNonNull(getActivity())).removeAlarmFragment();
        });
    }
}
