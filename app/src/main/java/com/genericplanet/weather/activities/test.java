package com.genericplanet.weather.activities;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.genericplanet.weather.R;
import com.genericplanet.weather.classes.Data_loader;
import com.genericplanet.weather.classes.Weather_Data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test extends AppCompatActivity implements Data_loader.Listener {

    TextView result;
    Button btn;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        result=findViewById(R.id.result);
        btn=findViewById(R.id.test_button);
        queue= Volley.newRequestQueue(this);
        Data_loader loader=new Data_loader();
        loader.setListener(this);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Data_loader.isthere(test.this,"lahore");
                    }
                });
    }
    @Override
    public void onresponse() {
        result.setText("Done");
        Weather_Data data[]=Data_loader.getdata();
        result.setText(data[0].getDate());
    }

    @Override
    public void onerror() {
        Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void oninterneterror() {

    }
}
