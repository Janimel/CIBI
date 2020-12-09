package com.example.cibi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddNotes extends AppCompatActivity {
    ImageButton add, toNotes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnotes);

        toNotes = findViewById(R.id.backArrow);

        toNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddNotes.this, Notes.class);
                startActivity(intent);
            }
        });
    }
}
