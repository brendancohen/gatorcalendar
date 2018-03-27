package com.group18.app.calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by eddie on 2/21/18.
 */

public class addClassFragment extends Fragment {

    private EditText enterclassname, enterprofessor;
    private CheckBox MWF, TR;
    private Button startdate, enddate, commit, starttime, endtime;
    private Calendar mCalendar = Calendar.getInstance();
    private onFragmentEnd mylistener;
    private ExpandableListView mListView;
    private ExpandableListAdapter mListAdapter;
    private List<String> mlistDataHeader;
    private HashMap<String, List<String>> mListHashMap;
    Commitments obj1 = new Commitments("","","");

    private static final String CLASS_BEGIN_DATE = "BeginDate";
    private static final int START_DATE_PICKED = 1;
    private static final int END_DATE_PICKED = 0;
    private static final int TIME_START_PICKED = 2;
    private static final int TIME_END_PICKED = 3;
    public static final String DAYS = "com.group18.app.calendar.addClassFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //retain this fragment across configuration changes
        this.setRetainInstance(true);
    }

    //declare interface so that AddClassActivity can receive the Commitment object from this fragment
    public interface onFragmentEnd {
        void sendUFClass(Commitments ufclass);
    }

    //called once fragment is associated with its activity
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mylistener = (onFragmentEnd) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_class, container, false);

        enterclassname = v.findViewById(R.id.class_name);
        enterprofessor = v.findViewById(R.id.professor_name);
        startdate = v.findViewById(R.id.start_date_class);
        enddate = v.findViewById(R.id.end_date_class);
        starttime = v.findViewById(R.id.time_start_class);
        endtime = v.findViewById(R.id.time_end_class);
        //android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) getActivity().findViewById(R.id.mytoolbar);


        //mListView.expandGroup(0,true);



        //start DatePickerFragment so that user selects start date of commitment
        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendar.set(2018, 0, 8);
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mCalendar, "Start");
                dialog.setTargetFragment(addClassFragment.this, START_DATE_PICKED);
                dialog.show(manager, CLASS_BEGIN_DATE);
            }
        });

        //start DatePickerFragment so that user selects start date of commitment
        enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendar.set(2018, 3, 25);
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mCalendar, "End");
                dialog.setTargetFragment(addClassFragment.this, END_DATE_PICKED);
                dialog.show(manager, CLASS_BEGIN_DATE);
            }
        });


        starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new TimePickerFragment();
                dialogFragment.setTargetFragment(addClassFragment.this,TIME_START_PICKED);
                dialogFragment.show(getFragmentManager(),"TimePicker" );
            }
        });

        endtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new TimePickerFragment();
                dialogFragment.setTargetFragment(addClassFragment.this,TIME_END_PICKED);
                dialogFragment.show(getFragmentManager(),"TimePicker" );
            }
        });

         //adding listener, taking in user input as to class name
         enterclassname.addTextChangedListener(new TextWatcher() {


             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {
               obj1.setCname(s.toString());
             }

             @Override
             public void afterTextChanged(Editable s) {

             }
         });

        //adding listener, taking in user input as to Professor Name
         enterprofessor.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {
                 obj1.setProfessor(s.toString());
             }

             @Override
             public void afterTextChanged(Editable s) {

             }
         });
        commit = v.findViewById(R.id.commit);

        //once commit button is pressed, call interface method sendUFClass (implemented by AddClassActivity)
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check to see if user has input a class name, else set Error
               if(enterclassname.getText().toString().length() == 0)
                   enterclassname.setError("Class Name is Required!");

               //check to see if user has input a professor name, else set Error
                if(enterprofessor.getText().toString().length() == 0)
                    enterprofessor.setError("Professor Name is Required!");

                //if EditText error, do not proceed
                if(enterprofessor.getError() != null || enterclassname.getError() != null)
                    return;

               String Days = "";

               for(int i = 0 ; i < mListAdapter.getCheckedDays().size(); i++)
                   Days += mListAdapter.getCheckedDays().get(i) + ",";

                 if(Days.length() != 0 )
                     obj1.setOnTheseDays(Days);
                 else {
                     Toast.makeText(getContext(), "Please select Meeting Days in the Dropdown Menu", Toast.LENGTH_SHORT).show();
                     return;
                 }
                 mylistener.sendUFClass(obj1);
            }
        });
        return v;
    }

    //receive information from DatePicker Fragment (dates)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK)
            return;
        if(requestCode == START_DATE_PICKED){
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            obj1.setStart(date);
        }
        if(requestCode == END_DATE_PICKED){
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            obj1.setEnd(date);
        }
        if(requestCode == TIME_START_PICKED){
            int hour = (int) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME_HOUR);
            int minute = (int) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME_MINUTE);
            obj1.setStartHour(hour);
            obj1.setStartMinute(minute);
        }
        if(requestCode == TIME_END_PICKED){
            int hour = (int) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME_HOUR);
            int minute = (int) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME_MINUTE);
            obj1.setEndHour(hour);
            obj1.setEndMinute(minute);
        }
    }


    public void initializeData(){
        mlistDataHeader = new ArrayList<>();
        mListHashMap = new HashMap<>();

        mlistDataHeader.add("Days on Campus");
        List<String> Days = new ArrayList<>();
        Days.add("Monday");
        Days.add("Tuesday");
        Days.add("Wednesday");
        Days.add("Thursday");
        Days.add("Friday");
        Days.add("Saturday");
        Days.add("Sunday");

        mListHashMap.put(mlistDataHeader.get(0), Days);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView = (ExpandableListView) view.findViewById(R.id.expandableList);
        initializeData();
        if(savedInstanceState != null) {
            mListAdapter = new com.group18.app.calendar.ExpandableListAdapter(this.getContext(), mlistDataHeader, mListHashMap, savedInstanceState.getStringArrayList(DAYS));
        }
        else
            mListAdapter = new com.group18.app.calendar.ExpandableListAdapter(this.getContext(), mlistDataHeader, mListHashMap, null);
        if(mListView != null)
        mListView.setAdapter(mListAdapter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if(!mListAdapter.getCheckedDays().isEmpty())
        outState.putStringArrayList(DAYS, mListAdapter.getCheckedDays());
        super.onSaveInstanceState(outState);
    }
}
