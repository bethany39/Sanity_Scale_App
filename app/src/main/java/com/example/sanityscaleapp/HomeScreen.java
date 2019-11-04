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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        USERID = getIntent().getExtras().getInt("USERID");

        weeklyAvgBtn=findViewById(R.id.weeklyAvgBtn);
        weeklyAvgBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                EspressoIdlingResource.increment();
                IWeightsController weightsService = RetrofitApi.getInstance().getWeightsService();
                Call<Weight> weightsCall = weightsService.getAverageWeight(USERID);

                weightsCall.enqueue(new Callback<Weight>() {
                    @Override
                    public void onResponse(Call<Weight> call, Response<Weight> response) {
                        if(!response.isSuccessful()){
                            //should do something for the error handlign
                            Log.d("WEightsController", "inside if in onResponse");
                            return;

                        }
                        Log.d("WeightsController", "outside if in onResponse");
                        Weight avg = response.body();
                        weeklyAverage = avg.getWeeklyAverage();
                        Intent intent =new Intent(HomeScreen.this, GraphScreen.class);
                        intent.putExtra("weeklyavg", weeklyAverage);

                        HomeScreen.this.startActivity(intent);
                        EspressoIdlingResource.decrement();


                    }

                    @Override
                    public void onFailure(Call<Weight> call, Throwable t) {
                        Log.d("WeightsController", "inside onFailure");
                        EspressoIdlingResource.decrement();

                    }
                });


            }
        });

        menuBtn=findViewById(R.id.menuBtn);
        menuBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeScreen.this, HomeWMenuScreen.class);
                intent.putExtra("USERID", USERID);
                HomeScreen.this.startActivity(intent);

            }
        });


    }


}