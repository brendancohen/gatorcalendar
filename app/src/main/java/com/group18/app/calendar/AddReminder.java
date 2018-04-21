package com.group18.app.calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
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

public class AddReminder extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private EditText reminderName, reminderNotes;
    private Button reminderTime, reminderDate;
    private Calendar mCalendar = GregorianCalendar.getInstance(Locale.ENGLISH);
    public int year;
    public int month;
    public int day;
    public int hour;
    public int min;
    private String name, notes;
    Reminders reminderObj = new Reminders("","");


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reminder);

        //create a toolbar so that we can navigate back to MainActivity if user wants to not commit a reminder anymore
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
                name = reminderName.getText().toString();
            }
        });

        //i'm not sure if this is necessary (need to test with better
        //computer and see if object makes it to main activity)
        reminderNotes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                notes = reminderNotes.getText().toString();
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

        //once commit button is pressed, check inputs, set values of name and notes & send object to MainActivity.java
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check to see if user has input a Reminder name, else set Error
                if(reminderName.getText().toString().length() == 0)
                    reminderName.setError("Event Name is Required!");

                //if EditText error, do not proceed
                if(reminderName.getError() != null || reminderNotes.getError() != null)
                    return;

                if(reminderObj.getHour() == 0 && reminderObj.getMin() == 0) {
                    Toast.makeText(AddReminder.this, "Please select a Start Time", Toast.LENGTH_SHORT).show();
                    return;
                }

                //places the name and notes into the reminderObj object
                reminderObj.setName(reminderName.getText().toString());
                reminderObj.setNotes(reminderNotes.getText().toString());

                //checks to make sure proper values are being placed into the object
//                Log.i("aly", "reminderName = " + reminderName.getText().toString());
//                Log.i("aly", "reminderNotes = " + reminderNotes.getText().toString());
                Intent intent = new Intent(AddReminder.this, MainActivity.class);
                intent.putExtra("reminderObj", reminderObj);

                //when i was testing to see if the name and notes would go to MainActivity = the answer is YES
//                intent.putExtra("Reminder Name", reminderName.getText().toString());
//                intent.putExtra("Reminder Notes", reminderNotes.getText().toString());

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    public void onDateSet(DatePicker view, int y, int m, int dayOfMonth) {
        //time is sent from ReminderDatePicker
        //initialize values
        year = y;
        month = m;
        day = dayOfMonth;
        //change the information into the same format as the class Date
        Date date = new GregorianCalendar(year,month,day).getTime();
//        Log.i("addReminder", "the date = " + date);

        //place the values into the reminderObj object
        reminderObj.setDate(date);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        //time is sent from ReminderTimePicker
        //initialize values
        this.hour = hourOfDay;
        this.min = minute;

        //place the values into the reminderObj object
        reminderObj.setHour(hourOfDay);
        reminderObj.setMin(minute);
    }
}