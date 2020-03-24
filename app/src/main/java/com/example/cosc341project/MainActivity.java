package com.example.cosc341project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        ImageButton chat = findViewById(R.id.chatBtn);
        ImageButton task = findViewById(R.id.taskBtn);
        ImageButton calendar = findViewById(R.id.calendarBtn);

        ArrayList<String> projList = new ArrayList<>();

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
                    projList.add(projName);
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

        Spinner spin = findViewById(R.id.spinner2);

        // Starting spinner for the drop down menu of team member
        String[] proj = {"None"};

        proj = addTo(projList, proj);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, proj);


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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if (requestCode == 1) {
         //   if(resultCode == RESULT_OK) {
                Intent intent = new Intent(MainActivity.this, project_edit.class);
                startActivity(intent);
                //finish();
          //  }
        //}
    }

    public void createProj(View view) {
        Intent intent = new Intent(MainActivity.this, project_create.class);
        //startActivityForResult(intent, 1);
        startActivity(intent);
        //finish();

    }

    public void openProj(View view) {
        Spinner spin = findViewById(R.id.spinner2);
        String proj = spin.getSelectedItem().toString();

        if (proj == "None") {
            Toast none = Toast.makeText(this, "Please Select a Project", Toast.LENGTH_SHORT);
            none.show();
        } else {
            Intent intent = new Intent(MainActivity.this, project_edit.class);
            intent.putExtra("proj", proj);

            startActivity(intent);
        }
    }

}
