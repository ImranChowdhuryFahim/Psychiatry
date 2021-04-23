package com.brainfluence.psychiatry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class QuestionnaireActivity extends AppCompatActivity {

    private RadioGroup ques1,ques2,ques4,ques5,ques6,ques7,ques8,ques9,ques10,ques111,ques112,ques113,ques114;
    private RadioButton b1,b2,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14;
    private TextInputEditText cgpaInput;
    private TextInputLayout cgpa;
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        cgpa = findViewById(R.id.cgpa);
        cgpaInput = findViewById(R.id.cgpaInput);
        submit = findViewById(R.id.submit);

        ques1 = findViewById(R.id.ques1);
        ques2 = findViewById(R.id.ques2);
        ques4 = findViewById(R.id.ques4);
        ques5 = findViewById(R.id.ques5);
        ques6 = findViewById(R.id.ques6);
        ques7 = findViewById(R.id.ques7);
        ques8 = findViewById(R.id.ques8);
        ques9 = findViewById(R.id.ques9);
        ques10 = findViewById(R.id.ques10);
        ques111 = findViewById(R.id.ques111);
        ques112 = findViewById(R.id.ques112);
        ques113 = findViewById(R.id.ques113);
        ques114 = findViewById(R.id.ques114);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1 = findViewById(ques1.getCheckedRadioButtonId());

            }
        });








    }
}