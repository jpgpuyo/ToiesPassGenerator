package com.toies.jpuyo.toiespassgenerator.app;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

public class PasswordNotification {

    private Context context;
    private String password;

    private static final int PASSWORD_NOTIFICATION_ID = 3004;

    public PasswordNotification(Context context, String password){
        this.context = context;
        this.password = password;
    }

    public void send(){
        int iconId = R.drawable.ic_clear;
        String title = context.getString(R.string.app_name);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setColor(context.getResources().getColor(R.color.sunshine_light_blue))
                        .setSmallIcon(iconId)
                        .setContentTitle(title)
                        .setContentText(password);

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(PASSWORD_NOTIFICATION_ID, mBuilder.build());

    }
}
