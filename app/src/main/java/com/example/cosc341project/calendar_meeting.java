package com.example.cosc341project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class calendar_meeting extends AppCompatActivity {

    Long date, today = System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_meeting);

        if(date==null) {
            // Code block for date conversion from system time to human readable time
            SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
            Date sysDate = new Date();
            String sDate = formatter.format(sysDate);
            //https://stackabuse.com/how-to-get-current-date-and-time-in-java/

            EditText dateField = findViewById(R.id.date);
            dateField.setText(sDate);
        } else {
            // Create intent to get date value for display
            Intent cMain = getIntent();
            date = cMain.getLongExtra("dates",today);
            SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
            String sDate = formatter.format(date);

        }
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

    public void createMeeting(View view) {

        openWebPage("https://www.when2meet.com/");

        finish();

    }

    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}

