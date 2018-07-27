package com.genericplanet.weather.activities;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.genericplanet.weather.R;
import com.genericplanet.weather.classes.Data_loader;
import com.genericplanet.weather.classes.Weather_Data;

public class test extends AppCompatActivity implements Data_loader.Listener {

    TextView result;
    Button btn;
    RequestQueue queue;
    Weather_Data data[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        result=findViewById(R.id.result);
        btn=findViewById(R.id.test_button);
        queue= Volley.newRequestQueue(this);
        Data_loader loader=new Data_loader();
        loader.setListener(this);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String url="https://www.metaweather.com/api/location/search/?query=london";
//                JsonArrayRequest request=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        try {
//                            String title;
//                            title =response.getJSONObject(0).getString("title");
//                            Toast.makeText(test.this,title,Toast.LENGTH_LONG).show();
//                        } catch (JSONException e) {
//                            Toast.makeText(test.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
//
//                        }
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(test.this,error.getLocalizedMessage(),Toast.LENGTH_LONG);
//                    }
//                });
//                queue.add(request);
//            }
//
//        });
//
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
         data= Data_loader.getdata();
        result.setText(data[1].getDate());
    }
}
