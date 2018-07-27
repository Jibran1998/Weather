package com.genericplanet.weather.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.genericplanet.weather.R;
import com.genericplanet.weather.classes.Data_loader;

public class MainActivity extends AppCompatActivity implements Data_loader.Listener {

    EditText search_city;
    Button main_go;
    TextView cityname;
   RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_city=findViewById(R.id.main_search_input);
        main_go=findViewById(R.id.main_button);
        cityname=findViewById(R.id.main_result);
        cityname.setVisibility(View.INVISIBLE);
        queue=Volley.newRequestQueue(MainActivity.this);
        Data_loader loader=new Data_loader();
        loader.setListener(this);
        main_go.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Data_loader.isthere(MainActivity.this,"lahore");
//               if(Data_loader.isthere(MainActivity.this,search_city.getText().toString()))
//               {
//                   Toast.makeText(MainActivity.this,"Done",Toast.LENGTH_LONG).show();
//               }




            }

        });


    }

    @Override
    public void onresponse() {
        Toast.makeText(MainActivity.this,"done",Toast.LENGTH_LONG).show();
        main_go.setVisibility(View.INVISIBLE);
        search_city.setVisibility(View.INVISIBLE);
        cityname.setVisibility(View.VISIBLE);
        cityname.setText("City Found:"+search_city.getText().toString());
        cityname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Weather_update.class);
                intent.putExtra("city_name",search_city.getText().toString());
                MainActivity.this.finish();
                startActivity(intent);
            }
        });
    }
}
