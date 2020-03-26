package com.example.cosc341project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class chat_createchannel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_create_channel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Check project name and if anything is written to chat channels

        // Reading files
        // Modify for the purpose
        try {
            InputStream inputStream = openFileInput("chat_channel.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                    int p = receiveString.indexOf(",");
                    String projName = receiveString.substring(0, p);
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
    @Override
    public void onBackPressed() {
        Intent backToMain = new Intent(this, chat_main.class);

        startActivity(backToMain);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, chat_main.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void createChannel(View view) {
        TextView cName = findViewById(R.id.editText);
        CheckBox box = findViewById(R.id.checkBox);
        CheckBox box2 = findViewById(R.id.checkBox2);
        CheckBox box3 = findViewById(R.id.checkBox3);
        CheckBox box4 = findViewById(R.id.checkBox4);
        String channelN = cName.getText().toString();
        StringBuilder bld = new StringBuilder();
        String[] teamMembers = {"Keira", "Brian","Evan","Lydia"};


        if (cName.getText().toString().matches("")) {
            // checks for if the first name field is blank or null and prompt for correction
            Toast noSelect = Toast.makeText(this, "Enter your channel name", Toast.LENGTH_SHORT);
            noSelect.show();
            return;

        } else if(!box.isChecked() && !box2.isChecked() && !box3.isChecked() && !box4.isChecked()) {
            Toast noSelect = Toast.makeText(this, "Selected team member to invite", Toast.LENGTH_SHORT);
            noSelect.show();
            return;
        } else {
                if(box.isChecked()&& !box2.isChecked() && !box4.isChecked()&& !box3.isChecked()) {
                    bld.append(teamMembers[0]);
                } else {
                    bld.append(teamMembers[0] + ",");
                }
                if(box2.isChecked() && !box3.isChecked() && !box4.isChecked()) {
                    bld.append(teamMembers[1]);
                } else {
                    bld.append(teamMembers[1] + ",");
                }
                if(box3.isChecked() && !box4.isChecked()) {
                    bld.append(teamMembers[2]);
                } else {
                    bld.append(teamMembers[2] + ",");
                }
                if(box4.isChecked()) {
                    bld.append(teamMembers[3]);
                }
        }

        String teamM = bld.toString();
        String filename = "chat_channel.txt";
        String fileContents = channelN + "," + teamM + "\n";
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

        Intent toDm = new Intent(this, chat_dM.class);

        startActivity(toDm);
        finish();

    }

}
