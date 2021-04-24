package com.brainfluence.psychiatry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPref = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        isLoggedIn = sharedPref.getBoolean(IS_LOGGED_IN,false);
        accountType = sharedPref.getString(ACCOUNT_TYPE,null);
        infoAdded = sharedPref.getString(INFO_ADDED1,"false");

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
}