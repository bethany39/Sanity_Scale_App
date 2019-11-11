package com.example.sanityscaleapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface IWeightsController {

    @GET("weights/{sid}")
    Call <Weight> getAverageWeight(@Path("sid") String id);
}
