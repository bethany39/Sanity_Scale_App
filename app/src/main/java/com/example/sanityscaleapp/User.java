package com.example.sanityscaleapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("name") @Expose
    private String name;
    @SerializedName("email") @Expose
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("unit") @Expose
    private String unit;
    @SerializedName("goal") @Expose
    private String goal;
    @SerializedName("userid") @Expose
    private int userid;
    private String sessionid;

    public User(String name, String email, String password, String unit, String goal, int id){
        this.name=name;
        this.email=email;
        this.password=password;
        this.unit=unit;
        this.goal=goal;
        this.userid=id;
    }

    public User(String unit){
        this.goal=goal;
    }

    //public User(String goal){}

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
