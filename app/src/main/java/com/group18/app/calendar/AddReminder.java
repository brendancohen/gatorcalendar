package com.group18.app.calendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class AddReminder extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    private EditText reminderName, reminderNotes;
    private Button reminderTime, reminderDate;
    private Calendar mCalendar = GregorianCalendar.getInstance(Locale.ENGLISH);
    Bundle mbundle = new Bundle();
    public static final String EXTRA_DATE = "com.group18.app.calendar.date";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reminder);

        //create a toolbar so that we can navigate back to MainActivity if user wants to not commit a class anymore
        Toolbar toolbar = (Toolbar) findViewById(R.id.remindertoolbar);

        setSupportActionBar(toolbar);
        toolbar.setTitle(null);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //initializing the values of the textboxes and the buttons
        reminderName = findViewById(R.id.commitment_name);
        reminderNotes = findViewById(R.id.notes);
        reminderDate = findViewById(R.id.start_date_reminder);
        reminderTime = findViewById(R.id.start_time_reminder);
        Date currentTime = Calendar.getInstance().getTime();
        mCalendar.setTime(currentTime);
        mCalendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);

        reminderName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mbundle.putString("reminderName", reminderName.getText().toString());
                Log.i("ads", "reminder Name = " + mbundle.getString("reminderName"));
            }
        });

        reminderNotes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mbundle.putString("reminderNotes", reminderNotes.getText().toString());
//                Log.i("aly", "ReminderName = " + mbundle.getString("reminderName") + "reminderNotes = " + mbundle.getString("   reminderNotes"));
            }
        });


        reminderTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogFragment timePicker = new ReminderTimePicker();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        reminderDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogFragment datePicker = new ReminderDatePicker();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        Button commit = findViewById(R.id.reminder_commit);

        //once commit button is pressed, call interface method sendUFClass (implemented by AddClassActivity)
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check to see if user has input a Reminder name, else set Error
//                Log.i("aly1", "reminderName = " + reminderName);
//                Log.i("alyy2", "reminderName.getText() = " + reminderName.getText());
//                Log.i("alyyy3", "reminderName.getText().toString() = " + reminderName.getText().toString());
                if(reminderName.getText().toString().length() == 0)
                    reminderName.setError("Class Name is Required!");

                Log.i("aly", "reminderName from the actual thing = " + reminderName.getText().toString());
            }
        });
        Log.i("ads2", "reminder Name = " + mbundle.getString("reminderName"));
        Intent intent = new Intent(this, MainActivity.class);
        Log.i("aly", "ReminderName = " + mbundle.getString("reminderName") + "reminderNotes = " + mbundle.getString("   reminderNotes"));
        intent.putExtra("reminderValues", mbundle);

    }
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        //the date that was chosen is sent here, i can do whatever i want with the date
        Log.i("aly", "the date picker info = " + year + " , " + month + " , " + dayOfMonth);
//        Bundle mbundle = new Bundle();
        mbundle.putInt("year", year);
        mbundle.putInt("month", month);
        mbundle.putInt("day", dayOfMonth);
//        Intent intent = new Intent();
//        intent.putExtra("date", mbundle);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        //the time that was chosen is sent here, i can do whatever i want with the time
        Log.i("aly", "the time picker info = " + hourOfDay + " , " + minute);
        mbundle.putInt("hour", hourOfDay);
        mbundle.putInt("minute", minute);

    }


}