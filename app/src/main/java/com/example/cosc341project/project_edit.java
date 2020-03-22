package com.example.cosc341project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode;

public class project_edit extends MainActivity {

    String pName;

    TextView projname;
    TextView teamPeps;
    TextView duedate;
    TextView additional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_edit);
        setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        Intent intent = getIntent();
        pName = intent.getStringExtra("proj");

        projname = findViewById(R.id.projName);
        teamPeps = findViewById(R.id.spin);
        duedate = findViewById(R.id.date);
        additional = findViewById(R.id.infoField);

        try {
            InputStream inputStream = openFileInput("projects.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                    int p = receiveString.indexOf(",");
                    String projName = receiveString.substring(0, p);
                    if (projName.equals(pName)) {
                        projname.setText(pName);
                        int t = receiveString.indexOf(",", p+1);
                        String team = receiveString.substring(p+1, t);
                        teamPeps.setText(team);
                        int d = receiveString.indexOf(",", t+1);
                        String date = receiveString.substring(t+1, d);
                        duedate.setText(date);
                        String add = receiveString.substring(d+1);
                        additional.setText(add);
                    }
                }

                inputStream.close();
                String ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }


        ArrayList<String> taskList = new ArrayList<>();

        try {
            InputStream inputStream = openFileInput("tasks.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                    int pNum = receiveString.indexOf(",");
                    String pN = receiveString.substring(0, pNum);
                    if (pName.equals(pN)) {
                        int p = receiveString.indexOf(",", pNum+1);
                        String task = receiveString.substring(pNum + 1, p);
                        taskList.add(task);
                    }
                }

                inputStream.close();
                String ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        Spinner spin = findViewById(R.id.spinnerTask);

        // Starting spinner for the drop down menu of team member
        String[] proj = {"None"};

        String[] proj2 = addTo(taskList, proj);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, proj2);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(adapter);
    }

    public void finProj(View view) {

        finish();
    }

    public void add_task(View view) {
        Intent intent = new Intent(project_edit.this, task_create.class);
        intent.putExtra("proj", pName);
        startActivity(intent);
    }

    public void opentask(View view) {

        Spinner spin = findViewById(R.id.spinnerTask);
        String task = spin.getSelectedItem().toString();

        if (task == "None") {
            Toast none = Toast.makeText(this, "Please Select a Task", Toast.LENGTH_SHORT);
            none.show();
        } else {
            Intent intent = new Intent(project_edit.this, task_edit.class);
            intent.putExtra("task", task);
            startActivity(intent);
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        ArrayList<String> taskList = new ArrayList<>();

        try {
            InputStream inputStream = openFileInput("tasks.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                    int pNum = receiveString.indexOf(",");
                    String pN = receiveString.substring(0, pNum);
                    if (pName.equals(pN)) {
                        int p = receiveString.indexOf(",", pNum+1);
                        String task = receiveString.substring(pNum + 1, p);
                        taskList.add(task);
                    }
                }

                inputStream.close();
                String ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        Spinner spin = findViewById(R.id.spinnerTask);

        // Starting spinner for the drop down menu of team member
        String[] proj = {"None"};

        String[] proj2 = addTo(taskList, proj);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, proj2);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(adapter);
    }

    private String[] addTo(ArrayList<String> proj, String[] teamMembers) {
        ArrayList temp = new ArrayList<String>();
        for (String s: teamMembers) {
            temp.add(s);
        }

        for (String e : proj) {
            temp.add(e);
        }

        //temp.add(proj);

        String stringArray[] = new String[temp.size()];
        for (int j = 0; j<temp.size(); j++) {
            stringArray[j] = (String) temp.get(j);
        }

        return stringArray;
    }
}
