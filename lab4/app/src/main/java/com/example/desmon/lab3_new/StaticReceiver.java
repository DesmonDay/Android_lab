package com.example.desmon.lab3_new;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

/**
 * Created by Desmon on 2017/10/29.
 */

public class StaticReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        Bundle mBundle = intent.getExtras();
        Intent detailItent = new Intent(context, DetailActivity.class);
        detailItent.putExtras(mBundle);
        int imageId = (int) mBundle.get("Image"); //小图标
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), mBundle.getInt("Image"));//大图标
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("新商品热卖")
                .setContentText(mBundle.get("name")+"仅售"+mBundle.get("price"))
                .setSmallIcon(imageId)
                .setLargeIcon(bitmap)
                .setAutoCancel(true);
        //绑定intent, 点击图标能够进入详细界面的activity

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, detailItent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        Notification notify = builder.build();
        notificationManager.notify(0,notify);
    }
}
