package com.brainfluence.psychiatry;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.brainfluence.psychiatry.ViewHolder.DoctorViewHolder;
import com.brainfluence.psychiatry.model.DoctorModel;
import com.brainfluence.psychiatry.model.DoctorRequestModel;
import com.brainfluence.psychiatry.model.NotificationModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

import es.dmoral.toasty.Toasty;

import static com.brainfluence.psychiatry.LoginActivity.SHARED_PREFS;
import static com.brainfluence.psychiatry.LoginActivity.UID;

public class DoctorsListShowActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference,databaseReferenceRead,databaseReferenceWrite;
    private SharedPreferences sharedPref;
    private FirebaseRecyclerAdapter<DoctorModel, DoctorViewHolder> adapter;
    private String uid;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_list_show);


        recyclerView = findViewById(R.id.doctorsListShow);
        sharedPref = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        uid = sharedPref.getString(UID,"123");
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("doctors");
        databaseReferenceRead = firebaseDatabase.getReference("");
        databaseReferenceWrite = firebaseDatabase.getReference("");
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
                holder.consultDoctor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(DoctorsListShowActivity.this);
                        builder.setTitle("Add Details");

                        final EditText input = new EditText(DoctorsListShowActivity.this);


                        input.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                        input.setSingleLine(false);
                        input.setLines(5);
                        input.setMaxLines(5);
                        input.setGravity(Gravity.LEFT | Gravity.TOP);
                        builder.setView(input);

                        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                progressDialog = new ProgressDialog(DoctorsListShowActivity.this);
                                progressDialog.setMessage("Please wait..."); // Setting Message
                                progressDialog.setTitle("Sending Request"); // Setting Title
                                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                                progressDialog.show(); // Display Progress Dialog
                                progressDialog.setCancelable(false);

                                databaseReferenceRead.child("students").child(uid).addValueEventListener(new ValueEventListener() {

                                    @RequiresApi(api = Build.VERSION_CODES.O)
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        String name = snapshot.child("name").getValue().toString();


                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");


                                        String dob = snapshot.child("dob").getValue().toString();

                                        Date date1= null;
                                        try {
                                            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        Date date = new Date();



                                        long difference_In_Time =  date.getTime() - date1.getTime();

                                        long difference_In_Years
                                                = (difference_In_Time
                                                / (1000l * 60 * 60 * 24 * 365));



                                        String age = String.valueOf(difference_In_Years+" years");

                                        String gender = snapshot.child("gender").getValue().toString();
                                        String patientDetails = input.getText().toString();

                                        String phoneNumber = snapshot.child("phoneNumber").getValue().toString();

                                        Date now = new Date();



                                        DoctorRequestModel doctorRequestModel = new DoctorRequestModel(name,age,gender,uid,patientDetails,simpleDateFormat.format(now),phoneNumber);
                                        NotificationModel notificationModel = new NotificationModel("Appointment Request","New appointment request from "+name,simpleDateFormat.format(now));

                                        databaseReferenceWrite.child("appointmentRequests").child(model.getUid()).child(System.currentTimeMillis()+"").setValue(doctorRequestModel);

                                        databaseReferenceWrite.child("notifications").child(model.getUid()).child(System.currentTimeMillis()+"").setValue(notificationModel);

                                        progressDialog.dismiss();
                                        Toasty.success(DoctorsListShowActivity.this,"Successfully requested for appointment",Toasty.LENGTH_SHORT).show();


                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                                Toasty.normal(DoctorsListShowActivity.this,input.getText().toString(),Toasty.LENGTH_SHORT).show();

                            }
                        });

                        builder.setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });
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