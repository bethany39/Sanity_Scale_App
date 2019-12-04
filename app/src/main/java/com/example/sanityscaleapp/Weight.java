package com.example.sanityscaleapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weight {

    @SerializedName("weight") @Expose
    private float weight;

    @SerializedName("weeklyaverage") @Expose
    private float weeklyAverage;

    @SerializedName("sessionid")
    private String sessionid;

    @SerializedName("timeperiod")
    private String timeperiod;

    public Weight(float weight, float weeklyAverage) {
        this.weight=weight;
        this.weeklyAverage=weeklyAverage;
    }

    public Weight(String sid){
        this.sessionid=sid;
    }
    public float getWeight() {
        return weight;
    }

    public float getWeeklyAverage(){
        return weeklyAverage;
    }

}
