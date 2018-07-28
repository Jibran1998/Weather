package com.genericplanet.weather.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.genericplanet.weather.R;
import com.genericplanet.weather.classes.DataLoader;
import com.genericplanet.weather.classes.WeatherData;

import java.util.ArrayList;

public class WeatherUpdate extends AppCompatActivity implements DataLoader.Listener {

    String term;
    TextView day1_temp,date,day1_state,day2_state,day2_temp;
    LinearLayout todayData;
    ProgressBar progress;
    DataLoader loader;
    ArrayList<WeatherData> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_update);
        day1_state=findViewById(R.id.weather_update_today_state);
        day2_state=findViewById(R.id.weather_update_tomorrow_state);
        day1_temp=findViewById(R.id.weather_update_today_temp);
        date=findViewById(R.id.weather_update_today_date);
        day2_temp=findViewById(R.id.weather_update_tomorrow_temp);
        todayData=findViewById(R.id.weather_update_today_data);
        progress=findViewById(R.id.weather_progress);
        loader=new DataLoader();
        loader.setListener(this);
        Intent intent=getIntent();
        term=intent.getStringExtra("city_name");
        loader.isThere(this,term);
//        getSupportActionBar().setTitle(data.get(0).getName());


    }

    void setview(){

    }

    @Override
    public void onresponse() {
        progress.setVisibility(View.INVISIBLE);
        findViewById(R.id.weather_main_divider).setVisibility(View.VISIBLE);
        todayData.setVisibility(View.VISIBLE);
        todayData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WeatherUpdate.this,Details.class);
                startActivity(intent);
            }
        });
        data= loader.getdata();
        day1_temp.setText(data.get(0).getTemperature()+"°");
        date.setText(data.get(0).getDate());
        day1_state.setText(data.get(0).getWeather_state());
        day2_state.setText(data.get(1).getWeather_state());
        day2_temp.setText(data.get(1).getTemperature()+"°");
    }

    @Override
    public void onerror() {

    }

    @Override
    public void oninterneterror() {

    }
}
