package com.example.toshiba.mylogin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.toshiba.mylogin.R;
import com.example.toshiba.mylogin.activity.ScheduleActivity;
import com.example.toshiba.mylogin.model.MSchedule;
import com.squareup.picasso.Picasso;

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
        inflater = LayoutInflater.from(context);
        list = new ArrayList<MSchedule>();

    }

    public void setData(ArrayList<MSchedule> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_schedule, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MSchedule schedule = list.get(position);
        holder.date.setText(schedule.getDate());
        Picasso.with(context)
                .load(schedule.getImage())
                .placeholder(R.drawable.loading)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView date;
        public ImageView image;


        public MyViewHolder(View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.tvDate);
            image = (ImageView) itemView.findViewById(R.id.imgSchedule);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MSchedule schedule = list.get(getAdapterPosition());
                    ScheduleActivity.schedule=schedule;
                    context.startActivity(new Intent(context, ScheduleActivity.class));


                }
            });

        }
    }
}
