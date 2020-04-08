package com.example.tema3;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreteAlarmFragment extends Fragment {

    private Button btn_time;
    private Button btn_date;
    private Button btn_alarm;
    private TextView text_time;
    private TextView text_date;
    private TimePickerDialog timePickerDialog;
    private DatePickerDialog datePickerDialog;
    private static String todoTitle;

    public CreteAlarmFragment(String title) {
        todoTitle = title;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_crete_alarm, container, false);

        btn_time = view.findViewById(R.id.btn_time);
        btn_date = view.findViewById(R.id.btn_date);
        btn_alarm = view.findViewById(R.id.btn_alarm);
        text_time = view.findViewById(R.id.text_time);
        text_date = view.findViewById(R.id.text_date);

        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minutes = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int sHour, int sMinute) {
                                String selectedHour;
                                if (sHour < 10)
                                    selectedHour = "0" + sHour;
                                else
                                    selectedHour = "" + sHour;
                                String selectedMinute;
                                if (sMinute < 10)
                                    selectedMinute = "0" + sMinute;
                                else
                                    selectedMinute = "" + sMinute;
                                text_time.setText("Selected time: "+ selectedHour + ":" + selectedMinute);
                            }
                        }, hour, minutes, true);
                timePickerDialog.show();
            }
        });

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                final int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                                String selectedDay, selectedMonth;
                                monthOfYear = monthOfYear + 1;
                                if (monthOfYear < 10)
                                    selectedMonth = "0" + monthOfYear;
                                else
                                    selectedMonth = "" + monthOfYear;
                                if (dayOfMonth < 10)
                                    selectedDay = "0" + dayOfMonth;
                                else
                                    selectedDay = "" + dayOfMonth;
                                text_date.setText("Selected date: " + selectedDay + "/" + selectedMonth + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        btn_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                calendar.setTimeInMillis(System.currentTimeMillis());

                int hour = 0, minute = 0, dayOfMonth = 0, monthOfYear = 0, year = 0;

                try {
                    hour = Integer.parseInt(text_time.getText().toString().substring(15, 17));
                    minute = Integer.parseInt(text_time.getText().toString().substring(18));
                    dayOfMonth = Integer.parseInt(text_date.getText().toString().substring(15, 17));
                    monthOfYear = Integer.parseInt(text_date.getText().toString().substring(18, 20)) - 1;
                    year = Integer.parseInt(text_date.getText().toString().substring(21));
                }   catch (Exception e)
                {
                    Toast.makeText(getContext(), "Please pick a time and a date", Toast.LENGTH_SHORT).show();
                    return;
                }

                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                calendar.set(Calendar.SECOND, 0);
            }
        });

        return view;
    }
}
