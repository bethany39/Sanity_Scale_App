package com.example.sanityscaleapp;



import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserController {

    //private static UserController userController;
    private IUserController iUserController;
    public static final String base_url = "https://sanityscaleapp/";
    private boolean successfulLogin;
    public int USER_ID;
    public UserController() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://sanityscaleapi")
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().setPrettyPrinting().create()))
                .build();
        iUserController = retrofit.create(IUserController.class);

    }
    //these methods shouldn't be void... should return something else to let the calling code
    //know if it was successful
    public boolean getUser(String email, String password){
        Call<User> call = iUserController.getUser(email, password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    successfulLogin=false;
                    return;
                    //should do something for the error handlign
                }

                User user = response.body();
                USER_ID = user.getUserId();
                successfulLogin=true;
                return;

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                successfulLogin=false;
            }
        });
        return successfulLogin;
    }


    public void putUserGoal(String goal){
        Call<User> call = iUserController.patchUserGoal(USER_ID, goal);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    return;
                    //should do something for the error handlign
                }
                User user = response.body();
                //userId = user.getUserId();
                return;

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


    }

    public void patchUserUnits(int units){
        Call<User> call = iUserController.patchUserUnits(USER_ID, units);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    return;
                    //should do something for the error handlign
                }

                //User user = response.body();

                return;

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }



}
