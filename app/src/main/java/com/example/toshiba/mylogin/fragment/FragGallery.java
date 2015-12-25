package com.example.toshiba.mylogin.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.toshiba.mylogin.R;
import com.example.toshiba.mylogin.activity.PhotoGalleryActivity;


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
        view = inflater.inflate(R.layout.lay_gallery, container, false);
        imgPhoto =(ImageView)view.findViewById(R.id.imgPhoto);
        imgVideo =(ImageView)view.findViewById(R.id.imgVideo);
        imgPhoto.setOnClickListener(this);
        imgVideo.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgPhoto:
                PhotoGalleryActivity.type=1;
                getActivity().startActivity(new Intent(getActivity(), PhotoGalleryActivity.class));
                break;
            case R.id.imgVideo:
                PhotoGalleryActivity.type=2;
                getActivity().startActivity(new Intent(getActivity(), PhotoGalleryActivity.class));
                break;
        }
    }
}
