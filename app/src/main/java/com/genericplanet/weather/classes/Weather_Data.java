package com.genericplanet.weather.classes;

public class Weather_Data {

    private String name,weather_state,abbr,date;
    private int weoid,temperature,humidity,windspeed,visibility;
    public Weather_Data(){

    }
    public Weather_Data setName(String name){
        this.name=name;
        return this;
    }
    public Weather_Data setWeather_state(String state){
        weather_state=state;
        return this;
    }
    public Weather_Data setAbbr(String abbri)
    {
        abbr=abbri;
        return this;
    }

    public Weather_Data setDate(String dte) {
        date=dte;
        return this;
    }

    public Weather_Data setWeoid(int id){
        weoid=id;
        return this;
    }
    public Weather_Data setTemperature(int temp) {
        temperature=temp;
        return this;
    }

    public Weather_Data setHumidity(int humidity){
        this.humidity=humidity;
        return this;
    }
    public Weather_Data setWindspeed(int windspeed){
        this.windspeed=windspeed;
        return this;
    }
    public Weather_Data setVisibility(int visibility) {
        this.visibility=visibility;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getAbbr() {
        return abbr;
    }

    public String getWeather_state() {
        return weather_state;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getVisibility() {
        return visibility;
    }

    public int getWeoid() {
        return weoid;
    }

    public int getWindspeed() {
        return windspeed;
    }
}
