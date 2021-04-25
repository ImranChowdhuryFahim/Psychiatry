package com.brainfluence.psychiatry.ui.notification;

import android.content.Context;
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

import com.brainfluence.psychiatry.R;
import com.brainfluence.psychiatry.ViewHolder.DoctorRequestViewHolder;
import com.brainfluence.psychiatry.ViewHolder.NotificationViewHolder;
import com.brainfluence.psychiatry.model.DoctorRequestModel;
import com.brainfluence.psychiatry.model.NotificationModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.brainfluence.psychiatry.LoginActivity.SHARED_PREFS;
import static com.brainfluence.psychiatry.LoginActivity.UID;


public class Notification extends Fragment {


    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private SharedPreferences sharedPref;
    private FirebaseRecyclerAdapter<NotificationModel, NotificationViewHolder> adapter;
    private String uid;
    private Button shareWithTeacher;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerView = root.findViewById(R.id.notificationRecycler);

        sharedPref = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        uid = sharedPref.getString(UID,"123");
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("notifications");
        databaseReference.keepSynced(true);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        if(!uid.equals("123"))
        {
            FirebaseRecyclerOptions options=new FirebaseRecyclerOptions.Builder<NotificationModel>().setQuery(databaseReference.child(uid) ,NotificationModel.class).build();


            adapter = new FirebaseRecyclerAdapter<NotificationModel, NotificationViewHolder>(options) {
                @Override
                protected void onBindViewHolder(@NonNull NotificationViewHolder holder, int position, @NonNull NotificationModel model) {
                    holder.notificationTitle.setText(model.notificationTitle);
                    holder.notificationBody.setText(model.notificationBody);
                    holder.notificationDate.setText(model.notificationDate);

                }

                @NonNull
                @Override
                public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.notification_row,parent,false);
                    NotificationViewHolder notificationViewHolder = new NotificationViewHolder(view);
                    return notificationViewHolder;
                }
            };

            adapter.startListening();
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
        }
        return root;
    }
}