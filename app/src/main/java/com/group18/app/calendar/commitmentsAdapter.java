package com.group18.app.calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;


/**
 * Created by alysonknowles on 2/25/18.
 */

public class commitmentsAdapter extends RecyclerView.Adapter<commitmentsAdapter.CustomViewHolder> {
    Context context;
    ArrayList<Commitments> commitments;
//    private GestureDetectorCompat myDetector;

//    Constructor
    public commitmentsAdapter(Context context, ArrayList<Commitments> commitments) {
        this.context = context;
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
    }

    @Override
    public int getItemCount() {
        //returns the size of the commitments array
        return commitments.size();
    }


//        i may need to extend Activity to this class but i can't extend more than one class
//          one way that i found is to make an interface and then extend activity to it
//          but this seems overly complicated -- must be another way

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        //can add other things to make a custom layout e.g. images or checkboxes
        TextView profName;
        TextView className;

        public CustomViewHolder(View view) {
             super(view);
             //the layout is being binded to the view
            profName = (TextView) view.findViewById(R.id.ProfessorName);
            className = (TextView) view.findViewById(R.id.ClassName);

            view.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View arg0) {
//                    vv this is the code that we are working on right now
                    boolean confirmed = true;
                    DeleteCommitmentFragment myDeleteCommitmentFragment = new DeleteCommitmentFragment();
                    MainActivity mainActivity = (MainActivity)context;
                    android.app.FragmentManager manager = mainActivity.getFragmentManager();



                    myDeleteCommitmentFragment.show(manager, "Delete Alert");

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
//                    return confirmed;




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
