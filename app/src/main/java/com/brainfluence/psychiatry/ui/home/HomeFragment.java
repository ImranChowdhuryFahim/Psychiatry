package com.brainfluence.psychiatry.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.brainfluence.psychiatry.R;
import com.brainfluence.psychiatry.TextShowActivity;
import com.google.android.material.card.MaterialCardView;

public class HomeFragment extends Fragment {

    private MaterialCardView mentalHealth,stress,apd,anxiety,bipolar,bpd,clinicalDepression,mentalIllness,ocd,eatingDisorder,drugAddiction;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        mentalHealth = root.findViewById(R.id.mentalHealth);
        stress = root.findViewById(R.id.stress);
        apd = root.findViewById(R.id.apd);
        anxiety = root.findViewById(R.id.anxiety);
        bipolar = root.findViewById(R.id.bipolar);
        bpd = root.findViewById(R.id.bpd);
        clinicalDepression = root.findViewById(R.id.clinicalDepression);
        mentalIllness = root.findViewById(R.id.mentalIllness);
        ocd = root.findViewById(R.id.ocd);
        eatingDisorder = root.findViewById(R.id.eatingDisorder);
        drugAddiction = root.findViewById(R.id.drugAddiction);

        mentalHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),TextShowActivity.class);
                intent.putExtra("title","Mental Health");
                startActivity(intent);
            }
        });

        stress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),TextShowActivity.class);
                intent.putExtra("title","Acute Stress Disorder");
                startActivity(intent);
            }
        });

        apd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),TextShowActivity.class);
                intent.putExtra("title","Antisocial Personality Disorder");
                startActivity(intent);
            }
        });

        anxiety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),TextShowActivity.class);
                intent.putExtra("title","Anxiety Disorder");
                startActivity(intent);
            }
        });

        bipolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),TextShowActivity.class);
                intent.putExtra("title","Bipolar Disorder");
                startActivity(intent);
            }
        });

        bpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),TextShowActivity.class);
                intent.putExtra("title","Borderline Personality Disorder");
                startActivity(intent);
            }
        });

        clinicalDepression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),TextShowActivity.class);
                intent.putExtra("title","Clinical Depression");
                startActivity(intent);
            }
        });

        mentalIllness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),TextShowActivity.class);
                intent.putExtra("title","Mental Illness");
                startActivity(intent);
            }
        });

        ocd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),TextShowActivity.class);
                intent.putExtra("title","Obsessive Compulsive Disorder (OCD)");
                startActivity(intent);
            }
        });

        eatingDisorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),TextShowActivity.class);
                intent.putExtra("title","Eating Disorder");
                startActivity(intent);
            }
        });

        drugAddiction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),TextShowActivity.class);
                intent.putExtra("title","Drug Addiction");
                startActivity(intent);
            }
        });

        return root;
    }
}