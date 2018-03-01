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


public class AddClassActivity extends AppCompatActivity implements addClassFragment.onFragmentEnd {

    private UFClass myobj;
    public static final String EXTRA_UFCLASS_DATE = "com.group18.app.calendar.UFCLASS.date";


    public static Intent newIntent(Context packageContext, Date date){

        Intent intent = new Intent(packageContext, AddClassActivity.class);
        intent.putExtra(EXTRA_UFCLASS_DATE, date);
        return intent;
    }

    @Override
    public void sendUFClass(UFClass ufclass) {
        Log.v("AddClassActivity", "sendUFClass called");
        myobj = ufclass;
        Intent myIntent = new Intent();
        myIntent.putExtra("retrieveUFClass", myobj);
        setResult(Activity.RESULT_OK, myIntent);
       finish();
    }

    @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.add_class_container);
           FragmentManager fm = getSupportFragmentManager();
           Fragment fragment = fm.findFragmentById(R.id.fragment_add_class);
           if(fragment == null){
               fragment = new addClassFragment();
               fm.beginTransaction().add(R.id.add_class_container, fragment).commit();
           }

        }

}

