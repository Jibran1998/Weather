package com.genericplanet.weather.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.genericplanet.weather.R;
import com.genericplanet.weather.classes.DataLoader;
import com.genericplanet.weather.classes.WeatherData;

import java.util.ArrayList;

public class test extends AppCompatActivity implements DataLoader.Listener {

    TextView result;
    Button btn;
    RequestQueue queue;

    DataLoader loader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        result=findViewById(R.id.result);
        btn=findViewById(R.id.test_button);
        queue= Volley.newRequestQueue(this);
        loader=new DataLoader();

        loader.setListener(this);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loader.isThere(test.this,"lahore");
                    }
                });
    }
    @Override
    public void onresponse() {
        result.setText("Done");
        ArrayList<WeatherData> data= loader.getdata();
        result.setText(data.get(0).getDate());
    }

    @Override
    public void onerror() {
        Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void oninterneterror() {

    }

    @Override
    public void debugger(String text) {

    }
}
