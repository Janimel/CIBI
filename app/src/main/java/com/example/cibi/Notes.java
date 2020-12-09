package com.example.cibi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Notes extends AppCompatActivity {
    ImageButton toLists, toSettings, toAddNotes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notespage);

        toLists = findViewById(R.id.btn_Tasks);
        toSettings = findViewById(R.id.btnSettings);
        toAddNotes = findViewById(R.id.btn_AddNotes);

        toLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Notes.this, Lists.class);
                startActivity(intent);
            }
        });

        toSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentS = new Intent(Notes.this, Settings.class);
                startActivity(intentS);
            }
        });

        toAddNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentB = new Intent(Notes.this, AddNotes.class);
                startActivity(intentB);
            }
        });
    }
}
