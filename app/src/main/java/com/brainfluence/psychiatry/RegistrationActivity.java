package com.brainfluence.psychiatry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegistrationActivity extends AppCompatActivity {
    private Button studentReg,teacherReg,doctorReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_registration);

        studentReg = findViewById(R.id.signUpAsStudent);
        teacherReg = findViewById(R.id.signUpAsTeacher);
        doctorReg = findViewById(R.id.signUpAsDoctor);

        studentReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this,StudentRegActivity.class));
            }
        });

        teacherReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this,TeacherRegActivity.class));
            }
        });

        doctorReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this,DoctorRegActivity.class));
            }
        });
    }
}