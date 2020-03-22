package com.example.cosc341project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode;

public class task_edit extends AppCompatActivity {

    TextView projName;
    TextView taskName;
    TextView teamMem;
    TextView date;
    TextView addinfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_edit);
        setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        projName = findViewById(R.id.projName1);
        taskName = findViewById(R.id.taskname);
        teamMem = findViewById(R.id.spin);
        date = findViewById(R.id.date);
        addinfo = findViewById(R.id.infoField);

        try {
            InputStream inputStream = openFileInput("tasks.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                    int p = receiveString.indexOf(",");
                    String pN = receiveString.substring(0, p);
                    projName.setText(pN);
                    int t = receiveString.indexOf(",", p+1);
                    String tN = receiveString.substring(p+1, t);
                    taskName.setText(tN);
                    int m = receiveString.indexOf(",", t+1);
                    String tm = receiveString.substring(t+1, m);
                    teamMem.setText(tm);
                    int d = receiveString.indexOf(",", m+1);
                    String da = receiveString.substring(m+1, d);
                    date.setText(da);
                    int a = receiveString.indexOf(",", d+1);
                    String ai = receiveString.substring(d+1, a);
                    addinfo.setText(ai);
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

    public void finTask(View view) {

        finish();
    }
}
