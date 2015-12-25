package com.example.toshiba.mylogin.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.toshiba.mylogin.R;
import com.example.toshiba.mylogin.model.MGallery;
import com.squareup.picasso.Picasso;

public class SimpleActivity extends AppCompatActivity {
    public static MGallery gallery;
    private Toolbar toolbar;
    private ImageView imgPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        init();
        prepareDis();
    }

    private void init() {
        imgPhoto=(ImageView)findViewById(R.id.imgPhoto);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void prepareDis(){
        Picasso.with(this)
                .load(gallery.getSrc())
                .placeholder(R.drawable.simple)
                .into(imgPhoto);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
