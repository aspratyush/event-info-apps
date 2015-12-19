package com.example.toshiba.mylogin.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.toshiba.mylogin.R;
import com.example.toshiba.mylogin.adapter.ASchedule;
import com.example.toshiba.mylogin.model.MSchedule;

import java.util.ArrayList;


public class FragSchedule extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private ArrayList<MSchedule>schedules;
    private ASchedule adapter;

    public static FragSchedule getInstance() {
        FragSchedule fragExam = new FragSchedule();
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
        schedules=new ArrayList<MSchedule>();
        MSchedule schedule=new MSchedule();
        schedule.setDate("20 Dec");
        schedule.setDay("Sunday");
        schedules.add(schedule);

        schedule=new MSchedule();
        schedule.setDate("21 Dec");
        schedule.setDay("Monday");
        schedules.add(schedule);

        schedule=new MSchedule();
        schedule.setDate("22 Dec");
        schedule.setDay("Tuesday");
        schedules.add(schedule);

        schedule=new MSchedule();
        schedule.setDate("23 Dec");
        schedule.setDay("Wednesday");
        schedules.add(schedule);

        schedule=new MSchedule();
        schedule.setDate("24 Dec");
        schedule.setDay("Thursday");
        schedules.add(schedule);

        schedule=new MSchedule();
        schedule.setDate("25 Dec");
        schedule.setDay("Friday");
        schedules.add(schedule);

        adapter=new ASchedule(getActivity());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        adapter.setData(schedules);

    }
}
