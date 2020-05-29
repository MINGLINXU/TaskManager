package com.myapplicationdev.android.taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    private ArrayList<Task> tasks;
    private Context context;
    private TextView tvName, tvDescription;
    int resource;


    public CustomAdapter(Context context, int resource, ArrayList<Task> tasks){
        super(context, resource, tasks);

        this.context = context;
        this.tasks = tasks;
        this.resource = resource;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row, parent, false);

        tvName = (TextView) rowView.findViewById(R.id.tvName);
        tvDescription = (TextView) rowView.findViewById(R.id.tvDescription);


        Task currentTask = tasks.get(position);
        String id = Integer.toString(currentTask.getId());
        tvName.setText(id + " " + currentTask.getName());
        tvDescription.setText(currentTask.getDescription());



        return rowView;
    }

}
