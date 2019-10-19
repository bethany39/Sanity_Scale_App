package com.example.sanityscaleapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weight {

    @SerializedName("weight") @Expose
    private float weight;

    public Weight(float weight) {
        this.weight=weight;
    }

    public float getWeight() {
        return weight;
    }
}
