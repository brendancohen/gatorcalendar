package com.group18.app.calendar;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import com.group18.app.calendar.database.CommitmentHelper;
import com.group18.app.calendar.database.CommitmentSchema;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//this is the Activity that is launched when app is started, see manifest file
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout myDrawerLayout;
    private ArrayList<Commitments> myCommits = new ArrayList<>();
    private boolean mScheduleVisible = true;

    private static final String SAVED_DATABASE = "database";
    private static final String SAVED_SCHEDULE_VISIBLE = "schedule";
    private static final int AddClassCode = 0; //code used to identify result information coming from AddClassActivity
    private static final int DeleteFragmentCode = 1;
    private static final int AddReminderCode = 2;
    private CommitmentHelper mDbHelper;
    private SQLiteDatabase mDatabase;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String retrieve = "ret";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mDbHelper = new CommitmentHelper(getApplicationContext());
        mDatabase = mDbHelper.getWritableDatabase();
        setContentView(R.layout.navigation_drawer);
        Toolbar mytoolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(mytoolbar);
        mytoolbar.setTitle(R.string.app_name);
        mRecyclerView = findViewById(R.id.my_recycler_view);

        //this improves performance of Recyclerview
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CommitmentsAdapter(MainActivity.this, myCommits);
        mRecyclerView.setAdapter(mAdapter);

        if(myCommits.isEmpty())
            LoadDatabase();

        //upon rotation, activity is recreated, retrieve icon status from savedInstanceState
        if(savedInstanceState != null){
            mScheduleVisible = savedInstanceState.getBoolean(SAVED_SCHEDULE_VISIBLE);
        }


        NavigationView myNavView = findViewById(R.id.nav_view);
        myNavView.setNavigationItemSelectedListener(this);
        myDrawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,myDrawerLayout, mytoolbar,R.string.open_drawer,R.string.close_drawer);
        myDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Button startaddClass = findViewById(R.id.start_add_class);
        startaddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddClassActivity.class);
                startActivityForResult(intent,AddClassCode);
            }
        });

        Button startReminder = findViewById(R.id.start_reminder);
        startReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddReminder.class);
                startActivityForResult(intent, AddReminderCode);
            }
        });
    }

    //called when Activity is being destroyed and relevant data should be saved
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //save icon status (which is one is viewable)
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

    @Override //Function called after a commitment is committed. It adds the commitment to the myCommits array
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

            //if we receive a bad result from the activity, do nothing
            if(resultCode != Activity.RESULT_OK)
                return;

            //if the activity reporting back is AddClassActivity, do the following
            if(requestCode == AddClassCode){
                Commitments tempclass =  data.getParcelableExtra("retrieveUFClass");
                myCommits.add(tempclass);
                mDatabase.insert(CommitmentSchema.CommitmentTable.NAME, null, getContentValues(tempclass));
                mAdapter.notifyItemInserted(myCommits.size()-1);
            }
    }

    /*
    method will take a commitment object and insert it into a ContentValues object,
    it will return a ContentValues object that is ready to be inserted into the database
    */

    private static ContentValues getContentValues(Commitments my_commitment){
        ContentValues values = new ContentValues();
        values.put(CommitmentSchema.CommitmentTable.Cols.PROFESSOR, my_commitment.getProfessor());
        values.put(CommitmentSchema.CommitmentTable.Cols.CNAME, my_commitment.getCname());
        values.put(CommitmentSchema.CommitmentTable.Cols.ID, my_commitment.getPrimarykey().toString());
        values.put(CommitmentSchema.CommitmentTable.Cols.ONTHESEDAYS, my_commitment.getOnTheseDays());
        values.put(CommitmentSchema.CommitmentTable.Cols.START, my_commitment.getStart().toString());
        values.put(CommitmentSchema.CommitmentTable.Cols.END, my_commitment.getEnd().toString());
        return values;
    }

    private void LoadDatabase(){

        Cursor cursor = mDatabase.query(CommitmentSchema.CommitmentTable.NAME, null,
                null, null, null, null, null);

            try {
                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {

                    String professor = cursor.getString(cursor.getColumnIndex(CommitmentSchema.CommitmentTable.Cols.PROFESSOR));
                    String cname = cursor.getString(cursor.getColumnIndex(CommitmentSchema.CommitmentTable.Cols.CNAME));
                    String id = cursor.getString(cursor.getColumnIndex(CommitmentSchema.CommitmentTable.Cols.ID));
                    String days = cursor.getString(cursor.getColumnIndex(CommitmentSchema.CommitmentTable.Cols.ONTHESEDAYS));
                    String start = cursor.getString(cursor.getColumnIndex(CommitmentSchema.CommitmentTable.Cols.START));
                    String end = cursor.getString(cursor.getColumnIndex(CommitmentSchema.CommitmentTable.Cols.END));

                    SimpleDateFormat stringformatter = new SimpleDateFormat("E MMM dd HH:mm:ss z YYYY");
                    Date startdate = stringformatter.parse(start);
                    Date enddate = stringformatter.parse(end);

                    Commitments obj1 = new Commitments(professor, cname, days);
                    obj1.setPrimarykey(id);
                    obj1.setStart(startdate);
                    obj1.setEnd(enddate);
                    myCommits.add(obj1);
                    cursor.moveToNext();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
                cursor.close();
            }


    }
}
