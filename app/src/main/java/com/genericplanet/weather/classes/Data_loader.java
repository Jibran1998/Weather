package com.genericplanet.weather.classes;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Data_loader {
    static  Weather_Data data[]=new Weather_Data[2];
    static Weather_Data dat=new Weather_Data();
    public interface Listener{
        void onresponse();
    }
    static Listener listener;
    public void setListener(Listener listener){
        this.listener=listener;
    }
    public static void setdata(String url,Context context){
        RequestQueue queue= Volley.newRequestQueue(context);
       JsonObjectRequest request=new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
           @Override
           public void onResponse(JSONObject response) {
               try {

                  for(int i=0;i<2;i++){
                       response.getString("title");
                       JSONArray days=response.getJSONArray("consolidated_weather");
                       JSONObject day=days.getJSONObject(i);
                       String weather_state=day.getString("weather_state_name");
                       String date=day.getString("applicable_date");
                       int temp=day.getInt("the_temp");
                       int humidity=day.getInt("humidity");
                       int visibility=day.getInt("visibility");
                       int windspeed=day.getInt("wind_speed");
                       dat.setDate(date);
                       dat.setName(response.getString("title")).setHumidity(humidity).setTemperature(temp)
                               .setVisibility(visibility).setWeather_state(weather_state).setWindspeed(windspeed);
                       data[i]=dat;
                 }
                   listener.onresponse();

               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {

           }
       });
       queue.add(request);
    };
    public static Weather_Data[] getdata()
    {
        return data;
    }
public static void isthere(final Context context, final String term){
    RequestQueue queue= Volley.newRequestQueue(context);

    String url="https://www.metaweather.com/api/location/search/?query="+term;
    JsonArrayRequest request=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {


            try {
                if(response.getJSONObject(0).has("title")==true)
                {
                String url ="https://www.metaweather.com/api/location/"+response.getJSONObject(0).getString("woeid");

                setdata(url,context);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
//         listener.onresponse();
        }
    });
    queue.add(request);

}

}
