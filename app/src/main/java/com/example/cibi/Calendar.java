package com.example.cibi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Calendar extends AppCompatActivity {
    Button toLists;
    ImageButton toNotes, toTasks, toSettings, toAddTasks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendarpage);

        toLists = findViewById(R.id.btn_List);
        toNotes = findViewById(R.id.btn_Notes);
        toTasks = findViewById(R.id.btn_Tasks);
        toSettings = findViewById(R.id.btnSettings);
        toAddTasks = findViewById(R.id.btn_AddTask);

        toLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Calendar.this, Lists.class);
                startActivity(intent);
            }
        });

        toNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentN = new Intent(Calendar.this, Notes.class);
                startActivity(intentN);
            }
        });

        toTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentT = new Intent(Calendar.this, Lists.class);
                startActivity(intentT);
            }
        });

        toSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentS = new Intent(Calendar.this, Settings.class);
                startActivity(intentS);
            }
        });

        toAddTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Calendar.this, AddTasks.class);
                startActivity(intent);
            }
        });
    }
}
