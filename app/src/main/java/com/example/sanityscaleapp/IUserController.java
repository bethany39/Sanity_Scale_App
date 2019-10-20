package com.example.sanityscaleapp;

import java.util.List;

import okhttp3.ResponseBody;
//import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Response;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface IUserController {

    @GET("users")
    Call<User> getUser(@Query("email") String email, @Query("password") String password);

    @PATCH("users/{id}/goal")
    Call<User> patchUserGoal(@Path("id") int id, @Body String goal);

    @PATCH("users/{id}/units/")
    Call<User> patchUserUnits(@Path("id") int id, @Body int units);


}
