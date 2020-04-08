package com.example.tema3;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    TextView id;
    TextView name;
    TextView username;
    TextView email;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);

        id = itemView.findViewById(R.id.id);
        name = itemView.findViewById(R.id.name);
        username = itemView.findViewById(R.id.username);
        email = itemView.findViewById(R.id.email);
    }
}
