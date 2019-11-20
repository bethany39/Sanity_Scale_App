package com.example.sanityscaleapp;

import com.google.gson.annotations.SerializedName;

public class APIResponse {

    @SerializedName("email in use")
    private Boolean message;

    public APIResponse(Boolean message) {this.message=message;}

    public Boolean getMessage() {return message;}
}
