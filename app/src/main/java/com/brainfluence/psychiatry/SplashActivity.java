package com.brainfluence.psychiatry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;

import static com.brainfluence.psychiatry.LoginActivity.ACCOUNT_TYPE;
import static com.brainfluence.psychiatry.LoginActivity.INFO_ADDED1;
import static com.brainfluence.psychiatry.LoginActivity.IS_LOGGED_IN;
import static com.brainfluence.psychiatry.LoginActivity.SHARED_PREFS;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private Boolean isLoggedIn;
    private String accountType;
    private String infoAdded;

    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID =
            "psychiatry_notification_channel";

    private NotificationManager mNotifyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_splash);

        sharedPref = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        isLoggedIn = sharedPref.getBoolean(IS_LOGGED_IN,false);
        accountType = sharedPref.getString(ACCOUNT_TYPE,null);
        infoAdded = sharedPref.getString(INFO_ADDED1,"false");


        createNotificationChannel();

        new CountDownTimer(2000, 1000) {
            @Override
            public void onFinish() {

                if(isLoggedIn)
                {
                    if(accountType.equals("students") && infoAdded.equals("false"))
                    {

                        Intent intent = new Intent(SplashActivity.this,QuestionnaireActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Intent intent = new Intent(SplashActivity.this,Home.class);
                        startActivity(intent);
                        finish();
                    }

                }

                else {
                    Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }



            }
            @Override
            public void onTick(long millisUntilFinished) {
            }
        }.start();
    }

    private void createNotificationChannel() {

        // Create a notification manager object.
        mNotifyManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel
                    (PRIMARY_CHANNEL_ID,
                            getString(R.string.notification_channel_name),
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription
                    (getString(R.string.notification_channel_description));

            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }
}