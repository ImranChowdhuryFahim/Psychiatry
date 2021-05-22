package com.brainfluence.psychiatry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.brainfluence.psychiatry.ViewHolder.DoctorViewHolder;
import com.brainfluence.psychiatry.ViewHolder.TeacherViewHolder;
import com.brainfluence.psychiatry.model.DoctorModel;
import com.brainfluence.psychiatry.model.NotificationModel;
import com.brainfluence.psychiatry.model.StudentRequestModel;
import com.brainfluence.psychiatry.model.TeacherModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

import static com.brainfluence.psychiatry.LoginActivity.SHARED_PREFS;
import static com.brainfluence.psychiatry.LoginActivity.UID;
import static com.brainfluence.psychiatry.LoginActivity.UNIVERSITY_NAME;

public class TeachersListShowActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference,databaseReferenceRead,databaseReferenceWrite;
    private SharedPreferences sharedPref;
    private FirebaseRecyclerAdapter<TeacherModel, TeacherViewHolder> adapter;
    private String uid;
    private String uniName;
    private ProgressDialog progressDialog;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_teachers_list_show);



        recyclerView = findViewById(R.id.teachersListShow);
        sharedPref = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        uid = sharedPref.getString(UID,"123");
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("teachers");
        databaseReferenceRead = firebaseDatabase.getReference("");
        databaseReferenceWrite = firebaseDatabase.getReference("");
        databaseReference.keepSynced(true);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        uniName = sharedPref.getString(UNIVERSITY_NAME,"CUET");

        requestQueue = Volley.newRequestQueue(TeachersListShowActivity.this);

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
                holder.shareWithTeacher.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(TeachersListShowActivity.this);
                        builder.setTitle("Add Details");

                        final EditText input = new EditText(TeachersListShowActivity.this);


                        input.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                        input.setSingleLine(false);
                        input.setLines(5);
                        input.setMaxLines(5);
                        input.setGravity(Gravity.LEFT | Gravity.TOP);
                        builder.setView(input);


                        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                progressDialog = new ProgressDialog(TeachersListShowActivity.this);
                                progressDialog.setMessage("Please wait..."); // Setting Message
                                progressDialog.setTitle("Sending Request"); // Setting Title
                                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                                progressDialog.show(); // Display Progress Dialog
                                progressDialog.setCancelable(false);


                                databaseReferenceRead.child("students").child(uid).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        String name = snapshot.child("name").getValue().toString();
                                        String id = snapshot.child("studentId").getValue().toString();
                                        String dept = snapshot.child("department").getValue().toString();
                                        String uniName = snapshot.child("universityName").getValue().toString();
                                        String phoneNumber = snapshot.child("phoneNumber").getValue().toString();
                                        String token = snapshot.child("fToken").getValue().toString();


                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

                                        Date now = new Date();
                                        StudentRequestModel studentRequestModel = new StudentRequestModel(name,id,dept,uniName,input.getText().toString().trim(),
                                                simpleDateFormat.format(now),phoneNumber,uid,token);

                                        NotificationModel notificationModel = new NotificationModel("Student Request","New student request from "+name,simpleDateFormat.format(now));

                                        databaseReferenceWrite.child("studentRequests").child(model.getUid()).child(System.currentTimeMillis()+"").setValue(studentRequestModel);

                                        databaseReferenceWrite.child("notifications").child(model.getUid()).child(System.currentTimeMillis()+"").setValue(notificationModel);

                                        ArrayList<String> t = new ArrayList<String>();
                                        t.add(model.getfToken());
                                        Notifier(t,"Student Request","New student request from "+name);
                                        progressDialog.dismiss();
                                        Toasty.success(TeachersListShowActivity.this,"Successfully requested",Toasty.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });



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

    private void Notifier(ArrayList<String> regToken, String title, String body){

        JSONObject data = new JSONObject();
        try {
            JSONObject notify = new JSONObject();
            JSONArray tokens = new JSONArray(regToken);
            notify.put("title", title);
            notify.put("body", body);
//            notify.put("android_channel_id","cuet_connect_primary_notification_channel");
            data.put("notification", notify);
            data.put("registration_ids", tokens);
            Log.d("simji", "Notifier: " + data);

            String Url = "https://fcm.googleapis.com/fcm/send";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Url,
                    data,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> header = new HashMap<>();
                    header.put("content-type","application/json");
                    header.put("authorization","key=AAAAkfbVJ1c:APA91bGxoMpoGXy3p_MDw5hUrbKOBXmdNJeGcL2Wtf0NabqQAJF_dJy8jR_bDN17fo_jDzW26odTpMY-vPzSROf5tkN-VcznNtdPmzfK7-ha-moCL4mfuQ62zYquHSJdeUq--jPVEzYg");
                    return  header;
                }
            };
            requestQueue.add(request);
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}