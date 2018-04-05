package com.group18.app.calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by alysonknowles on 3/19/18.
 */

public class DeleteCommitmentFragment extends DialogFragment {

    public interface OnInputSelected {
//        public boolean pressed;
        void sendInput(boolean input);
    }

    public OnInputSelected mOnInputSelected;
//    boolean delete;
    int position;

    ArrayList<Commitments> commitments;

    //    Constructor
//    public DeleteCommitmentFragment(ArrayList<Commitments> commitments, int position) {
//        this.commitments = commitments;
//        this.position = position;
//    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        Bundle bundledData = getArguments();
        if(bundledData != null) {
            commitments = bundledData.getParcelableArrayList("commitmentsArray");
            position = bundledData.getInt("position");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Delete Commitment?");
        builder.setMessage("Are you sure you want to delete this commitment?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User wants to delete
//                delete = true;
                Toast.makeText(getActivity(), "Positive button was pressed", Toast.LENGTH_SHORT).show();
//                mOnInputSelected.sendInput(delete);
                Log.i("positive", "positive button was pressed! position = " + position);
                commitments.remove(position);
//                notifyItemRemoved(position);
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

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        try {
//            Log.i("edge", "we are about to see a change");
//            mOnInputSelected = (OnInputSelected) getActivity();
//            Log.i("didIMakeIt?", "yayayayayayayayayay i made it " + mOnInputSelected);
//        } catch(ClassCastException e) {
//            Log.e("Incorrect", "onAttach ClassCastException : " + e.getMessage());
//        }
//    }

}
