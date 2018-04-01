package com.group18.app.calendar;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.group18.app.calendar.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by eddie on 3/17/18.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<String> mlistDataHeader;
    private HashMap<String, List<String>> mListHashMap;
    private ArrayList<String> checkedDays = new ArrayList<>();

    public ExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listHashMap, ArrayList<String> usercheckedDays){
        mContext = context;
        mlistDataHeader = listDataHeader;
        mListHashMap = listHashMap;
        if(usercheckedDays != null)
            checkedDays = usercheckedDays;

    }

    @Override
    public int getGroupCount() {
        return mlistDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mListHashMap.get(mlistDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mlistDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mListHashMap.get(mlistDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String headerTitle = (String) getGroup(groupPosition);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group,null);
        }

        TextView lblListHeader = convertView.findViewById(R.id.ListHeader);
        lblListHeader.setText(headerTitle);
        ImageView indicator = convertView.findViewById(R.id.ivGroupIndicator);
        indicator.setSelected(isExpanded);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group_item,null);
        }
        TextView txtListChild = convertView.findViewById(R.id.ListHeaderitem);
        txtListChild.setTypeface(null, Typeface.BOLD);
        txtListChild.setText(childText);
        CheckBox mCheckbox = convertView.findViewById(R.id.checkbox);

        mCheckbox.setChecked(ShouldIBeChecked(childPosition));

        mCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    switch(childPosition){
                        case 0:
                            checkedDays.add("Monday");
                            break;
                        case 1:
                            checkedDays.add("Tuesday");
                            break;
                        case 2:
                            checkedDays.add("Wednesday");
                            break;
                        case 3:
                            checkedDays.add("Thursday");
                            break;
                        case 4:
                            checkedDays.add("Friday");
                            break;
                        case 5:
                            checkedDays.add("Saturday");
                            break;
                        case 6:
                            checkedDays.add("Sunday");
                            break;

                    }
                }
                else
                    switch(childPosition){
                        case 0:
                            checkedDays.remove("Monday");
                            break;
                        case 1:
                            checkedDays.remove("Tuesday");
                            break;
                        case 2:
                            checkedDays.remove("Wednesday");
                            break;
                        case 3:
                            checkedDays.remove("Thursday");
                            break;
                        case 4:
                            checkedDays.remove("Friday");
                            break;
                        case 5:
                            checkedDays.remove("Saturday");
                            break;
                        case 6:
                            checkedDays.remove("Sunday");
                            break;
                    }
            }
        });
        return convertView;
    }

    public ArrayList<String> getCheckedDays() {
        return checkedDays;
    }

    public boolean ShouldIBeChecked(int childPosition){

        if(checkedDays.isEmpty())
            return false;
            for(int i = 0 ; i < checkedDays.size(); i++) {

                if (checkedDays.get(i).equals("Monday") && childPosition == 0)
                    return true;
                if (checkedDays.get(i).equals("Tuesday") && childPosition == 1)
                    return true;
                if (checkedDays.get(i).equals("Wednesday") && childPosition == 2)
                    return true;
                if (checkedDays.get(i).equals("Thursday") && childPosition == 3)
                    return true;
                if (checkedDays.get(i).equals("Friday") && childPosition == 4)
                    return true;
                if (checkedDays.get(i).equals("Saturday") && childPosition == 5)
                    return true;
                if (checkedDays.get(i).equals("Sunday") && childPosition == 6)
                    return true;
            }
            return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
