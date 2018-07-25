package com.genericplanet.weather.models;

public class City {

    private String name,weather_state,abbr,date;
    private int weoid,temperature,humidity,windspeed,visibility;

    public City setName(String name){
        this.name=name;
        return this;
    }
    public City setWeather_state(String state){
        weather_state=state;
        return this;
    }
    public City setAbbr(String abbri)
    {
        abbr=abbri;
        return this;
    }

    public City setDate(String dte) {
        date=dte;
        return this;
    }

    public City setWeoid(int id){
        weoid=id;
        return this;
    }
    public City setTemperature(int temp) {
        temperature=temp;
        return this;
    }

    public City setHumidity(int humidity){
        this.humidity=humidity;
        return this;
    }
    public City setWindspeed(int windspeed){
        this.windspeed=windspeed;
        return this;
    }
    public City setVisibility(int visibility) {
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
