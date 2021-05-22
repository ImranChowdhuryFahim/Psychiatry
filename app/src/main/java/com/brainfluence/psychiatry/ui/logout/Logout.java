package com.brainfluence.psychiatry.ui.logout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.brainfluence.psychiatry.Home;
import com.brainfluence.psychiatry.LoginActivity;
import com.brainfluence.psychiatry.QuestionnaireActivity;
import com.brainfluence.psychiatry.R;
import com.google.firebase.auth.FirebaseAuth;

import static com.brainfluence.psychiatry.LoginActivity.IS_LOGGED_IN;
import static com.brainfluence.psychiatry.LoginActivity.SHARED_PREFS;


public class Logout extends Fragment {

    private SharedPreferences sharedPref;
    private Button logoutButton;
    private ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        View root = inflater.inflate(R.layout.fragment_logout, container, false);

        logoutButton = root.findViewById(R.id.logoutButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Please wait..."); // Setting Message
                progressDialog.setTitle("Logging out"); // Setting Title
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                progressDialog.show(); // Display Progress Dialog
                progressDialog.setCancelable(false);

                FirebaseAuth.getInstance().signOut();
                sharedPref = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                sharedPref.edit().clear().commit();

                new CountDownTimer(2000, 1000) {
                    @Override
                    public void onFinish() {

                        progressDialog.dismiss();
                        Intent intent=new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                        getActivity().finish();


                    }
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }
                }.start();
//                SharedPreferences.Editor.clear().commit();

//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putBoolean(IS_LOGGED_IN,false);
//                editor.apply();

            }
        });

        return root;
    }
}