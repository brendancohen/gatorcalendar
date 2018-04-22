package com.group18.app.calendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class HelpActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_layout);
        Log.i("here", "i made it to helpActivity");
//        setContentView(R.layout.help_layout);

        //create a toolbar so that we can navigate back to MainActivity if user wants to not commit a reminder anymore
        Toolbar toolbar = (Toolbar) findViewById(R.id.helptoolbar);

        setSupportActionBar(toolbar);
        toolbar.setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
