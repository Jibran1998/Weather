package com.genericplanet.weather.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.genericplanet.weather.R;
import com.genericplanet.weather.classes.DataLoader;
import com.genericplanet.weather.classes.WeatherData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WeatherUpdate extends AppCompatActivity implements DataLoader.Listener {

    String term;
    TextView day1_temp,date,day1_state,day2_state,day2_temp;
    LinearLayout todayData,day2Data;
    ProgressBar progress;
    DataLoader loader;
    ImageView todayImage,day2Img;
    ArrayList<WeatherData> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_update);
        loadViews();
        loader=new DataLoader();
        loader.setListener(this);
        Intent intent=getIntent();
        term=intent.getStringExtra("city_name");
        loader.isThere(this,term);
    }
    void loadViews()
    {
        day1_state=findViewById(R.id.weather_update_today_state);
        day2_state=findViewById(R.id.weather_update_tomorrow_state);
        day1_temp=findViewById(R.id.weather_update_today_temp);
        date=findViewById(R.id.weather_update_today_date);
        day2_temp=findViewById(R.id.weather_update_tomorrow_temp);
        todayData=findViewById(R.id.weather_update_today_data);
        progress=findViewById(R.id.weather_progress);
        todayImage=findViewById(R.id.weatherStateImg);
        day2Img=findViewById(R.id.weatherDay2Img);
        day2Data=findViewById(R.id.waeatherday2Data);
    }

    @Override
    public void onresponse() {
        data= loader.getdata();
        Picasso.get().load(data.get(0).getimagerurl()).fit().into(todayImage);
        Picasso.get().load(data.get(1).getimagerurl()).fit().into(day2Img);
     getSupportActionBar().setTitle(data.get(0).getName());
        todayData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WeatherUpdate.this,Details.class);
                intent.putExtra("index",0);
                intent.putExtra("term",term);
                startActivity(intent);
            }
        });
        day2Data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WeatherUpdate.this,Details.class);
                intent.putExtra("index",1);
                intent.putExtra("term",term);
                startActivity(intent);
            }
        });

        findViewById(R.id.weather_main_divider).setVisibility(View.VISIBLE);
        day1_temp.setText(data.get(0).getTemperature()+"°");
        date.setText(data.get(0).getDate());
        day1_state.setText(data.get(0).getWeather_state());
        day2_state.setText(data.get(1).getWeather_state());
        day2_temp.setText(data.get(1).getTemperature()+"°");
        todayData.setVisibility(View.VISIBLE);
        day2Data.setVisibility(View.VISIBLE);
        progress.setVisibility(View.INVISIBLE);
        findViewById(R.id.hider).setVisibility(View.INVISIBLE);
    }

    @Override
    public void onerror() {

    }

    @Override
    public void oninterneterror() {

    }

    @Override
    public void debugger(String text) {
        Toast.makeText(this,text,Toast.LENGTH_LONG).show();
    }
}
