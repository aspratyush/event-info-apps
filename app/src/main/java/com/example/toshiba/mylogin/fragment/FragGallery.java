package com.example.toshiba.mylogin.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.toshiba.mylogin.R;
import com.example.toshiba.mylogin.adapter.AGallery;
import com.example.toshiba.mylogin.adapter.ASchedule;
import com.example.toshiba.mylogin.model.MGallery;

import java.util.ArrayList;


public class FragGallery extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private ArrayList<MGallery> galleries;
    private AGallery adapter;

    public static FragGallery getInstance() {
        FragGallery fragExam = new FragGallery();
        return fragExam;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_schedule, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        prepareList();
    }
    private void prepareList(){
        galleries =new ArrayList<MGallery>();
        MGallery gallery=new MGallery();
        gallery.setName("Shared by Others");
        galleries.add(gallery);

        gallery=new MGallery();
        gallery.setName("Shared by You");
        galleries.add(gallery);




        adapter=new AGallery(getActivity());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));

        adapter.setData(galleries);

    }
}
