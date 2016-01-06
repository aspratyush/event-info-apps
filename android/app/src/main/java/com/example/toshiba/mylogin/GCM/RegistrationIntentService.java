package com.example.toshiba.mylogin.GCM;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;


/**
 * Created by Jewel on 1/7/2016.
 */
public class RegistrationIntentService extends IntentService {

    private static final String TAG = "registration";


    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        try {
            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken("GCM_PROJECT_NUMBER", GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);


            //call web to save this ID
            sendRegistrationToServer(token);


        } catch (Exception e) {

        }
    }

    private void sendRegistrationToServer(String token) {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        //params.add("id",s);
        params.add("regId", token);
        params.add("username", "");
        params.add("email", "");
        client.post("http://step2code.com/gcm/api/register", params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(getApplicationContext(), new String(responseBody), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), new String(responseBody), Toast.LENGTH_SHORT).show();
            }
        });
    }
}