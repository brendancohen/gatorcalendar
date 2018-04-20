package com.group18.app.calendar;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.Date;


public class AddClassActivity extends AppCompatActivity implements addClassFragment.onFragmentEnd {

    /*implemented method in onFragmentEnd interface, this will receive the commitment object in addClassFragment
    and send it to MainActivity
    */
    @Override
    public void sendUFClass(Commitments ufclass) {
        Log.v("AddClassActivity", "sendUFClass called");
        Commitments myobj = ufclass;
        Intent myIntent = new Intent();
        myIntent.putExtra("retrieveUFClass", myobj);
        setResult(Activity.RESULT_OK, myIntent);
        //finish ends this activity, will pop off AddClassActivity from activity stack
       finish();
    }

    @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.add_class_container);


            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentByTag("addclassFragment");

           // If the Fragment is non-null, then it is currently being
           // retained across a configuration change.
           if(fragment == null){
               fragment = new addClassFragment();
               fm.beginTransaction().add(R.id.add_class_container, fragment, "addclassFragment").commit();
           }

        }

}

