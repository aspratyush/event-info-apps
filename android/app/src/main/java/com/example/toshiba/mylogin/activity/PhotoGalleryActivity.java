package com.example.toshiba.mylogin.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.toshiba.mylogin.R;
import com.example.toshiba.mylogin.adapter.AGalleryPhoto;
import com.example.toshiba.mylogin.model.MGallery;
import com.example.toshiba.mylogin.model.MSchedule;
import com.example.toshiba.mylogin.utils.Globals;
import com.example.toshiba.mylogin.utils.SpacesItemDecoration;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Jewel on 12/22/2015.
 */
public class PhotoGalleryActivity extends AppCompatActivity {

    public static int type;

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private AGalleryPhoto adapter;
    private ArrayList<MGallery> galleries;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_photos);

        init();
       getOnlineData();
    }



    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        adapter = new AGalleryPhoto(this);

        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpacesItemDecoration(10));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void getOnlineData(){
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams params=new RequestParams();
        params.add("user_type", Globals.USER_TYPE+"");
        client.post("http://step2code.com/pratyush/api/gallery", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                jsonParser(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

    private void jsonParser(JSONArray jsonArray){
        galleries=new ArrayList<MGallery>();
        MGallery gallery;
        for(int i=0;i<jsonArray.length();i++){
            try {
                JSONObject jObj=jsonArray.getJSONObject(i);
                String img=jObj.getString("file_name");


                gallery=new MGallery();
                gallery.setSrc(img);

                galleries.add(gallery);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        adapter.setData(galleries);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
