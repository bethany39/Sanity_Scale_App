package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeWMenuScreen extends AppCompatActivity {
    Button settingsBtn, logOutBtn;
    Retrofit retrofit;
    private int USERID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_wmenu_screen);


        USERID = getIntent().getExtras().getInt("USERID");

        settingsBtn=findViewById(R.id.settingsBtn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent =new Intent(HomeWMenuScreen.this, SettingsScreen.class);
                HomeWMenuScreen.this.startActivity(intent);

            }
        });
        logOutBtn=findViewById(R.id.logOutBtn);
        logOutBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeWMenuScreen.this, LogoutHome.class);
                HomeWMenuScreen.this.startActivity(intent);

            }
        });
    }
}
