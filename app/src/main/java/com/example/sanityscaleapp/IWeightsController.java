package com.example.sanityscaleapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Body;


public interface IWeightsController {

    @GET("weights")
    Call <Weight> getAverageWeight(@Body String id);
}
