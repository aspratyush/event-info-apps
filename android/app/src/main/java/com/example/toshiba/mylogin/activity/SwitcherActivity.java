package com.example.toshiba.mylogin.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.toshiba.mylogin.GCM.RegistrationIntentService;
import com.example.toshiba.mylogin.R;
import com.example.toshiba.mylogin.utils.Globals;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Jewel on 12/17/2015.
 */
public class SwitcherActivity extends AppCompatActivity {
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_splash);


        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp = getSharedPreferences("pratyush", Context.MODE_PRIVATE);
                int user_id = sp.getInt("user_id", 0);
                int user_type = sp.getInt("user_type", 0);

                Globals.USER_ID = user_id;
                Globals.USER_TYPE = user_type;

                if (user_id == 0) {
                    startActivity(new Intent(SwitcherActivity.this, LoginActivity.class));
                } else {
                    startActivity(new Intent(SwitcherActivity.this, MainActivity.class));
                }

                finish();
            }
        }, 5000);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        handler=null;
    }


}
