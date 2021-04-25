package com.brainfluence.psychiatry.ui.appintmentRequests;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.brainfluence.psychiatry.PatientDetailsActivity;
import com.brainfluence.psychiatry.R;
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

public class AppointmentRequests extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private SharedPreferences sharedPref;
    private FirebaseRecyclerAdapter<DoctorRequestModel, DoctorRequestViewHolder> adapter;
    private String uid;
    private Button shareWithTeacher;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_appointment_requests, container, false);
        recyclerView = root.findViewById(R.id.appointmentRequestRecycler);

        sharedPref = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        uid = sharedPref.getString(UID,"123");
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("appointmentRequests");
        databaseReference.keepSynced(true);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        if(!uid.equals("123"))
        {
            FirebaseRecyclerOptions options=new FirebaseRecyclerOptions.Builder<DoctorRequestModel>().setQuery(databaseReference.child(uid) ,DoctorRequestModel.class).build();


            adapter = new FirebaseRecyclerAdapter<DoctorRequestModel, DoctorRequestViewHolder>(options) {
                @Override
                protected void onBindViewHolder(@NonNull DoctorRequestViewHolder holder, int position, @NonNull DoctorRequestModel model) {

                    holder.patientName.setText(model.getPatientName());
                    holder.patientInfo.setText(model.getPatientAge()+","+model.getPatientGender());
                    holder.requestDate.setText(model.getRequestDate());
                    holder.patientDetails.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(getActivity(), PatientDetailsActivity.class);
                            intent.putExtra("uid",model.getPatientUid());
                            intent.putExtra("note",model.getPatientDetails());
                            intent.putExtra("age",model.getPatientAge());
                            intent.putExtra("gender",model.getPatientGender());
                            intent.putExtra("name",model.getPatientName());
                            intent.putExtra("phoneNumber",model.getPhoneNumber());
                            startActivity(intent);
                        }
                    });


                }

                @NonNull
                @Override
                public DoctorRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.appointment_request_row,parent,false);
                    DoctorRequestViewHolder doctorRequestViewHolder = new DoctorRequestViewHolder(view);
                    return doctorRequestViewHolder;
                }
            };

            adapter.startListening();
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
        }
        return root;
    }
}