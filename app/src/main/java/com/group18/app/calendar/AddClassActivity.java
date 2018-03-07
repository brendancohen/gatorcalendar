package com.group18.app.calendar;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Date;

//implements interface in addClassFragment, necessary to receive information from a fragment
public class AddClassActivity extends AppCompatActivity implements addClassFragment.onFragmentEnd {

    private UFClass myobj;

    /*implement method in onFragmentEnd interface, this will receive the commitment object in addClassFragment
    and send it to MainActivity
    */
    @Override
    public void sendUFClass(UFClass ufclass) {
        Log.v("AddClassActivity", "sendUFClass called");
        myobj = ufclass;
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
            //get FragmentManager
           FragmentManager fm = getSupportFragmentManager();
           Fragment fragment = fm.findFragmentById(R.id.fragment_add_class);
           //new Fragment transaction to add addClassFragment to containver view (fragment_add_class)
           if(fragment == null){
               fragment = new addClassFragment();
               fm.beginTransaction().add(R.id.add_class_container, fragment).commit();
           }

        }

}

