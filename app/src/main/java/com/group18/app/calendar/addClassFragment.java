package com.group18.app.calendar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by eddie on 2/21/18.
 */

public class addClassFragment extends Fragment {
    private EditText entername;
    private EditText enterclass;
    private CheckBox MWF;
    private CheckBox TR;
    private Button start;
    private Button end;
    private static final String CLASS_BEGIN_DATE = "BeginDate";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_class, container, false);

        entername = v.findViewById(R.id.class_name);
        enterclass = v.findViewById(R.id.professor_name);
        start = v.findViewById(R.id.button);
        end = v.findViewById(R.id.button2);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.show(manager, CLASS_BEGIN_DATE);
            }
        });

        return v;
    }



}
