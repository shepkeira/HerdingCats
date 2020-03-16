package com.example.cosc341project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;

import static androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode;

public class project_edit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_edit);
        setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

    }
}
