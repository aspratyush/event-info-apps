package in.confluenceoftech.android.swedsd.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.confluenceoftech.android.swedsd.adapter.ASchedule;
import in.confluenceoftech.android.swedsd.model.MSchedule;
import in.confluenceoftech.android.swedsd.utils.Globals;
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
        view = inflater.inflate(in.confluenceoftech.android.swedsd.R.layout.lay_recycler, container, false);
        recyclerView = (RecyclerView) view.findViewById(in.confluenceoftech.android.swedsd.R.id.recyclerView);


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
            schedule.setImg_id(in.confluenceoftech.android.swedsd.R.drawable.man_21);
            schedule.setDate("21st Jan - Sagai & Sangeet");
            schedule.setDesc("Trust is the start of it.. Joy is a part of it.. Love is the heart of it. With these, we begin " +
                    "a life full of Romance, Music and Beats. Dr. Surita requests your presence at her Sagai & Sangeet\n");
            schedule.setDress_code("IndoWestern");
            schedule.setTime("");
            schedule.setVenue("");
            schedule.setLocation("geo:0,0?q=");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(in.confluenceoftech.android.swedsd.R.drawable.man_22);
            schedule.setDate("22nd Jan - Mehndi");
            schedule.setDesc("When dreams and reality meet, it's time for Bollywood Dhamaal and treat.. Let Mehndi adorn your hands " +
                            "and spirit your feet, all in a filmy avatar, ready to greet! Kapish, Vikas, Ishitaa, Pranav, Raunak, " +
                            "Roshini, Gitika, Anish, Nikeeta & Mihieka cordially invite you to celebrate the Mehendi party of their " +
                            "beloved sister, Dr. Surita Mandhyan\n"
            );
            schedule.setDress_code("Bollywood");
            schedule.setTime("");
            schedule.setVenue("");
            schedule.setLocation("geo:0,0?q=");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(in.confluenceoftech.android.swedsd.R.drawable.man_23);
            schedule.setDate("23rd Jan - Ganesh Sthapana & Mata ki Chowki");
            schedule.setDesc("The Mandhyan family requests your presence as we request the lord to bless the union of Dr. Surita and Dr. Dhaval.\n");
            schedule.setDress_code("Traditional");
            schedule.setTime("");
            schedule.setVenue("");
            schedule.setLocation("geo:0,0?q=");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(in.confluenceoftech.android.swedsd.R.drawable.man_24);
            schedule.setDate("24th Jan - Wedding Ceremony");
            schedule.setDesc("She came in like an angel, she lives like a Star.. She is the laughter and light of our Family by Far.. " +
                    "Soon she will seek the joys of life with Another. As time has come for the Bidaai of our daughter, Mrs. Nita and " +
                    "Dr. Suresh Mandhyan solicit your blessing for the Wedding Ceremony of their daughter, Dr. Surita with Dr. Dhaval.\n");
            schedule.setDress_code("Dhoti for Men, Sari for Women");
            schedule.setTime("");
            schedule.setVenue("");
            schedule.setLocation("geo:0,0?q=");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(in.confluenceoftech.android.swedsd.R.drawable.man_25);
            schedule.setDate("24th Jan - Reception");
            schedule.setDesc("As precious as the Ocean's treasure, Too deep for anyone to Measure.. Two birds of a feather, " +
                    "sharing their lives together.. The Mandhyan Family cordially invite you to the Reception of Dr. " +
                    "Surita Mandhyan and Dr. Dhaval Shah\n");
            schedule.setDress_code("Formal");
            schedule.setTime("");
            schedule.setVenue("");
            schedule.setLocation("geo:0,0?q=");
            schedules.add(schedule);

            // for mandhyan_213455
        } else if (Globals.USER_ID == 2) {
            MSchedule schedule = new MSchedule();
            schedule = new MSchedule();
            schedule.setImg_id(in.confluenceoftech.android.swedsd.R.drawable.man_24);
            schedule.setDate("24th Jan - Wedding Ceremony");
            schedule.setDesc("She came in like an angel, she lives like a Star.. She is the laughter and light of our Family by Far.. " +
                    "Soon she will seek the joys of life with Another. As time has come for the Bidaai of our daughter, Mrs. Nita and " +
                    "Dr. Suresh Mandhyan solicit your blessing for the Wedding Ceremony of their daughter, Dr. Surita with Dr. Dhaval.\n");
            schedule.setDress_code("Dhoti for Men, Sari for Women");
            schedule.setTime("");
            schedule.setVenue("");
            schedule.setLocation("geo:0,0?q=");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(in.confluenceoftech.android.swedsd.R.drawable.man_25);
            schedule.setDate("24th Jan - Reception");
            schedule.setDesc("As precious as the Ocean's treasure, Too deep for anyone to Measure.. Two birds of a feather, " +
                    "sharing their lives together.. The Mandhyan Family cordially invite you to the Reception of Dr. " +
                    "Surita Mandhyan and Dr. Dhaval Shah\n");
            schedule.setDress_code("Formal");
            schedule.setTime("");
            schedule.setVenue("");
            schedule.setLocation("geo:0,0?q=");
            schedules.add(schedule);
        }
        // for mandhyan_345589
        else if (Globals.USER_ID == 3) {
            MSchedule schedule = new MSchedule();
            schedule.setImg_id(in.confluenceoftech.android.swedsd.R.drawable.man_21);
            schedule.setDate("21st Jan - Sagai & Sangeet");
            schedule.setDesc("Trust is the start of it.. Joy is a part of it.. Love is the heart of it. With these, we begin " +
                    "a life full of Romance, Music and Beats. Dr. Surita requests your presence at her Sagai & Sangeet\n");
            schedule.setDress_code("IndoWestern");
            schedule.setTime("");
            schedule.setVenue("");
            schedule.setLocation("geo:0,0?q=");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(in.confluenceoftech.android.swedsd.R.drawable.man_24);
            schedule.setDate("24th Jan - Wedding Ceremony");
            schedule.setDesc("She came in like an angel, she lives like a Star.. She is the laughter and light of our Family by Far.. " +
                    "Soon she will seek the joys of life with Another. As time has come for the Bidaai of our daughter, Mrs. Nita and " +
                    "Dr. Suresh Mandhyan solicit your blessing for the Wedding Ceremony of their daughter, Dr. Surita with Dr. Dhaval.\n");
            schedule.setDress_code("Dhoti for Men, Sari for Women");
            schedule.setTime("");
            schedule.setVenue("");
            schedule.setLocation("geo:0,0?q=");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(in.confluenceoftech.android.swedsd.R.drawable.man_25);
            schedule.setDate("24th Jan - Reception");
            schedule.setDesc("As precious as the Ocean's treasure, Too deep for anyone to Measure.. Two birds of a feather, " +
                    "sharing their lives together.. The Mandhyan Family cordially invite you to the Reception of Dr. " +
                    "Surita Mandhyan and Dr. Dhaval Shah\n");
            schedule.setDress_code("Formal");
            schedule.setTime("");
            schedule.setVenue("");
            schedule.setLocation("geo:0,0?q=");
            schedules.add(schedule);
        }

        // sha family : for shah_132134
        else if (Globals.USER_ID==4) {
            MSchedule schedule = new MSchedule();
            schedule.setImg_id(in.confluenceoftech.android.swedsd.R.drawable.sha_21);
            schedule.setDate("21st Jan - Sangeet");
            schedule.setDesc("What is life without a lit bit of drama.. What is a shaadi without some thumkas and Naach Gana.." +
                    "Come - perform - cheer - applaud !! Followed by Dinner.\n");
            schedule.setDress_code("");
            schedule.setTime("");
            schedule.setVenue("");
            schedule.setLocation("geo:0,0?q=");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(in.confluenceoftech.android.swedsd.R.drawable.sha_22);
            schedule.setDate("22nd Jan - Siddhachakra Mahapoojan");
            schedule.setDesc("It is rightly said that nothing happens in life without a reason. As we celebrate the " +
                    "coming together of two families, we would like to thank the almighty for this joyous occassion. " +
                    "Do join us for the Pooja followed by Lunch.\n");
            schedule.setDress_code("");
            schedule.setTime("");
            schedule.setVenue("");
            schedule.setLocation("geo:0,0?q=");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(in.confluenceoftech.android.swedsd.R.drawable.sha_23);
            schedule.setDate("23rd Jan - Mandap Mahurat & Haldi");
            schedule.setDesc("We all love brides, dont we? They add colour and spice and variety to the weddings. " +
                    "Dont you think that the grooms should not be left behind? Come - help us make the groom glow " +
                    "with Haldi and match the awesomeness of the bride. Haldi and Mandap Mahurat followed by Lunch\n");
            schedule.setDress_code("");
            schedule.setTime("");
            schedule.setVenue("");
            schedule.setLocation("geo:0,0?q=");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(in.confluenceoftech.android.swedsd.R.drawable.sha_24);
            schedule.setDate("24th Jan - Wedding");
            schedule.setDesc("Weddings are unique in a way. People laugh and people cry. Some people celebrate a " +
                    "new member entering their family while some cry letting go of their beloved daughters. Its " +
                    "brilliant how all these elements get together and create a beautiful expression of love and harmony. " +
                    "We would love to have you when we try to create our own little story which shall last us a lifetime.... \n" +
                    "Jaan Prasthan\t:9 AM\n" +
                    "Hasthmelap\t:12.39 PM\n" +
                    "Lunch\t\t:11 AM onwards\n");
            schedule.setDress_code("");
            schedule.setTime("");
            schedule.setVenue("");
            schedule.setLocation("geo:0,0?q=");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(in.confluenceoftech.android.swedsd.R.drawable.sha_25);
            schedule.setDate("24th Jan - Reception");
            schedule.setDesc("Receptions are the biggest celebrations in the lives of every couple. It is that moment " +
                    "when the grooms family is proud of their son taking bigger responsibilities and starting his " +
                    "own family, while the brides family is just amazed at how quickly their daughter has grown older, " +
                    "matured and is now ready to be someones beloved wife!! Such joyous occassions dont come often. " +
                    "Please join us as we take the next step forward in our lives.\n");
            schedule.setDress_code("");
            schedule.setTime("");
            schedule.setVenue("");
            schedule.setLocation("geo:0,0?q=");
            schedules.add(schedule);

            // for shah_213455
        }else if(Globals.USER_ID==5){
            MSchedule schedule = new MSchedule();
            schedule = new MSchedule();
            schedule.setImg_id(in.confluenceoftech.android.swedsd.R.drawable.sha_22);
            schedule.setDate("22nd Jan - Siddhachakra Mahapoojan");
            schedule.setDesc("It is rightly said that nothing happens in life without a reason. As we celebrate the " +
                    "coming together of two families, we would like to thank the almighty for this joyous occassion. " +
                    "Do join us for the Pooja followed by Lunch.\n");
            schedule.setDress_code("");
            schedule.setTime("");
            schedule.setVenue("");
            schedule.setLocation("geo:0,0?q=");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(in.confluenceoftech.android.swedsd.R.drawable.sha_24);
            schedule.setDate("24th Jan - Wedding");
            schedule.setDesc("Weddings are unique in a way. People laugh and people cry. Some people celebrate a " +
                    "new member entering their family while some cry letting go of their beloved daughters. Its " +
                    "brilliant how all these elements get together and create a beautiful expression of love and harmony. " +
                    "We would love to have you when we try to create our own little story which shall last us a lifetime.... \n" +
                    "Jaan Prasthan\t:9 AM\n" +
                    "Hasthmelap\t:12.39 PM\n" +
                    "Lunch\t\t:11 AM onwards\n");
            schedule.setDress_code("");
            schedule.setTime("");
            schedule.setVenue("");
            schedule.setLocation("geo:0,0?q=");
            schedules.add(schedule);

            // for shah_345589
        }else if(Globals.USER_ID==6){
            MSchedule schedule = new MSchedule();
            schedule.setImg_id(in.confluenceoftech.android.swedsd.R.drawable.sha_21);
            schedule.setDate("21st Jan - Sangeet");
            schedule.setDesc("What is life without a lit bit of drama.. What is a shaadi without some thumkas and Naach Gana.." +
                    "Come - perform - cheer - applaud !! Followed by Dinner.\n");
            schedule.setDress_code("");
            schedule.setTime("");
            schedule.setVenue("");
            schedule.setLocation("geo:0,0?q=");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(in.confluenceoftech.android.swedsd.R.drawable.sha_24);
            schedule.setDate("24th Jan - Wedding");
            schedule.setDesc("Weddings are unique in a way. People laugh and people cry. Some people celebrate a " +
                    "new member entering their family while some cry letting go of their beloved daughters. Its " +
                    "brilliant how all these elements get together and create a beautiful expression of love and harmony. " +
                    "We would love to have you when we try to create our own little story which shall last us a lifetime.... \n" +
                    "Jaan Prasthan\t:9 AM\n" +
                    "Hasthmelap\t:12.39 PM\n" +
                    "Lunch\t\t:11 AM onwards\n");
            schedule.setDress_code("");
            schedule.setTime("");
            schedule.setVenue("");
            schedule.setLocation("geo:0,0?q=");
            schedules.add(schedule);

            schedule = new MSchedule();
            schedule.setImg_id(in.confluenceoftech.android.swedsd.R.drawable.sha_25);
            schedule.setDate("24th Jan - Reception");
            schedule.setDesc("Receptions are the biggest celebrations in the lives of every couple. It is that moment " +
                    "when the grooms family is proud of their son taking bigger responsibilities and starting his " +
                    "own family, while the brides family is just amazed at how quickly their daughter has grown older, " +
                    "matured and is now ready to be someones beloved wife!! Such joyous occassions dont come often. " +
                    "Please join us as we take the next step forward in our lives.\n");
            schedule.setDress_code("");
            schedule.setTime("");
            schedule.setVenue("");
            schedule.setLocation("geo:0,0?q=");
            schedules.add(schedule);

        }

        adapter = new ASchedule(getActivity());
        adapter.setData(schedules);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));

    }
}
