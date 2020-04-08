package com.example.tema3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdaptor extends RecyclerView.Adapter<UserViewHolder> {

    ArrayList<User> users;

    public UserAdaptor() {
        users = new ArrayList<>();
    }

    public void setData(ArrayList<User> users){
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View userView = layoutInflater.inflate(R.layout.recycler_layout,parent, false);
        return new UserViewHolder(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.id.setText(String.valueOf(user.id));
        holder.name.setText(user.name);
        holder.username.setText(user.username);
        holder.email.setText(user.email);


    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
