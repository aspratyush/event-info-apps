package com.example.toshiba.mylogin.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.toshiba.mylogin.R;
import com.example.toshiba.mylogin.adapter.ASchedule;
import com.example.toshiba.mylogin.model.MSchedule;
import com.example.toshiba.mylogin.utils.Globals;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class FragSchedule extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private ArrayList<MSchedule> schedules;
    private ASchedule adapter;
    private ProgressDialog dialog;

    public static FragSchedule getInstance() {
        FragSchedule fragExam = new FragSchedule();
        return fragExam;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lay_recycler, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // getOnlineData();
        prepareList();

    }

    private void getOnlineData() {

        dialog = ProgressDialog.show(getActivity(), "", "Loading...", true);
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("key", "value");
        client.post("http://step2code.com/pratyush/api/schedule_list", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                //jsonParser(response);
                dialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                dialog.dismiss();
            }
        });
    }

   /* private void jsonParser(JSONArray jsonArray) {
        schedules = new ArrayList<MSchedule>();
        MSchedule schedule;
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jObj = jsonArray.getJSONObject(i);
                String date = jObj.getString("date");
                String img = jObj.getString("img");
                String venue = jObj.getString("venue");
                schedule = new MSchedule();
                schedule.setDate(date);
                schedule.setImage(img);
                schedule.setVenue(venue);
                schedules.add(schedule);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        adapter.setData(schedules);
    }*/


    private void prepareList() {
        schedules = new ArrayList<MSchedule>();
        // for mandhyan_132134
        if (Globals.USER_ID == 1) {
            MSchedule schedule = new MSchedule();
            schedule.setImg_id(R.drawable.man_21);
            schedule.setDate("21/01/2016");
            schedule.setDesc("Trust is the start of it\n" +
                    "Joy is a part of it\n" +
                    "Love is the heart of it\n" +
                    "With these, we begin a life full of Romance, Music and Beats\n" +
                    "Dr Surita requests your presence at her Sagai & Sangeet\n");
            schedule.setDress_code("IndoWestern");
            schedule.setTime("");
            schedule.setVenue("");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(R.drawable.man_22);
            schedule.setDate("22/01/2016");
            schedule.setDesc("When dreams and reality meet,\n" +
                            "It's time for Bollywood Dhamaal and treat,\n" +
                            "Let Mehndi adorn your hands and spirit your feet,\n" +
                            "All in a filmy avatar, ready to great!\n" +
                            "Kapish, Vikas, Ishitaa, Pranav, Raunak, Roshini, Gitika, Anish, Nikeeta & Mihieka cordially invite you to celebrate the Mehendi party of their beloved sister, Dr Surita Mandhyan\n"
            );
            schedule.setDress_code(" Bollywood");
            schedule.setTime("");
            schedule.setVenue("");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(R.drawable.man_23);
            schedule.setDate("23/01/2016");
            schedule.setDesc("(TBD)\n");
            schedule.setDress_code("Traditional");
            schedule.setTime("");
            schedule.setVenue("");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(R.drawable.man_24);
            schedule.setDate("24/01/2016");
            schedule.setDesc("She came in like an angel, she lives like a Star,\n" +
                    "She is the laughter and light of our Family by Far,\n" +
                    "Soon she will seek the joys of life with Another,\n" +
                    "As time has come for the Bidaai of our daughter,\n" +
                    "Mrs Nita and Dr Suresh Mandhyan solicit your blessing for the Wedding Ceremony of their daughter, Dr Surita with Dr Dhaval.\n" +
                    "\n");
            schedule.setDress_code("Dhoti for Men, Sari for Women");
            schedule.setTime("");
            schedule.setVenue("");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(R.drawable.man_25);
            schedule.setDate("24/01/2016");
            schedule.setDesc("As precious as the Ocean's treasure \n" +
                    "Too deep for anyone to Measure\n" +
                    "Two birds of a feather \n" +
                    "Sharing their lives together\n" +
                    "\n" +
                    "The Mandhyan Family cordially invite you to the Reception of Dr. Surita Mandhyan and Dr. Dhaval Shah\n" +
                    "\n");
            schedule.setDress_code("Formal");
            schedule.setTime("");
            schedule.setVenue("");
            schedules.add(schedule);

            // for mandhyan_213455
        } else if (Globals.USER_ID == 2) {
            MSchedule schedule = new MSchedule();
            schedule.setImg_id(R.drawable.man_24);
            schedule.setDate("24/01/2016");
            schedule.setDesc("She came in like an angel, she lives like a Star,\n" +
                    "She is the laughter and light of our Family by Far,\n" +
                    "Soon she will seek the joys of life with Another,\n" +
                    "As time has come for the Bidaai of our daughter,\n" +
                    "Mrs Nita and Dr Suresh Mandhyan solicit your blessing for the Wedding Ceremony of their daughter, Dr Surita with Dr Dhaval.\n" +
                    "\n");
            schedule.setDress_code(" Dhoti for Men, Sari for Women");
            schedule.setTime("");
            schedule.setVenue("");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(R.drawable.man_25);
            schedule.setDate("24/01/2016");
            schedule.setDesc("As precious as the Ocean's treasure \n" +
                    "Too deep for anyone to Measure\n" +
                    "Two birds of a feather \n" +
                    "Sharing their lives together\n" +
                    "\n" +
                    "The Mandhyan Family cordially invite you to the Reception of Dr. Surita Mandhyan and Dr. Dhaval Shah\n" +
                    "\n");
            schedule.setDress_code("Formal");
            schedule.setTime("");
            schedule.setVenue("");
            schedules.add(schedule);
        }
        // for mandhyan_345589
        else if (Globals.USER_ID == 3) {
            MSchedule schedule = new MSchedule();
            schedule.setImg_id(R.drawable.man_21);
            schedule.setDate("21/01/2016");
            schedule.setDesc("Trust is the start of it\n" +
                    "Joy is a part of it\n" +
                    "Love is the heart of it\n" +
                    "With these, we begin a life full of Romance, Music and Beats\n" +
                    "Dr Surita requests your presence at her Sagai & Sangeet\n" +
                    "\n");
            schedule.setDress_code("IndoWestern");
            schedule.setTime("");
            schedule.setVenue("");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(R.drawable.man_24);
            schedule.setDate("24/01/2016");
            schedule.setDesc("She came in like an angel, she lives like a Star,\n" +
                    "She is the laughter and light of our Family by Far,\n" +
                    "Soon she will seek the joys of life with Another,\n" +
                    "As time has come for the Bidaai of our daughter,\n" +
                    "Mrs Nita and Dr Suresh Mandhyan solicit your blessing for the Wedding Ceremony of their daughter, Dr Surita with Dr Dhaval.\n" +
                    "\n");
            schedule.setDress_code("Dhoti for Men, Sari for Women");
            schedule.setTime("");
            schedule.setVenue("");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(R.drawable.man_25);
            schedule.setDate("24/01/2016");
            schedule.setDesc("As precious as the Ocean's treasure \n" +
                    "Too deep for anyone to Measure\n" +
                    "Two birds of a feather \n" +
                    "Sharing their lives together\n" +
                    "\n" +
                    "The Mandhyan Family cordially invite you to the Reception of Dr. Surita Mandhyan and Dr. Dhaval Shah\n" +
                    "\n");
            schedule.setDress_code("Formal");
            schedule.setTime("");
            schedule.setVenue("");
            schedules.add(schedule);
        }

        // sha family : for shah_132134
        else if (Globals.USER_ID==4) {
            MSchedule schedule = new MSchedule();
            schedule.setImg_id(R.drawable.sha_21);
            schedule.setDate("21/01/2016");
            schedule.setDesc("What is life without a lit bit of drama \n" +
                    "What is a shadi without some thumkas and Naach Gana\n" +
                    "Come - perform - cheer - applaud !! \n" +
                    "Followed by Dinner \n");
            schedule.setDress_code("");
            schedule.setTime("");
            schedule.setVenue("");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(R.drawable.sha_22);
            schedule.setDate("22/01/2016");
            schedule.setDesc("It is rightly said that nothing happens in life without a reason. \n" +
                    "As we celebrate the coming together of two families, \n" +
                    "we would like to thank the almighty for this joyous occassion. \n" +
                    "Do join us for the Pooja followed by Lunch .\n");
            schedule.setDress_code("");
            schedule.setTime("");
            schedule.setVenue("");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(R.drawable.sha_23);
            schedule.setDate("23/01/2016");
            schedule.setDesc("We all love brides, dont we ? \n" +
                    "They add colour and spice and variety to the weddings. \n" +
                    "Dont you think that the grooms shouldnt be left behind ?\n" +
                    "Come - help us make the groom glow with Haldi \n" +
                    "and match the awesomeness of the bride.\n" +
                    "Haldi and Mandap Mahurat followed by Lunch\n");
            schedule.setDress_code("");
            schedule.setTime("");
            schedule.setVenue("");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(R.drawable.sha_24);
            schedule.setDate("24/01/2016");
            schedule.setDesc("Weddings are unique in a way. \n" +
                    "Please laugh & people cry. \n" +
                    "Some people celebrate a new member entering \n" +
                    "their family while some cry letting \n" +
                    "go of their beloved daughters. \n" +
                    "\n" +
                    "Its brilliant how all these elements get together \n" +
                    "and create a beautiful expression of love & harmony. \n" +
                    "We would love to have you when we try to create \n" +
                    "our own little story which shall last us a lifetime.... \n" +
                    "\n" +
                    "Jaan Prasthan\t:\n" +
                    "Hasthmelap\t:\n" +
                    "Lunch\t\t:\n");
            schedule.setDress_code("");
            schedule.setTime("");
            schedule.setVenue("");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(R.drawable.sha_25);
            schedule.setDate("24/01/2016");
            schedule.setDesc("Receptions are the biggest celebrations \n" +
                    "in the lives of every couple. \n" +
                    "It is that moment when the grooms family \n" +
                    "is proud of their son taking bigger responsibilities \n" +
                    "and starting his own family , while the brides family \n" +
                    "is just amazed at how quickly their daughter has grown older, \n" +
                    "matured and is now ready to be someones beloved wife !! \n" +
                    "Such joyous occassions dont come often. \n" +
                    "Please join us as we take the next step forward in our life.\n");
            schedule.setDress_code("");
            schedule.setTime("");
            schedule.setVenue("");
            schedules.add(schedule);

            // for shah_213455
        }else if(Globals.USER_ID==5){
            MSchedule schedule = new MSchedule();
            schedule.setImg_id(R.drawable.sha_22);
            schedule.setDate("22/01/2016");
            schedule.setDesc("It is rightly said that nothing happens in life without a reason. \n" +
                    "As we celebrate the coming together of two families, \n" +
                    "we would like to thank the almighty for this joyous occassion. \n" +
                    "Do join us for the Pooja followed by Lunch .\n");
            schedule.setDress_code("");
            schedule.setTime("");
            schedule.setVenue("");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(R.drawable.sha_24);
            schedule.setDate("24/01/2016");
            schedule.setDesc("Weddings are unique in a way. \n" +
                    "Please laugh & people cry. \n" +
                    "Some people celebrate a new member entering \n" +
                    "their family while some cry letting \n" +
                    "go of their beloved daughters. \n" +
                    "\n" +
                    "Its brilliant how all these elements get together \n" +
                    "and create a beautiful expression of love & harmony. \n" +
                    "We would love to have you when we try to create \n" +
                    "our own little story which shall last us a lifetime.... \n" +
                    "\n" +
                    "Jaan Prasthan\t:\n" +
                    "Hasthmelap\t:\n" +
                    "Lunch\t\t:\n");
            schedule.setDress_code("");
            schedule.setTime("");
            schedule.setVenue("");
            schedules.add(schedule);

            // for shah_345589
        }else if(Globals.USER_ID==6){
            MSchedule schedule = new MSchedule();
            schedule.setImg_id(R.drawable.sha_21);
            schedule.setDate("21/01/2016");
            schedule.setDesc("What is life without a lit bit of drama \n" +
                    "What is a shadi without some thumkas and Naach Gana\n" +
                    "Come - perform - cheer - applaud !! \n" +
                    "Followed by Dinner \n");
            schedule.setDress_code("");
            schedule.setTime("");
            schedule.setVenue("");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(R.drawable.sha_24);
            schedule.setDate("24/01/2016");
            schedule.setDesc("Weddings are unique in a way. \n" +
                    "Please laugh & people cry. \n" +
                    "Some people celebrate a new member entering \n" +
                    "their family while some cry letting \n" +
                    "go of their beloved daughters. \n" +
                    "\n" +
                    "Its brilliant how all these elements get together \n" +
                    "and create a beautiful expression of love & harmony. \n" +
                    "We would love to have you when we try to create \n" +
                    "our own little story which shall last us a lifetime.... \n" +
                    "\n" +
                    "Jaan Prasthan\t:\n" +
                    "Hasthmelap\t:\n" +
                    "Lunch\t\t:\n");
            schedule.setDress_code("");
            schedule.setTime("");
            schedule.setVenue("");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(R.drawable.sha_24);
            schedule.setDate("24/01/2016");
            schedule.setDesc("Weddings are unique in a way. \n" +
                    "Please laugh & people cry. \n" +
                    "Some people celebrate a new member entering \n" +
                    "their family while some cry letting \n" +
                    "go of their beloved daughters. \n" +
                    "\n" +
                    "Its brilliant how all these elements get together \n" +
                    "and create a beautiful expression of love & harmony. \n" +
                    "We would love to have you when we try to create \n" +
                    "our own little story which shall last us a lifetime.... \n" +
                    "\n" +
                    "Jaan Prasthan\t:\n" +
                    "Hasthmelap\t:\n" +
                    "Lunch\t\t:\n");
            schedule.setDress_code("");
            schedule.setTime("");
            schedule.setVenue("");
            schedules.add(schedule);

        }

        adapter = new ASchedule(getActivity());
        adapter.setData(schedules);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));

    }
}
