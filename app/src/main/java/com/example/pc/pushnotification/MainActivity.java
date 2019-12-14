package com.example.pc.pushnotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity

{

    //Notification channel
    //Notification Builder
    //Notification Manager

    private static final String CHANNEL_ID="unipro";
    private static final String CHANNEL_NAME="unipro";
    private static final String CHANNEL_DESC="Unipro Notification";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel=new NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        findViewById(R.id.btn_notify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                displayNotification();

            }
        });

    }
    private void displayNotification()
    {
        try {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                    .setSmallIcon(R.drawable.pdaiconimg)
                    .setContentTitle("Hurray! It is working...")
                    .setContentText("Your first notification...")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"));
          //  Intent intent = new Intent(getApplicationContext(), SecondActivityScreen.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            mBuilder.setContentIntent(pendingIntent);
            mBuilder.setAutoCancel(true);

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
            notificationManagerCompat.notify(1, mBuilder.build());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
