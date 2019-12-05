package com.example.sanityscaleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;

import com.google.android.material.navigation.NavigationView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

public class LogoutHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigation;
    Button yesBtn,noBtn;
    private String SESSIONID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout_home);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {
            SESSIONID=bundle.getString("SESSIONID");
        }

        drawerLayout = findViewById(R.id.logoutScreen);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view4);
        navigationView.setNavigationItemSelectedListener(this);

        yesBtn=findViewById(R.id.yesBtn);
        yesBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
         });

        noBtn=findViewById(R.id.noBtn);
        noBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LogoutHome.this, HomeScreen.class);
                intent.putExtra("SESSIONID", SESSIONID);
                LogoutHome.this.startActivity(intent);
            }
        });

        }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Intent intent = new Intent(LogoutHome.this, HomeScreen.class);
                startActivity(intent);
                break;
            case R.id.nav_settings:
                Intent intent2 = new Intent(LogoutHome.this, SettingsScreen.class);
                startActivity(intent2);
                break;
            case R.id.nav_logout:
                Intent intent3 = new Intent(LogoutHome.this, LogoutHome.class);
                startActivity(intent3);
                break;
        }
        return true;
    }

    public void logout(){
        EspressoIdlingResource.increment();
        IUserController userService = RetrofitApi.getInstance().getUserService();
        Call<ResponseBody> logout = userService.deleteSid(SESSIONID);

        logout.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()) {
                    //should do something for the error handling
                    Log.d("UserController", "inside if in onResponse");
                    EspressoIdlingResource.decrement();
                    return;

                }
                Log.d("UserController", "outside if in onResponse");
                Intent intent=new Intent(LogoutHome.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                LogoutHome.this.startActivity(intent);
                EspressoIdlingResource.decrement();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("UserController", "inside onFailure");
                EspressoIdlingResource.decrement();

            }
        });
    }
}