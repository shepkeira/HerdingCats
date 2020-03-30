package com.example.cosc341project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.acl.Group;
import java.util.*;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import static android.icu.lang.UCharacter.NumericType.NONE;

public class chat_main extends AppCompatActivity {
    private static final int MENU_ITEM_ITEM1 = 1;
    //yes
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_main);

        //TODO read from file: chat_channel.txt

        NavigationView navigationView = (NavigationView) findViewById(R.id.nv);
        Menu m = navigationView.getMenu();
        SubMenu sm = m.addSubMenu("Sub");
        SubMenu Channels = m.getItem(0).getSubMenu();
        SubMenu DM = m.getItem(1).getSubMenu();
        //Channels.add("Proj");
        //DM.add("Person");

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
                    String chatName = receiveString.substring(0, p);
                    Channels.add(chatName);
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
        //TODO finish

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

    public void createChat(View view) {
        Intent go = new Intent(chat_main.this, chat_createchannel.class);

        //startActivityForResult(go, RESULT_OK);
        startActivity(go);
    }


    public void openChat(View view) {

    }

    public void openTask(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
        finish();
    }

    public void openCalendar(View view) {
        Intent intent = new Intent(this, calendar_main.class);

        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent backToMain = new Intent(this, chat_dM.class);

        startActivity(backToMain);
        finish();
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


*/

}
