package com.example.desmon.lab3_new;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.RemoteViews;

import drawable.mWidget;

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
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.m_widget);
            AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(context);
            views.setTextViewText(R.id.widtext, bundle.getString("name")+"已添加到购物车");
            views.setImageViewResource(R.id.widimage, bundle.getInt("Image"));
            builder.setTicker("A new message")
                    .setContentTitle("马上下单")
                    .setContentText(bundle.get("name")+"已添加到购物车")
                    .setSmallIcon(imageId)
                    .setLargeIcon(bitmap)
                    .setAutoCancel(true);
            Intent detailItent = new Intent(context, MainActivity.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 2, detailItent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);
            Notification notify = builder.build();
            notificationManager.notify(0,notify);

            Intent detailIntent2 = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent2 = PendingIntent.getActivity(context,1,detailIntent2,PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.mwidget, pendingIntent2);
            appWidgetManager.updateAppWidget(new ComponentName(context, mWidget.class), views);
        }
    }
}
