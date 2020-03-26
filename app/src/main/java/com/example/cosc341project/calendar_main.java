package com.example.cosc341project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class calendar_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_main);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CalendarView calendar = findViewById(R.id.calendarView);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int date) {
                SimpleDateFormat formatter=new SimpleDateFormat("yyyy MM dd");
                // Have to make gregorian calendar conversion to date and stirng to pass to edittext
            }
        });

    }

    public void cPEvent(View view) {
        // Create basic calendar view to get date and move to creation of event on the day
        CalendarView simpleCalendarView = (CalendarView) findViewById(R.id.calendarView); // get the reference of CalendarView
        long selectedDate = simpleCalendarView.getDate(); // get selected date in milliseconds

        // Prepare intent and pass date as extra to personal meeting intent
        Intent intent = new Intent(this, calendar_event.class);
        intent.putExtra("date", selectedDate);

        startActivity(intent);
        //finish();
    }

    public void cPGmeeting(View view) {
        CalendarView simpleCalendarView = (CalendarView) findViewById(R.id.calendarView); // get the reference of CalendarView
        long selectedDate = simpleCalendarView.getDate(); // get selected date in milliseconds

        Intent intent = new Intent(this, calendar_meeting.class);
        intent.putExtra("date", selectedDate);

        startActivity(intent);
        //finish();
        //openWebPage("https://www.when2meet.com/");
    }

    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void openChat(View view) {
        Intent intent = new Intent(this, chat_main.class);

        startActivity(intent);
        finish();
    }

    public void openTask(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
        finish();
    }

    public void openCalendar(View view) {

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

}
