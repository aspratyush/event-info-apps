package com.example.toshiba.mylogin.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.toshiba.mylogin.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ScheduleActivity extends AppCompatActivity {
    public static String date;
    private TextView tvData, tvVenue;
    private Toolbar toolbar;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        init();
        callOnline();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        tvData = (TextView) findViewById(R.id.tvData);
        tvVenue = (TextView) findViewById(R.id.tvVenu);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getDay());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        handler=new Handler();

    }

    private String getDay() {
        String day = date;
        return day;
    }

    private void callOnline() {
        OkHttpClient client = new OkHttpClient();
        /*RequestBody requestBody = new FormEncodingBuilder()
                .add("key", "value")
                .build();*/
        Request request = new Request.Builder()
                .url("http://step2code.com/pratyush/api/schedule/"+date)
                //.post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d("Jewel",e.toString());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                jsonParser(response.body().string());
            }
        });


    }

    private void jsonParser(String json) {

        try {
            JSONObject object=new JSONObject(json);
            final String venue=object.getString("venue");
            final String data=object.getString("data");

            handler.post(new Runnable() {
                @Override
                public void run() {
                    tvData.setText(data);
                    tvVenue.setText(venue);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
