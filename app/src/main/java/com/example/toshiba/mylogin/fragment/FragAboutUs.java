package com.example.toshiba.mylogin.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.toshiba.mylogin.R;

/**
 * Created by Jewel on 12/20/2015.
 */
public class FragAboutUs extends Fragment {

    public static FragAboutUs getInstance() {
        FragAboutUs fragAboutUs = new FragAboutUs();
        return fragAboutUs;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_about_us, container, false);
    }
}
