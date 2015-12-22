package com.example.toshiba.mylogin.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.toshiba.mylogin.R;
import com.example.toshiba.mylogin.adapter.AGalleryPhoto;
import com.example.toshiba.mylogin.model.MGallery;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jewel on 12/22/2015.
 */
public class PhotoGalleryActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private AGalleryPhoto adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_photos);

        init();
        prepareDis();
    }

    private void prepareDis() {
        ArrayList<MGallery>galleries=new ArrayList<MGallery>();
        galleries.add(new MGallery());
        galleries.add(new MGallery());
        galleries.add(new MGallery());
        galleries.add(new MGallery());
        galleries.add(new MGallery());
        galleries.add(new MGallery());
        galleries.add(new MGallery());
        galleries.add(new MGallery());


        adapter.setData(galleries);
    }

    private void init() {
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);

        adapter=new AGalleryPhoto(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
