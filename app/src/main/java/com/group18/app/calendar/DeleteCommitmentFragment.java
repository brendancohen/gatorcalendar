package com.group18.app.calendar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by alysonknowles on 3/19/18.
 */

public class DeleteCommitmentFragment extends DialogFragment {

    public interface OnInputSelected {
        void sendInput(boolean input);
    }

    public OnInputSelected mOnInputSelected;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Delete Commitment?");
        builder.setMessage("Are you sure you want to delete this commitment?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User wants to delete
                boolean delete = true;
                Toast.makeText(getActivity(), "Positive button was pressed", Toast.LENGTH_SHORT).show();
                mOnInputSelected.sendInput(delete);
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
//    private Button mOpenDialog;
//    public TextView mInputDisplay;
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundle) {
//        View view = inflater.inflate(R.layout.row_view, container, false);
//        mOpenDialog = view.findViewById(R.id.open_dialog);
//        mInputDisplay = view.findViewById(R.id.input_display);
//
//        mOpenDialog.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //left off here at minute 6:27 of CodingWithMitch video
//            }
//        });
//
//        return view;
//    }
//
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnInputSelected = (OnInputSelected) getActivity();
        } catch(ClassCastException e) {
            Log.e("Incorrect", "onAttach ClassCastException : " + e.getMessage());
        }
    }

//    public boolean sendDeleteBool() {
//        return true;
//    }
}
