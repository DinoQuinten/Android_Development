package com.example.android.quakereport;

import java.util.Date;

public class Earthquake {
    private String city;
    private long timeInMilliseconds;
    private Double magnitude;
    private String url;

    public Earthquake(Double magnitude, String city, long timeInMilliseconds, String url){
        this.magnitude=magnitude;
        this.city=city;
        this.timeInMilliseconds=timeInMilliseconds;
        this.url=url;
    }

    public Double getMagnitude(){return magnitude;}
    public String getCity(){return city;}
    public String getUrl(){return url;}
    public Date getDate(){return new Date(timeInMilliseconds);}
}
