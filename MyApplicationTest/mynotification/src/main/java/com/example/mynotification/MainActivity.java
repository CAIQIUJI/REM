package com.example.mynotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private NotificationManager manager;
    private Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("cai", "测试通知", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(this, NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        notification = new NotificationCompat.Builder(this,"cai")
                .setContentTitle("官方通知")
                .setContentText("快去学习")
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setColor(Color.parseColor("#FFFF00"))
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ceshi))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

    }

    public void sendNotification(View view) {
        manager.notify(1, notification);
    }

    public void cancelNotification(View view) {
        //可以用autocancel可以用这个
        manager.cancel(1);
    }
}