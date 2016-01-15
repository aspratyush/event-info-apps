package com.example.toshiba.mylogin.GCM;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


/**
 * Created by Jewel on 1/7/2016.
 */
public class RegistrationIntentService extends IntentService {

    private static final String TAG = "registration";
    private static final String GCM_ID="503718163383";


    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        try {
            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(GCM_ID, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);


            if(token!=null)
                LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("gcm intent").putExtra("token",token));

        } catch (Exception e) {

        }
    }


}