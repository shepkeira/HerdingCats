package com.example.cosc341project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;

import static androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode;

public class task_edit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_edit);
        setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

    }
}
