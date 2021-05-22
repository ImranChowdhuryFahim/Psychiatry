package com.brainfluence.psychiatry;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class DepressionCheckerActivity extends AppCompatActivity {

    private RadioGroup q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,q111,q112,q113,q114,q115,q116,q117,q118,q119,q200,q201;
    private RadioButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,b21;
    private Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_depression_checker);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        q1 = findViewById(R.id.q1);
        q2 = findViewById(R.id.q2);
        q3 = findViewById(R.id.q3);
        q4 = findViewById(R.id.q4);
        q5 = findViewById(R.id.q5);
        q6 = findViewById(R.id.q6);
        q7 = findViewById(R.id.q7);
        q8 = findViewById(R.id.q8);
        q9 = findViewById(R.id.q9);
        q10 = findViewById(R.id.q10);
        q111 = findViewById(R.id.q111);
        q112 = findViewById(R.id.q112);
        q113 = findViewById(R.id.q113);
        q114 = findViewById(R.id.q114);
        q115 = findViewById(R.id.q115);
        q116 = findViewById(R.id.q116);
        q117 = findViewById(R.id.q117);
        q118 = findViewById(R.id.q118);
        q119 = findViewById(R.id.q119);
        q200 = findViewById(R.id.q200);
        q201 = findViewById(R.id.q201);


        calculate = findViewById(R.id.calculate);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                b1 = findViewById(q1.getCheckedRadioButtonId());
                b2 = findViewById(q2.getCheckedRadioButtonId());
                b3 = findViewById(q3.getCheckedRadioButtonId());
                b4 = findViewById(q4.getCheckedRadioButtonId());
                b5 = findViewById(q5.getCheckedRadioButtonId());
                b6 = findViewById(q6.getCheckedRadioButtonId());
                b7 = findViewById(q7.getCheckedRadioButtonId());
                b8 = findViewById(q8.getCheckedRadioButtonId());
                b9 = findViewById(q9.getCheckedRadioButtonId());
                b10 = findViewById(q10.getCheckedRadioButtonId());
                b11 = findViewById(q111.getCheckedRadioButtonId());
                b12 = findViewById(q112.getCheckedRadioButtonId());
                b13 = findViewById(q113.getCheckedRadioButtonId());
                b14 = findViewById(q114.getCheckedRadioButtonId());
                b15 = findViewById(q115.getCheckedRadioButtonId());
                b16 = findViewById(q116.getCheckedRadioButtonId());
                b17 = findViewById(q117.getCheckedRadioButtonId());
                b18 = findViewById(q118.getCheckedRadioButtonId());
                b19 = findViewById(q119.getCheckedRadioButtonId());
                b20 = findViewById(q200.getCheckedRadioButtonId());
                b21 = findViewById(q201.getCheckedRadioButtonId());



                int score = Integer.parseInt(b1.getText().toString().split(" ")[0])+
                        Integer.parseInt(b2.getText().toString().split(" ")[0])+
                        Integer.parseInt(b3.getText().toString().split(" ")[0])+
                        Integer.parseInt(b4.getText().toString().split(" ")[0])+
                        Integer.parseInt(b5.getText().toString().split(" ")[0])+
                        Integer.parseInt(b6.getText().toString().split(" ")[0])+
                        Integer.parseInt(b7.getText().toString().split(" ")[0])+
                        Integer.parseInt(b8.getText().toString().split(" ")[0])+
                        Integer.parseInt(b9.getText().toString().split(" ")[0])+
                        Integer.parseInt(b10.getText().toString().split(" ")[0])+
                        Integer.parseInt(b11.getText().toString().split(" ")[0])+
                        Integer.parseInt(b12.getText().toString().split(" ")[0])+
                        Integer.parseInt(b13.getText().toString().split(" ")[0])+
                        Integer.parseInt(b14.getText().toString().split(" ")[0])+
                        Integer.parseInt(b15.getText().toString().split(" ")[0])+
                        Integer.parseInt(b16.getText().toString().split(" ")[0])+
                        Integer.parseInt(b17.getText().toString().split(" ")[0])+
                        Integer.parseInt(b18.getText().toString().split(" ")[0])+
                        Integer.parseInt(b19.getText().toString().split(" ")[0])+
                        Integer.parseInt(b20.getText().toString().split(" ")[0])+
                        Integer.parseInt(b21.getText().toString().split(" ")[0]);



                AlertDialog.Builder builder=new AlertDialog.Builder(DepressionCheckerActivity.this);
                builder.setCancelable(true);
                builder.setIcon(R.drawable.ic_baseline_info_24);
                builder.setTitle("Level of Depression");
                builder.setMessage(getDepressionLevel(score));
                builder.setInverseBackgroundForced(true);
                builder.setPositiveButton("Close",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        dialog.dismiss();
                    }
                });

                AlertDialog alert=builder.create();
                alert.show();

            }
        });


    }

    private String getDepressionLevel(int score) {


         if( score>=1 && score<=10)
        {
            return "These ups and downs are considered normal";
        }

        else if( score>=11 && score<=16 )
        {
            return "Mild mood disturbance";
        }

        else if( score>=17 && score<=20 )
        {
            return "Borderline clinical depression";
        }
        else if( score>=21 && score<=30 )
        {
            return "Moderate depression";
        }
        else if( score>=31 && score<=40 )
        {
            return "Severe depression";
        }
        else if( score>=40 )
        {
            return "Extreme depression";
        }
        else
        {
            return "You didn't select any option";
        }

    }


}