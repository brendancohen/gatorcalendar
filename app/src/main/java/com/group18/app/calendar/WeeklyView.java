package com.group18.app.calendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.group18.app.calendar.database.CommitmentSchema;

import java.util.UUID;


public class WeeklyView extends AppCompatActivity {
    private RecyclerView MondayRecyclerView;
    private AdapterMonday AdapterMonday;

    private RecyclerView TuesdayRecyclerView;
    private AdapterTuesday AdapterTuesday;

    private RecyclerView WednesdayRecyclerView;
    private AdapterWednesday AdapterWednesday;

    private RecyclerView ThursdayRecyclerView;
    private AdapterThursday AdapterThursday;

    private RecyclerView FridayRecyclerView;
    private AdapterFriday AdapterFriday;

    private RecyclerView SaturdayRecyclerView;
    private AdapterSaturday AdapterSaturday;

    private RecyclerView SundayRecyclerView;
    private AdapterSunday AdapterSunday;
    private TextView DayOne;
    private TextView DayTwo;
    private TextView DayThree;
    private TextView DayFour;
    private TextView DayFive;
    private TextView DaySix;
    private TextView DaySeven;

    private static final int DeleteCommitmentCode = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_weekly);
        DayOne = (TextView) findViewById(R.id.day1);
        DayTwo = (TextView) findViewById(R.id.day2);
        DayThree = (TextView) findViewById(R.id.day3);
        DayFour = (TextView) findViewById(R.id.day4);
        DayFive = (TextView) findViewById(R.id.day5);
        DaySix = (TextView) findViewById(R.id.day6);
        DaySeven = (TextView) findViewById(R.id.day7);

        if (MainActivity.first_day_monday) {
            DayOne.setText(R.string.monday);
            DayTwo.setText(R.string.tuesday);
            DayThree.setText(R.string.wednesday);
            DayFour.setText(R.string.thursday);
            DayFive.setText(R.string.friday);
            DaySix.setText(R.string.saturday);
            DaySeven.setText(R.string.sunday);
        } else {
            DayOne.setText(R.string.sunday);
            DayTwo.setText(R.string.monday);
            DayThree.setText(R.string.tuesday);
            DayFour.setText(R.string.wednesday);
            DayFive.setText(R.string.thursday);
            DaySix.setText(R.string.friday);
            DaySeven.setText(R.string.saturday);
        }

        //create a toolbar so that we can navigate back to MainActivity if user wants to not commit a reminder anymore
        Toolbar toolbar = (Toolbar) findViewById(R.id.weeklyviewtoolbar);

        setSupportActionBar(toolbar);
        toolbar.setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Stuff for Monday goes below
        /////////////////////////////////////////////////////////////////////////////////////////
        if (MainActivity.first_day_monday) {
            MondayRecyclerView = findViewById(R.id.my_recycler_view_0);
        } else {
            MondayRecyclerView = findViewById(R.id.my_recycler_view_1);
        }
        //this improves performance of RecyclerView
        MondayRecyclerView.setHasFixedSize(true);
        MondayRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterMonday = new AdapterMonday(WeeklyView.this, MainActivity.myCommits);
        MondayRecyclerView.setAdapter(AdapterMonday);

        //Stuff for Tuesday goes below
        /////////////////////////////////////////////////////////////////////////////////////////
        if (MainActivity.first_day_monday) {
            TuesdayRecyclerView = findViewById(R.id.my_recycler_view_1);
        } else {
            TuesdayRecyclerView = findViewById(R.id.my_recycler_view_2);
        }
        //this improves performance of RecyclerView
        TuesdayRecyclerView.setHasFixedSize(true);
        TuesdayRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterTuesday = new AdapterTuesday(WeeklyView.this, MainActivity.myCommits);
        TuesdayRecyclerView.setAdapter(AdapterTuesday);

        //Stuff for Wednesday goes below
        /////////////////////////////////////////////////////////////////////////////////////////
        if (MainActivity.first_day_monday) {
            WednesdayRecyclerView = findViewById(R.id.my_recycler_view_2);
        } else {
            WednesdayRecyclerView = findViewById(R.id.my_recycler_view_3);
        }
        //this improves performance of RecyclerView
        WednesdayRecyclerView.setHasFixedSize(true);
        WednesdayRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterWednesday = new AdapterWednesday(WeeklyView.this, MainActivity.myCommits);
        WednesdayRecyclerView.setAdapter(AdapterWednesday);

        //Stuff for Thursday goes below
        /////////////////////////////////////////////////////////////////////////////////////////
        if (MainActivity.first_day_monday) {
            ThursdayRecyclerView = findViewById(R.id.my_recycler_view_3);
        } else {
            ThursdayRecyclerView = findViewById(R.id.my_recycler_view_4);
        }
        //this improves performance of RecyclerView
        ThursdayRecyclerView.setHasFixedSize(true);
        ThursdayRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterThursday = new AdapterThursday(WeeklyView.this, MainActivity.myCommits);
        ThursdayRecyclerView.setAdapter(AdapterThursday);

        //Stuff for Friday goes below
        /////////////////////////////////////////////////////////////////////////////////////////
        if (MainActivity.first_day_monday) {
            FridayRecyclerView = findViewById(R.id.my_recycler_view_4);
        } else {
            FridayRecyclerView = findViewById(R.id.my_recycler_view_5);
        }
        //this improves performance of RecyclerView
        FridayRecyclerView.setHasFixedSize(true);
        FridayRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterFriday = new AdapterFriday(WeeklyView.this, MainActivity.myCommits);
        FridayRecyclerView.setAdapter(AdapterFriday);

        //Stuff for Saturday goes below
        /////////////////////////////////////////////////////////////////////////////////////////
        if (MainActivity.first_day_monday) {
            SaturdayRecyclerView = findViewById(R.id.my_recycler_view_5);
        } else {
            SaturdayRecyclerView = findViewById(R.id.my_recycler_view_6);
        }
        //this improves performance of RecyclerView
        SaturdayRecyclerView.setHasFixedSize(true);
        SaturdayRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterSaturday = new AdapterSaturday(WeeklyView.this, MainActivity.myCommits);
        SaturdayRecyclerView.setAdapter(AdapterSaturday);

        //Stuff for Sunday goes below
        /////////////////////////////////////////////////////////////////////////////////////////
        if (MainActivity.first_day_monday) {
            SundayRecyclerView = findViewById(R.id.my_recycler_view_6);
        } else {
            SundayRecyclerView = findViewById(R.id.my_recycler_view_0);
        }
        //this improves performance of RecyclerView
        SundayRecyclerView.setHasFixedSize(true);
        SundayRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterSunday = new AdapterSunday(WeeklyView.this, MainActivity.myCommits);
        SundayRecyclerView.setAdapter(AdapterSunday);
    }
}
