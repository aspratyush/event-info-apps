package in.confluenceoftech.android.swedsd.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import in.confluenceoftech.android.swedsd.R;
import in.confluenceoftech.android.swedsd.activity.PhotoGalleryActivity;
import in.confluenceoftech.android.swedsd.model.MGallery;

import java.util.ArrayList;


/**
 * Created by Jewel on 12/20/2015.
 */
public class AGallery extends RecyclerView.Adapter<AGallery.MyViewHolder> {
    private ArrayList<MGallery> galleries;
    private Context context;
    private LayoutInflater inflater;

    private Dialog dialog;
    public AGallery(Context context) {
        this.context = context;
        inflater=LayoutInflater.from(context);
        galleries = new ArrayList<MGallery>();

    }

    public void setData(ArrayList<MGallery> galleries) {
        this.galleries = galleries;
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_gallery,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MGallery schedule = galleries.get(position);
        holder.owner.setText(schedule.getName());


    }

    @Override
    public int getItemCount() {
        return galleries.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView owner,day;


        public MyViewHolder(View itemView) {
            super(itemView);

            owner = (TextView) itemView.findViewById(R.id.tvOwner);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog=new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_gallery);
                    dialog.show();
                    TextView tvPhotos=(TextView)dialog.findViewById(R.id.tvPhotos);
                    TextView tvVideos=(TextView)dialog.findViewById(R.id.tvVideo);
                    tvPhotos.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            context.startActivity(new Intent(context, PhotoGalleryActivity.class));
                        }
                    });
                    tvVideos.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            context.startActivity(new Intent(context, PhotoGalleryActivity.class));
                        }
                    });
                }
            });

        }
    }
}
