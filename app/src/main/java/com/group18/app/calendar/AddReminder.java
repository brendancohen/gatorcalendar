package com.group18.app.calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class AddReminder extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

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

//        reminderDate.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                DialogFragment datePicker = new ReminderDatePicker();
//                datePicker.show(getSupportFragmentManager(), "date picker");
//            }
//        });
        CalendarView calendarView = (CalendarView) findViewById(R.id.Reminder_Calendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //time is sent from ReminderDatePicker
                //initialize values
//                year = y;
//                month = m;
//                day = dayOfMonth;
                //change the information into the same format as the class Date
                Date date = new GregorianCalendar(year,month,dayOfMonth).getTime();
//        Log.i("addReminder", "the date = " + date);

                //place the values into the reminderObj object
                reminderObj.setDate(date);
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

                String content = reminderObj.getNotes();
                scheduleNotification(reminderObj.getHour(), reminderObj.getNotificationMinute(), reminderObj.getDate(), content);
                finish();
            }
        });
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

    private void scheduleNotification(int startHour, int startMinute, Date notificationDate, String content) {

        //Build channel
        Context context = AddReminder.this;
        String channel_id = "gc_channel";
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            String channel_name = "gator_calendar_notifications";
            String channel_description = "notifications for gator calendar app";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channel_id, channel_name, importance);
            channel.setDescription(channel_description);
            //fun things
            channel.enableLights(true);
            channel.setLightColor(Color.BLUE);
            // Register the channel with the system
            notificationManager.createNotificationChannel(channel);
        }

        //Build notification
        String textTitle = "Reminder for " + reminderObj.getName() + " in 5 minutes" ;
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, channel_id)
                .setSmallIcon(R.drawable.gc_png)
                .setContentTitle(textTitle)
                .setContentText(content)
                // .setContentText(className + " with " + profName + " at " + startTime)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        //Set intent
        Intent intent = new Intent(context, addClassFragment.class);
        PendingIntent activity = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        notificationBuilder.setContentIntent(activity);

        Notification notification = notificationBuilder.build();

        Intent notificationIntent = new Intent(context, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        if(startMinute < 5) {
            int leftoverMinutes = 5-startMinute;
            startMinute = 60-leftoverMinutes;
            startHour--;
        }

        Calendar calendar = Calendar.getInstance().getInstance();
        calendar.setTime(notificationDate);
     //   int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, startHour);
        calendar.set(Calendar.MINUTE, startMinute);
        calendar.set(Calendar.DAY_OF_YEAR, day);

   //           Toast testing = Toast.makeText(context, "set for " + startHour + ":"
     //                 + startMinute + ", day: " + day, Toast.LENGTH_LONG);
      //       testing.show();

        //check to see if setting isn't in the past (would trigger alarm)
        if(calendar.getTimeInMillis() < System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 7);
        }

        //set alarm
        AlarmManager alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent);

    }

}