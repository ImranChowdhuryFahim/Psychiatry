package com.brainfluence.psychiatry.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brainfluence.psychiatry.R;

public class StudentRequestViewHolder  extends RecyclerView.ViewHolder {
    public TextView studentName,studentInfo,requestDate;
    public Button studentDetails;
    public StudentRequestViewHolder(@NonNull View itemView) {
        super(itemView);

        studentName = itemView.findViewById(R.id.studentName);
        studentInfo = itemView.findViewById(R.id.studentInfo);
        studentDetails = itemView.findViewById(R.id.studentDetails);
        requestDate = itemView.findViewById(R.id.studentRequestDate);
    }
}
