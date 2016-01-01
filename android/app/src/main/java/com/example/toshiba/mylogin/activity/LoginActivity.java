package com.example.toshiba.mylogin.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.toshiba.mylogin.R;
import com.example.toshiba.mylogin.model.MGallery;
import com.example.toshiba.mylogin.utils.Globals;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by TOSHIBA on 12/17/2015.
 */
public class LoginActivity extends AppCompatActivity {
    private EditText edtUser,edtPass;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_login);

        init();

    }

    private void init() {
        edtUser=(EditText)findViewById(R.id.edtUsername);
        edtPass=(EditText)findViewById(R.id.edtPassword);
        btnLogin=(Button)findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog dialog=ProgressDialog.show(LoginActivity.this,"","Login...");
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.add("username", edtUser.getText().toString().trim());
               // params.add("password", edtPass.getText().toString().trim());
                client.post("http://step2code.com/pratyush/api/login", params, new JsonHttpResponseHandler() {
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
                        Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private void jsonParser(JSONObject jsonObject){



            try {

                int status=jsonObject.getInt("status");
                int user_type=jsonObject.getInt("user_type");
                int user_id=jsonObject.getInt("user_id");
                Globals.USER_TYPE=user_type;
                Globals.USER_ID=user_id;

                SharedPreferences sp=getSharedPreferences("pratyush", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putInt("user_id", user_id);
                editor.putInt("user_type",user_type);
                editor.commit();

                if(status==1){
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


}
