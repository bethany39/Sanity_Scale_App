package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogInScreen extends AppCompatActivity {
    Button nextBtn, backBtn;
    private int USERID;
    private float weeklyAverage;
    Retrofit retrofit;
    //public final UserController userController = new UserController();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);

        retrofit = new Retrofit.Builder().baseUrl("https://sanity-scale-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().setPrettyPrinting().create()))
                .build();
        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new OnClickListener() {



            @Override
            public void onClick(View v) {


                EditText email = (EditText) findViewById(R.id.emailBox);
                EditText password = (EditText) findViewById(R.id.passwordBox);

                IUserController iUserController = retrofit.create(IUserController.class);
                //UserController userController = new UserController();
                //boolean success = userController.getUser(email.getText().toString(), password.getText().toString(), LogInScreen.this);
                //Call<User> call = iUserController.getUser(email.getText().toString(), password.getText().toString());
                Call<User> call = iUserController.getUser("david@gmail.com", "davidmayes");

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
                        USERID = user.getUserId();
                        //USERID = Integer.parseInt(rb);
                        goToHomeScreen();
                        //Intent intent = new Intent(LogInScreen.this, HomeScreen.class);
                        //LogInScreen.this.startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("UserController", "inside onFailure");

                    }
                });
            }
        });

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInScreen.this, MainActivity.class);
                LogInScreen.this.startActivity(intent);

            }
        });
    }

    public void goToHomeScreen() {
        //Intent intent = new Intent(LogInScreen.this, HomeScreen.class);
        Intent intent = new Intent(getBaseContext(), HomeScreen.class);
        intent.putExtra("USERID", USERID);
        LogInScreen.this.startActivity(intent);


//  moved all the below code to HomeScreen but haven't tested it yet

//        IWeightsController iWeightsController = retrofit.create(IWeightsController.class);
//
//        Call<WeeklyAverage> weightsCall = iWeightsController.getAverageWeight(USERID);
//
//        weightsCall.enqueue(new Callback<WeeklyAverage>() {
//            @Override
//            public void onResponse(Call<WeeklyAverage> call, Response<WeeklyAverage> response) {
//                if(!response.isSuccessful()){
//                    //should do something for the error handlign
//                    Log.d("WEightsController", "inside if in onResponse");
//                    return;
//
//                }
//                Log.d("WeightsController", "outside if in onResponse");
//                WeeklyAverage avg = response.body();
//                weeklyAverage = avg.getWeeklyAverage();
//                //TextView avgWeightTextView =
//                //System.out.println(weeklyAverage);
//                //goToHomeScreen();
//                //Intent intent = new Intent(LogInScreen.this, HomeScreen.class);
//                //LogInScreen.this.startActivity(intent);
//
//            }
//
//            @Override
//            public void onFailure(Call<WeeklyAverage> call, Throwable t) {
//                Log.d("WeightsController", "inside onFailure");
//
//            }
//        });

    }


}
