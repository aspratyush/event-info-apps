package in.confluenceoftech.android.swedsd.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.DialogPreference;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import in.confluenceoftech.android.swedsd.activity.WebActivity;
import in.confluenceoftech.android.swedsd.model.MSchedule;
import in.confluenceoftech.android.swedsd.utils.Globals;
import in.confluenceoftech.android.swedsd.utils.LoadingDialog;
import in.confluenceoftech.android.swedsd.utils.Utils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Jewel on 12/20/2015.
 */
public class FragAboutUs extends Fragment implements View.OnClickListener {


    private ImageView imgUs,imgFamily;
    private View view = null;
    private ProgressDialog dialog;


    public static FragAboutUs getInstance() {
        FragAboutUs fragAboutUs = new FragAboutUs();
        return fragAboutUs;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(in.confluenceoftech.android.swedsd.R.layout.frag_about_us, container, false);
        imgUs=(ImageView)view.findViewById(in.confluenceoftech.android.swedsd.R.id.imgUs);
        imgFamily=(ImageView)view.findViewById(in.confluenceoftech.android.swedsd.R.id.imgFamily);
        imgUs.setOnClickListener(this);
        imgFamily.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //getOnlineData();

    }
    private void getOnlineData(){
        dialog = ProgressDialog.show(getActivity(), "", "Loading...", true);
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams params=new RequestParams();
        params.add("key", "value");
        client.post(Utils.BASE_URL+"about", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                jsonParser(response);
                dialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                dialog.dismiss();
            }
        });
    }
    private void jsonParser(JSONObject jObj){
        try {

            Picasso.with(getActivity())
                    .load(jObj.getString("img_us"))
                    .placeholder(in.confluenceoftech.android.swedsd.R.drawable.loading)
                    .into(imgUs);
            Picasso.with(getActivity())
                    .load(jObj.getString("img_family"))
                    .placeholder(in.confluenceoftech.android.swedsd.R.drawable.loading)
                    .into(imgFamily);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case in.confluenceoftech.android.swedsd.R.id.imgUs:
                if (Globals.USER_TYPE == 11 || Globals.USER_TYPE == 12)
                    WebActivity.url = "http://confluenceoftech.in/android/about-us.html";
                else
                    //for sha family
                    WebActivity.url = "http://confluenceoftech.in/android/about-us.html";
                getActivity().startActivity(new Intent(getActivity(), WebActivity.class));
                break;
            case in.confluenceoftech.android.swedsd.R.id.imgFamily:
                if (Globals.USER_TYPE == 11 || Globals.USER_TYPE == 12)
                    WebActivity.url = "http://confluenceoftech.in/android/about-family-m.html";
                else
                    //for sha family
                    WebActivity.url = "http://confluenceoftech.in/android/about-family-s.html";
                getActivity().startActivity(new Intent(getActivity(), WebActivity.class));
                break;
        }
    }
}
