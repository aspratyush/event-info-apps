package com.example.toshiba.mylogin.GCM;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.NotificationCompat;

import com.example.toshiba.mylogin.R;
import com.example.toshiba.mylogin.activity.MainActivity;
import com.example.toshiba.mylogin.utils.Utils;
import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by Jewel on 1/7/2016.
 */
public class MyGcmListenerService extends GcmListenerService {
    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);

        String message=data.getString("msg_body");
        String img=data.getString("msg_img");
        if(img.isEmpty()){
            Utils.sendNotfication(this,"Info",message);
        }else{
            Utils.sendNotfication(this,"Info",message,img);
        }


        //update UI
        updateUI(data);
    }



    private void updateUI(Bundle data) {
        String message = data.getString("msg_body");
        Intent newMessage = new Intent("gcm intent");
        newMessage.putExtra("message",message);

        LocalBroadcastManager.getInstance(this).sendBroadcast(newMessage);
    }
}