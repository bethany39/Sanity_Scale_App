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
    private int unit;
    @SerializedName("goal") @Expose
    private String goal;
    @SerializedName("userId") @Expose
    private int userId;

    public User(String name, String email, String password, int unit, String goal, int id){
        this.name=name;
        this.email=email;
        this.password=password;
        this.unit=unit;
        this.goal=goal;
        this.userId=id;
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getUnit() {
        return unit;
    }

    public String getGoal() {
        return goal;
    }

    public int getUserId() {
        return userId;
    }
}
