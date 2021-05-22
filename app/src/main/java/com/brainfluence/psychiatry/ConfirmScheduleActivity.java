package com.brainfluence.psychiatry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.brainfluence.psychiatry.model.AppointmentScheduleModel;
import com.brainfluence.psychiatry.model.NotificationModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

import static com.brainfluence.psychiatry.LoginActivity.SHARED_PREFS;
import static com.brainfluence.psychiatry.LoginActivity.UID;
import static com.brainfluence.psychiatry.LoginActivity.USER_NAME;

public class ConfirmScheduleActivity extends AppCompatActivity {
    private SharedPreferences sharedPref;
    private TextInputLayout date,time;
    private TextInputEditText dateInput,timeInput;
    private Button confirm;
    private Calendar calendar;
    private int year,month,day;
    private DatePickerDialog.OnDateSetListener myDateListener;
    private TimePickerDialog.OnTimeSetListener myTimeSetListener;
    private String name;
    private String uid,studentUid,StudentName;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String token;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_confirm_schedule);


        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        dateInput = findViewById(R.id.dateInput);
        timeInput = findViewById(R.id.timeInput);

        confirm = findViewById(R.id.confirm);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        sharedPref = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        name = sharedPref.getString(USER_NAME,"user");
        uid = sharedPref.getString(UID,"123");
        studentUid = getIntent().getStringExtra("studentUid");
        StudentName = getIntent().getStringExtra("studentName");
        token = getIntent().getStringExtra("token");

        requestQueue = Volley.newRequestQueue(ConfirmScheduleActivity.this);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("");

        timeInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(ConfirmScheduleActivity.this,
                        myTimeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        });

        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ConfirmScheduleActivity.this,
                        myDateListener, year, month, day).show();
            }
        });
        myDateListener = new
                DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker arg0,
                                          int arg1, int arg2, int arg3) {
                        // TODO Auto-generated method stub
                        // arg1 = year
                        // arg2 = month
                        // arg3 = day
                        showDate(arg1, arg2+1, arg3);
                    }
                };
        myTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                showTime(hourOfDay,minute);
            }
        };

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateDate() | !validateTime())
                {
                    return;
                }

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

                Date now = new Date();
                NotificationModel notificationModel = new NotificationModel("Appointment Scheduled","Appointment Scheduled with "+name,simpleDateFormat.format(now));
                AppointmentScheduleModel appointmentScheduleModelStudent = new AppointmentScheduleModel(name,timeInput.getText().toString(),dateInput.getText().toString());
                AppointmentScheduleModel appointmentScheduleModelDoctor = new AppointmentScheduleModel(StudentName,timeInput.getText().toString(),dateInput.getText().toString());

                databaseReference.child("notifications").child(studentUid).child(System.currentTimeMillis()+"").setValue(notificationModel);
                databaseReference.child("appointmentSchedules").child(studentUid).child(System.currentTimeMillis()+"").setValue(appointmentScheduleModelStudent);
                databaseReference.child("appointmentSchedules").child(uid).child(System.currentTimeMillis()+"").setValue(appointmentScheduleModelDoctor);

                ArrayList<String> t = new ArrayList<String>();
                t.add(token);
                Notifier(t,"Appointment Scheduled","Appointment Scheduled with "+name);
                Toasty.success(ConfirmScheduleActivity.this,"Successfully confirmed the schedule",Toasty.LENGTH_SHORT).show();
            }
        });



    }

    private void showTime(int hourOfDay, int minute) {
        timeInput.setText(new StringBuilder().append(hourOfDay).append(":")
                .append(minute));
    }

    private boolean validateTime() {

        String val = timeInput.getText().toString();

        if(val.isEmpty())
        {
            time.setError("Field can't be empty");
            return  false;
        }
        else{
            return true;
        }
    }

    private boolean validateDate() {
        String val = dateInput.getText().toString();

        if(val.isEmpty())
        {
            date.setError("Field can't be empty");
            return  false;
        }
        else{
            return true;
        }
    }

    private void showDate(int year, int month, int day) {
        dateInput.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));

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