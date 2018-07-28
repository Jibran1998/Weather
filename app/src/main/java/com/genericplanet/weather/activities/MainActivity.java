package com.genericplanet.weather.activities;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.genericplanet.weather.R;
import com.genericplanet.weather.classes.Data_loader;
import com.genericplanet.weather.classes.Weather_Data;

public class MainActivity extends AppCompatActivity implements Data_loader.Listener {

    EditText search_city;
    Button main_search_button,main_proceed,main_back;
    TextView cityname;
    Weather_Data data[]=new Weather_Data[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_city=findViewById(R.id.main_search_input);
        main_search_button =findViewById(R.id.main_button);
        cityname=findViewById(R.id.main_result);
        main_proceed=findViewById(R.id.main_proceed);
        main_back=findViewById(R.id.main_back);
        Data_loader loader=new Data_loader();
        loader.setListener(this);
        main_search_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                hidekeyboard(search_city);
                Data_loader.isthere(MainActivity.this,search_city.getText().toString());
            }

        });
    }
        public void hidekeyboard(View view)
        {
            InputMethodManager imm = (InputMethodManager)getSystemService(MainActivity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    @Override
    public void onresponse() {
        search_city.setVisibility(View.GONE);
        main_search_button.setVisibility(View.INVISIBLE);
        data= Data_loader.getdata();
        cityname.setText(data[0].getName());
        cityname.setVisibility(View.VISIBLE);
        main_proceed.setVisibility(View.VISIBLE);
        main_back.setVisibility(View.VISIBLE);
        findViewById(R.id.main_text_show).setVisibility(View.VISIBLE);
        main_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Weather_update.class);
                intent.putExtra("city_name",search_city.getText().toString());
                startActivity(intent);
            }
        });
        main_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search_city.setText("");
                search_city.setVisibility(View.VISIBLE);
                main_search_button.setVisibility(View.VISIBLE);
                cityname.setText(search_city.getText().toString());
                cityname.setVisibility(View.INVISIBLE);
                main_proceed.setVisibility(View.INVISIBLE);
                main_back.setVisibility(View.INVISIBLE);
                findViewById(R.id.main_text_show).setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public void onerror() {
        Toast.makeText(this,"Please enter correct name",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void oninterneterror() {
        Toast.makeText(this,"Please check your internet connection",Toast.LENGTH_SHORT).show();
    }
}
