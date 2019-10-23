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
    private int USERID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);
        USERID = getIntent().getExtras().getInt("USERID");


        backBtn=findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SettingsScreen.this, HomeScreen.class);
                SettingsScreen.this.startActivity(intent);

            }
        });
        profileTab=findViewById(R.id.profileBtn);

        changeGoalsTab=findViewById(R.id.goalsBtn);
        changeGoalsTab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ///IUserController iUserController = retrofit.create(IUserController.class);
                IUserController userService = RetrofitApi.getInstance().getUserService();

                Call<User> call = userService.getUserGoal(USERID);
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
                        Intent intent;
                        intent =new Intent(SettingsScreen.this, Goals.class);

                        switch(goal){
                            case "maintain weight":
                                intent.putExtra("selected", "maintain");
                                break;
                            case "gain weight":
                                intent.putExtra("selected", "gain");
                                break;
                            case "lose weight":
                                intent.putExtra("selected", "lose");
                                break;

                        }
                        SettingsScreen.this.startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("UserController", "inside onFailure");

                    }
                });
            }
        });




        changeUnitsTab=findViewById(R.id.changeUnitsBtn);
        changeUnitsTab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //IUserController iUserController = retrofit.create(IUserController.class);
                IUserController userService = RetrofitApi.getInstance().getUserService();

                Call<User> unitsCall = userService.getUserUnits(USERID);
                unitsCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(!response.isSuccessful()){
                            //should do something for the error handlign
                            Log.d("UserController", "inside if in onResponse");
                            return;

                        }
                        User user = response.body();
                        String unit = user.getUnit();
                        Intent intent;
                        switch(unit){
                            case "pounds":
                                intent =new Intent(SettingsScreen.this, ChangeUnits.class);
                                intent.putExtra("USERID", USERID);
                                SettingsScreen.this.startActivity(intent);
                                break;
                            case "kgs":
                                intent =new Intent(SettingsScreen.this, ChangeUnits.class);
                                intent.putExtra("USERID", USERID);
                                SettingsScreen.this.startActivity(intent);
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

    }


}