package com.brainfluence.psychiatry.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brainfluence.psychiatry.R;

public class DoctorRequestViewHolder extends RecyclerView.ViewHolder {
    public TextView patientName,patientInfo,requestDate;
    public Button patientDetails;
    public DoctorRequestViewHolder(@NonNull View itemView) {
        super(itemView);

        patientName = itemView.findViewById(R.id.patientName);
        patientDetails = itemView.findViewById(R.id.patientDetails);
        patientInfo = itemView.findViewById(R.id.patientInfo);
        requestDate = itemView.findViewById(R.id.patientRequestDate);
    }
}
