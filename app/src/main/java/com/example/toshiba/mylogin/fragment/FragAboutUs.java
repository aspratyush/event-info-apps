package com.example.toshiba.mylogin.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.toshiba.mylogin.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Jewel on 12/20/2015.
 */
public class FragAboutUs extends Fragment {


    private TextView tvAboutUs, tvAboutFamily;
    private View view = null;
    private Handler handler;

    public static FragAboutUs getInstance() {
        FragAboutUs fragAboutUs = new FragAboutUs();
        return fragAboutUs;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_about_us, container, false);
        tvAboutUs = (TextView) view.findViewById(R.id.tvAboutUs);
        tvAboutFamily = (TextView) view.findViewById(R.id.tvAboutFamily);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (handler == null)
            handler = new Handler();
        callOnline();


    }

    private void callOnline() {
        OkHttpClient client = new OkHttpClient();
        /*RequestBody requestBody = new FormEncodingBuilder()
                .add("key", "value")
                .build();*/
        Request request = new Request.Builder()
                .url("http://step2code.com/pratyush/api/about")
                        //.post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d("Jewel", e.toString());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                jsonParser(response.body().string());
            }
        });


    }

    private void jsonParser(String json) {

        try {
            JSONObject object = new JSONObject(json);
            final String us = object.getString("us");
            final String family = object.getString("family");

            Log.d("Jewel", us + " : " + family);

            handler.post(new Runnable() {
                @Override
                public void run() {
                    tvAboutUs.setText(us);
                    tvAboutFamily.setText(family);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
