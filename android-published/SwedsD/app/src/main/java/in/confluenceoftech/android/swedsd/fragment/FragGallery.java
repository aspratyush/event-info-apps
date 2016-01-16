package in.confluenceoftech.android.swedsd.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import in.confluenceoftech.android.swedsd.activity.PhotoGalleryActivity;


public class FragGallery extends Fragment implements View.OnClickListener {
    private View view;

    private ImageView imgPhoto, imgVideo;
    public static FragGallery getInstance() {
        FragGallery fragExam = new FragGallery();
        return fragExam;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(in.confluenceoftech.android.swedsd.R.layout.lay_gallery, container, false);
        imgPhoto =(ImageView)view.findViewById(in.confluenceoftech.android.swedsd.R.id.imgPhoto);
        imgVideo =(ImageView)view.findViewById(in.confluenceoftech.android.swedsd.R.id.imgVideo);
        imgPhoto.setOnClickListener(this);
        imgVideo.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case in.confluenceoftech.android.swedsd.R.id.imgPhoto:
                //(15Jan)
                PhotoGalleryActivity.type=1;
                getActivity().startActivity(new Intent(getActivity(), PhotoGalleryActivity.class));
                //Toast.makeText( getActivity(), "Upload servers preparing to be back in action...", Toast.LENGTH_SHORT).show();
                break;
            case in.confluenceoftech.android.swedsd.R.id.imgVideo:
//              //15th Jan
//                PhotoGalleryActivity.type=2;
//                getActivity().startActivity(new Intent(getActivity(), PhotoGalleryActivity.class));
                Toast.makeText( getActivity(), "Video upload server has caught a chill... will be back soon", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
