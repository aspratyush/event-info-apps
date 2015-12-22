package com.example.toshiba.mylogin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.toshiba.mylogin.R;
import com.example.toshiba.mylogin.model.MGallery;

import java.util.ArrayList;


/**
 * Created by Jewel on 12/20/2015.
 */
public class AGalleryPhoto extends RecyclerView.Adapter<AGalleryPhoto.MyViewHolder> {
    private ArrayList<MGallery> galleries;
    private Context context;
    private LayoutInflater inflater;


    public AGalleryPhoto(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        galleries = new ArrayList<MGallery>();

    }

    public void setData(ArrayList<MGallery> galleries) {
        this.galleries = galleries;
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_gallery_photo, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MGallery schedule = galleries.get(position);
        holder.imgPhoto.setBackgroundResource(R.drawable.simple);


    }

    @Override
    public int getItemCount() {
        return galleries.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgPhoto;


        public MyViewHolder(View itemView) {
            super(itemView);

            imgPhoto = (ImageView) itemView.findViewById(R.id.imgPhoto);


        }
    }
}