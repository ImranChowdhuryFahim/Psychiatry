package com.brainfluence.psychiatry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class TextShowActivity extends AppCompatActivity {
    private TextView textShow;
    private ImageView infoImage;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_show);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        textShow = findViewById(R.id.textShow);
        infoImage = findViewById(R.id.infoImage);

        title = getIntent().getStringExtra("title");

        Info info = new Info();

        getSupportActionBar().setTitle(title);

        switch (title){

            case "Mental Health":
                textShow.setText(info.getMentalHealth());
                infoImage.setImageDrawable(getResources().getDrawable(R.drawable.mental_health));
                break;
            case "Acute Stress Disorder":
                textShow.setText(info.getStress());
                infoImage.setImageDrawable(getResources().getDrawable(R.drawable.stress));
                break;
            case "Antisocial Personality Disorder":
                textShow.setText(info.getApd());
                infoImage.setImageDrawable(getResources().getDrawable(R.drawable.apsd));
                break;
            case "Anxiety Disorder":
                textShow.setText(info.getAnxiety());
                infoImage.setImageDrawable(getResources().getDrawable(R.drawable.anxiety));
                break;
            case "Bipolar Disorder":
                textShow.setText(info.getBipolar());
                infoImage.setImageDrawable(getResources().getDrawable(R.drawable.bipolar));
                break;
            case "Borderline Personality Disorder":
                textShow.setText(info.getBpd());
                infoImage.setImageDrawable(getResources().getDrawable(R.drawable.borderline));
                break;
            case "Clinical Depression":
                textShow.setText(info.getClinicalDepression());
                infoImage.setImageDrawable(getResources().getDrawable(R.drawable.clinical));
                break;
            case "Mental Illness":
                textShow.setText(info.getMentalIllness());
                infoImage.setImageDrawable(getResources().getDrawable(R.drawable.mentalillness));
                break;
            case "Obsessive Compulsive Disorder (OCD)":
                textShow.setText(info.getOcd());
                infoImage.setImageDrawable(getResources().getDrawable(R.drawable.ocd));
                break;
            case "Eating Disorder":
                textShow.setText(info.getEatingDisorder());
                infoImage.setImageDrawable(getResources().getDrawable(R.drawable.eating));
                break;
            case "Drug Addiction":
                textShow.setText(info.getDrugAddiction());
                infoImage.setImageDrawable(getResources().getDrawable(R.drawable.drug));
                break;
            default:
                break;
        }

    }
}