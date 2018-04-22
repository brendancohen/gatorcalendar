package com.group18.app.calendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;

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

    private static final int DeleteCommitmentCode = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_weekly);

        //create a toolbar so that we can navigate back to MainActivity if user wants to not commit a reminder anymore
        Toolbar toolbar = (Toolbar) findViewById(R.id.weeklyviewtoolbar);

        setSupportActionBar(toolbar);
        toolbar.setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Stuff for Monday goes below
        /////////////////////////////////////////////////////////////////////////////////////////
        MondayRecyclerView = findViewById(R.id.my_recycler_view_0);
        //this improves performance of RecyclerView
        MondayRecyclerView.setHasFixedSize(true);
        MondayRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterMonday = new AdapterMonday(WeeklyView.this, MainActivity.myCommits);
        MondayRecyclerView.setAdapter(AdapterMonday);

        //Stuff for Tuesday goes below
        /////////////////////////////////////////////////////////////////////////////////////////
        TuesdayRecyclerView = findViewById(R.id.my_recycler_view_1);
        //this improves performance of RecyclerView
        TuesdayRecyclerView.setHasFixedSize(true);
        TuesdayRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterTuesday = new AdapterTuesday(WeeklyView.this, MainActivity.myCommits);
        TuesdayRecyclerView.setAdapter(AdapterTuesday);

        //Stuff for Wednesday goes below
        /////////////////////////////////////////////////////////////////////////////////////////
        WednesdayRecyclerView = findViewById(R.id.my_recycler_view_2);
        //this improves performance of RecyclerView
        WednesdayRecyclerView.setHasFixedSize(true);
        WednesdayRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterWednesday = new AdapterWednesday(WeeklyView.this, MainActivity.myCommits);
        WednesdayRecyclerView.setAdapter(AdapterWednesday);

        //Stuff for Thursday goes below
        /////////////////////////////////////////////////////////////////////////////////////////
        ThursdayRecyclerView = findViewById(R.id.my_recycler_view_3);
        //this improves performance of RecyclerView
        ThursdayRecyclerView.setHasFixedSize(true);
        ThursdayRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterThursday = new AdapterThursday(WeeklyView.this, MainActivity.myCommits);
        ThursdayRecyclerView.setAdapter(AdapterThursday);

        //Stuff for Friday goes below
        /////////////////////////////////////////////////////////////////////////////////////////
        FridayRecyclerView = findViewById(R.id.my_recycler_view_4);
        //this improves performance of RecyclerView
        FridayRecyclerView.setHasFixedSize(true);
        FridayRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterFriday = new AdapterFriday(WeeklyView.this, MainActivity.myCommits);
        FridayRecyclerView.setAdapter(AdapterFriday);

        //Stuff for Saturday goes below
        /////////////////////////////////////////////////////////////////////////////////////////
        SaturdayRecyclerView = findViewById(R.id.my_recycler_view_5);
        //this improves performance of RecyclerView
        SaturdayRecyclerView.setHasFixedSize(true);
        SaturdayRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterSaturday = new AdapterSaturday(WeeklyView.this, MainActivity.myCommits);
        SaturdayRecyclerView.setAdapter(AdapterSaturday);

        //Stuff for Sunday goes below
        /////////////////////////////////////////////////////////////////////////////////////////
        SundayRecyclerView = findViewById(R.id.my_recycler_view_6);
        //this improves performance of RecyclerView
        SundayRecyclerView.setHasFixedSize(true);
        SundayRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterSunday = new AdapterSunday(WeeklyView.this, MainActivity.myCommits);
        SundayRecyclerView.setAdapter(AdapterSunday);
    }
}
