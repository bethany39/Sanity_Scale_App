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

public class HomeScreen extends AppCompatActivity {
    Button weeklyAvgBtn, menuBtn;
    private float weeklyAverage;
    private int USERID;
    private Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        USERID = getIntent().getExtras().getInt("USERID");
        retrofit = new Retrofit.Builder().baseUrl("https://sanity-scale-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().setPrettyPrinting().create()))
                .build();

        weeklyAvgBtn=findViewById(R.id.weeklyAvgBtn);
        weeklyAvgBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                IWeightsController iWeightsController = retrofit.create(IWeightsController.class);

                Call<WeeklyAverage> weightsCall = iWeightsController.getAverageWeight(USERID);

                weightsCall.enqueue(new Callback<WeeklyAverage>() {
                    @Override
                    public void onResponse(Call<WeeklyAverage> call, Response<WeeklyAverage> response) {
                        if(!response.isSuccessful()){
                            //should do something for the error handlign
                            Log.d("WEightsController", "inside if in onResponse");
                            return;

                        }
                        Log.d("WeightsController", "outside if in onResponse");
                        WeeklyAverage avg = response.body();
                        weeklyAverage = avg.getWeeklyAverage();

                        TextView avgWeightTextView = (TextView) findViewById(R.id.AvgWeightTextView);
                        avgWeightTextView.append(Float.toString(weeklyAverage));
                        //System.out.println(weeklyAverage);
                        //goToHomeScreen();
                        //Intent intent = new Intent(LogInScreen.this, HomeScreen.class);
                        //LogInScreen.this.startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<WeeklyAverage> call, Throwable t) {
                        Log.d("WeightsController", "inside onFailure");

                    }
                });
                Intent intent =new Intent(HomeScreen.this, GraphScreen.class);
                HomeScreen.this.startActivity(intent);

            }
        });

        menuBtn=findViewById(R.id.menuBtn);
        menuBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeScreen.this, HomeWMenuScreen.class);
                HomeScreen.this.startActivity(intent);

            }
        });


    }


}