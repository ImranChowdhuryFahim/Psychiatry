package com.brainfluence.psychiatry;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private TextView reg;
    private Button login;
    private ArrayList<String> accountTypeList;
    private ArrayAdapter<String> accountTypeAdapter;
    private AutoCompleteTextView accountTypeInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        reg = findViewById(R.id.reg);
        accountTypeInput = findViewById(R.id.accountTypeInput);
        login = findViewById(R.id.login);

        accountTypeList = new ArrayList<String>();
        accountTypeList.add("Teacher");
        accountTypeList.add("Student");
        accountTypeList.add("Doctor");

        accountTypeAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.uni_name_list,accountTypeList);
        accountTypeInput.setAdapter(accountTypeAdapter);
        accountTypeInput.setThreshold(1);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,Home.class));
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        exitapp();
    }
    private void exitapp() {
        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
        builder.setCancelable(true);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("Exit App");
        builder.setMessage("Are you sure you want to leave the application?");
        builder.setInverseBackgroundForced(true);
        builder.setPositiveButton("Yes",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which){
                finish();

            }
        });

        builder.setNegativeButton("No",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }
}