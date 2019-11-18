package com.example.sanityscaleapp;

import com.google.gson.annotations.SerializedName;

public class SessionId {
    @SerializedName("sessionid")
    private String sid;

    public SessionId(String sid){
        this.sid=sid;
    }
}
