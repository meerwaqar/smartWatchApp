package com.example.waqar.smartwatch.ammsmartwatch;

/**
 * Created by Waqar on 9/18/2016.
 */
public class Locations {

    public double lat;
    public double log;
    public String name;

    public Locations(double lat, double log, String name){
        this.lat = lat;
        this.log = log;
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public double getLat(){
        return this.lat;
    }

    public double getLong(){
        return  this.log;
    }
    public void setName(String name){
        this.name =name;
    }
    public  void setLat(float lat){
        this.lat = lat;
    }

    public  void setLong(float log){
        this.log = log;
    }


}
