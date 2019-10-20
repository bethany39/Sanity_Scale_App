package com.example.sanityscaleapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeeklyAverage {
    @SerializedName("weeklyaverage") @Expose
    private float weeklyaverage;

    private WeeklyAverage (float weeklyaverage) {
        this.weeklyaverage=weeklyaverage;
    }

    public float getWeeklyAverage(){
        return weeklyaverage;
    }



}
