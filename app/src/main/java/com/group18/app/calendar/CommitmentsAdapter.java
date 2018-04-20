package com.group18.app.calendar;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by alysonknowles on 2/25/18.
 */

public class CommitmentsAdapter extends RecyclerView.Adapter<CommitmentsAdapter.CustomViewHolder> {
    private Context context;
    private ArrayList<Commitments> commitments;


    public CommitmentsAdapter(Context context, ArrayList<Commitments> commitments) {
        this.context =  context;
        this.commitments = commitments;
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
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Commitments commitment = commitments.get(position);
        holder.profName.setText(commitment.getProfessor());
        holder.className.setText(commitment.getCname());
        Context c;
        c = context;

      /*  Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, commitment.getStartHour());
        calendar.set(Calendar.MINUTE, commitment.getNotificationMinute());
        int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK);


        String channel_id = "gc_channel";
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            String channel_name = "gator_calendar_notifications";
            String channel_description = "notifications for gator calendar app";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channel_id, channel_name, importance);
            channel.setDescription(channel_description);
            //fun things
            channel.enableLights(true);
            channel.setLightColor(Color.BLUE);
            // Register the channel with the system
            notificationManager.createNotificationChannel(channel);
        }

        //Make notification
        String textTitle = "Better Hurry! Your class starts in 5 minutes!";
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, channel_id)
                .setSmallIcon(R.drawable.gc_png)
                .setContentTitle(textTitle)
                .setContentText(commitment.getCname() + " with " + commitment.getProfessor() + " at " + commitment.getStartHour() + ":" + commitment.getStartMinute())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        //Show notification (if the start time is 5 minutes before the current time)
        //Check the current day of the week
        for(int i = 0; i < commitment.getClassesPerWeek(); i++ ) {
            if(dayOfTheWeek == commitment.getClassesPerWeek()) {
                //check if the time is 5 minutes of the current time
            }
        }


        notificationManager.notify(0, notificationBuilder.build());

        Toast testing = Toast.makeText(c, "starthour:" + commitment.getStartHour() + ", startminute: " + commitment.getNotificationMinute()
                + ", endhour: " + commitment.getEndHour() + ", endminute: " + commitment.getEndMinute(), Toast.LENGTH_LONG);
        testing.show();
        holder.startTime.setText(commitment.getStartHour() + ":" + commitment.getStartMinute());
        holder.endTime.setText(commitment.getEndHour() + ":" + commitment.getEndMinute());
        String[] date_no_time_start = commitment.getStart().toString().split(" ",0);
        String[] date_no_time_end = commitment.getStart().toString().split(" ", 0);
        holder.startDate.setText("Start: "+ date_no_time_start[1] + " " + date_no_time_start[2]);
        holder.endDate.setText("End:   "+ date_no_time_end[1] + " " + date_no_time_end[2]);
   */
    }

    @Override
    public int getItemCount() {
        return commitments.size();
    }


//        i may need to extend Activity to this class but i can't extend more than one class
//          one way that i found is to make an interface and then extend activity to it
//          but this seems overly complicated -- must be another way

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        //can add other things to make a custom layout e.g. images or checkboxes
        TextView profName;
        TextView className;
        //Brooke changes
        TextView startTime;
        TextView startDate;
        TextView endTime;
        TextView endDate;

        public CustomViewHolder(View view) {
             super(view);
             //the layout is being binded to the view
            profName = (TextView) view.findViewById(R.id.ProfessorName);
            className = (TextView) view.findViewById(R.id.ClassName);
            startTime = view.findViewById(R.id.time_start);
            startDate = view.findViewById(R.id.date_start);
            endDate = view.findViewById(R.id.date_end);
            endTime = view.findViewById(R.id.time_end);

            view.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View arg0) {
//                    vv this is the code that we are working on right now

                    boolean confirmed = true;
                    android.app.FragmentManager mFragmentManager = ((Activity) context).getFragmentManager();
                    DeleteCommitmentFragment dialog = new DeleteCommitmentFragment();
                    dialog.show(mFragmentManager, "deleteCommitment");


                    return confirmed;



//                    vvv this is the code from DeleteCommitmentFragment.java
//                    boolean confirmed = true;
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                    builder.setTitle("Delete Commitment?");
//                    builder.setMessage("Are you sure you want to delete this commitment?");
//                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            // delete commitment
//                            commitments.remove(getAdapterPosition());
//                            notifyItemRemoved(getAdapterPosition());
//
//                        }
//                    });
//                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            // User cancelled the dialog
//                        }
//                    });
//
//                    AlertDialog alertDialog = builder.create();
//                    alertDialog.show();
//
//                    return true;




//               when the commitment is longClicked (aka. pressed for a couple of seconds) it will delete using .remove
//               .remove deletes commitment when it is clicked

//                    this long click works
//                    commitments.remove(getAdapterPosition());
//                    notifyItemRemoved(getAdapterPosition());
//                    return true;    // <- set to true
//
                }
            });
        }
    }
}
