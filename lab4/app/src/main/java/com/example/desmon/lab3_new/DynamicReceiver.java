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

public class DynamicReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent){
        if(intent.getAction().equals("dynamic")){
            Bundle bundle = intent.getExtras();
            int imageId = (int) bundle.get("Image"); //小图标
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), bundle.getInt("Image"));//大图标
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification.Builder builder = new Notification.Builder(context);
            builder.setContentTitle("马上下单")
                    .setContentText(bundle.get("name")+"已添加到购物车")
                    .setSmallIcon(imageId)
                    .setLargeIcon(bitmap)
                    .setAutoCancel(true);
            Intent detailItent = new Intent(context, MainActivity.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 2, detailItent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);
            Notification notify = builder.build();
            notificationManager.notify(0,notify);
        }
    }
}
