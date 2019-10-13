package com.example.sanityscaleapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface IUserController {

    @GET("user")
    Call<User> getUser(@Query("email") String email, @Query("password") String password);

    @PATCH("users/goal")
    Call<User> patchUserGoal(@Path("id") int userId, @Body String goal);

    @PATCH("users/units/{id}")
    Call<User> patchUserUnits(@Path("id") int userId, @Body int units);


}
