package com.brainfluence.psychiatry.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brainfluence.psychiatry.R;

public class AppointmentScheduleViewHolder extends RecyclerView.ViewHolder {

    public TextView appointmentWith,appointmentTime,appointmentDate;

    public AppointmentScheduleViewHolder(@NonNull View itemView) {
        super(itemView);

        appointmentWith = itemView.findViewById(R.id.appointmentWith);
        appointmentTime = itemView.findViewById(R.id.appointmentTime);
        appointmentDate = itemView.findViewById(R.id.appointmentDate);
    }
}
