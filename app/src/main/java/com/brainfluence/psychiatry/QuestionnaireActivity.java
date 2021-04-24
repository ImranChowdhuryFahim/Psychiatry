package com.brainfluence.psychiatry;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.brainfluence.psychiatry.model.InfoModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import static com.brainfluence.psychiatry.LoginActivity.INFO_ADDED1;
import static com.brainfluence.psychiatry.LoginActivity.SHARED_PREFS;
import static com.brainfluence.psychiatry.LoginActivity.UID;

public class QuestionnaireActivity extends AppCompatActivity {

    private RadioGroup ques1,ques2,ques4,ques5,ques6,ques7,ques8,ques9,ques10,ques111,ques112,ques113,ques114;
    private RadioButton b1,b2,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14;
    private TextInputEditText cgpaInput;
    private TextInputLayout cgpa;
    private Button submit;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference,databaseReference1;
    private SharedPreferences sharedPref;
    private String uid;


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

        sharedPref = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        uid = sharedPref.getString(UID,"123");

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("");
        databaseReference1 = firebaseDatabase.getReference("");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validateCGPA())
                {
                    return;
                }




                b1 = findViewById(ques1.getCheckedRadioButtonId());
                b2 = findViewById(ques2.getCheckedRadioButtonId());
                b4 = findViewById(ques4.getCheckedRadioButtonId());
                b5 = findViewById(ques5.getCheckedRadioButtonId());
                b6 = findViewById(ques6.getCheckedRadioButtonId());
                b7 = findViewById(ques7.getCheckedRadioButtonId());
                b8 = findViewById(ques8.getCheckedRadioButtonId());
                b9 = findViewById(ques9.getCheckedRadioButtonId());
                b10 = findViewById(ques10.getCheckedRadioButtonId());
                b11 = findViewById(ques111.getCheckedRadioButtonId());
                b12 = findViewById(ques112.getCheckedRadioButtonId());
                b13 = findViewById(ques113.getCheckedRadioButtonId());
                b14 = findViewById(ques114.getCheckedRadioButtonId());
                Random random = new Random();

                if(Double.parseDouble(cgpaInput.getText().toString()) < 3.00)
                {
                    databaseReference1.child("academicProblems").child(uid).child(System.currentTimeMillis()+""+random.nextInt()).child("name").setValue("Low CGPA");
                }
                if(b13.getText().toString().equals("yes"))
                {
                    databaseReference1.child("academicProblems").child(uid).child(System.currentTimeMillis()+""+random.nextInt()).child("name").setValue("Got Ragged");
                }

                if(b1.getText().toString().equals("yes"))
                {
                    databaseReference1.child("psychologicalProblems").child(uid).child(System.currentTimeMillis()+""+random.nextInt()).child("name").setValue("Has Mental Illness");
                }
                if(b2.getText().toString().equals("no"))
                {
                    databaseReference1.child("academicProblems").child(uid).child(System.currentTimeMillis()+""+random.nextInt()).child("name").setValue("Not Happy With The Academic Condition");
                }
                if(b4.getText().toString().equals("yes"))
                {
                    databaseReference1.child("psychologicalProblems").child(uid).child(System.currentTimeMillis()+""+random.nextInt()).child("name").setValue("Drug Addicted");
                }
                if(b5.getText().toString().equals("yes") && b6.getText().toString().equals("no"))
                {
                    databaseReference1.child("psychologicalProblems").child(uid).child(System.currentTimeMillis()+""+random.nextInt()).child("name").setValue("Not Happy In Relationship");
                }
                if(b7.getText().toString().equals("yes"))
                {
                    databaseReference1.child("psychologicalProblems").child(uid).child(System.currentTimeMillis()+""+random.nextInt()).child("name").setValue("Had Recent Breakup");
                }
                if(b8.getText().toString().equals("yes"))
                {
                    databaseReference1.child("psychologicalProblems").child(uid).child(System.currentTimeMillis()+""+random.nextInt()).child("name").setValue("Had Conflict With Friends");
                }
                if(b9.getText().toString().equals("yes"))
                {
                    databaseReference1.child("academicProblems").child(uid).child(System.currentTimeMillis()+""+random.nextInt()).child("name").setValue("Has Financial Problem");
                }
                if(b10.getText().toString().equals("yes"))
                {
                    databaseReference1.child("psychologicalProblems").child(uid).child(System.currentTimeMillis()+""+random.nextInt()).child("name").setValue("Has Sadness");
                }
                if(b11.getText().toString().equals("yes"))
                {
                    databaseReference1.child("psychologicalProblems").child(uid).child(System.currentTimeMillis()+""+random.nextInt()).child("name").setValue("Has Bad Habit");
                }
                if(b12.getText().toString().equals("yes"))
                {
                    databaseReference1.child("academicProblems").child(uid).child(System.currentTimeMillis()+""+random.nextInt()).child("name").setValue("Got Bullied");
                }
                if(b14.getText().toString().equals("yes"))
                {
                    databaseReference1.child("academicProblems").child(uid).child(System.currentTimeMillis()+""+random.nextInt()).child("name").setValue("Got Harassed");
                }

                databaseReference.child("students").child(uid).child("infoAdded").setValue("true");

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(INFO_ADDED1,"true");
                editor.apply();


                startActivity(new Intent(QuestionnaireActivity.this,Home.class));
                finish();

            }
        });








    }

    private boolean validateCGPA() {
        String val = cgpaInput.getText().toString().trim();
        String pattern = "^\\d+(\\.\\d+)*$";

        if(val.isEmpty())
        {
            cgpa.setError("Field can't be empty");
            return false;
        }
        else if(!val.matches(pattern))
        {
            cgpa.setError("Invalid CGPA");
            return false;
        }
        else {
            return true;
        }

    }
}