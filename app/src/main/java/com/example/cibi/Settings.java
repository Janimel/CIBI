package com.example.cibi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {
    ImageButton toTasks, toNotes;
    Button red, yellow, green, blue, purple, toLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        toTasks = findViewById(R.id.btn_Tasks);
        toLogin = findViewById(R.id.btn_Login);
        toNotes = findViewById(R.id.btn_Notes);
        red = findViewById(R.id.btn_red);
        yellow = findViewById(R.id.btn_yellow);
        green = findViewById(R.id.btn_green);
        blue = findViewById(R.id.btn_blue);
        purple = findViewById(R.id.btn_purple);

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTheme().applyStyle(R.style.AppTheme, true);
            }
        });

        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTheme().applyStyle(R.style.YellowTheme, true);
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTheme().applyStyle(R.style.GreenTheme, true);
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTheme().applyStyle(R.style.BlueTheme, true);
            }
        });

        purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTheme().applyStyle(R.style.PurpleTheme, true);
            }
        });

        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentL = new Intent(Settings.this, Login.class);
                startActivity(intentL);
            }
        });


        toTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, Lists.class);
                startActivity(intent);
            }
        });

        toNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentN = new Intent(Settings.this, Notes.class);
                startActivity(intentN);
            }
        });


    }
}
