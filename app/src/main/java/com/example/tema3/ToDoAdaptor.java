package com.example.tema3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ToDoAdaptor extends RecyclerView.Adapter<ToDoViewHolder> {

    ArrayList<ToDo> toDos;

    public ToDoAdaptor() {
        toDos = new ArrayList<>();
    }

    public void setData(ArrayList<ToDo> toDos){
        this.toDos = toDos;
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View toDoView = layoutInflater.inflate(R.layout.todo_layout,parent, false);
        return new ToDoViewHolder(toDoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
        ToDo toDo = toDos.get(position);
        holder.userId.setText(String.valueOf(toDo.userId));
        holder.id.setText(String.valueOf(toDo.id));
        holder.title.setText(toDo.title);
        holder.completed.setText(toDo.completed);
    }

    @Override
    public int getItemCount() {
        return toDos.size();
    }
}
