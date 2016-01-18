package in.confluenceoftech.android.swedsd.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import in.confluenceoftech.android.swedsd.R;
import in.confluenceoftech.android.swedsd.activity.SimpleActivity;
import in.confluenceoftech.android.swedsd.model.MGallery;
import com.squareup.picasso.Picasso;

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
        Picasso.with(context)
                .load("http://confluenceoftech.in/uploads/image/small/"+schedule.getSrc())
                .placeholder(R.drawable.loading)
                .into(holder.imgPhoto);


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

            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();

            ViewGroup.LayoutParams params=imgPhoto.getLayoutParams();
            params.width=(display.getWidth()-20)/3;
            params.height=(display.getWidth()-20)/3;
            imgPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MGallery gallery=galleries.get(getAdapterPosition());
                    SimpleActivity.gallery=gallery;
                    context.startActivity(new Intent(context, SimpleActivity.class));
                }
            });


        }
    }
}
