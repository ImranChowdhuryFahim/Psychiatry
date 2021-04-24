package com.brainfluence.psychiatry;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import es.dmoral.toasty.Toasty;

public class DoctorRegActivity extends AppCompatActivity {

    private TextInputLayout name,email,phoneNum,pass,cpass,degree,exp;
    private TextInputEditText nameInput,emailInput,phoneNumInput,passInput,cpassInput,degreeInput,expInput;
    private Button SignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_reg);

        name = findViewById(R.id.name);
        nameInput = findViewById(R.id.nameInput);
        email = findViewById(R.id.email);
        emailInput = findViewById(R.id.emailInput);
        phoneNum = findViewById(R.id.phoneNum);
        phoneNumInput = findViewById(R.id.phoneNumInput);
        pass = findViewById(R.id.pass);
        passInput = findViewById(R.id.passInput);
        cpass = findViewById(R.id.cpass);
        cpassInput = findViewById(R.id.cpassInput);
        SignUp = findViewById(R.id.signUp);
        degree = findViewById(R.id.degree);
        degreeInput = findViewById(R.id.degreeInput);
        exp = findViewById(R.id.exp);
        expInput = findViewById(R.id.expInput);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkConnectivity()){
                } else {
                    nointernetp();
                    return;
                }

                if(!validateName() | !validateEmail()  | !validatePhoneNumber() | !validateDegree() | !validatePass() | !validateCpass() | !validateExp() )
                {
                    return;
                }

                if(!passInput.getText().toString().trim().equals(cpassInput.getText().toString().trim()))
                {
                    cpass.setError("Password do not match");
                    return;
                }
            }
        });

    }

    private boolean validateDegree() {
        String val = degreeInput.getText().toString();

        if(val.isEmpty())
        {
            degree.setError("Field cannot be empty");
            return false;
        }
        else {
            degree.setError(null);
            return true;
        }
    }


    private boolean validateExp() {
        String val = expInput.getText().toString();

        if(val.isEmpty())
        {
            exp.setError("Field cannot be empty");
            return false;
        }
        else {
            exp.setError(null);
            return true;
        }
    }



    private boolean validateCpass() {
        String val = cpassInput.getText().toString();

        if(val.isEmpty())
        {
            cpass.setError("Field cannot be empty");
            return false;
        }
        else {
            cpass.setError(null);
            return true;
        }
    }

    private boolean validatePass() {
        String val = passInput.getText().toString();

        if(val.isEmpty())
        {
            pass.setError("Field cannot be empty");
            return false;
        }
        else {
            pass.setError(null);
            return true;
        }
    }


    private boolean validatePhoneNumber() {
        String val = phoneNumInput.getText().toString().trim();
        String phoneNumberPattern = "^([0][1]|[+][8][8][0][1])([3-9]{1}[0-9]{8})";
        if(val.isEmpty())
        {
            phoneNum.setError("Field cannot be empty");
            return false;
        }
        if(!val.matches(phoneNumberPattern))
        {
            phoneNum.setError("Invalid phone number");
            return false;
        }
        else {
            phoneNum.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String val = emailInput.getText().toString();
        String emailPattern = "^(.+)@(.+)$";

        if(val.isEmpty())
        {
            email.setError("Field cannot be empty");
            return false;
        }
        if(!val.matches(emailPattern))
        {
            email.setError("Invalid email address");
            return false;
        }
        else {
            email.setError(null);
            return true;
        }
    }

    private boolean validateName() {
        String val = nameInput.getText().toString();

        if(val.isEmpty())
        {
            name.setError("Field cannot be empty");
            return false;
        }
        else {
            name.setError(null);
            return true;
        }
    }


    private void nointernetp() {
        AlertDialog.Builder builder=new AlertDialog.Builder(DoctorRegActivity.this);
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

                Intent intent = new Intent(getBaseContext(), DoctorRegActivity.class);
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