package com.genericplanet.weather.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.genericplanet.weather.R;
import com.genericplanet.weather.classes.DataLoader;
import com.genericplanet.weather.classes.WeatherData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Details extends AppCompatActivity implements DataLoader.Listener {
    DataLoader loader;
    int index;
    String term;
    ArrayList<WeatherData> data;
    TextView day,date,weatherState,temp,humidity,windSpeed,pressure,visibility;
    LinearLayout view1,view2;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setTitle("Details");
        setViews();
        loader=new DataLoader();
        loader.setListener(this);
        Intent intent=getIntent();
        term=intent.getStringExtra("term");
        index=intent.getIntExtra("index",0);
        loader.isThere(this,term);

    }
    void setViews(){
        view1 =findViewById(R.id.detailsMainData);
        view2=findViewById(R.id.detailsOtherData);
        day =findViewById(R.id.detailsDay);
        date=findViewById(R.id.detailsDate);
        weatherState=findViewById(R.id.detailsState);
        temp=findViewById(R.id.detailsTemp);
        humidity=findViewById(R.id.detailsHumidity);
        windSpeed=findViewById(R.id.detailsSpeed);
        pressure=findViewById(R.id.detailsPressure);
        visibility=findViewById(R.id.detailsVisibilty);
        image=findViewById(R.id.detailsImg);
    }
    void loadViews(){
        Picasso.get().load(data.get(index).getimagerurl()).into(image);
        if(index==0)
        day.setText("Today");
        else day.setText("Tomorrow");
        date.setText(data.get(index).getDate());
        weatherState.setText(data.get(index).getWeather_state());
        temp.setText(data.get(index).getTemperature()+"°");
        humidity.setText("Humidity: "+data.get(index).getHumidity()+" %");
        windSpeed.setText("Windspeed: "+data.get(index).getWindspeed()+" km/h");
        pressure.setText("Pressure: "+data.get(index).getPressure()+" mb");
        visibility.setText("Visibility: "+data.get(index).getVisibility()+" miles");
        view1.setVisibility(View.VISIBLE);
        view2.setVisibility(View.VISIBLE);
        findViewById(R.id.detailsProgress).setVisibility(View.INVISIBLE);
        findViewById(R.id.detailsDivider).setVisibility(View.VISIBLE);
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
