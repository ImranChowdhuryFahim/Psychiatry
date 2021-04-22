package com.brainfluence.psychiatry.ui.depressionMeter;

import android.content.Intent;
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



public class DepressionMeter extends Fragment {

    private Button start;
    private TextView infoDepressionCheck;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_depression_meter, container, false);
        start = root.findViewById(R.id.start);
        infoDepressionCheck = root.findViewById(R.id.infoDepressionCheck);

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