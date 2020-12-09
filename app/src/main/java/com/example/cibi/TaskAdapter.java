package com.example.cibi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.taskViewHolder> {
    private List<TaskItems> taskexamplelist;
    private Lists activity;
    private DatabaseHandler taskDB;

    public TaskAdapter(DatabaseHandler taskDB, Lists activity){
        taskDB = taskDB;
        this.activity = activity;
    }

    public static class taskViewHolder extends RecyclerView.ViewHolder {
        public TextView title, desc;

        public taskViewHolder( View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tasktitle);
            desc = itemView.findViewById(R.id.taskdesc);
        }
    }

    public void taskUpdate(List<TaskItems> updateList) {
        this.taskexamplelist = updateList;
        notifyDataSetChanged();
    }

    public taskViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.taskcard, parent, false);
        return new taskViewHolder(v);
    }

    public void onBindViewHolder( taskViewHolder holder, int position) {
        taskDB.openDatabase();
        TaskItems currentItem = taskexamplelist.get(position);
        holder.title.setText(currentItem.getTasktitle());
        holder.desc.setText(currentItem.getTaskdesc());
    }

    public void editItem(int position){
        TaskItems item = taskexamplelist.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("title", item.getTasktitle());
        bundle.putString("desc", item.getTaskdesc());
        AddTasks fragment = new AddTasks();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddTasks.TAG);
    }

    public int getItemCount() {
        return taskexamplelist.size();
    }
}
