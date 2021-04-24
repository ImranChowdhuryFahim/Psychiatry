package com.brainfluence.psychiatry.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brainfluence.psychiatry.R;

public class ProblemViewHolder extends RecyclerView.ViewHolder {
    public TextView problemName;
    public ProblemViewHolder(@NonNull View itemView) {
        super(itemView);
        problemName = itemView.findViewById(R.id.problemName);
    }
}
