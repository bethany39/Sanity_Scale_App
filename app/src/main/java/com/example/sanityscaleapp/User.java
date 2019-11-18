package com.example.sanityscaleapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("firstname")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("units")
    @Expose
    private String unit;
    @SerializedName("goal")
    @Expose
    private String goal;
    @SerializedName("userid")
    private int userid;

    @SerializedName("sessionid")
    private String sessionid;


    public User(String email, String password) {
        this.email=email;
        this.password=password;
    }


    public User(String email, String password, String firstname, String goal, String units)
    {
        this.email=email;
        this.password=password;
        this.name=firstname;
        this.goal=goal;
        this.unit=units;

    }

    public User(String goal, String units, String sid){
        this.goal=goal;
        this.unit=units;
        this.sessionid=sid;
    }
    public User(String unit){
        this.goal=goal;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUnit() {
        return unit;
    }

    public String getGoal() {
        return goal;
    }

    public int getUserId() {
        return userid;
    }

    public String getSessionId() { return sessionid; }
}
