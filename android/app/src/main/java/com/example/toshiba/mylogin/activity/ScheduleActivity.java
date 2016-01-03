package com.example.toshiba.mylogin.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toshiba.mylogin.R;
import com.example.toshiba.mylogin.model.MSchedule;
import com.squareup.picasso.Picasso;

public class ScheduleActivity extends AppCompatActivity {
    public static MSchedule schedule;
    private TextView tvVenue,tvDes,tvTime,tvDress;
    private ImageView imgSchedule;
    private Toolbar toolbar;
    private Button btnReach;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        init();
       prepareDis();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        imgSchedule=(ImageView)findViewById(R.id.imgSchedule);

        tvDes = (TextView) findViewById(R.id.tvDes);
        tvVenue = (TextView) findViewById(R.id.tvVenu);
        tvDress=(TextView)findViewById(R.id.tvDress);
        tvTime = (TextView) findViewById(R.id.tvTime);
        btnReach=(Button)findViewById(R.id.btnReach);
        toolbar = (Toolbar) findViewById(R.id.toolbar);


        //setting custom fonts
        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/candlescript.otf");
        tvDes.setTypeface(face);

        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle(getDay());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnReach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ScheduleActivity.this,"Google maps is preparing to guide you",Toast.LENGTH_LONG).show();
            }
        });


    }

    private void prepareDis(){
        tvDes.setText(schedule.getDesc());
        tvVenue.setText("Venue : "+schedule.getVenue());
        tvDress.setText("Dress Code : "+schedule.getDress_code());
        tvTime.setText("Time : "+schedule.getTime());
        imgSchedule.setImageResource(schedule.getImg_id());
        /*Picasso.with(this)
                .load(schedule.getImage())
                .placeholder(R.drawable.simple)
                .into(imgSchedule);*/
    }


}
