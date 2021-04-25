package com.brainfluence.psychiatry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brainfluence.psychiatry.ViewHolder.ProblemViewHolder;
import com.brainfluence.psychiatry.model.ProblemModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentDetailsActivity extends AppCompatActivity {
    private TextView studentName,studentId,studentDept,studentPhoneNumber,studentNote,studentUniName;
    private String uid,note,id,uniName,name,phoneNumber,dept;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseRecyclerAdapter<ProblemModel, ProblemViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        studentName = findViewById(R.id.studentName);
        studentId = findViewById(R.id.studentId);
        studentDept = findViewById(R.id.studentDept);
        studentNote = findViewById(R.id.studentNote);
        studentPhoneNumber = findViewById(R.id.studentPhoneNumber);
        studentUniName = findViewById(R.id.studentUniName);
        recyclerView = findViewById(R.id.educationalProblemsList);


        uid = getIntent().getStringExtra("uid");
        note = getIntent().getStringExtra("note");
        id = getIntent().getStringExtra("id");
        uniName = getIntent().getStringExtra("uniName");
        name = getIntent().getStringExtra("name");
        phoneNumber = getIntent().getStringExtra("phoneNumber");
        dept = getIntent().getStringExtra("dept");



        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("academicProblems");
        databaseReference.keepSynced(true);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        studentName.setText("Name: "+name);
        studentId.setText("ID: "+id);
        studentDept.setText("Department: "+dept);
        studentNote.setText("Student's Note: "+note);
        studentPhoneNumber.setText("Phone Number: "+  phoneNumber);
        studentUniName.setText("University Name: "+uniName);



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