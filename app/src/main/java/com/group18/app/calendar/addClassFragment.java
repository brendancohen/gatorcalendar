package com.group18.app.calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by eddie on 2/21/18.
 */

public class addClassFragment extends Fragment {

    private EditText enterclassname;
    private EditText enterprofessor;
    private CheckBox MWF;
    private CheckBox TR;
    private Button start;
    private Button end;
    private Button commit;
    private Calendar mCalendar = Calendar.getInstance();
    private onFragmentEnd mylistener;
    Commitments obj1 = new Commitments("","","");

    private static final String CLASS_BEGIN_DATE = "BeginDate";
    private static final int START_DATE_PICKED = 1;
    private static final int END_DATE_PICKED = 0;

    private ExpandableListView mListView;
    private ExpandableListAdapter mListAdapter;
    private List<String> mlistDataHeader;
    private HashMap<String, List<String>> mListHashMap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //declare interface so that AddClassActivity can receive the Commitment object from this activity
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
        start = v.findViewById(R.id.button);
        end = v.findViewById(R.id.button2);

        mListView = (ExpandableListView) v.findViewById(R.id.expandableList);
        initializeData();

        mListAdapter = new com.group18.app.calendar.ExpandableListAdapter(this.getContext(), mlistDataHeader, mListHashMap);

        mListView.setAdapter(mListAdapter);

        //start DatePickerFragment so that user selects start date of commitment
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendar.set(2018, 0, 8);
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mCalendar);
                dialog.setTargetFragment(addClassFragment.this, START_DATE_PICKED);
                dialog.show(manager, CLASS_BEGIN_DATE);
            }
        });

        //start DatePickerFragment so that user selects start date of commitment
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendar.set(2018, 3, 25);
                FragmentManager manager = getFragmentManager();

                DatePickerFragment dialog = DatePickerFragment.newInstance(mCalendar);
                dialog.setTargetFragment(addClassFragment.this, END_DATE_PICKED);
                dialog.show(manager, CLASS_BEGIN_DATE);
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
        commit = v.findViewById(R.id.button3);

        //once commit button is pressed, call interface method sendUFClass (implemented by AddClassActivity)
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


}
