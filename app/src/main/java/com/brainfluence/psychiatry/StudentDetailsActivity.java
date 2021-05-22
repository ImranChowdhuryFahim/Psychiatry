package com.brainfluence.psychiatry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.brainfluence.psychiatry.ViewHolder.ProblemViewHolder;
import com.brainfluence.psychiatry.model.AppointmentScheduleModel;
import com.brainfluence.psychiatry.model.NotificationModel;
import com.brainfluence.psychiatry.model.ProblemModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class StudentDetailsActivity extends AppCompatActivity {
    private TextView studentName,studentId,studentDept,studentPhoneNumber,studentNote,studentUniName;
    private String uid,note,id,uniName,name,phoneNumber,dept;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference,databaseReference1;
    private FirebaseRecyclerAdapter<ProblemModel, ProblemViewHolder> adapter;
    private Button takeAction;
    private String token;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_student_details);

        studentName = findViewById(R.id.studentName);
        studentId = findViewById(R.id.studentId);
        studentDept = findViewById(R.id.studentDept);
        studentNote = findViewById(R.id.studentNote);
        studentPhoneNumber = findViewById(R.id.studentPhoneNumber);
        studentUniName = findViewById(R.id.studentUniName);
        recyclerView = findViewById(R.id.educationalProblemsList);
        takeAction = findViewById(R.id.takeAction);


        uid = getIntent().getStringExtra("uid");
        note = getIntent().getStringExtra("note");
        id = getIntent().getStringExtra("id");
        uniName = getIntent().getStringExtra("uniName");
        name = getIntent().getStringExtra("name");
        phoneNumber = getIntent().getStringExtra("phoneNumber");
        dept = getIntent().getStringExtra("dept");
        token = getIntent().getStringExtra("token");


        requestQueue = Volley.newRequestQueue(StudentDetailsActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("academicProblems");
        databaseReference1 = firebaseDatabase.getReference("");
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


        takeAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

                Date now = new Date();
                NotificationModel notificationModel = new NotificationModel("Action Taken","Action has been taken with respect to your complaint",simpleDateFormat.format(now));

                databaseReference1.child("notifications").child(uid).child(System.currentTimeMillis()+"").setValue(notificationModel);

                ArrayList<String> t = new ArrayList<String>();
                t.add(token);
                Notifier(t,"Action Taken","Action has been taken with respect to your complaint");

                Toasty.success(StudentDetailsActivity.this,"Successfully notified the student",Toasty.LENGTH_SHORT).show();
            }
        });


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