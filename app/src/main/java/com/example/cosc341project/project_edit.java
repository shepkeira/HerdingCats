package com.example.cosc341project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

        TextView projname = findViewById(R.id.projName);
        TextView teamPeps = findViewById(R.id.spin);
        TextView duedate = findViewById(R.id.date);
        TextView additional = findViewById(R.id.infoField);
/*

*/

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


    }

    public void finProj(View view) {

        finish();
    }
}
