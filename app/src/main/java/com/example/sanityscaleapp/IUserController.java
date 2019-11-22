package com.example.sanityscaleapp;

import java.util.List;

import okhttp3.ResponseBody;
//import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Response;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.DELETE;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface IUserController {

    @POST("users/sessionid")
    Call<User> getUser(@Body User user);

    @GET("users/email")
    Call<APIResponse> getEmail(@Query("email") String email);

    @GET("users/timesweighed")
    Call<User> getNumTimesWeighed(@Query("timesweighed") String sid);

    @PATCH("users/units")
    Call<ResponseBody> patchUserUnits(@Body User user);

    @GET("users/units")
    Call<User> getUserUnits(@Query("sessionid") String sid);

    @GET("users/goal")
    Call<User> getUserGoal(@Query("sessionid") String sid);

    @PATCH("users/goal")
    Call<ResponseBody> patchUserGoal(@Body User user);

    @DELETE("users/logout")
    Call<ResponseBody> deleteSid(@Query("sessionid") String sid);

    @POST("users")
    Call<ResponseBody> createUser(@Body User user);



}
