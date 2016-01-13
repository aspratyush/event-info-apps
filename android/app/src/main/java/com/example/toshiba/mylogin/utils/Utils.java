package com.example.toshiba.mylogin.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.example.toshiba.mylogin.R;
import com.example.toshiba.mylogin.activity.MainActivity;

/**
 * Created by Jewel on 1/13/2016.
 */
public class Utils {

    public static void sendNotfication(Context context,String title,String msg){

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Intent intent=new Intent(context,MainActivity.class);
        PendingIntent pintent=PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Notification notification=new NotificationCompat.Builder(context)
                .setContentTitle(title)
                .setContentText(msg)
                .setContentIntent(pintent)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setSmallIcon(R.drawable.a)
                .build();

        NotificationManager notificationMana= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationMana.notify(0,notification);
    }
}
