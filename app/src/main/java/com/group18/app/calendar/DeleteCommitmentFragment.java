package com.group18.app.calendar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import java.util.Date;

/**
 * Created by alysonknowles on 3/19/18.
 */

public class DeleteCommitmentFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Delete Commitment?");
        builder.setMessage("Are you sure you want to delete this commitment?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // FIRE ZE MISSILES!
                }
            });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
        // Create the AlertDialog object and return it
        return builder.create();
    }

//    private void sendResult(int resultCode, Date date){
//        if(getTargetFragment() == null){
//            return;
//        }
//        Intent intent = new Intent();
////        intent.putExtra(EXTRA_DATE, date);
//        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
//    }

}
