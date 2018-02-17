package com.group18.app.calendar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.accessibility.AccessibilityManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar mytoolbar;
    private NavigationView myNavView;
    private DrawerLayout myDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);
        mytoolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(mytoolbar);
        mytoolbar.setTitle(R.string.app_name);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myNavView = findViewById(R.id.nav_view);
        myNavView.setNavigationItemSelectedListener(this);
        myDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,myDrawerLayout,mytoolbar,R.string.open_drawer,R.string.close_drawer);
        myDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.about_us_id:
                Toast.makeText(this, "We are still building this ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings_id:
                Toast.makeText(this, "What setting are we including here? ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.users_id:
                Toast.makeText(this, "Is this a valid menu option? ", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
*/
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
                Toast.makeText(this,"Settings",Toast.LENGTH_SHORT).show();
                break;
            case R.id.Help_id:
                Toast.makeText(this,"Help",Toast.LENGTH_SHORT).show();
                break;
        }
        myDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(myDrawerLayout.isDrawerOpen(GravityCompat.START)){
            myDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        super.onBackPressed();
    }
}
