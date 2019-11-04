package com.example.sanityscaleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;
import android.util.Log;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.widget.ImageView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChangeUnits extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Button lbsBtn, kgsBtn;
    ImageView bluebuttonlb, bluebuttonkg;
    private int USERID;
    private String UNITS;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigation;

    IUserController userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_units);
        bluebuttonlb=findViewById(R.id.bluebuttonlb);
        bluebuttonkg=findViewById(R.id.bluebuttonkg);
        //bluebuttonkg.setVisibility(View.INVISIBLE);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {
            USERID = bundle.getInt("USERID");
            UNITS = bundle.getString("selected");
        }
        userService = RetrofitApi.getInstance().getUserService();


        if (UNITS.equals("lbs"))
        {
            bluebuttonlb.setVisibility(View.VISIBLE);
            bluebuttonkg.setVisibility(View.INVISIBLE);
        }
        else if (UNITS.equals("kgs"))
        {
            bluebuttonlb.setVisibility(View.INVISIBLE);
            bluebuttonkg.setVisibility(View.VISIBLE);

        }


        drawerLayout= findViewById(R.id.changeUnitsScreen);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView=(NavigationView) findViewById(R.id.nav_view6);
        navigationView.setNavigationItemSelectedListener(this);


        kgsBtn=findViewById(R.id.kgsBtn);
        kgsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               // Intent intent =new Intent(ChangeUnits.this, ChangeUnitsKgs.class);
               // ChangeUnits.this.startActivity(intent);

                String userJson = "{'unit': 'kgs'}";
                Gson gson = new Gson();
                User userObject = gson.fromJson(userJson, User.class);
                EspressoIdlingResource.increment();

                Call<ResponseBody> changeToKgsCall = userService.patchUserUnits(USERID, userObject);
                changeToKgsCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(!response.isSuccessful()){
                            //should do something for the error handlign
                            Log.d("UserController", "inside if in onResponse");
                            return;

                        }
                        //System.out.println(response);
                        //System.out.println(response.body());
                        //ResponseBody jsonResponse = response.body();
                        //User user = response.body();
                        //String unit = user.getUnit();
                        //System.out.println(response.body());
                        bluebuttonlb.setVisibility(View.INVISIBLE);
                        bluebuttonkg.setVisibility(View.VISIBLE);
                        EspressoIdlingResource.decrement();

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("UserController", "inside onFailure");
                        EspressoIdlingResource.decrement();

                    }
                });


            }
        });

        lbsBtn=findViewById(R.id.lbsBtn);
        lbsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userJson = "{'unit': 'lbs'}";
                Gson gson = new Gson();
                User userObject = gson.fromJson(userJson, User.class);
                EspressoIdlingResource.increment();

                Call<ResponseBody> changeToLbsCall = userService.patchUserUnits(USERID, userObject);
                changeToLbsCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(!response.isSuccessful()) {
                            //should do something for the error handlign
                            Log.d("UserController", "inside if in onResponse");
                            return;

                        }
                        bluebuttonlb.setVisibility(View.VISIBLE);
                        bluebuttonkg.setVisibility(View.INVISIBLE);
                        EspressoIdlingResource.decrement();

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
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
                System.out.println("here");
                Intent intent=new Intent(ChangeUnits.this,HomeScreen.class);
                intent.putExtra("USERID", USERID);
                startActivity(intent);
                break;
            case R.id.nav_settings:
                Intent intent2=new Intent(ChangeUnits.this,SettingsScreen.class);
                intent2.putExtra("USERID", USERID);
                startActivity(intent2);
                break;
            case R.id.nav_logout:
                Intent intent3=new Intent(ChangeUnits.this,LogoutHome.class);
                intent3.putExtra("USERID", USERID);
                startActivity(intent3);
                break;
        }
        return true;
    }

}
