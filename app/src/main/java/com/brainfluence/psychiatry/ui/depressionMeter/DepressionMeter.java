package com.brainfluence.psychiatry.ui.depressionMeter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.brainfluence.psychiatry.DepressionCheckerActivity;
import com.brainfluence.psychiatry.Info;
import com.brainfluence.psychiatry.R;

import static com.brainfluence.psychiatry.LoginActivity.SHARED_PREFS;
import static com.brainfluence.psychiatry.LoginActivity.USER_NAME;


public class DepressionMeter extends Fragment {

    private SharedPreferences sharedPref;
    private Button start;
    private TextView infoDepressionCheck,welcomeUser;
    private String userName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_depression_meter, container, false);
        start = root.findViewById(R.id.start);
        infoDepressionCheck = root.findViewById(R.id.infoDepressionCheck);
        welcomeUser = root.findViewById(R.id.welcomeUser);
        sharedPref = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        userName = sharedPref.getString(USER_NAME,"user");

        welcomeUser.setText("You're not alone,"+userName);
        Info info = new Info();

        infoDepressionCheck.setText(info.getDepressionCheck());

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DepressionCheckerActivity.class));
            }
        });
        return root;
    }
}