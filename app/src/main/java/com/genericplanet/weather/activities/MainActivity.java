package com.genericplanet.weather.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.genericplanet.weather.R;

public class MainActivity extends AppCompatActivity {

    EditText search_city;
    Button main_go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_city=findViewById(R.id.main_search_input);
        main_go=findViewById(R.id.main_button);

        main_go.setOnClickListener(new View.OnClickListener() {
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
