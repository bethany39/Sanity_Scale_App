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


   // @GET("users")
  //  Call<User> getUser(@Body  String email, @Body String password);

 //     Call<User> getUser(@Path("email")String email, @Path("password") String password);
//    @GET("users/units/{id}")
//    Call<User> getUserUnits(@Path("id") int id);

//    @GET("users/goal/{id}")
 //   Call<User> getUserGoal(@Path("id") int id);

 //   @PATCH("users/goal/{id}")
 //   Call<ResponseBody> patchUserGoal(@Path("id") int id, @Body User goal);

  //  @PATCH("users/units/{id}")
  //  Call<ResponseBody> patchUserUnits(@Path("id") int id, @Body User unit);

    @POST("users/sessionid")
    Call<User> getUser(@Body User user);

 //   @POST("users/sessionid") //what???
  //  Call<ResponseBody> postEmail(@Body String email, @Body String password);

    @PATCH("users/units")
    Call<ResponseBody> patchUserUnits(@Body String sid, @Body User unit);

    @GET("users/units")
    Call<User> getUserUnits(@Body String sid);

    @GET("users/goal")
    Call<User> getUserGoal(@Body String sid);

    @PATCH("users/goal")
    Call<ResponseBody> patchUserGoal(@Body String sid, @Body User goal);

    @DELETE("users/logout")
    Call<ResponseBody> deleteSid(@Query("sessionid") String sid);

    @POST("users")
    Call<ResponseBody> createUser(@Body User user);



}
