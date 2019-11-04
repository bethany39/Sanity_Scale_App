package com.example.sanityscaleapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;


import com.google.android.material.navigation.NavigationView;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Button weeklyAvgBtn;
    private float weeklyAverage;
    private int USERID;
    private Retrofit retrofit;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigation;
    // private Toolbar toolbar;
    // private androidx.appcompat.widget.Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {
            USERID = bundle.getInt("USERID");
        }
        retrofit = new Retrofit.Builder().baseUrl("https://sanity-scale-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().setPrettyPrinting().create()))
                .build();


      //  toolbar = findViewById(R.id.nav_action);
       // setSupportActionBar(toolbar);
        //doesn't like toolbar

        drawerLayout= findViewById(R.id.homeScreen);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        NavigationView navigationView=(NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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
                        Intent intent =new Intent(HomeScreen.this, GraphScreen.class);
                        intent.putExtra("weeklyavg", weeklyAverage);

                        HomeScreen.this.startActivity(intent);

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


            }
        });



    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }

    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.nav_home:
                Intent intent=new Intent(HomeScreen.this,HomeScreen.class);
                startActivity(intent);
                break;
            case R.id.nav_settings:
                Intent intent2=new Intent(HomeScreen.this,SettingsScreen.class);
                startActivity(intent2);
                break;
            case R.id.nav_logout:
                Intent intent3=new Intent(HomeScreen.this,LogoutHome.class);
                startActivity(intent3);
                break;
        }
        return true;
    }


}