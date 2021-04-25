package com.brainfluence.psychiatry.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brainfluence.psychiatry.R;

public class DoctorViewHolder extends RecyclerView.ViewHolder {
    public TextView doctorName,doctorDegree,doctorExp;
    public Button consultDoctor;
    public DoctorViewHolder(@NonNull View itemView) {
        super(itemView);
        doctorName = itemView.findViewById(R.id.doctorName);
        doctorDegree = itemView.findViewById(R.id.doctorDegree);
        doctorExp = itemView.findViewById(R.id.doctorExp);
        consultDoctor = itemView.findViewById(R.id.consultDoctor);
    }
}
