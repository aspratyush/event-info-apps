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
 * Created by TOSHIBA on 12/17/2015.
 */
public class SwitcherActivity extends AppCompatActivity {
    private Handler handler;
    private BroadcastReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_splash);

/*
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String token=intent.getStringExtra("token");
                sendRegistrationToServer(token);
            }
        };
        getGCMToken();*/

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
    protected void onResume() {
        super.onResume();
       // LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("gcm intent"));
    }
    @Override
    protected void onPause() {
        super.onPause();
       // LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //handler.removeCallbacksAndMessages(null);
        //handler=null;
    }

    private void getGCMToken() {
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }



    private void sendRegistrationToServer(String token) {
        Log.d("Jewel","call to"+token);
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        //params.add("id",s);
        params.add("regId", token);
        params.add("name", "");
        params.add("email", "");
        client.post("http://step2code.com/gcm/api/register", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("Jewel", "status " + response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d("Jewel", "status " + responseString);
            }
        });
    }

}
