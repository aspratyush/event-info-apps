package com.example.toshiba.mylogin.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.toshiba.mylogin.utils.Globals;

/**
 * Created by TOSHIBA on 12/17/2015.
 */
public class SwitcherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences("pratyush", Context.MODE_PRIVATE);
        int user_id = sp.getInt("user_id", 0);
        int user_type = sp.getInt("user_type", 0);

        Globals.USER_ID = user_id;
        Globals.USER_TYPE = user_type;

        if (user_id == 0) {
            startActivity(new Intent(this, LoginActivity.class));
        }else{
            startActivity(new Intent(this, MainActivity.class));
        }

        finish();
    }
}
