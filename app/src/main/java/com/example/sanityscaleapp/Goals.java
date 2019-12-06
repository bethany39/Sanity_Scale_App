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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class Goals extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Button gainBtn,loseBtn, maintainBtn;
    ImageView bluebuttonm, bluebuttong,bluebuttonl;
    private String SESSIONID;
    private String goal;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigation;
    private TextView nav_user;
    private String firstname;

    IUserController userService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SESSIONID=getIntent().getExtras().getString("SESSIONID");
        firstname=getIntent().getExtras().getString("firstname");
        goal = getIntent().getExtras().getString("selected");
        setContentView(R.layout.activity_goals);
        userService = RetrofitApi.getInstance().getUserService();

        bluebuttonm=findViewById(R.id.bluebuttonm);
        bluebuttonl=findViewById(R.id.bluebuttonl);
        bluebuttong=findViewById(R.id.bluebuttong);

        if(goal.equals("maintain")){
            selectMaintain(findViewById(R.id.activityGoals));
        } else if(goal.equals("lose")){
            selectLose(findViewById(R.id.activityGoals));
        } else if(goal.equals("gain")){
            selectGain(findViewById(R.id.activityGoals));
        } else{
            Log.e("Goals", "we got a problem");
        }

        drawerLayout= findViewById(R.id.activityGoals);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView=(NavigationView) findViewById(R.id.nav_view5);
        View hView=navigationView.getHeaderView(0);
        nav_user=(TextView) hView.findViewById(R.id.nameTxt);
        nav_user.setText(firstname);
        navigationView.setNavigationItemSelectedListener(this);

        gainBtn=findViewById(R.id.gainBtn);
        gainBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EspressoIdlingResource.increment();

                User userToSend = new User("gain weight", null, SESSIONID);

                Call<ResponseBody> gainCall = userService.patchUserGoal(userToSend);
                gainCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(!response.isSuccessful()) {
                            //should do something for the error handling
                            Log.d("UserController", "inside if in onResponse");
                            return;

                        }
                        selectGain(findViewById(R.id.activityGoals));
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
        loseBtn=findViewById(R.id.loseBtn);
        loseBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EspressoIdlingResource.increment();
                User userToSend = new User("lose weight", null, SESSIONID);

                Call<ResponseBody> loseCall = userService.patchUserGoal(userToSend);
                loseCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(!response.isSuccessful()) {
                            //should do something for the error handling
                            Log.d("UserController", "inside if in onResponse");
                            return;

                        }
                        selectLose(findViewById(R.id.activityGoals));
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

        maintainBtn=findViewById(R.id.maintainBtn);
        maintainBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EspressoIdlingResource.increment();
                User userToSend = new User("maintain weight", null, SESSIONID);

                Call<ResponseBody> maintainCall = userService.patchUserGoal(userToSend);
                maintainCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(!response.isSuccessful()) {
                            //should do something for the error handling
                            Log.d("UserController", "inside if in onResponse");
                            return;

                        }
                        selectMaintain(findViewById(R.id.activityGoals));
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
    public void selectLose(View v){
        bluebuttonl.setVisibility(v.VISIBLE);
        bluebuttonm.setVisibility(v.INVISIBLE);
        bluebuttong.setVisibility(v.INVISIBLE);
    }
    public void selectGain(View v){
        bluebuttong.setVisibility(v.VISIBLE);
        bluebuttonm.setVisibility(v.INVISIBLE);
        bluebuttonl.setVisibility(v.INVISIBLE);
    }

    public void selectMaintain(View v) {
        bluebuttonm.setVisibility(v.VISIBLE);
        bluebuttonl.setVisibility(v.INVISIBLE);
        bluebuttong.setVisibility(v.INVISIBLE);
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
                Intent intent=new Intent(Goals.this,HomeScreen.class);
                intent.putExtra("SESSIONID", SESSIONID);
                intent.putExtra("firstname",firstname);

                startActivity(intent);
                break;
            case R.id.nav_settings:
                Intent intent2=new Intent(Goals.this,SettingsScreen.class);
                intent2.putExtra("SESSIONID", SESSIONID);
                intent2.putExtra("firstname",firstname);

                startActivity(intent2);
                break;
            case R.id.nav_logout:
                Intent intent3=new Intent(Goals.this,LogoutHome.class);
                intent3.putExtra("SESSIONID", SESSIONID);
                intent3.putExtra("firstname",firstname);

                startActivity(intent3);
                break;
        }
        return true;
    }
}
