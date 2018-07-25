package com.genericplanet.weather.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.genericplanet.weather.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Weather_update extends AppCompatActivity {

    String term;
    TextView cityname;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_update);

        Intent intent=getIntent();
        term=intent.getStringExtra("city_name");
//        cityname=findViewById();
//        cityname.setText(term);
        getSupportActionBar().setTitle("Weather Update Of "+term);




    }
}
