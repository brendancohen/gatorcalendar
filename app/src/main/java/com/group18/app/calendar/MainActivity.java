package com.group18.app.calendar;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

//this is the Activity that is launched when app is started, see manifest file
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar mytoolbar;
    private NavigationView myNavView;
    private DrawerLayout myDrawerLayout;
    private Button startaddClass;
//    private Button test;
    private ArrayList<Commitments> myCommits = new ArrayList<>();
    private boolean mScheduleVisible = true;
    private static final String SAVED_SCHEDULE_VISIBLE = "schedule";
    private static final int AddClassCode = 0; //code used to identify result information coming from AddClassActivity
    private static final int DeleteFragmentCode = 1;
    private Context mContext;
    private SQLiteDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);
//        test = findViewById(R.id.test_button);
        mytoolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(mytoolbar);
        mytoolbar.setTitle(R.string.app_name);


        //upon rotation, activity is recreated, retrieve icon status from savedInstanceState
        if(savedInstanceState != null){
            mScheduleVisible = savedInstanceState.getBoolean(SAVED_SCHEDULE_VISIBLE);
        }

        myNavView = findViewById(R.id.nav_view);
        myNavView.setNavigationItemSelectedListener(this);

        myDrawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,myDrawerLayout,mytoolbar,R.string.open_drawer,R.string.close_drawer);
        myDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //startaddClass will start AddClassActivity for result
        startaddClass = findViewById(R.id.start_add_class);
        startaddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddClassActivity.class);
                startActivityForResult(intent,AddClassCode);
            }
        });

//        test.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    //called when Activity is being destroyed and relevant data should be saved
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SCHEDULE_VISIBLE, mScheduleVisible);
    }

    //called before menu is shown
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(!mScheduleVisible)
            menu.findItem(R.id.schedule).setIcon(R.drawable.calendar);
        return super.onPrepareOptionsMenu(menu);
    }

    //create Optionsmenu on toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //what do we do if an item on the menu bar is selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.nav:
                Intent intent = new Intent (this, MapActivity.class);
                startActivity(intent);
                Toast.makeText(this, "We are still building this ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.schedule:
                Toast.makeText(this, "What setting are we including here? ", Toast.LENGTH_SHORT).show();
                if(mScheduleVisible)
                item.setIcon(R.drawable.calendar);
                else
                    item.setIcon(R.drawable.schedule);
                mScheduleVisible = !mScheduleVisible;
                break;

        }
        return super.onOptionsItemSelected(item);
    }

//what do we do if an option is selected on the navigation drawer, answer below
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.home:
                Toast.makeText(this,"Home",Toast.LENGTH_SHORT).show();
                break;
            case R.id.cal_view:
                Toast.makeText(this,"Calendar",Toast.LENGTH_SHORT).show();
                break;
            case R.id.classes:
                Toast.makeText(this,"Classes",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tasks:
                Toast.makeText(this,"Tasks",Toast.LENGTH_SHORT).show();
                break;
            case R.id.commute:
                Toast.makeText(this,"Commute",Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings_id:
                Intent intent = new Intent (this, SettingsActivity.class);
                startActivity(intent);
                Toast.makeText(this,"Settings",Toast.LENGTH_SHORT).show();
                break;
            case R.id.Help_id:
                Toast.makeText(this,"Help",Toast.LENGTH_SHORT).show();
                break;
        }
        myDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //closes drawer after an option is pressed on navigation drawer or if area not on navigation drawer is pressed
    @Override
    public void onBackPressed() {
        if(myDrawerLayout.isDrawerOpen(GravityCompat.START)){
            myDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        super.onBackPressed();
    }

    //@Override //Function called after a commitment is committed. It adds the commitment to the myCommits array
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if(resultCode != Activity.RESULT_OK)
                return;
            if(requestCode == AddClassCode){
                Commitments tempclass =  data.getParcelableExtra("retrieveUFClass");
                myCommits.add(tempclass);
            }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Commitments com1 = new Commitments("Fox", "Soft Eng", "MWF");
        //myCommits.add(com1);
        commitmentsAdapter cCommitmentsAdapter = new commitmentsAdapter(getApplicationContext(), myCommits, MainActivity.this);
        recyclerView.setAdapter(cCommitmentsAdapter);
    }
/*
    public void callDeleteFragment() {

    }
    */
}
