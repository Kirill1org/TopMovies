package com.koromyslov.topmovies;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification("Hey!", "It's time to watch "+intent.getStringExtra("filmTitle"));
        notificationHelper.getManager().notify(intent.getIntExtra("filmID",1), nb.build());

    }
}
