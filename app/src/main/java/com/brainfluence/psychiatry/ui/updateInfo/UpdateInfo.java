package com.brainfluence.psychiatry.ui.updateInfo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.brainfluence.psychiatry.Home;
import com.brainfluence.psychiatry.LoginActivity;
import com.brainfluence.psychiatry.QuestionnaireActivity;
import com.brainfluence.psychiatry.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import static com.brainfluence.psychiatry.LoginActivity.INFO_ADDED1;
import static com.brainfluence.psychiatry.LoginActivity.SHARED_PREFS;
import static com.brainfluence.psychiatry.LoginActivity.UID;


public class UpdateInfo extends Fragment {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        View root = inflater.inflate(R.layout.fragment_update_info, container, false);


        cgpa = root.findViewById(R.id.cgpa);
        cgpaInput = root.findViewById(R.id.cgpaInput);
        submit = root.findViewById(R.id.submit);

        ques1 = root.findViewById(R.id.ques1);
        ques2 = root.findViewById(R.id.ques2);
        ques4 = root.findViewById(R.id.ques4);
        ques5 = root.findViewById(R.id.ques5);
        ques6 = root.findViewById(R.id.ques6);
        ques7 =root.findViewById(R.id.ques7);
        ques8 = root.findViewById(R.id.ques8);
        ques9 = root.findViewById(R.id.ques9);
        ques10 = root.findViewById(R.id.ques10);
        ques111 = root.findViewById(R.id.ques111);
        ques112 = root.findViewById(R.id.ques112);
        ques113 = root.findViewById(R.id.ques113);
        ques114 = root.findViewById(R.id.ques114);

        sharedPref = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
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

                progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Please wait..."); // Setting Message
                progressDialog.setTitle("Updating info"); // Setting Title
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                progressDialog.show(); // Display Progress Dialog
                progressDialog.setCancelable(false);

                databaseReference1.child("academicProblems").child(uid).removeValue();
                databaseReference1.child("psychologicalProblems").child(uid).removeValue();

                b1 = root.findViewById(ques1.getCheckedRadioButtonId());
                b2 = root.findViewById(ques2.getCheckedRadioButtonId());
                b4 = root.findViewById(ques4.getCheckedRadioButtonId());
                b5 = root.findViewById(ques5.getCheckedRadioButtonId());
                b6 = root.findViewById(ques6.getCheckedRadioButtonId());
                b7 = root.findViewById(ques7.getCheckedRadioButtonId());
                b8 = root.findViewById(ques8.getCheckedRadioButtonId());
                b9 = root.findViewById(ques9.getCheckedRadioButtonId());
                b10 = root.findViewById(ques10.getCheckedRadioButtonId());
                b11 = root.findViewById(ques111.getCheckedRadioButtonId());
                b12 = root.findViewById(ques112.getCheckedRadioButtonId());
                b13 = root.findViewById(ques113.getCheckedRadioButtonId());
                b14 = root.findViewById(ques114.getCheckedRadioButtonId());
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

                        startActivity(new Intent(getActivity(), Home.class));
                        getActivity().finish();


                    }
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }
                }.start();

            }
        });
        return root;

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
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
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

                Intent intent = new Intent(getContext(), Home.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }

    private boolean checkConnectivity() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if ((info == null || !info.isConnected() || !info.isAvailable())) {
            // Toast.makeText(getApplicationContext(), "Sin conexión a Internet...", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}