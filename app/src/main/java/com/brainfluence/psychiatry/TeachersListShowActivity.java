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
import com.brainfluence.psychiatry.ViewHolder.TeacherViewHolder;
import com.brainfluence.psychiatry.model.DoctorModel;
import com.brainfluence.psychiatry.model.TeacherModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import static com.brainfluence.psychiatry.LoginActivity.SHARED_PREFS;
import static com.brainfluence.psychiatry.LoginActivity.UNIVERSITY_NAME;

public class TeachersListShowActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private SharedPreferences sharedPref;
    private FirebaseRecyclerAdapter<TeacherModel, TeacherViewHolder> adapter;
    private String uid;
    private String uniName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_list_show);



        recyclerView = findViewById(R.id.teachersListShow);
        sharedPref = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("teachers");
        databaseReference.keepSynced(true);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        uniName = sharedPref.getString(UNIVERSITY_NAME,"CUET");

        Query query = databaseReference.orderByChild("universityName").equalTo(uniName);

        FirebaseRecyclerOptions options=new FirebaseRecyclerOptions.Builder<TeacherModel>().setQuery(query ,TeacherModel.class).build();


        adapter = new FirebaseRecyclerAdapter<TeacherModel, TeacherViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull TeacherViewHolder holder, int position, @NonNull TeacherModel model) {
                holder.teacherName.setText(model.getName());
                holder.teacherDegree.setText(model.getPosition()+","+model.getDepartment()+","+model.getUniversityName());
                if(model.dsw.equals("yes"))
                {
                    holder.dsw.setText("Associated with DSW");
                }


            }

            @NonNull
            @Override
            public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.teacher_list_row,parent,false);
               TeacherViewHolder teacherViewHolder = new TeacherViewHolder(view);
                return teacherViewHolder;
            }
        };

        adapter.startListening();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}