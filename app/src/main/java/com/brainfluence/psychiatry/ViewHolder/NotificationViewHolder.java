package com.brainfluence.psychiatry.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brainfluence.psychiatry.R;

public class NotificationViewHolder extends RecyclerView.ViewHolder {

    public TextView notificationTitle,notificationBody,notificationDate;

    public NotificationViewHolder(@NonNull View itemView) {
        super(itemView);

        notificationTitle = itemView.findViewById(R.id.notificationTitle);
        notificationBody = itemView.findViewById(R.id.notificationBody);
        notificationDate = itemView.findViewById(R.id.notificationDate);
    }
}
