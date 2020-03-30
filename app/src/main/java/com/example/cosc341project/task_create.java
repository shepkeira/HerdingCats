package com.example.cosc341project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode;

public class task_create extends AppCompatActivity {

    String projName;
    TextView projectName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_create);
        setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        Intent intent = getIntent();
        projName = intent.getStringExtra("proj");

        projectName = findViewById(R.id.projName);
        projectName.setText(projName);

        Spinner spin = findViewById(R.id.spinner);

        // Starting spinner for the drop down menu of team member
        String[] teamMembers = {"Keira", "Brian", "Even", "Lydia"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, teamMembers);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(adapter);

    }

    public void createTask(View view) {

        EditText tName = findViewById(R.id.taskName);
        EditText dueDate = findViewById(R.id.date);
        EditText addInfo = findViewById(R.id.infoField);

        // Change to string for inputs
        String TaskName = tName.getText().toString();
        String DueDate = dueDate.getText().toString();
        String AddInfo = addInfo.getText().toString();

        // Set up a spinner for the team member selection field
        Spinner spin = findViewById(R.id.spinner);
        String teamMembers = spin.getSelectedItem().toString();

        String filename = "tasks.txt";
        String fileContents = projName + "," + TaskName + "," + DueDate + "," + AddInfo + "," + teamMembers + "\n";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_APPEND);
            outputStream.write(fileContents.getBytes());
            outputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        finish();
    }
    @Override
    public void onBackPressed() {
        Intent backToMain = new Intent(this, chat_dM.class);

        startActivity(backToMain);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, chat_dM.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
