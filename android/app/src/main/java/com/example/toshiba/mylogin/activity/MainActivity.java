package com.example.toshiba.mylogin.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.toshiba.mylogin.GCM.RegistrationIntentService;
import com.example.toshiba.mylogin.R;
import com.example.toshiba.mylogin.fragment.FragAboutUs;
import com.example.toshiba.mylogin.fragment.FragGallery;
import com.example.toshiba.mylogin.fragment.FragSchedule;
import com.example.toshiba.mylogin.utils.Globals;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MyAdapter adapter;
    private FloatingActionButton btnFloating;
    private NavigationView navigationView;

    private BroadcastReceiver updateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        prepareToolbar();
        setupViewPager();
        setupTabLayout();

        updateReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //tvStatus.setText(intent.getStringExtra("message"));
                Toast.makeText(context,"Secceed",Toast.LENGTH_LONG).show();
                String token=intent.getStringExtra("token");
                sendRegistrationToServer(token);

            }
        };
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
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
    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.pager);
        btnFloating = (FloatingActionButton) findViewById(R.id.btnFloating);
        navigationView = (NavigationView) findViewById(R.id.navigationView);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);

        btnFloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Upload testing", Toast.LENGTH_SHORT).show();
                /*Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image*//*");
                startActivityForResult(intent,10);*/
                startActivity(new Intent(MainActivity.this, UploadActivity.class));
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);

                drawer.closeDrawers();
                switch (menuItem.getItemId()) {
                    case R.id.dmenu_contact:
                        //for mandhyan family
                        if (Globals.USER_TYPE == 11 || Globals.USER_TYPE == 12)
                            WebActivity.url = "https://aspratyush.wordpress.com/";
                        else
                        //for sha family
                            WebActivity.url = "https://github.com/aspratyush";
                        startActivity(new Intent(MainActivity.this, WebActivity.class));
                        break;
                    case R.id.dmenu_donate:
                        if (Globals.USER_TYPE == 11 || Globals.USER_TYPE == 12)
                            WebActivity.url = "https://aspratyush.wordpress.com/";
                        else
                            //for sha family
                            WebActivity.url = "https://github.com/aspratyush";
                        startActivity(new Intent(MainActivity.this, WebActivity.class));
                        break;
                    case R.id.dmenu_notification:
                        if (Globals.USER_TYPE == 11 || Globals.USER_TYPE == 12)
                            WebActivity.url = "https://aspratyush.wordpress.com/";
                        else
                            //for sha family
                            WebActivity.url = "https://github.com/aspratyush";
                        startActivity(new Intent(MainActivity.this, WebActivity.class));
                        break;
                }

                return true;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(updateReceiver, new IntentFilter("gcm intent"));
    }
    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(updateReceiver);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void prepareToolbar() {


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void setupViewPager() {
        adapter = new MyAdapter(getSupportFragmentManager());


        adapter.addFragment(FragSchedule.getInstance(), "Schedule");
        adapter.addFragment(FragGallery.getInstance(), "Gallery");
        adapter.addFragment(FragAboutUs.getInstance(), "About Us");

        viewPager.setAdapter(adapter);

    }

    private void setupTabLayout() {

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());

                adapter.notifyDataSetChanged();


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.mainContent, fragment).commit();

    }

    class MyAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<Fragment>();
        private final List<String> mFragmentTitleList = new ArrayList<String>();

        public MyAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

       /* @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }*/
    }

}