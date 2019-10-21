package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;

import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SettingsScreen extends AppCompatActivity {
    Button backBtn, changeGoalsTab, changeUnitsTab, profileTab;
    Retrofit retrofit;
    private int USERID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);
        USERID = getIntent().getExtras().getInt("USERID");

        retrofit = new Retrofit.Builder().baseUrl("https://sanity-scale-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().setPrettyPrinting().create()))
                .build();

        backBtn=findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SettingsScreen.this, HomeScreen.class);
                SettingsScreen.this.startActivity(intent);

            }
        });
        profileTab=findViewById(R.id.profile);

        changeGoalsTab=findViewById(R.id.changeGoals);
        changeGoalsTab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                IUserController iUserController = retrofit.create(IUserController.class);

                Call<User> call = iUserController.getUserGoal(USERID);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(!response.isSuccessful()){
                            //should do something for the error handlign
                            Log.d("UserController", "inside if in onResponse");
                            return;

                        }
                        Log.d("UserController", "outside if in onResponse");
                        User user = response.body();
                        String goal = user.getGoal();
                        switch(goal){
                            case "maintain weight":
                                //in each of these navigate to correct screen with right option selected
                                break;
                            case "gain weight":
                                break;
                            case "lose weight":
                                break;

                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("UserController", "inside onFailure");

                    }
                });
            }
        });




        changeUnitsTab=findViewById(R.id.changeUnits);
        changeUnitsTab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                IUserController iUserController = retrofit.create(IUserController.class);

                Call<User> call = iUserController.getUserUnits(USERID);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(!response.isSuccessful()){
                            //should do something for the error handlign
                            Log.d("UserController", "inside if in onResponse");
                            return;

                        }
                        Log.d("UserController", "outside if in onResponse");
                        User user = response.body();
                        int unit = user.getUnit();

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("UserController", "inside onFailure");

                    }
                });
            }
        });

    }


}