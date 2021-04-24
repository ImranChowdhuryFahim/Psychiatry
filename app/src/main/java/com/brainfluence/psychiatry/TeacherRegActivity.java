package com.brainfluence.psychiatry;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;

import es.dmoral.toasty.Toasty;

public class TeacherRegActivity extends AppCompatActivity {

    private TextInputLayout position,uniName,name,email,phoneNum,dept,pass,cpass;
    private TextInputEditText nameInput,emailInput,phoneNumInput,deptInput,passInput,cpassInput;
    private AutoCompleteTextView autoCompleteUniName,positionInput;
    private Button SignUp;
    private ArrayList<String> uniNameList,positionList;
    private ArrayAdapter<String> uniNameAdapter,positionAdapter;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_reg);


        uniName = findViewById(R.id.uniName);
        autoCompleteUniName = findViewById(R.id.autoCompleteUniName);
        positionInput = findViewById(R.id.positionInput);
        name = findViewById(R.id.name);
        nameInput = findViewById(R.id.nameInput);
        email = findViewById(R.id.email);
        emailInput = findViewById(R.id.emailInput);
        phoneNum = findViewById(R.id.phoneNum);
        phoneNumInput = findViewById(R.id.phoneNumInput);
        dept = findViewById(R.id.dept);
        deptInput = findViewById(R.id.deptInput);
        pass = findViewById(R.id.pass);
        passInput = findViewById(R.id.passInput);
        cpass = findViewById(R.id.cpass);
        cpassInput = findViewById(R.id.cpassInput);
        SignUp = findViewById(R.id.signUp);
        radioGroup = findViewById(R.id.radioDSW);
        position = findViewById(R.id.position);


        uniNameList = new ArrayList<String>();
        uniNameList.add("BUET");
        uniNameList.add("CUET");
        uniNameList.add("KUET");
        uniNameList.add("RUET");
        uniNameList.add("CU");
        uniNameList.add("DU");
        uniNameList.add("SUST");
        uniNameList.add("DUET");
        uniNameList.add("JU");
        uniNameList.add("JNU");
        uniNameList.add("PUST");



        positionList = new ArrayList<String>();
        positionList.add("Lecturer");
        positionList.add("Assistant Professor");
        positionList.add("Associate Professor");
        positionList.add("Professor");
        positionList.add("Head");

        positionAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.uni_name_list,positionList);
        positionInput.setAdapter(positionAdapter);
        positionInput.setThreshold(1);

        uniNameAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.uni_name_list,uniNameList);
        autoCompleteUniName.setAdapter(uniNameAdapter);
        autoCompleteUniName.setThreshold(1);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkConnectivity()){
                } else {
                    nointernetp();
                    return;
                }

                if(!validateName() | !validateEmail()  | !validatePhoneNumber() | !validatePosition() | !validatePass() | !validateCpass() | !validateDept() | !validateUniName() )
                {
                    return;
                }

                if(!passInput.getText().toString().trim().equals(cpassInput.getText().toString().trim()))
                {
                    Toasty.warning(TeacherRegActivity.this,"Password do not match.",Toasty.LENGTH_SHORT).show();
                    return;
                }

                radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                radioButton.getText();
            }
        });

    }

    private boolean validatePosition() {
        String val = positionInput.getText().toString();

        if(val.isEmpty())
        {
            position.setError("Field cannot be empty");
            return false;
        }
        else {
            position.setError(null);
            return true;
        }
    }


    private boolean validateUniName() {
        String val = autoCompleteUniName.getText().toString();

        if(val.isEmpty())
        {
            uniName.setError("Field cannot be empty");
            return false;
        }
        else {
            uniName.setError(null);
            return true;
        }
    }

    private boolean validateDept() {
        String val = deptInput.getText().toString();

        if(val.isEmpty())
        {
            dept.setError("Field cannot be empty");
            return false;
        }
        else {
            dept.setError(null);
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
        AlertDialog.Builder builder=new AlertDialog.Builder(TeacherRegActivity.this);
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

                Intent intent = new Intent(getBaseContext(), TeacherRegActivity.class);
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