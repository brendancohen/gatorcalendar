package com.group18.app.calendar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RemindersAdapter extends RecyclerView.Adapter<RemindersAdapter.CustomViewHolder>  {

        private ArrayList<Reminders> reminders;
        private com.group18.app.calendar.RemindersAdapter.OnItemClickListener mListener;

        public interface OnItemClickListener{
            void onItemClick(int position);
        }
        public void setonItemClickListener(com.group18.app.calendar.RemindersAdapter.OnItemClickListener listener){
            mListener = listener;
        }

        public RemindersAdapter(Context context, ArrayList<Reminders> reminders) {

            this.reminders = reminders;
        }

        @Override
        public com.group18.app.calendar.RemindersAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Instantiate the gesture detector with the
            // application context and an implementation of
            // GestureDetector.OnGestureListener
//        myDetector = new GestureDetectorCompat(this,this);

            //if you wanted to change the layout you can do so by replacing null with viewType
            //so if viewType == 1 then do this one otherwise do different layout
            //instantiating a view
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_row_view, parent, false);
            return new com.group18.app.calendar.RemindersAdapter.CustomViewHolder(view, mListener);
        }

        @Override
        public void onBindViewHolder(com.group18.app.calendar.RemindersAdapter.CustomViewHolder holder, int position) {
            Reminders reminder = reminders.get(position);
            holder.remName.setText(reminder.getName());
            if(reminder.getNotes().length() != 0) {
                Log.i("notes", "reminder.getNotes() = " + reminder.getNotes());
                Log.i("notes", "the length of the reminder.getNotes().length() = " + reminder.getNotes().length());
                holder.remNotes.setText(reminder.getNotes());
            }

            if(reminder.getMin() == 0) {
                holder.startTime.setText(reminder.getHour() + ":" + reminder.getMin()+"0");
            }
            else
                holder.startTime.setText(reminder.getHour() + ":" + reminder.getMin());

            String[] date_no_time_start = reminder.getDate().toString().split(" ",0);
            holder.startDate.setText(date_no_time_start[1] + " " + date_no_time_start[2]);
        }

        @Override
        public int getItemCount() {
            return reminders.size();
        }


//        i may need to extend Activity to this class but i can't extend more than one class
//          one way that i found is to make an interface and then extend activity to it
//          but this seems overly complicated -- must be another way

        public static class CustomViewHolder extends RecyclerView.ViewHolder {
            //can add other things to make a custom layout e.g. images or checkboxes
            TextView remName;
            TextView remNotes;
            TextView startTime;
            TextView startDate;

            public CustomViewHolder(View view, com.group18.app.calendar.RemindersAdapter.OnItemClickListener listener) {
                super(view);
                //the layout is being binded to the view
                remName = (TextView) view.findViewById(R.id.commitment_name);
                remNotes = (TextView) view.findViewById(R.id.notes);
                startTime = view.findViewById(R.id.time_start);
                startDate = view.findViewById(R.id.date_start);

                view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if(listener != null){
                            int position = getAdapterPosition();
                            //make sure position is valid
                            if(position != RecyclerView.NO_POSITION) {
                                listener.onItemClick(position);
                            }
                        }
                        return false;
                    }
                });
            }
        }


}

