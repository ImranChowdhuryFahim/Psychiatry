package com.brainfluence.psychiatry;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
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
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
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

                if (checkConnectivity()){
                } else {
                    nointernetp();
                    return;
                }

                if(!validateCGPA())
                {
                    return;
                }


                progressDialog = new ProgressDialog(QuestionnaireActivity.this);
                progressDialog.setMessage("Please wait..."); // Setting Message
                progressDialog.setTitle("Uploading info"); // Setting Title
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                progressDialog.show(); // Display Progress Dialog
                progressDialog.setCancelable(false);


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

                if(Double.parseDouble(cgpaInput.getText().toString()) < 3.00 | b2.getText().toString().equals("no"))
                {
                    databaseReference1.child("academicProblems").child(uid).child(System.currentTimeMillis()+""+random.nextInt()).child("name").setValue("Problem in Study/Academic problem");
                }


                if(b1.getText().toString().equals("yes"))
                {
                    databaseReference1.child("psychologicalProblems").child(uid).child(System.currentTimeMillis()+""+random.nextInt()).child("name").setValue("Has Mental Illness");
                }
                if(b5.getText().toString().equals("yes") && b6.getText().toString().equals("no"))
                {
                    databaseReference1.child("psychologicalProblems").child(uid).child(System.currentTimeMillis()+""+random.nextInt()).child("name").setValue("Not Happy In Relationship");
                }
                if(b8.getText().toString().equals("yes"))
                {
                    databaseReference1.child("psychologicalProblems").child(uid).child(System.currentTimeMillis()+""+random.nextInt()).child("name").setValue("Had Conflict With Friends");
                }
                if(b9.getText().toString().equals("yes"))
                {
                    databaseReference1.child("academicProblems").child(uid).child(System.currentTimeMillis()+""+random.nextInt()).child("name").setValue("Has Financial Problem");
                }
                if(b7.getText().toString().equals("yes") | b10.getText().toString().equals("yes"))
                {
                    databaseReference1.child("psychologicalProblems").child(uid).child(System.currentTimeMillis()+""+random.nextInt()).child("name").setValue("Suffers From Loss");
                }
                if(b4.getText().toString().equals("yes") | b11.getText().toString().equals("yes"))
                {
                    databaseReference1.child("psychologicalProblems").child(uid).child(System.currentTimeMillis()+""+random.nextInt()).child("name").setValue("Has Addiction");
                }

                if(b12.getText().toString().equals("yes") | b13.getText().toString().equals("yes") | b14.getText().toString().equals("yes"))
                {
                    databaseReference1.child("academicProblems").child(uid).child(System.currentTimeMillis()+""+random.nextInt()).child("name").setValue("Been Harassed");
                }

                databaseReference.child("students").child(uid).child("infoAdded").setValue("true");

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(INFO_ADDED1,"true");
                editor.apply();

                new CountDownTimer(2000, 1000) {
                    @Override
                    public void onFinish() {

                        progressDialog.dismiss();
                        startActivity(new Intent(QuestionnaireActivity.this,Home.class));
                        finish();


                    }
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }
                }.start();



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


    private void nointernetp() {
        AlertDialog.Builder builder=new AlertDialog.Builder(QuestionnaireActivity.this);
        builder.setCancelable(true);
        builder.setIcon(R.drawable.ic_baseline_network_check_24);
        builder.setTitle("Bad Connection");
        builder.setMessage("No internet access, please activate the internet to use the app!");
        builder.setInverseBackgroundForced(true);
        builder.setPositiveButton("Close",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Reload",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which){

                Intent intent = new Intent(getBaseContext(),QuestionnaireActivity.class);
                startActivity(intent);
                finish();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }

    private boolean checkConnectivity() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if ((info == null || !info.isConnected() || !info.isAvailable())) {
            // Toast.makeText(getApplicationContext(), "Sin conexión a Internet...", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}