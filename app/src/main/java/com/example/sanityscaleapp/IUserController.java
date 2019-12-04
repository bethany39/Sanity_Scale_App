package com.example.sanityscaleapp;

import okhttp3.ResponseBody;
//import okhttp3.Response;
import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.DELETE;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface IUserController {

    @POST("users/sessionid")
    Call<User> getUser(@Body User user);

    @GET("users/email")
    Call<BooleanAPIResponse> getEmail(@Query("email") String email);

    @GET("users/firstname")
    Call<User> getFirstName(@Query("sessionid") String sid);

    @GET("users/timesweighed")
    Call<User> getNumTimesWeighed(@Query("sessionid") String sid);

    @GET("users/canseeweight")
    Call<User> canSeeWeight(@Query("sessionid") String sid);

    @GET("users/units")
    Call<User> getUserUnits(@Query("sessionid") String sid);

    @GET("users/goal")
    Call<User> getUserGoal(@Query("sessionid") String sid);

    @PATCH("users/goal")
    Call<ResponseBody> patchUserGoal(@Body User user);

    @PATCH("users/units")
    Call<ResponseBody> patchUserUnits(@Body User user);

    @PATCH("users/setLastChecked")
    Call<ResponseBody> setLastChecked(@Query("sessionid") String sid);

    @DELETE("users/logout")
    Call<ResponseBody> deleteSid(@Query("sessionid") String sid);

    @POST("users")
    Call<ResponseBody> createUser(@Body User user);


}
