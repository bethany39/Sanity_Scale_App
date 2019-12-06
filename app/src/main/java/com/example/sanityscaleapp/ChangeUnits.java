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
import android.widget.TextView;

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
    private String SESSIONID;
    private String UNITS;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigation;
    private TextView nav_user;
    private String firstname;

    IUserController userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_units);
        bluebuttonlb=findViewById(R.id.bluebuttonlb);
        bluebuttonkg=findViewById(R.id.bluebuttonkg);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {
            SESSIONID=bundle.getString("SESSIONID");
            UNITS = bundle.getString("selected");
            firstname=bundle.getString("firstname");
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
        View hView=navigationView.getHeaderView(0);
        nav_user=(TextView) hView.findViewById(R.id.nameTxt);
        nav_user.setText(firstname);

        navigationView.setNavigationItemSelectedListener(this);


        kgsBtn=findViewById(R.id.kgsBtn);
        kgsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EspressoIdlingResource.increment();
                User userToSend = new User(null, "kgs", SESSIONID);

                Call<ResponseBody> changeToKgsCall = userService.patchUserUnits(userToSend);
                changeToKgsCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(!response.isSuccessful()){
                            Log.d("UserController", "inside if in onResponse");
                            return;

                        }
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

                EspressoIdlingResource.increment();
                User userToSend = new User(null, "lbs", SESSIONID);

                Call<ResponseBody> changeToLbsCall = userService.patchUserUnits(userToSend);

                changeToLbsCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(!response.isSuccessful()) {
                            //should do something for the error handling
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
                intent.putExtra("SESSIONID", SESSIONID);
                intent.putExtra("firstname",firstname);
                startActivity(intent);
                break;
            case R.id.nav_settings:
                Intent intent2=new Intent(ChangeUnits.this,SettingsScreen.class);
                intent2.putExtra("SESSIONID", SESSIONID);
                intent2.putExtra("firstname",firstname);
                startActivity(intent2);
                break;
            case R.id.nav_logout:
                Intent intent3=new Intent(ChangeUnits.this,LogoutHome.class);
                intent3.putExtra("SESSIONID", SESSIONID);
                intent3.putExtra("firstname",firstname);
                startActivity(intent3);
                break;
        }
        return true;
    }

}
