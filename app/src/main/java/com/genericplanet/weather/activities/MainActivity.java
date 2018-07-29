package com.genericplanet.weather.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.genericplanet.weather.R;
import com.genericplanet.weather.classes.DataLoader;
import com.genericplanet.weather.classes.WeatherData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DataLoader.Listener {

    EditText search_city;
    Button main_search_button,main_proceed,main_back;
    TextView cityname;
    ProgressBar progress;
    ArrayList<WeatherData> data;
    DataLoader loader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_city=findViewById(R.id.main_search_input);
        main_search_button =findViewById(R.id.main_button);
        cityname=findViewById(R.id.main_result);
        main_proceed=findViewById(R.id.main_proceed);
        main_back=findViewById(R.id.main_back);
        progress=findViewById(R.id.main_acitivity_progressbar);
        loader=new DataLoader();
        loader.setListener(this);
        main_search_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                hidekeyboard(search_city);
                loader.isThere(MainActivity.this,search_city.getText().toString());
            }

        });
    }
        void hideviews(){
            search_city.setVisibility(View.INVISIBLE);
            main_search_button.setVisibility(View.INVISIBLE);
    }
    void showviews()
    {
        search_city.setText("");
        search_city.setVisibility(View.VISIBLE);
        main_search_button.setVisibility(View.VISIBLE);
        cityname.setText(search_city.getText().toString());
        cityname.setVisibility(View.INVISIBLE);
        main_proceed.setVisibility(View.INVISIBLE);
        main_back.setVisibility(View.INVISIBLE);
        findViewById(R.id.main_text_show).setVisibility(View.INVISIBLE);

    }
        public void hidekeyboard(View view)
        {   hideviews();
            progress.setVisibility(View.VISIBLE);
            InputMethodManager imm = (InputMethodManager)getSystemService(MainActivity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    @Override
    public void onresponse() {
        hideviews();
        progress.setVisibility(View.INVISIBLE);
        data= loader.getdata();
        cityname.setText(data.get(0).getName());
        cityname.setVisibility(View.VISIBLE);
        main_proceed.setVisibility(View.VISIBLE);
        main_back.setVisibility(View.VISIBLE);
        findViewById(R.id.main_text_show).setVisibility(View.VISIBLE);
        main_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,WeatherUpdate.class);
                intent.putExtra("city_name",search_city.getText().toString());
                startActivity(intent);
            }
        });
        main_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showviews();
            }
        });

    }

    @Override
    public void onerror() {
        progress.setVisibility(View.INVISIBLE);
        showviews();
        Toast.makeText(this,"Please enter correct name",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void oninterneterror() {
        progress.setVisibility(View.INVISIBLE);
        showviews();
        Toast.makeText(this,"Please check your internet connection",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void debugger(String text) {

    }
}
