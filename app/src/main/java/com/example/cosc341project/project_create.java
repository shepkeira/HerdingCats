package com.example.cosc341project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;

import static androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode;

public class project_create extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_screen);
        setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

    }
}
