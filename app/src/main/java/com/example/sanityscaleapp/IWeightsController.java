package com.example.sanityscaleapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface IWeightsController {

    @GET("weights/{id}")
    Call <WeeklyAverage> getAverageWeight(@Path("id") int id);
}
