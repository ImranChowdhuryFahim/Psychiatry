package com.brainfluence.psychiatry.ui.studentRequests;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.brainfluence.psychiatry.PatientDetailsActivity;
import com.brainfluence.psychiatry.R;
import com.brainfluence.psychiatry.StudentDetailsActivity;
import com.brainfluence.psychiatry.ViewHolder.DoctorRequestViewHolder;
import com.brainfluence.psychiatry.ViewHolder.StudentRequestViewHolder;
import com.brainfluence.psychiatry.model.DoctorRequestModel;
import com.brainfluence.psychiatry.model.StudentRequestModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.brainfluence.psychiatry.LoginActivity.SHARED_PREFS;
import static com.brainfluence.psychiatry.LoginActivity.UID;


public class StudentRequests extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private SharedPreferences sharedPref;
    private FirebaseRecyclerAdapter<StudentRequestModel, StudentRequestViewHolder> adapter;
    private String uid;
    private Button shareWithTeacher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        View root = inflater.inflate(R.layout.fragment_student_requests, container, false);

        recyclerView = root.findViewById(R.id.studentRequestRecycler);

        sharedPref = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        uid = sharedPref.getString(UID,"123");
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("studentRequests");
        databaseReference.keepSynced(true);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        if(!uid.equals("123"))
        {
            FirebaseRecyclerOptions options=new FirebaseRecyclerOptions.Builder<StudentRequestModel>().setQuery(databaseReference.child(uid) ,StudentRequestModel.class).build();

            adapter = new FirebaseRecyclerAdapter<StudentRequestModel, StudentRequestViewHolder>(options) {
                @Override
                protected void onBindViewHolder(@NonNull StudentRequestViewHolder holder, int position, @NonNull StudentRequestModel model) {
                    holder.requestDate.setText(model.getRequestDate());

                    holder.studentName.setText(model.getStudentName());
                    holder.studentInfo.setText(model.getStudentId()+","+model.getStudentDept()+","+model.getStudentUniName());
                    holder.studentDetails.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            Intent intent = new Intent(getActivity(), StudentDetailsActivity.class);
                            intent.putExtra("uid",model.getStudentUid());
                            intent.putExtra("note",model.getStudentDetails());
                            intent.putExtra("id",model.getStudentId());
                            intent.putExtra("uniName",model.getStudentUniName());
                            intent.putExtra("name",model.getStudentName());
                            intent.putExtra("phoneNumber",model.getStudentPhoneNumber());
                            intent.putExtra("dept",model.getStudentDept());
                            intent.putExtra("token",model.getToken());
                            startActivity(intent);


                        }
                    });
                }

                @NonNull
                @Override
                public StudentRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.student_request_row,parent,false);
                    StudentRequestViewHolder studentRequestViewHolder = new StudentRequestViewHolder(view);
                    return studentRequestViewHolder;
                }
            };



            adapter.startListening();
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
        }
        return root;
    }
}