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
import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by Jewel on 1/7/2016.
 */
public class MyGcmListenerService extends GcmListenerService {
    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);


        //send notification
        sendNotification(data);

        //update UI
        updateUI(data);
    }

    private void sendNotification(Bundle data) {
        String message=data.getString("message");

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Intent intent=new Intent(this,MainActivity.class);
        PendingIntent pintent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Notification notification=new NotificationCompat.Builder(this)
                .setContentTitle("GCM Test")
                .setContentText(message)
                .setContentIntent(pintent)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setSmallIcon(R.drawable.a)
                .build();

        NotificationManager notificationMana= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationMana.notify(0,notification);

    }

    private void updateUI(Bundle data) {
        String message = data.getString("message");
        Intent newMessage = new Intent("gcm intent");
        newMessage.putExtra("message",message);

        LocalBroadcastManager.getInstance(this).sendBroadcast(newMessage);
    }
}