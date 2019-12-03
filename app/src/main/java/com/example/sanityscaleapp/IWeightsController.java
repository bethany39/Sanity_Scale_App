package com.example.sanityscaleapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Body;


public interface IWeightsController {

    @GET("weights/averageweight")
    Call <Weight> getAverageWeight(@Query("sessionid") String sid);

    @GET("weights")
    Call <List<Weight>> getWeights(@Query("sessionid") String sid, @Query("timeperiod") String timeperiod);
}
