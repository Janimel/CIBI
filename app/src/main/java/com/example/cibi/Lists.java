package com.example.cibi;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lists extends AppCompatActivity implements DialogCloseListener {
    private RecyclerView taskView;
    private TaskAdapter taskAdapt;
    private List<TaskItems> taskList;
    private DatabaseHandler taskDB;
    Button toCalendar;
    ImageButton toNotes, toSettings, toAddTasks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listspage);

        taskDB = new DatabaseHandler(this);
        taskDB.openDatabase();

        toCalendar = findViewById(R.id.btn_Calendar);
        toNotes = findViewById(R.id.btn_Notes);
        toSettings = findViewById(R.id.btnSettings);
        taskView = findViewById(R.id.taskrecycler);

        toAddTasks = findViewById(R.id.btn_AddTask);

        taskAdapt = new TaskAdapter(taskDB, this);
        taskView.setLayoutManager(new LinearLayoutManager(this));
        taskView.setAdapter(taskAdapt);

        taskList = taskDB.getAllTasks();
        Collections.reverse(taskList);
        taskAdapt.taskUpdate(taskList);

        toCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Lists.this, Calendar.class);
                startActivity(intent);
            }
        });

        toNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentN = new Intent(Lists.this, Notes.class);
                startActivity(intentN);
            }
        });

        toSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentS = new Intent(Lists.this, Settings.class);
                startActivity(intentS);
            }
        });

        toAddTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               AddTasks.newInstance().show(getSupportFragmentManager(), AddTasks.TAG);
            }
        });
    }

    @Override
    public void handleDialogClose(DialogInterface dialog){
        taskList = taskDB.getAllTasks();
        Collections.reverse(taskList);
        taskAdapt.taskUpdate(taskList);
        taskAdapt.notifyDataSetChanged();
    }
}