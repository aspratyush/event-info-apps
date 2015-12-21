package com.example.toshiba.mylogin.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.toshiba.mylogin.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class ScheduleActivity extends AppCompatActivity {
    public static String date;
    private TextView tvData;
    private EditText edtVenu;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        tvData=(TextView)findViewById(R.id.tvData);
        edtVenu=(EditText)findViewById(R.id.edtVenu);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getDay());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private String getDay(){
        String day=date;
        return day;
    }

    private void callOnline(){
        OkHttpClient client=new OkHttpClient();
        RequestBody requestBody=new FormEncodingBuilder()
                .add("key", "value")
                .build();
        Request request=new Request.Builder()
                .url("")
                .post(requestBody)
                .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(Response response) throws IOException {

                }
            });



    }
}
