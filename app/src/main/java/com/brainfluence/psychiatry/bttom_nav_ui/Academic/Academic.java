package com.brainfluence.psychiatry.bttom_nav_ui.Academic;

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

import com.brainfluence.psychiatry.R;
import com.brainfluence.psychiatry.ViewHolder.ProblemViewHolder;
import com.brainfluence.psychiatry.model.ProblemModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.brainfluence.psychiatry.LoginActivity.SHARED_PREFS;
import static com.brainfluence.psychiatry.LoginActivity.UID;

public class Academic extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private SharedPreferences sharedPref;
    private FirebaseRecyclerAdapter<ProblemModel, ProblemViewHolder> adapter;
    private String uid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_educational, container, false);
        recyclerView = root.findViewById(R.id.educationalProblemsList);
        sharedPref = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        uid = sharedPref.getString(UID,"123");
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("academicProblems");
        databaseReference.keepSynced(true);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        if(!uid.equals("123"))
        {
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


        return root;
    }
}