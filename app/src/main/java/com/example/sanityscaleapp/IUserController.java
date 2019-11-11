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
import retrofit2.http.DELETE;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface IUserController {

    @GET("users")
    Call<User> getUser(@Query("email") String email, @Query("password") String password);

//    @GET("users/units/{id}")
//    Call<User> getUserUnits(@Path("id") int id);

//    @GET("users/goal/{id}")
 //   Call<User> getUserGoal(@Path("id") int id);

 //   @PATCH("users/goal/{id}")
 //   Call<ResponseBody> patchUserGoal(@Path("id") int id, @Body User goal);

  //  @PATCH("users/units/{id}")
  //  Call<ResponseBody> patchUserUnits(@Path("id") int id, @Body User unit);

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
