package com.example.waqar.smartwatch.ammsmartwatch;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Waqar on 9/18/2016.
 */
public class Locations implements Parcelable {

    public double lat;
    public double log;
    public String name;

    public Locations(double lat, double log, String name){
        this.lat = lat;
        this.log = log;
        this.name = name;
    }

    protected Locations(Parcel in) {
        lat = in.readDouble();
        log = in.readDouble();
        name = in.readString();
    }

    public static final Creator<Locations> CREATOR = new Creator<Locations>() {
        @Override
        public Locations createFromParcel(Parcel in) {
            return new Locations(in);
        }

        @Override
        public Locations[] newArray(int size) {
            return new Locations[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(lat);
        dest.writeDouble(log);
        dest.writeString(name);
    }
}
