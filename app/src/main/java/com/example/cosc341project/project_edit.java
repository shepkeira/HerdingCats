package com.example.cosc341project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode;

public class project_edit extends MainActivity {

    String pName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_edit);
        setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
/*
        Intent intent = getIntent();
        pName = intent.getStringExtra("pName");
        String team = intent.getStringExtra("dfullName");
        String date = intent.getStringExtra("dgender");
        String add = intent.getStringExtra("dDiv");

        TextView projname = findViewById(R.id.projName);
        TextView teamPeps = findViewById(R.id.spin);
        TextView duedate = findViewById(R.id.date);
        TextView additional = findViewById(R.id.infoField);

        projname.setText(pName);
        teamPeps.setText(team);
        duedate.setText(date);
        additional.setText(add);
*/


    }

    public void finProj(View view) {
        finish();

    }
}
