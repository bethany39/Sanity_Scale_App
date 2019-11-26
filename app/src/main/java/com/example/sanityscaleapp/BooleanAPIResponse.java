package com.example.sanityscaleapp;

import com.google.gson.annotations.SerializedName;

public class BooleanAPIResponse {

    @SerializedName("email in use")
    private Boolean message;

    public BooleanAPIResponse(Boolean message) {this.message=message;}

    public Boolean getMessage() {return message;}
}
