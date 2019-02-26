package com.example.ciara.drugsmart;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class NotificationService extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        //Creating a notification manage and creating an instant of it
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent repeatingIntent = new Intent(context, MainActivity.class);
        //This activity should be the vaccinations activity once it's build out
        repeatingIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, repeatingIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setContentText("Check withdrawal period ")
                .setSmallIcon(android.R.drawable.arrow_up_float)
                .setContentTitle("Vaccination Info")
                //Dismissible when the user swipes right
                .setAutoCancel(true);

        notificationManager.notify(100, builder.build());


    }
}
