package com.group18.app.calendar;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class AddClassActivity extends AppCompatActivity {

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

