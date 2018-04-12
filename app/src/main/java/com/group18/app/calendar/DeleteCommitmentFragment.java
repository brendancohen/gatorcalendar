package com.group18.app.calendar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by alysonknowles on 3/19/18.
 */

public class DeleteCommitmentFragment extends DialogFragment {
    ArrayList<Commitments> commitments;
    int position;

    public interface OnInputSelected {
        void deleteView(int position);
    }

    public OnInputSelected mOnInputSelected;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //using this object to try to connect to a function in CommitmentsAdapter
        //I need to call notifyItemRemoved(getAdapterPosition());
        CommitmentsAdapter commitsObj = new CommitmentsAdapter(getActivity().getApplicationContext(), commitments, getActivity());
//        CommitmentsAdapter.CustomViewHolder mObject = new CommitmentsAdapter.CustomViewHolder(getView());

        //Send the commitments array and the position of the adapter
        //using a Bundle bc this is a fragment and
        //you can't use a non-default constructor with a fragment
        Bundle bundledData = getArguments();
        if(bundledData != null) {
            commitments = bundledData.getParcelableArrayList("commitmentsArray");
            position = bundledData.getInt("position");
        }

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Delete Commitment?");
        builder.setMessage("Are you sure you want to delete this commitment?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User wants to delete
                Toast.makeText(getActivity(), "Positive button was pressed", Toast.LENGTH_SHORT).show();
                Log.i("positive", "positive button was pressed! position = " + position);

                //removes the commitment from the array
                commitments.remove(position);
                Log.i("deleteFrag", "position = " + position);
//                commitsObj.deleteView(position);
                mOnInputSelected.deleteView(position);
//                commitsObj.notifyItemRemoved(position);
                getDialog().dismiss();
                }
            });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog

                getDialog().dismiss();
            }
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }

}
