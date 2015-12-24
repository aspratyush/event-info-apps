package com.example.toshiba.mylogin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.toshiba.mylogin.R;
import com.example.toshiba.mylogin.activity.ScheduleActivity;
import com.example.toshiba.mylogin.model.MSchedule;


import java.util.ArrayList;


/**
 * Created by Jewel on 12/20/2015.
 */
public class ASchedule extends RecyclerView.Adapter<ASchedule.MyViewHolder> {
    private ArrayList<MSchedule> list;
    private Context context;
    private LayoutInflater inflater;

    public ASchedule(Context context) {
        this.context = context;
        inflater=LayoutInflater.from(context);
        list = new ArrayList<MSchedule>();

    }

    public void setData(ArrayList<MSchedule> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_schedule,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MSchedule schedule = list.get(position);
        holder.date.setText(schedule.getDate()+" "+schedule.getDay());
        //holder.day.setText(schedule.getDay());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView date;


        public MyViewHolder(View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.tvDate);
           // day = (TextView) itemView.findViewById(R.id.tvDay);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MSchedule schedule= list.get(getAdapterPosition());
                    ScheduleActivity.date=schedule.getDay();
                    context.startActivity(new Intent(context, ScheduleActivity.class));


                }
            });

        }
    }
}
