package com.brainfluence.psychiatry.ui.appointmentSchedules;

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

import com.brainfluence.psychiatry.PatientDetailsActivity;
import com.brainfluence.psychiatry.R;
import com.brainfluence.psychiatry.ViewHolder.AppointmentScheduleViewHolder;
import com.brainfluence.psychiatry.ViewHolder.DoctorRequestViewHolder;
import com.brainfluence.psychiatry.model.AppointmentScheduleModel;
import com.brainfluence.psychiatry.model.DoctorRequestModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.common.data.SingleRefDataBufferIterator;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.brainfluence.psychiatry.LoginActivity.SHARED_PREFS;
import static com.brainfluence.psychiatry.LoginActivity.UID;

public class AppointmentSchedules extends Fragment {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private SharedPreferences sharedPref;
    private FirebaseRecyclerAdapter<AppointmentScheduleModel, AppointmentScheduleViewHolder> adapter;
    private String uid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        View root = inflater.inflate(R.layout.fragment_appointment_schedules, container, false);
        recyclerView = root.findViewById(R.id.appointmentRequestRecycler);

        sharedPref = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        uid = sharedPref.getString(UID,"123");
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("appointmentSchedules");
        databaseReference.keepSynced(true);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        if(!uid.equals("123"))
        {
            FirebaseRecyclerOptions options=new FirebaseRecyclerOptions.Builder<AppointmentScheduleModel>().setQuery(databaseReference.child(uid) ,AppointmentScheduleModel.class).build();


            adapter = new FirebaseRecyclerAdapter<AppointmentScheduleModel, AppointmentScheduleViewHolder>(options) {
                @Override
                protected void onBindViewHolder(@NonNull AppointmentScheduleViewHolder holder, int position, @NonNull AppointmentScheduleModel model) {
                    holder.appointmentWith.setText("Appointment with "+model.getName());
                    holder.appointmentDate.setText(model.getDate());
                    holder.appointmentTime.setText(model.getTime());
                }

                @NonNull
                @Override
                public AppointmentScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.appointment_schedule_row,parent,false);
                    AppointmentScheduleViewHolder appointmentScheduleViewHolder = new AppointmentScheduleViewHolder(view);
                    return appointmentScheduleViewHolder;
                }
            };
            adapter.startListening();
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
        }
        return root;
    }
}