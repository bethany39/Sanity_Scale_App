package com.example.sanityscaleapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.GsonBuilder;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Button weeklyAvgBtn;
    private float weeklyAverage;
    private int numTimes;
    private boolean averageIsVisible;
    private String SESSIONID;
    private String UNITS;
    private String firstName;
    TextView avgError;
    private IUserController userService;

    private Retrofit retrofit;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigation;
    private TextView nav_user;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


        Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {
            SESSIONID=bundle.getString("SESSIONID");
        }
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://sanity-scale-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().setPrettyPrinting().create()))
                .build();



        drawerLayout= findViewById(R.id.homeScreen);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        NavigationView navigationView=(NavigationView) findViewById(R.id.nav_view);
        View hView=navigationView.getHeaderView(0);
         nav_user=(TextView) hView.findViewById(R.id.nameTxt);

        userService=RetrofitApi.getInstance().getUserService();
        Call<User> firstNameCall=userService.getFirstName(SESSIONID);

        EspressoIdlingResource.increment();

        firstNameCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    //should do something for the error handling
                    Log.d("IUserController", "inside if in onResponse");
                    return;

                }
                Log.d("IUserController", "outside if in onResponse");
                User user = response.body();
                firstName = user.getFirstName();
                nav_user.setText(firstName);
                EspressoIdlingResource.decrement();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("IUserController", "inside onFailure");
                EspressoIdlingResource.decrement();

            }
        });


        navigationView.setNavigationItemSelectedListener(this);

        avgError=findViewById(R.id.errorMessage);
        userService=RetrofitApi.getInstance().getUserService();
        Call<User> unitsCall=userService.getUserUnits(SESSIONID);

        EspressoIdlingResource.increment();

        unitsCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    //should do something for the error handling
                    Log.d("IUserController", "inside if in onResponse");
                    return;

                }
                Log.d("IUserController", "outside if in onResponse");
                User user = response.body();
                UNITS = user.getUnit();
                EspressoIdlingResource.decrement();


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("IUserController", "inside onFailure");
                EspressoIdlingResource.decrement();

            }
        });




        userService=RetrofitApi.getInstance().getUserService();
        Call<User> numTimesCall=userService.getNumTimesWeighed(SESSIONID);

        EspressoIdlingResource.increment();

        numTimesCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    //should do something for the error handling
                    Log.d("IUserController", "inside if in onResponse");
                    return;

                }
                Log.d("IUserController", "outside if in onResponse");
                User user = response.body();
                numTimes = user.getTimesWeighed();
                TextView numTimesWeighedTextView = findViewById(R.id.numberTextView);
                numTimesWeighedTextView.setText(Integer.toString(numTimes));
                TextView timesTextView=findViewById(R.id.text2);
                if(numTimes==1){
                    timesTextView.append("time this week");
                }
                else {
                    timesTextView.append("times this week");
                }
                EspressoIdlingResource.decrement();


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("IUserController", "inside onFailure");
                EspressoIdlingResource.decrement();

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

    @Override
    public void onStart(){
        super.onStart();
        userService=RetrofitApi.getInstance().getUserService();

        Call<User> weeklyAvgActiveCall =userService.canSeeWeight(SESSIONID);
        EspressoIdlingResource.increment();

        weeklyAvgActiveCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    //should do something for the error handling
                    return;
                }
                User user = response.body();
                averageIsVisible = user.getCanSeeWeight();
                if(averageIsVisible){
                    setWeeklyAverageActive();
                }else {
                    setWeeklyAverageInactive();
                }

                EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                EspressoIdlingResource.decrement();

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.nav_home:
                Intent intent=new Intent(HomeScreen.this,HomeScreen.class);
                intent.putExtra("SESSIONID", SESSIONID);
                intent.putExtra("firstname", firstName);
                startActivity(intent);
                break;
            case R.id.nav_settings:
                Intent intent2=new Intent(HomeScreen.this,SettingsScreen.class);
                intent2.putExtra("SESSIONID", SESSIONID);
                intent2.putExtra("firstname", firstName);

                startActivity(intent2);
                break;
            case R.id.nav_logout:
                Intent intent3=new Intent(HomeScreen.this,LogoutHome.class);
                intent3.putExtra("SESSIONID", SESSIONID);
                intent3.putExtra("firstname", firstName);

                startActivity(intent3);
                break;
        }
        return true;
    }



    public void setWeeklyAverageActive(){

        weeklyAvgBtn=findViewById(R.id.weeklyAvgBtn);
        weeklyAvgBtn.setAlpha(1f);

        weeklyAvgBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                weeklyAvgBtn.setAlpha(1f);

                EspressoIdlingResource.increment();
                Call<ResponseBody> setLastCheckedCall = userService.setLastChecked(SESSIONID);

                setLastCheckedCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (!response.isSuccessful()) {
                            //should do something for the error handling
                            Log.d("WeightsController", "inside if in onResponse");
                            return;

                        }
                        Log.d("WeightsController", "outside if in onResponse");
                        EspressoIdlingResource.decrement();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("WeightsController", "inside onFailure");
                        EspressoIdlingResource.decrement();

                    }
                });

                EspressoIdlingResource.increment();
                IWeightsController weightsService = RetrofitApi.getInstance().getWeightsService();
                Call<Weight> weightsCall = weightsService.getAverageWeight(SESSIONID);
                //default time is all time

                weightsCall.enqueue(new Callback<Weight>() {
                    @Override
                    public void onResponse(Call<Weight> call, Response<Weight> response) {
                        if (!response.isSuccessful()) {
                            //should do something for the error handling
                            Log.d("WeightsController", "inside if in onResponse");
                            return;

                        }
                        Log.d("WeightsController", "outside if in onResponse");
                        Weight avg = response.body();
                        weeklyAverage = avg.getWeeklyAverage();

                        Intent intent = new Intent(HomeScreen.this, GraphScreen.class);
                        intent.putExtra("weeklyavg", weeklyAverage);
                        intent.putExtra("UNITS",UNITS);
                        intent.putExtra("SESSIONID", SESSIONID);
                        intent.putExtra("firstname", firstName);

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
    }

    public void setWeeklyAverageInactive(){
        weeklyAvgBtn=findViewById(R.id.weeklyAvgBtn);
        weeklyAvgBtn.setAlpha(0.5f);

        weeklyAvgBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //say error message
                avgError.setVisibility(View.VISIBLE);
            }


        });

    }

}