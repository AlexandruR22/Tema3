package com.example.tema3;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ToDoViewHolder extends RecyclerView.ViewHolder {

    TextView userId;
    TextView id;
    TextView title;
    TextView completed;

    public ToDoViewHolder(@NonNull View itemView) {
        super(itemView);


        userId = itemView.findViewById(R.id.userId);
        id = itemView.findViewById(R.id.id);
        title = itemView.findViewById(R.id.title);
        completed = itemView.findViewById(R.id.completed);
    }
}
