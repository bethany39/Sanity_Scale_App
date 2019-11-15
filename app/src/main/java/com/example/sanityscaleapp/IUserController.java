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

    @PATCH("user/units/{sid}")
    Call<ResponseBody> patchUserUnits(@Path("sid") String sid, @Body User unit);

    @GET("users/units/{sid}")
    Call<User> getUserUnits(@Path("sid") String sid);

    @GET("users/goal/{sid}")
    Call<User> getUserGoal(@Path("sid") String sid);

    @PATCH("users/goal/{sid}")
    Call<ResponseBody> patchUserGoal(@Path("sid") String sid, @Body User goal);

    @DELETE("users/{sid}") //can't be users/{sid}
    Call<Void> deleteSid(@Path("sid")String sid);



}
