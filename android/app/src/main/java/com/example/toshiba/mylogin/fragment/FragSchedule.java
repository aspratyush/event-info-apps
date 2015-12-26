package com.example.toshiba.mylogin.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.toshiba.mylogin.R;
import com.example.toshiba.mylogin.adapter.ASchedule;
import com.example.toshiba.mylogin.model.MSchedule;
import com.example.toshiba.mylogin.utils.LoadingDialog;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class FragSchedule extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private ArrayList<MSchedule> schedules;
    private ASchedule adapter;

    public static FragSchedule getInstance() {
        FragSchedule fragExam = new FragSchedule();
        return fragExam;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lay_recycler, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getOnlineData();
        prepareList();

    }

    private void getOnlineData() {

        LoadingDialog.getInstance(getActivity()).open();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("key", "value");
        client.post("http://step2code.com/pratyush/api/schedule_list", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                jsonParser(response);
                LoadingDialog.close();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                LoadingDialog.close();
            }
        });
    }

    private void jsonParser(JSONArray jsonArray) {
        schedules = new ArrayList<MSchedule>();
        MSchedule schedule;
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jObj = jsonArray.getJSONObject(i);
                String date = jObj.getString("date");
                String img = jObj.getString("img");
                String venue = jObj.getString("venue");
                schedule = new MSchedule();
                schedule.setDate(date);
                schedule.setImage(img);
                schedule.setVenue(venue);
                schedules.add(schedule);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        adapter.setData(schedules);
    }

    private void prepareList() {


        adapter = new ASchedule(getActivity());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));


    }
}
