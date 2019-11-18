package com.example.sanityscaleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;


import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import androidx.appcompat.app.ActionBar;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SettingsScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Button changeGoalsTab, changeUnitsTab, profileTab;
    Retrofit retrofit;
 //   private int USERID;
    private String SESSIONID;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {
         //   USERID = bundle.getInt("USERID");
            SESSIONID=bundle.getString("SESSIONID");
        }

        retrofit = new Retrofit.Builder().baseUrl("https://sanity-scale-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().setPrettyPrinting().create()))
                .build();


        drawerLayout= findViewById(R.id.settingsScreen);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);


        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView=(NavigationView) findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(this);

        profileTab=findViewById(R.id.profileBtn);

        changeGoalsTab=findViewById(R.id.goalsBtn);
        changeGoalsTab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                ///IUserController iUserController = retrofit.create(IUserController.class);
                IUserController userService = RetrofitApi.getInstance().getUserService();
                EspressoIdlingResource.increment();
        //        Call<User> call = userService.getUserGoal(USERID);
                Call<User> call = userService.getUserGoal(SESSIONID);

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


                        System.out.println("it's here!!");

                    //    intent.putExtra("USERID", USERID);
                        intent.putExtra("SESSIONID",SESSIONID);

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
                        EspressoIdlingResource.decrement();

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("UserController", "inside onFailure");
                        EspressoIdlingResource.decrement();

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
                EspressoIdlingResource.increment();

        //        Call<User> unitsCall = userService.getUserUnits(USERID);
                Call<User> unitsCall = userService.getUserUnits(SESSIONID);
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
                        intent =new Intent(SettingsScreen.this, ChangeUnits.class);
                        switch(unit){
                            case "lbs":
                            //    intent.putExtra("USERID", USERID);
                                intent.putExtra("SESSIONID", SESSIONID);
                                intent.putExtra("selected", "lbs");
                              //  SettingsScreen.this.startActivity(intent);
                                break;
                            case "kgs":
                               // intent =new Intent(SettingsScreen.this, ChangeUnits.class);
                               // intent.putExtra("USERID", USERID);
                                intent.putExtra("SESSIONID", SESSIONID);
                                intent.putExtra("selected", "kgs");
                                //SettingsScreen.this.startActivity(intent);
                                break;
                        }
                        SettingsScreen.this.startActivity(intent);

                        EspressoIdlingResource.decrement();


                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("UserController", "inside onFailure");
                        EspressoIdlingResource.decrement();

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
                Intent intent=new Intent(SettingsScreen.this,HomeScreen.class);
            //    intent.putExtra("USERID", USERID);
                intent.putExtra("SESSIONID", SESSIONID);
                startActivity(intent);
                break;
            case R.id.nav_settings:
                Intent intent2=new Intent(SettingsScreen.this,SettingsScreen.class);
            //    intent2.putExtra("USERID", USERID);
                intent2.putExtra("SESSIONID", SESSIONID);
                startActivity(intent2);
                break;
            case R.id.nav_logout:
                Intent intent3=new Intent(SettingsScreen.this,LogoutHome.class);
            //    intent3.putExtra("USERID", USERID);
                intent3.putExtra("SESSIONID", SESSIONID);

                startActivity(intent3);
                break;
        }
        return true;
    }
}