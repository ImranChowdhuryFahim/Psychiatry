package com.brainfluence.psychiatry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brainfluence.psychiatry.ViewHolder.DoctorRequestViewHolder;
import com.brainfluence.psychiatry.ViewHolder.ProblemViewHolder;
import com.brainfluence.psychiatry.model.DoctorRequestModel;
import com.brainfluence.psychiatry.model.ProblemModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.brainfluence.psychiatry.LoginActivity.SHARED_PREFS;
import static com.brainfluence.psychiatry.LoginActivity.UID;

public class PatientDetailsActivity extends AppCompatActivity {

    private TextView patientName,patientAge,patientGender,patientPhoneNumber,patientNote;
    private String uid,note,age,gender,name,phoneNumber;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseRecyclerAdapter<ProblemModel, ProblemViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        patientName = findViewById(R.id.patientName);
        patientAge = findViewById(R.id.patientAge);
        patientGender = findViewById(R.id.patientGender);
        patientPhoneNumber = findViewById(R.id.patientPhoneNumber);
        patientNote = findViewById(R.id.patientNote);

        uid = getIntent().getStringExtra("uid");
        note = getIntent().getStringExtra("note");
        age = getIntent().getStringExtra("age");
        gender = getIntent().getStringExtra("gender");
        name = getIntent().getStringExtra("name");
        phoneNumber = getIntent().getStringExtra("phoneNumber");



        recyclerView = findViewById(R.id.psychologicalProblemsList);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("psychologicalProblems");
        databaseReference.keepSynced(true);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);


        patientName.setText("Name: "+name);
        patientAge.setText("Age: "+age);
        patientNote.setText("Patient's Note: "+note);
        patientGender.setText("Sex: "+gender);
        patientPhoneNumber.setText("Phone Number: "+phoneNumber);



        FirebaseRecyclerOptions options=new FirebaseRecyclerOptions.Builder<ProblemModel>().setQuery(databaseReference.child(uid) ,ProblemModel.class).build();

        adapter = new FirebaseRecyclerAdapter<ProblemModel, ProblemViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ProblemViewHolder holder, int position, @NonNull ProblemModel model) {
                holder.problemName.setText(model.getName());

            }

            @NonNull
            @Override
            public ProblemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.problem_row,parent,false);
                ProblemViewHolder problemViewHolder = new ProblemViewHolder(view);
                return problemViewHolder;
            }
        };

        adapter.startListening();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);


    }
}