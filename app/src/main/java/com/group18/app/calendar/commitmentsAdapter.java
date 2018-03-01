package com.group18.app.calendar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by alysonknowles on 2/25/18.
 */

public class commitmentsAdapter extends RecyclerView.Adapter<commitmentsAdapter.CustomViewHolder> {
    Context context;
    ArrayList<Commitments> commitments;

//    Constructor
    public commitmentsAdapter(Context context, ArrayList<Commitments> commitments) {
        this.context = context;
        this.commitments = commitments;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //if you wanted to change the layout you can do so by replacing null with viewType
        //so if viewType == 1 then do this one otherwise do different layout
        //instantiating a view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view, null, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Commitments commitment = commitments.get(position);
        holder.textView.setText(commitment.professor);
    }

    @Override
    public int getItemCount() {
        //returns the size of the commitments array
        return commitments.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        //can add other things to make a custom layout e.g. images or checkboxes
        TextView textView;

        public CustomViewHolder(View view) {
             super(view);
             //the layout is being binded to the view
             textView = (TextView) view.findViewById(R.id.textView);

             view.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     //gets rid of a commitment when it is clicked
                     commitments.remove(getAdapterPosition());
                     notifyItemRemoved(getAdapterPosition());
                 }
             });
        }
    }
}
