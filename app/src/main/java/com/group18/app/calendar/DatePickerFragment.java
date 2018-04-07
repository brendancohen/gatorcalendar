package com.group18.app.calendar;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by eddie on 2/21/18.
 */

public class DatePickerFragment extends DialogFragment {

    private static final String ARG_DATE_START = "Class Starts: ";
    private static final String ARG_DATE_END = "Class Ends: ";
    private static final String ARG_DATE ="Date";
    private static final String ARG_CALENDAR = "Calendar";
    public static final String EXTRA_DATE = "com.group18.app.calendar.date";
    private DatePicker mDatePicker;

    //create a newInstance of DatePicker, called by addClassFragment, calendar is given as an argument by caller
    public static DatePickerFragment newInstance(Calendar calendar, String StartorEnd){
     Bundle args = new Bundle();
     if(StartorEnd.equals("Start")) {
         args.putString(ARG_DATE, ARG_DATE_START);
     }
     else {
         args.putString(ARG_DATE, ARG_DATE_END);
     }
     args.putSerializable(ARG_CALENDAR, calendar);
     DatePickerFragment fragment = new DatePickerFragment();
     fragment.setArguments(args);
     return fragment;

    }

    //display the view of DatePicker
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = (Calendar) getArguments().getSerializable(ARG_CALENDAR);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        /*
        calendar.clear(Calendar.HOUR);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.MILLISECOND);
        */
        String title = getArguments().getString(ARG_DATE);
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date,null);

        mDatePicker = (DatePicker) v.findViewById(R.id.dialog_date_picker);
        mDatePicker.init(year,month,day,null);
        return new AlertDialog.Builder(getActivity()).setView(v).setTitle(title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = mDatePicker.getYear();
                        int month = mDatePicker.getMonth();
                        int day = mDatePicker.getDayOfMonth();

                        Date date = new GregorianCalendar(year,month,day).getTime();
                        sendResult(Activity.RESULT_OK, date);
                    }
                }).create();

    }
//send selected date by user to the calling Fragment, aka addClassFragment
    private void sendResult(int resultCode, Date date){
        if(getTargetFragment() == null){
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
