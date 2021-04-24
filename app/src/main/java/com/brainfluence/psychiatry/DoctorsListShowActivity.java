package com.brainfluence.psychiatry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brainfluence.psychiatry.ViewHolder.DoctorViewHolder;
import com.brainfluence.psychiatry.model.DoctorModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import static com.brainfluence.psychiatry.LoginActivity.SHARED_PREFS;
import static com.brainfluence.psychiatry.LoginActivity.UID;

public class DoctorsListShowActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private SharedPreferences sharedPref;
    private FirebaseRecyclerAdapter<DoctorModel, DoctorViewHolder> adapter;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_list_show);


        recyclerView = findViewById(R.id.doctorsListShow);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("doctors");
        databaseReference.keepSynced(true);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);



        FirebaseRecyclerOptions options=new FirebaseRecyclerOptions.Builder<DoctorModel>().setQuery(databaseReference ,DoctorModel.class).build();


        adapter = new FirebaseRecyclerAdapter<DoctorModel, DoctorViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull DoctorViewHolder holder, int position, @NonNull DoctorModel model) {

                holder.doctorName.setText(model.getName());
                holder.doctorDegree.setText(model.getDegree());
                holder.doctorExp.setText(model.getExp()+ " years experienced");
            }

            @NonNull
            @Override
            public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.doctors_list_row,parent,false);
                DoctorViewHolder doctorViewHolder = new DoctorViewHolder(view);
                return doctorViewHolder;
            }
        };
            adapter.startListening();
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);

    }
}