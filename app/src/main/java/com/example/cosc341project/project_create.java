package com.example.cosc341project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NavUtils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode;

public class project_create extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_create);
        setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        Spinner spin = findViewById(R.id.spinner);

            // Starting spinner for the drop down menu of team member
            String[] teamMembers = {"Keira", "Brian", "Even", "Lydia"};

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, teamMembers);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spin.setAdapter(adapter);

        }

        public void createProj(View view) {
            // Set up all the input fields by view
            EditText pName = findViewById(R.id.projName);
            EditText dueDate = findViewById(R.id.date);
            EditText addInfo = findViewById(R.id.infoField);

            // Change to string for inputs
            String dpName = pName.getText().toString();
            String dpDueDate = dueDate.getText().toString();
            String dpAddInfo = addInfo.getText().toString();

            // Set up a spinner for the team member selection field
            Spinner spin = findViewById(R.id.spinner);
            String teamMembers = spin.getSelectedItem().toString();

            Intent intent = new Intent(project_create.this, MainActivity.class);

            // Checking the input of each field that is required aren't empty
            if (pName.getText().toString().matches("")) {
                    // checks for if the first name field is blank or null and prompt for correction
                    Toast noSelect = Toast.makeText(this, "Enter your project name", Toast.LENGTH_SHORT);
                    noSelect.show();
                    return;

                } else if (dueDate.getText().toString().matches("")) {
                    // checks for if the last name field is blank or null and prompt for correction
                    Toast noSelect = Toast.makeText(this, "Enter a due date", Toast.LENGTH_SHORT);
                    noSelect.show();
                    return;

                }
                String filename = "projects.txt";
                String fileContents = dpName + "," + teamMembers + "," + dpDueDate + "," + dpAddInfo + "\n";
                FileOutputStream outputStream;

                try {
                    outputStream = openFileOutput(filename, Context.MODE_APPEND);
                    outputStream.write(fileContents.getBytes());
                    outputStream.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    //System.out.print("IO Exception");
                    e.printStackTrace();
                }
                // Passing values to be displayed in the main menu for users to keep track of how many projects
                // they are currently working on
                /*intent.putExtra("pName", dpName);
                intent.putExtra("dfullName", teamMembers.toString());
                intent.putExtra("dgender", dpDueDate);
                intent.putExtra("dDiv", dpAddInfo);*/

                startActivity(intent);
                //setResult(RESULT_OK, intent);

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