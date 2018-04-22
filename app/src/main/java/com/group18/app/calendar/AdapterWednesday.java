package com.group18.app.calendar;


import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Date;


/**
 * Created by alysonknowles on 2/25/18.
 */

public class AdapterWednesday extends RecyclerView.Adapter<AdapterWednesday.CustomViewHolder> {

    public ArrayList<Commitments> Wednesday_commitments = new ArrayList<Commitments>();

    public ArrayList<Commitments> sendArrayReference() {
        return Wednesday_commitments;
    }

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setonItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public AdapterWednesday(Context context, ArrayList<Commitments> commitments) {
        Log.i("Notify", "Commitments = " + commitments.size());
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        Date currentDate = calendar.getTime();
        for (int i = 0; i < commitments.size(); i++) {
            Log.i("Notify", "Entering for loop" + i);
            Commitments temp = commitments.get(i);
            //if ((currentDate.after(temp.getStart()) && currentDate.before(temp.getEnd()))) { //If we are within range of the dates
            //This condition must be changed
            if (true) {
                Log.i("Notify", "Date range met");
                if (temp.getOnTheseDays().contains("Wednesday")) { //If today is Wednesday and the class is on Wednesdays
                    if (this.Wednesday_commitments.isEmpty()) {
                        this.Wednesday_commitments.add(0, temp);
                        this.notifyItemInserted(0);

                    } else {
                        for (int k = 0; k < this.Wednesday_commitments.size(); k++) {
                            //is called when daily commitments hour is earlier than the new temp class we are trying to add
                            if (this.Wednesday_commitments.get(k).getStartHour() > temp.getStartHour() || (this.Wednesday_commitments.get(k).getStartHour() == temp.getStartHour() && this.Wednesday_commitments.get(k).getStartMinute() > temp.getStartMinute())) {
                                this.Wednesday_commitments.add(k, temp);
                                this.notifyItemInserted(k);
                                break;
                            }
                            if (k == this.Wednesday_commitments.size() - 1) {
                                this.Wednesday_commitments.add(k + 1, temp);
                                this.notifyItemInserted(k + 1);
                                break;
                            }
                        }
                    }

                }
            }
        }
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Instantiate the gesture detector with the
        // application context and an implementation of
        // GestureDetector.OnGestureListener
//        myDetector = new GestureDetectorCompat(this,this);

        //if you wanted to change the layout you can do so by replacing null with viewType
        //so if viewType == 1 then do this one otherwise do different layout
        //instantiating a view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view, parent, false);
        return new CustomViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Commitments commitment = this.Wednesday_commitments.get(position);
        holder.profName.setText(commitment.getProfessor());
        holder.className.setText(commitment.getCname());
        if (commitment.getStartMinute() == 0) {
            holder.startTime.setText(commitment.getStartHour() + ":" + commitment.getStartMinute() + "0");
        } else
            holder.startTime.setText(commitment.getStartHour() + ":" + commitment.getStartMinute());
        if (commitment.getEndMinute() == 0) {
            holder.endTime.setText(commitment.getEndHour() + ":" + commitment.getEndMinute() + "0");
        } else
            holder.endTime.setText(commitment.getEndHour() + ":" + commitment.getEndMinute());
        String[] date_no_time_start = commitment.getStart().toString().split(" ", 0);
        String[] date_no_time_end = commitment.getEnd().toString().split(" ", 0);
        holder.startDate.setText("Start: " + date_no_time_start[1] + " " + date_no_time_start[2]);
        holder.endDate.setText("End:   " + date_no_time_end[1] + " " + date_no_time_end[2]);
    }

    @Override
    public int getItemCount() {
        return Wednesday_commitments.size();
    }


//        i may need to extend Activity to this class but i can't extend more than one class
//          one way that i found is to make an interface and then extend activity to it
//          but this seems overly complicated -- must be another way

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        //can add other things to make a custom layout e.g. images or checkboxes
        TextView profName;
        TextView className;
        TextView startTime;
        TextView startDate;
        TextView endTime;
        TextView endDate;

        public CustomViewHolder(View view, OnItemClickListener listener) {
            super(view);
            //the layout is being binded to the view
            profName = (TextView) view.findViewById(R.id.ProfessorName);
            className = (TextView) view.findViewById(R.id.commitment_name);
            startTime = view.findViewById(R.id.time_start);
            startDate = view.findViewById(R.id.date_start);
            endDate = view.findViewById(R.id.date_end);
            endTime = view.findViewById(R.id.time_end);

        }
    }
}
