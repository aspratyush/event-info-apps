package in.confluenceoftech.android.swedsd.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
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

import in.confluenceoftech.android.swedsd.model.MSchedule;
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
        setContentView(in.confluenceoftech.android.swedsd.R.layout.activity_schedule);

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
        imgSchedule=(ImageView)findViewById(in.confluenceoftech.android.swedsd.R.id.imgSchedule);

        tvDes = (TextView) findViewById(in.confluenceoftech.android.swedsd.R.id.tvDes);
        tvVenue = (TextView) findViewById(in.confluenceoftech.android.swedsd.R.id.tvVenu);
        tvDress=(TextView)findViewById(in.confluenceoftech.android.swedsd.R.id.tvDress);
        tvTime = (TextView) findViewById(in.confluenceoftech.android.swedsd.R.id.tvTime);
        btnReach=(Button)findViewById(in.confluenceoftech.android.swedsd.R.id.btnReach);
        toolbar = (Toolbar) findViewById(in.confluenceoftech.android.swedsd.R.id.toolbar);


        //setting custom fonts
        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/dancingscript.otf");
        tvDes.setTypeface(face);

        Typeface face2 = Typeface.createFromAsset(getAssets(),
                "fonts/montserrat.otf");
        tvVenue.setTypeface(face2);
        tvDress.setTypeface(face2);
        tvTime.setTypeface(face2);

        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle(getDay());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnReach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location="geo:0,0?q=wankhade+stadium+mumbai";
                Uri gmmIntentUri = Uri.parse(schedule.getLocation());
                Intent mapIntent = new Intent( Intent.ACTION_VIEW, gmmIntentUri );
                mapIntent.setPackage( "com.google.android.apps.maps" );
                if (mapIntent.resolveActivity( getPackageManager() ) != null) {
                    startActivity(mapIntent);
                }
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
