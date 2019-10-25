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

    @GET("users/units/{id}")
    Call<User> getUserUnits(@Path("id") int id);

    @GET("users/goal/{id}")
    Call<User> getUserGoal(@Path("id") int id);

    @PATCH("users/goal/{id}")
    Call<ResponseBody> patchUserGoal(@Path("id") int id, @Body User goal);

    @PATCH("users/units/{id}")
    Call<ResponseBody> patchUserUnits(@Path("id") int id, @Body User unit);


}
