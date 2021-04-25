package com.brainfluence.psychiatry.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brainfluence.psychiatry.R;

public class TeacherViewHolder extends RecyclerView.ViewHolder {
    public TextView teacherName,teacherDegree,dsw;
    public Button shareWithTeacher;
    public TeacherViewHolder(@NonNull View itemView) {
        super(itemView);

        teacherName = itemView.findViewById(R.id.teacherName);
        teacherDegree = itemView.findViewById(R.id.teacherDegree);
        dsw = itemView.findViewById(R.id.teacherDsw);
        shareWithTeacher = itemView.findViewById(R.id.shareWithTeacher);
    }
}
