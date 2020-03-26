package com.example.cosc341project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import java.util.*;
import android.widget.Spinner;

public class chat_main extends AppCompatActivity {

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

}
