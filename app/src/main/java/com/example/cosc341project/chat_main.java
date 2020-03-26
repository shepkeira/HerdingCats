package com.example.cosc341project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import java.util.*;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class chat_main extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_main);

        dl = (DrawerLayout)findViewById(R.id.chat_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.DM1:
                        Toast.makeText(chat_main.this, "My Account",Toast.LENGTH_SHORT).show();break;
                    default:
                        return true;
                }


                return true;

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Start up intent and get value for the project list from MainActivity
        Intent values = getIntent();

        Spinner spin = findViewById(R.id.spinner2);

        ArrayList<String> projAL = values.getStringArrayListExtra("project");
        String[] proj = projAL.toArray(new String[projAL.size()]);


       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
              android.R.layout.simple_spinner_item, proj);


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

       spin.setAdapter(adapter);

    }

   public void createChat(View view) {
        //TODO
    }

    @Override
    public void onBackPressed() {
        Intent backToMain = new Intent(this, MainActivity.class);

        startActivity(backToMain);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openChat(View view) {
        Intent intent = new Intent(this, chat_main.class);

        startActivity(intent);
        finish();
    }

    public void openTask(View view) {
        Intent intent = new Intent(this, task_main.class);

        startActivity(intent);
        finish();
    }

    public void openCalendar(View view) {
        Intent intent = new Intent(this, calendar_main.class);

        startActivity(intent);
        finish();
    }
*/

}
