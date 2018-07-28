package com.genericplanet.weather.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.genericplanet.weather.R;
import com.genericplanet.weather.classes.DataLoader;
import com.genericplanet.weather.classes.WeatherData;

import java.util.ArrayList;

public class Details extends AppCompatActivity implements DataLoader.Listener {
    DataLoader loader;
    TextView day,temp,WeatherState,date,humidity,pressure,speed,visibility;
    ImageView image;
    int i;
    String term;
    ArrayList<WeatherData> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setViews();
        loader=new DataLoader();
        Intent intent=getIntent();
        term=intent.getStringExtra("term");
        loader.setListener(this);
        loader.isThere(this,term);
        i=intent.getIntExtra("index",0);
    }
    void setViews()
    {

        day=findViewById(R.id.detailsDay);
        temp=findViewById(R.id.detailsTemp);
        WeatherState=findViewById(R.id.detailsState);
        date=findViewById(R.id.detailsDate);
        humidity=findViewById(R.id.detailsHumidity);
        pressure=findViewById(R.id.detailsPressure);
        speed=findViewById(R.id.detailsSpeed);
        visibility=findViewById(R.id.detailsVisibilty);
        image=findViewById(R.id.detailsImg);
    }
    void loadViews()
    {
        day.setText("Today");
        Toast.makeText(this,term,Toast.LENGTH_LONG).show();
        String name=data.get(i).getName();
        day.setText("");
        temp.setText(data.get(i).getTemperature());
        WeatherState.setText(data.get(i).getWeather_state());
        date.setText(data.get(i).getDate());;
        humidity.setText("Humdidity: "+data.get(i).getHumidity());
        pressure.setText("Pressure: "+data.get(i).getWeoid());
        speed.setText("Wind Speed: "+data.get(i).getWindspeed());
        visibility.setText("Visibilty: "+data.get(i).getVisibility());
    }

    @Override
    public void onresponse() {
       data=loader.getdata();
        loadViews();
    }

    @Override
    public void onerror() {

    }

    @Override
    public void oninterneterror() {

    }

    @Override
    public void debugger(String text) {

    }
}
