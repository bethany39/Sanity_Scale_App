package com.example.sanityscaleapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Weight {

    @SerializedName("weight") @Expose
    private float weight;

    @SerializedName("weeklyaverage") @Expose
    private float weeklyAverage;

    @SerializedName("sessionid")
    private String sessionid;

    @SerializedName("date")
    private String dateString;

    private Date FormattedDate;



    public Weight(float weight, float weeklyAverage) {
        this.weight=weight;
        this.weeklyAverage=weeklyAverage;
    }

    public Weight(float weight, Date formattedDate){
        this.weight=weight;
        this.FormattedDate=formattedDate;
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

    public String getDateString() { return dateString; }

    public Date getDate() {return FormattedDate; }

    static class WeightComparator implements Comparator<Weight> {

        @Override
        public int compare(Weight w1, Weight w2) {
            if (w1.getDate() == null || w2.getDate() == null)
                return 0;
            return w1.getDate().compareTo(w2.getDate());
        }

    }
}
