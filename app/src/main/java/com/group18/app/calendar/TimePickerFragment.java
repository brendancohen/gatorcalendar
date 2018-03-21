package com.group18.app.calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import java.util.Calendar;

/**
 * Created by eddie on 3/20/18.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    public static final String EXTRA_TIME_START_HOUR = "com.group18.app.calendar.TimePickerFragment.start";
    public static final String EXTRA_TIME_START_MINUTE = "com.group18.app.calendar.TimePickerFragment.start";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,this,hour,minute,
                DateFormat.is24HourFormat(getActivity()));
        timePickerDialog.setTitle(R.string.time_picker_title);
        return  timePickerDialog;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
         sendResult(Activity.RESULT_OK, hourOfDay,minute);
    }
    private void sendResult(int resultCode, int hour, int minute){
        if(getTargetFragment() == null){
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME_START_HOUR, hour);
        intent.putExtra(EXTRA_TIME_START_MINUTE, minute);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
