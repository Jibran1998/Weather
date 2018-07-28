package com.genericplanet.weather.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.genericplanet.weather.R;
import com.genericplanet.weather.classes.Data_loader;
import com.genericplanet.weather.classes.Weather_Data;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Weather_update extends AppCompatActivity {

    String term;
    TextView day1_temp,date,day1_state,day2_state,day2_temp;
    Weather_Data data[]= Data_loader.getdata();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_update);
        day1_state=findViewById(R.id.weather_update_today_state);
        day2_state=findViewById(R.id.weather_update_tomorrow_state);
        day1_temp=findViewById(R.id.weather_update_today_temp);
        date=findViewById(R.id.weather_update_today_date);
        day2_temp=findViewById(R.id.weather_update_tomorrow_temp);
        Intent intent=getIntent();
        term=intent.getStringExtra("city_name");
        getSupportActionBar().setTitle(data[0].getName());
        setview();
    }

    void setview(){
        day1_temp.setText(data[0].getTemperature()+"°");
        date.setText(data[0].getDate());
        day1_state.setText(data[0].getWeather_state());
        day2_state.setText(data[1].getWeather_state());
        day2_temp.setText(data[1].getTemperature()+"°");
    }
}
